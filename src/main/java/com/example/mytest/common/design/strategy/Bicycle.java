package com.example.mytest.common.design.strategy;

/**
 * @Author zhangchao
 * @Date 2022/3/11
 */
public class Bicycle implements Vehicle {

    @Override
    public void run() {
        System.out.println("自行车");
    }
}
