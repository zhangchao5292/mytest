package com.example.mytest.controller;

import com.example.mytest.common.UnifyResp;
import com.example.mytest.common.utils.ValidationUtil;
import com.example.mytest.entity.User;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * Redisson的分布式闭锁（CountDownLatch）
 * @Author zhangchao
 * @Date 2021/8/3
 */
@RestController
@RequestMapping("/r")
public class TestController {

    @Autowired
    RedissonClient redissonClient;


    @GetMapping("/a")
    public String get() {
        RCountDownLatch latch = redissonClient.getCountDownLatch("anyCountDownLatch");
        latch.trySetCount(1);
        try {
            latch.await();
            System.out.println("done done done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @GetMapping("/b")
    public String countDown() {
        RCountDownLatch latch = redissonClient.getCountDownLatch("anyCountDownLatch");
        latch.countDown();
        return "ok";
    }

    @PostMapping("/c")
    public UnifyResp getB(@Validated @RequestBody User user) {
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(user);
        if (validResult.hasErrors()) {
            return UnifyResp.error(500, validResult.getErrors());
        }
        return UnifyResp.success();
    }

    @GetMapping("/lock")
    public void rlock() throws Exception {
        RLock lock = redissonClient.getLock("anyLock");
        try{
            // 1. 最常见的使用方法
            //lock.lock();
            // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
            //lock.lock(10, TimeUnit.SECONDS);
            // 3. 尝试加锁，最多等待3秒，上锁以后10秒自动解锁
            boolean res = lock.tryLock(3, 10, TimeUnit.SECONDS);
            if(res){ //成功
                // do your business
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
