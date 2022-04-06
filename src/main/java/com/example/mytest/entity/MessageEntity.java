package com.example.mytest.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * 直播间消息记录
 * Author : ellios
 * Date : 2021/1/11
 */
@Data
@Document(indexName = "wmv_live_messages", shards = 3)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MessageEntity implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    @EqualsAndHashCode.Include
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 消息类型，0 普通消息， 1转发的消息
     */
    private Integer messageType;

    /**
     * 消息发送者的appId
     */
    private Long appId;
    /**
     * 开发者账号id
     */
    private Long accountId;
    /**
     * 直播间id
     */
    private Long roomId;
    /**
     * 直播场次id
     */
    private Long sectionId;
    /**
     * 分组id
     */
    private Long groupId;
    /**
     * im分组id
     */
    @Field(type = FieldType.Keyword)
    private String imGroupId;
    /**
     * 用户id
     */
    @Field(type = FieldType.Keyword)
    private String uid;
    /**
     * 用户在im群组中分配的uid
     */
    @Field(type = FieldType.Keyword)
    private String imUid;
    /**
     * 用户角色， 0 学生 1助教 2 主讲老师 3 访客 4 白名单用户
     */
    private Integer userRole;
    /**
     * 用户昵称
     */
    @Field(type = FieldType.Keyword)
    private String nickname;
    /**
     * 用户头像
     */
    @Field(type = FieldType.Keyword, index = false)
    private String avatarUrl;
    /**
     * 用户ip
     */
    private String ip;
    /**
     * 消息内容
     */
    @Field(type = FieldType.Text)
    private String content;
//    /**
//     * 消息内容类型
//     */
//    @Field(type = FieldType.Keyword)
//    private MessageContentType contentType;
    /**
     * 是否通过审核
     */
    @Field(type = FieldType.Boolean)
    private Boolean pass;
    /**
     * 拒绝理由
     */
    private Integer refuseCode;
    /**
     * 拒绝理由
     */
    @Field(type = FieldType.Keyword, index = false)
    private String refuseReason;
//    /**
//     * pass服务商 0 腾讯 1 百家云 2 阿里云
//     */
//    @Field(type = FieldType.Keyword)
//    private Vendor vendor;
//    /**
//     * 消息发送的平台
//     */
//    @Field(type = FieldType.Keyword)
//    private Platform platform;
    /**
     * 内容指纹，用于区分用户短时间发的内容是否一致
     */
    @Field(type = FieldType.Keyword)
    private String fp;
    /**
     * 发出的消息在第三方平台的唯一id
     */
    @Field(type = FieldType.Keyword)
    private String msgKey;
    /**
     * 原始的消息唯一id，针对转发消息有效
     */
    @Field(type = FieldType.Keyword)
    private String originMsgKey;
    /**
     * 消息的序号
     */
    private Integer seq;
    /**
     * 消息的发送时间,到秒
     */
    private Long messageTime;
    /**
     * 第三方返回的随机值
     */
    private long random;
    /**
     * 是否被删除, false 未删除 true 已删除
     */
    @Field(type = FieldType.Boolean)
    private Boolean deleted;
    /**
     * sei时钟
     */
    private Long seiClock;
    /**
     * 扩展信息, 自定义消息中的Desc字段
     */
    private String ext;

    /**
     * 扩展消息（目前用于存储消息ID）
     */
    private String customData;


    /**
     * 自定义类型标记
     */
    @Field(type = FieldType.Keyword, nullValue = "0")
    private Integer ctype;

    /**
     * 自定义类型值
     */
    @Field(type = FieldType.Keyword)
    private String typeValue;

    private Date createTime;

    private Date updateTime;
}
