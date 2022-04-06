package com.example.mytest.rocketmq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhangchao
 * @Date 2021/8/31
 */
@Service
public class RocketmqProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void send(){
        rocketMQTemplate.convertAndSend("test-topic-2","hello====================world");
    }

}
