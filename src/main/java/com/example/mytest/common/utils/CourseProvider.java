package com.example.mytest.common.utils;

import com.alibaba.fastjson.JSON;
import com.example.mytest.service.CourseService;

import java.util.Set;

/**
 * @Author zhangchao
 * @Date 2021/12/6
 */
public class CourseProvider {

    Set<CourseService> courseSet;

    public CourseProvider(Set<CourseService> courseSet) {
        this.courseSet=courseSet;
    }


}
