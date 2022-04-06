package com.example.mytest.entity;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author zhangchao
 * @Date 2021/8/3
 */
@Getter
public enum UserRole {
    //学生，普通用户
    USER(0, "student", "学员"),
    //助教
    ASSIST(2, "admin", "助教"),
    //主讲老师
    ADMIN(1, "teacher", "直播老师"),
    //访客
    VISITOR(3, "visitor", "访客"),
    //白名单用户
    WHITE_UERS(4, "white_user", "白名单"),
    //首席助教/总主教
    CHIEF_ASSIST(5, "chief_assist", "总助教"),
    //机器人
    ROBOT(6, "robot", "水军"),
    ;

    private final Integer code;
    private final String title;
    private final String msg;

    UserRole(int code, String title, String msg) {
        this.code = code;
        this.title = title;
        this.msg = msg;
    }

    public static UserRole findByCode(int code) {
        for (UserRole role : UserRole.values()) {
            if (role.code == code) {
                return role;
            }
        }
        return null;
    }

    public static UserRole findByTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            return UserRole.USER;
        }
        switch (title) {
            case "teacher":
                return UserRole.ADMIN;
            case "assistant":
            case "admin":
                return UserRole.ASSIST;
            case "student":
                return UserRole.USER;
            case "visitor":
                return UserRole.VISITOR;
            case "white_user":
                return UserRole.WHITE_UERS;
            case "chief_assist":
                return UserRole.CHIEF_ASSIST;
            case "robot":
                return UserRole.ROBOT;
        }
        return null;
    }

}
