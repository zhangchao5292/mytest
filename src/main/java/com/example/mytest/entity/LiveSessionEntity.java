package com.example.mytest.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * t_live_session
 *
 * @author
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LiveSessionEntity implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 直播间id
     */
    private Long roomId;

    /**
     * 业务线id
     */
    private Long accountId;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 业务方课节id
     */
    private Long thirdSessionId;

    /**
     * 业务方课次id
     */
    private Long thirdTimesId;
    /**
     * 课次名称
     */
    private String name;

    /**
     * 实际上课开始时间
     */
    private Date startTime;

    /**
     * 实际上课结束时间
     */
    private Date endTime;

    /**
     * 预设开课时间
     */
    private Date presetStartTime;

    /**
     * 预设结课时间
     */
    private Date presetEndTime;

    /**
     * 状态（0，删除；1，待上课；2，上课中；3：结束
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 课节名称
     */
    private String groupName;

    /**
     * 序号
     */
    private Integer serialNum;

    /**
     * 课程类型 1正课 2加餐课 3微妙公开课 4微淼大咖课 5创业家
     */
    private Integer courseType;

    private static final long serialVersionUID = 1L;
}