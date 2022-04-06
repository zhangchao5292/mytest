package com.example.mytest.common;

import com.alibaba.fastjson.JSON;
import com.example.mytest.entity.TaskBodyDTO;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.codec.SerializationCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author zhangchao
 * @Date 2021/12/3
 */
@Component
@Order(value = 1)
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    RedissonClient redissonClient;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行 111111 <<<<<<<<<<<<<");
        RTopic topic = redissonClient.getTopic("anyTopic",new SerializationCodec());
        topic.addListener(TaskBodyDTO.class, new MessageListener<TaskBodyDTO>() {
            @Override
            public void onMessage(CharSequence charSequence, TaskBodyDTO taskBodyDTO) {
                System.out.println("onMessage: "+ JSON.toJSONString(taskBodyDTO));
            }
        });
    }
}
