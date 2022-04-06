package com.example.mytest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author zhangchao
 * @Date 2021/12/6
 */
@Service
@Slf4j
public class HistroyCourseServiceImpl implements CourseService{

    @Override
    public String getCourseName() {
        return "历史";
    }

    @Override
    public void attendCourse() {
        System.out.println("上历史课");
        log.info("上历史课");
    }
}
