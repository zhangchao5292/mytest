package com.example.mytest;

import com.example.mytest.entity.User;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author zhangchao
 * @Date 2021/8/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MytestApplication.class})
public class ListTest {

    //单线程下List，多线程下不安全
    static List<User> initList = Lists.newArrayListWithExpectedSize(512);
    //1.按照500次同步一次
    static ThreadLocal<List<User>> entityLocal = new ThreadLocal<>();
    //2.每5min同步一次（可能出现很长时间内累计不到500次）
    static ThreadLocal<Long> timeLocal = new ThreadLocal<>();


    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 1520; i++) {
            User user = new User();
            consume(user);
//            Thread.sleep(1000L);
        }
    }


    private void consume(User user) {
        String name = Thread.currentThread().getName();
        System.out.println("线程名称："+name);
        List<User> list = entityLocal.get();
        if (null == list) {
            entityLocal.set(Lists.newArrayListWithExpectedSize(512));//初始化
            timeLocal.set(System.currentTimeMillis());//开始时间
            list = entityLocal.get();
        }
        if (list.size() > 500) {
            System.out.println("================超过 500 Insert================");
            list.clear();
            return;
        }
        list.add(user);

        long now = System.currentTimeMillis();
        if ((timeLocal.get() + 30 * 1000) < now) {
            System.out.println("================超过5 min Insert================");
            timeLocal.set(now);
            list.clear();

        }
    }

}
