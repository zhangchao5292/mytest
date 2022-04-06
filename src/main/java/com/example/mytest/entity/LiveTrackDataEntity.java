package com.example.mytest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * 直播埋点的上报数据，目前运维会定时清理索引（保留60天）
 * Author : ellios
 * Date : 2021/2/5
 */
@Data
@Document(indexName = "wmv_live_track_data_#{@indexSuffixProvider.daySuffix()}", replicas = 0, shards = 3, refreshInterval = "30s")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LiveTrackDataEntity implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    @EqualsAndHashCode.Include
    private String id;

    /**
     * ------------------------------- 解析提取字段 -------------------------------
     */

    @Field(value = "group_id", type = FieldType.Long, nullValue = "0")
    private Long groupId;


    /**
     * ------------------------------- when -------------------------------
     */

    /**
     * 事件触发时间
     */
    @Field(value = "event_time", type = FieldType.Keyword)
    private String eventTime;

    /**
     * 后端接受请求时间
     */
    @Field(value = "request_time", type = FieldType.Keyword)
    private String requestTime;

    /**
     * 	后端完成数据处理封装时间
     */
    @Field(value = "report_time", type = FieldType.Keyword)
    private String reportTime;

    /**
     * 用户进入页面开始时间
     */
    @Field(value = "start_time", type = FieldType.Keyword)
    private String startTime;

    /**
     * 用户进入页面结束时间
     */
    @Field(value = "end_time", type = FieldType.Keyword)
    private String endTime;

    /**
     * 预留时间扩展字段
     */
    @Field(value = "when_reserve", type = FieldType.Object)
    private Object whenReserve;

    /**
     * ------------------------------- who -------------------------------
     */

    /**
     * 标识企业唯一用户；注册用户：有 未注册用户：“-”
     */
    @Field(value = "mid", type = FieldType.Keyword)
    private String mid;

    /**
     * 会话id
     */
    @Field(value = "ssid", type = FieldType.Keyword)
    private String ssid;

    /**
     * 进程id
     */
    @Field(value = "process_id", type = FieldType.Keyword)
    private String processId;

    /**
     * 用户进入页面生成一个trace_id和start_time,用户离开页面trace不变end_time改变
     */
    @Field(value = "trace_id", type = FieldType.Keyword)
    private String traceId;

    /**
     * 用户当前使用的移动端设备名称
     */
    @Field(value = "device_name", type = FieldType.Keyword)
    private String deviceName;

    /**
     * 当前用户所使用的设备类型
     */
    @Field(value = "device_type", type = FieldType.Keyword)
    private String deviceType;

    /**
     * 用户当前所在的平台
     */
    @Field(value = "platform_type", type = FieldType.Keyword)
    private String platformType;

    /**
     * 分辨率
     */
    @Field(value = "device_resolution", type = FieldType.Keyword)
    private String deviceResolution;

    /**
     * 当前设备的内存
     */
    @Field(value = "device_mem", type = FieldType.Keyword)
    private String deviceMem;

    /**
     * 当前设备的网络状态
     */
    @Field(value = "net_status", type = FieldType.Keyword)
    private String netStatus;

    /**
     * 用来标识游客模式用户
     */
    @Field(value = "turist_id", type = FieldType.Keyword)
    private String turistId;

    /**
     * 用户扩展字段
     */
    @Field(value = "who_reserve", type = FieldType.Keyword)
    private Object whoReserve;

    /**
     * ------------------------------- where -------------------------------
     */

    /**
     * 用来标识用户网络地址
     */
    @Field(value = "c_ip", type = FieldType.Keyword)
    private String cIp;

    /**
     * 标识用户当前使用的操作系统环境
     */
    @Field(value = "os", type = FieldType.Keyword)
    private String os;

    /**
     * 操作系统版本号
     */
    @Field(value = "os_version", type = FieldType.Keyword)
    private String osVersion;

    /**
     * 应用被打开的软件环境类型
     */
    @Field(value = "env_type", type = FieldType.Keyword)
    private String envType;

    /**
     * 应用被打开的软件环境的版本
     */
    @Field(value = "env_version", type = FieldType.Keyword)
    private String envVersion;

    /**
     * 用户访问的路径
     */
    @Field(value = "url", type = FieldType.Keyword)
    private String url;

    /**
     * 当前采集的sdk版本
     */
    @Field(value = "sdk", type = FieldType.Keyword)
    private String sdk;

    /**
     * 扩展字段
     */
    @Field(value = "where_reserve", type = FieldType.Object)
    private Object whereReserve;


    /**
     * ------------------------------- what -------------------------------
     */

    /**
     * 端类型
     */
    @Field(value = "application_type", type = FieldType.Keyword)
    private String applicationType;

    /**
     * 端版本号，如h5 版本号
     */
    @Field(value = "application_version", type = FieldType.Keyword)
    private String applicationVersion;

    /**
     * 产品业务线
     */
    @Field(value = "product_type", type = FieldType.Keyword)
    private String productType;

    /**
     * 业务方式
     */
    @Field(value = "business_type", type = FieldType.Keyword)
    private String businessType;

    /**
     * 页面-页面部分-页面元素模块
     */
    @Field(value = "cur_page_info", type = FieldType.Keyword)
    private String curPageInfo;

    /**
     * 页面元素模块或者按钮放的具体内容编号
     */
    @Field(value = "content", type = FieldType.Keyword)
    private String content;

    /**
     * 父页面-页面模块-页面元素
     */
    @Field(value = "pre_page_info", type = FieldType.Keyword)
    private String prePageInfo;

    /**
     * 业务数据
     */
    @Field(value = "business_message", type = FieldType.Object)
    private Object businessMessage;

    /**
     * 标识埋点项目（后面做埋点监控用）
     * 产品类型-埋点端-页面：项目编号
     */
    @Field(value = "project_no", type = FieldType.Keyword)
    private String projectNo;

    /**
     * 扩展字段
     */
    @Field(value = "what_reserve", type = FieldType.Object)
    private Object whatReserve;

    /**
     * ------------------------------- how -------------------------------
     */

    /**
     * 事件触发的方式
     */
    @Field(value = "event_type", type = FieldType.Keyword)
    private String eventType;

    /**
     * 扩展字段
     */
    @Field(value = "how_reserve", type = FieldType.Object)
    private String howReserve;

    @Field(value = "create_time", format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @Field(value = "update_time", format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    /*@Data
    public static class WhatReserve {
        @Field("what_en")
        private String whatEn;
        @Field("what_seq")
        private String whatSeq;
        @Field("what_reason")
        private String whatReason;
        @Field("what_to_pass")
        private String whatToPass;
        @Field("what_co")
        private String whatCo;
        @Field("what_app_id")
        private String whatAppId;
        @Field("what_live")
        private String whatLive;
        private String what_useragent;
        private String what_desc;
        @Field("what_code")
        private String whatCode;
    }

    @Data
    public static class WhereReserve {
        @Field("where_to_sip")
        private String where_to_sip;
    }

    @Data
    public static class BusinessMessage {
        @Field("dk_zb_role_type")
        private String dkZbRoleType;
        private String dkZbToMid;
    }*/


}
