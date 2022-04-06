package com.example.mytest.common.design.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author zhangchao
 * @Date 2022/3/10
 */

@Service
public class SimpleContext {

    @Autowired
    private final Map<String, Strategy> strategyMap = new ConcurrentHashMap<>();

//    public SimpleContext(Map<String, Strategy> strategyMap) {
//        this.strategyMap.clear();
//        strategyMap.forEach((k, v)-> this.strategyMap.put(k, v));
//    }

    public String getResource(String poolId) {
        strategyMap.forEach((k, v) -> System.out.println("key=" + k + ",value=" + v));
        Strategy strategy = strategyMap.get(poolId);
        return strategy.getVpcList(poolId);
    }


}
