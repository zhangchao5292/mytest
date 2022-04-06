package com.example.mytest.common;

/**
 * @Author zhangchao
 * @Date 2021/8/3
 */
public interface UnifyCode<T extends Enum> {
    T get();

    int key();

    String value();
}