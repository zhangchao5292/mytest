package com.example.mytest.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author zhangchao
 * @Date 2021/9/2
 *
 * https://springboot.io/t/topic/1246  SpringBoot整合RocketMQ
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
public class RocketmqConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info("onmessage consume:{}", s);
    }
}
