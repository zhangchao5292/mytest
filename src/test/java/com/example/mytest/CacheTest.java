package com.example.mytest;

import com.example.mytest.entity.User;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangchao
 * @Date 2021/8/13
 */
@SpringBootTest
public class CacheTest {

    private Cache<Long, User> cache = Caffeine.newBuilder().
            maximumSize(1000).
            expireAfterWrite(5, TimeUnit.MINUTES).
            build();

    @Test
    public void contextLoads() {
        User cacheUser = cache.getIfPresent("1200");
        for (int i = 0; i < 10000; i++) {
            if (null == cacheUser) {
                //查询mysql
                System.out.println("====================查询mysql====================");
                cacheUser = User.builder()
                        .name("jack")
                        .age(12)
                        .build();
            }
            System.out.println("====================return " + cacheUser.getAge() + "---" + cacheUser.getName() + "====================");
        }
    }

}
