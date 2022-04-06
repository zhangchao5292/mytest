package com.example.mytest.common;

/**
 * @Author zhangchao
 * @Date 2021/8/3
 */
public class UnifyResp<T> {
    private Integer code = 0;
    private String desc = "OK";
    private boolean success = true;
    private T data;

    public UnifyResp() {
    }

    public static UnifyResp success() {
        UnifyResp unifyResp = new UnifyResp();
        unifyResp.setCode(0);
        unifyResp.setSuccess(true);
        return unifyResp;
    }

    public static UnifyResp success(Object data, UnifyCode unifyCode) {
        UnifyResp unifyResp = new UnifyResp();
        unifyResp.setCode(0);
        unifyResp.setSuccess(true);
        unifyResp.setDesc(unifyCode.value());
        unifyResp.setData(data);
        return unifyResp;
    }

    public static UnifyResp error() {
        UnifyResp unifyResp = new UnifyResp();
        unifyResp.setCode(500);
        unifyResp.setSuccess(false);
        unifyResp.setDesc("处理失败，请联系管理员");
        return unifyResp;
    }

    public static UnifyResp error(int code, String desc) {
        UnifyResp unifyResp = new UnifyResp();
        unifyResp.setCode(code);
        unifyResp.setSuccess(false);
        unifyResp.setDesc(desc);
        return unifyResp;
    }

    public static UnifyResp error(UnifyCode unifyCode) {
        UnifyResp unifyResp = new UnifyResp();
        unifyResp.setCode(unifyCode.key());
        unifyResp.setSuccess(false);
        unifyResp.setDesc(unifyCode.value());
        return unifyResp;
    }

    public static UnifyResp error(Object data, int code, String desc) {
        UnifyResp unifyResp = new UnifyResp();
        unifyResp.setCode(code);
        unifyResp.setSuccess(false);
        unifyResp.setDesc(desc);
        unifyResp.setData(data);
        return unifyResp;
    }

    public static UnifyResp error(Object data, UnifyCode unifyCode) {
        UnifyResp unifyResp = new UnifyResp();
        unifyResp.setCode(unifyCode.key());
        unifyResp.setSuccess(false);
        unifyResp.setDesc(unifyCode.value());
        unifyResp.setData(data);
        return unifyResp;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}