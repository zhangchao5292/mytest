package com.example.mytest.common.config;

import com.example.mytest.common.utils.CourseProvider;
import com.example.mytest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @Author zhangchao
 * @Date 2021/12/6
 */
@Configuration
public class CourseConfig {
    /**
     * @Autowired注解用在集合上面，可以保持接口的所有实现类
     */
    @Autowired
    private Set<CourseService> courseSet;

    @Bean
    public CourseProvider provider() {
        return new CourseProvider(courseSet);
    }
}
