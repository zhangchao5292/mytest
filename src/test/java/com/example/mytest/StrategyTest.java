package com.example.mytest;

import com.example.mytest.common.design.factory.SimpleContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author zhangchao
 * @Date 2022/3/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MytestApplication.class})
public class StrategyTest {

    @Autowired
    private SimpleContext simpleContext;

    @Test
    public void name() {
        String resource = simpleContext.getResource("B");
        System.out.println(resource);
    }
}
