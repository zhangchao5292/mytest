package com.example.mytest.common.design.strategy;

/**
 * @Author zhangchao
 * @Date 2022/3/11
 */
public class Car implements Vehicle{
    @Override
    public void run() {
        System.out.println("小汽车");
    }
}
