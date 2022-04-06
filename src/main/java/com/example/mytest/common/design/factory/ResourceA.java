package com.example.mytest.common.design.factory;

import org.springframework.stereotype.Component;

/**
 * @Author zhangchao
 * @Date 2022/3/10
 */
@Component("A")
public class ResourceA implements Strategy{

    @Override
    public String getVpcList(String id) {

        return "ResourceA";
    }
}
