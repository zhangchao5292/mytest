package com.example.mytest.service;

import com.example.mytest.entity.Student;

/**
 * @Author zhangchao
 * @Date 2021/12/6
 */
public interface StudentService {
    void addStudent();

    Student getStudent(int id);

    void updateStudent(Student student);

    void attendAllCourse(Student student);
}
