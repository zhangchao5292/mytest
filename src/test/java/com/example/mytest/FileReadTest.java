package com.example.mytest;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.example.mytest.common.utils.GroupCodeUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

/**
 * @Author zhangchao
 * @Date 2021/9/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MytestApplication.class})
public class FileReadTest {


    @Test
    public void test() throws InterruptedException, IOException {

        String s = httpClientPost2("http://test-internal-open.weimiaocaishang.com/api/live/metadata/sign/app?code=2PFCABP2&proxyAppId=107&uid=lhq");
        System.out.println(s);
//
//        List<String> strings = Files.readLines(new File("C:\\Users\\zhang\\Desktop\\0916.txt"), Charset.defaultCharset());
////        List<String> strings = Lists.newArrayList("1601711400959048");
//        strings.stream().forEach(a-> {
//            if (StringUtils.isEmpty(a)){
//                return;
//            }
//            System.out.println(a.trim());
//            String url = "http://internal-open.weimiaocaishang.com/api/live/service/forbid/forbidUserSendMessagePost";
//            String code = "XBWRT5F7";
//            String uid = a.trim();
////            String duration = 3600 * 24 * 16 + "";
//            String duration =  "0";
//
//            HashMap<String, Object> params = Maps.newHashMap();
//            GroupCodeUtils.ParseCodeResult parseCodeResult = GroupCodeUtils.parseCode(code);
//            params.put("code", code);
//            params.put("uid", uid);
//            params.put("duration", duration);
//            params.put("groupId", parseCodeResult.getId());
//            params.put("msg", "");
//            params.put("opuid", "1627472393323436");
//            String result = httpClientPost(url, JSON.toJSONString(params));
//            System.out.println(result);
//
//        });
    }

    public static String httpClientPost(String url, String json) {
        HttpClient httpClient = new DefaultHttpClient();
        String content = null;
        try {
            HttpPost post = new HttpPost(url);
            StringEntity postingString = new StringEntity(json);// json传递
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");
            post.setHeader("h-app-id", "107");
//            post.setHeader("x-wmv-sign","");
            HttpResponse response = httpClient.execute(post);
            content = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String httpClientPost2(String url) {
        HttpClient httpClient = new DefaultHttpClient();
        String content = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("h-app-id", "107");
            HttpResponse response = httpClient.execute(httpGet);
            content = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }


}
