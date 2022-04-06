package com.example.mytest;

import com.example.mytest.rocketmq.RocketmqProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author zhangchao
 * @Date 2021/9/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MytestApplication.class})
public class MqTest {

    @Autowired
    private RocketmqProducer rocketmqProducer;

    @Test
    public void query1() throws InterruptedException {
        rocketmqProducer.send();
    }
}
