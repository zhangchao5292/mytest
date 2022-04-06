package com.example.mytest.common;

/**
 * @Author zhangchao
 * @Date 2021/8/3
 */
public enum BaseUnifyCode implements UnifyCode<BaseUnifyCode> {
    SUCCESS(0, "OK"),
    LOGIN_FAIL(999999, "登录失效，请重新登录！"),
    ERROR_400(400, "接口请求参数不匹配"),
    ERROR_401(401, "接口访问未授权"),
    ERROR_403(403, "接口禁止访问"),
    ERROR_404(404, "接口请求地址错误"),
    ERROR_408(408, "请求超时"),
    ERROR_415(415, "不支持的媒体类型"),
    ERROR_500(500, "服务内部异常"),
    ERROR_502(502, "服务网关错误"),
    ERROR_503(503, "服务暂不可用"),
    ERROR_504(504, "服务网关超时"),
    ERROR_PARAM(901, "参数不合法"),
    ERROR_APPID(902, "无效的APPID");

    private int code;
    private String desc;

    private BaseUnifyCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public String toString() {
        return "{\"code\":" + this.code + ",\"desc\":\"" + this.desc + "\"}";
    }

    public BaseUnifyCode get() {
        return this;
    }

    public int key() {
        return this.code;
    }

    public String value() {
        return this.desc;
    }
}