package com.example.mytest.common.design.factory;

import org.springframework.stereotype.Component;

/**
 * @Author zhangchao
 * @Date 2022/3/10
 */
@Component("B")
public class ResourceB implements Strategy {

    @Override
    public String getVpcList(String id) {

        return "ResourceB";
    }
}
