package com.example.mytest;

import com.example.mytest.entity.Student;
import com.example.mytest.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author zhangchao
 * @Date 2021/12/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MytestApplication.class})
public class StuServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void attendAllCourse(){
        Student student = studentService.getStudent(12);
        studentService.attendAllCourse(student);
    }
}
