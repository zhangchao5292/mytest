package com.example.mytest.common.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author zhangchao
 * @Date 2021/12/2
 */
@Component
public class RedisClient {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    @Value("${spring.redis.timeout}")
    private Integer timeout;

    private Config initRedissonConfig() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setTimeout(timeout)
                .setConnectionPoolSize(100)
                .setConnectionMinimumIdleSize(10);
        return config;
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = initRedissonConfig();
        return Redisson.create(config);
    }
}
