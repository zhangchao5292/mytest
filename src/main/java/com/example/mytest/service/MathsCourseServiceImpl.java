package com.example.mytest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author zhangchao
 * @Date 2021/12/6
 */
@Service
@Slf4j
public class MathsCourseServiceImpl implements CourseService {

    @Override
    public String getCourseName() {
        return "数学课";
    }

    @Override
    public void attendCourse() {
        System.out.println("上数学课");
        log.info("上数学课");
    }
}
