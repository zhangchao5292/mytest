package com.example.mytest.service;

/**
 * @Author zhangchao
 * @Date 2021/12/6
 */
//CourseService课程接口有2个子类，HistroyCourseServiceImpl和MathsCourseServiceImpl

public interface CourseService {

    String getCourseName();

    void attendCourse();

}
