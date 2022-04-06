package com.example.mytest;

import com.alibaba.fastjson.JSON;
import com.example.mytest.entity.LiveTrackDataEntity;
import com.example.mytest.entity.MessageEntity;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedCardinality;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchScrollHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author : ellios
 * Date : 2021/2/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MytestApplication.class})
public class ElasticsearchTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 基本查询
     */
    @Test
    public void query1() {
        //match_all查询   有点类似于mysql语法：select * from tableName

        //term主要用于精确匹配哪些值，比如数字，日期，布尔值或 not_analyzed 的字符串(未经分析的文本数据类型)
        TermQueryBuilder termQuery = QueryBuilders.termQuery("mid", "123456");

        //match_phrase查询分析文本,并从分析的文本中创建短语查询,match_phrase的主要作用是用于匹配包含当前短语的文档
        MatchPhraseQueryBuilder matchPhraseQuery = QueryBuilders.matchPhraseQuery("where_reserve.where_url", "c019c48e5285890816346245418");

        //matchQuery("filedname","value")匹配单个字段，匹配字段名为filedname,值为value的文档
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("mid", "123456");

        //wildcardQuery()模糊查询  模糊查询，?匹配单个字符，*匹配多个字符
        WildcardQueryBuilder wildcardQuery = QueryBuilders.wildcardQuery("mid", "*12*");

        // multiMatchQuery(Object text, String... fieldNames):text为文本值，fieldNames为字段名称。 举例说明：name、address为字段名称，13为文本值。查询name字段或者address字段文本值为13的结果集。
        MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery("769", "what_reserve.what_cntime", "what_reserve.what_seq");

        //查询范围
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("event_time")
                .gte("1629430646141")
                .lte("1629430646141");

        //使用BoolQueryBuilder进行复合查询
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.filter(termQuery);
        boolQuery.filter(rangeQuery);
        Query query = new NativeSearchQueryBuilder().withFilter(termQuery).build();
        SearchHits<LiveTrackDataEntity> hits = elasticsearchRestTemplate.search(query, LiveTrackDataEntity.class, IndexCoordinates.of("wmv_live_track_data_20210820"));
        if (null == hits.get()) {
            System.out.println("there is no data by query");
        }
        List<LiveTrackDataEntity> collect = hits.get().map(a -> a.getContent()).collect(Collectors.toList());
        System.out.println("数量：" + collect.size() + " ，json: " + JSON.toJSONString(collect));
    }

    /**
     * Count(distinct)查询
     */
    @Test
    public void query2() {
        String key = "mid_distinct_count";
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //查询条件，查询指定学员
        boolQueryBuilder.filter(QueryBuilders.termQuery("group_id", "10000671"));
        //指定count(distinct)字段名,cardinality为指定字段的别名,field为指定字段
        CardinalityAggregationBuilder aggregationBuilder = AggregationBuilders
                .cardinality(key)
                .field("mid.keyword");

        try {
            Query query = new NativeSearchQueryBuilder()
                    .withFilter(boolQueryBuilder)
                    .addAggregation(aggregationBuilder)
                    .build();
            SearchHits<LiveTrackDataEntity> hits = elasticsearchRestTemplate.search(query, LiveTrackDataEntity.class, IndexCoordinates.of("wmv_live_track_data_20210611"));
            Set<String> collect = hits.stream().map(e -> e.getContent().getMid()).collect(Collectors.toSet());
            System.out.println("去重后数量：" + collect.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * ES滚动查询 https://www.jianshu.com/p/d048c81ec450
     */
    @Test
    public void scroll() {
        List<MessageEntity> list = new ArrayList<>();
        int total = 0;
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("createTime")
                .gte("1625771600000")
                .lte("1626710400000");
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.filter(rangeQuery);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withSort(new FieldSortBuilder("createTime").order(SortOrder.DESC))
                .withFilter(boolQuery)
                .build();
        // 设置每页数据量
        nativeSearchQuery.setMaxResults(5000);
        long scrollTimeInMillis = 60 * 1000;//表示查询结果中scrollId的有效时间，单位毫秒
        //第一次查询
        SearchScrollHits<MessageEntity> searchScrollHits = elasticsearchRestTemplate.searchScrollStart(scrollTimeInMillis, nativeSearchQuery, MessageEntity.class, IndexCoordinates.of("wmv_live_messages"));
        String scrollId = searchScrollHits.getScrollId();

        while (searchScrollHits.hasSearchHits()) {
            System.out.println(total += searchScrollHits.getSearchHits().size());
            List<MessageEntity> hitList = searchScrollHits.getSearchHits().stream().map(a->a.getContent()).collect(Collectors.toList());
            list.addAll(hitList);
            // 后续查询 查询结果中都携带了一个scrollId
            searchScrollHits = elasticsearchRestTemplate.searchScrollContinue(scrollId, scrollTimeInMillis, MessageEntity.class, IndexCoordinates.of("wmv_live_messages"));
            scrollId = searchScrollHits.getScrollId();
        }
        List<String> scrollIds = new ArrayList<>();
        scrollIds.add(scrollId);

        System.out.println("总数量：" + list.size());
        // 清除 scroll
        elasticsearchRestTemplate.searchScrollClear(scrollIds);

    }
}
