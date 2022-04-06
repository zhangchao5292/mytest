package com.example.mytest.service;

/**
 * @Author zhangchao
 * @Date 2021/8/12
 */
public interface IDefaultMethod {

    default void get(){
        System.out.println("interface ---------------");
    }
}
