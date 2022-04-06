package com.example.mytest.service;

import com.example.mytest.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * @Author zhangchao
 * @Date 2021/12/6
 */
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    /**
     * @Autowired注解用在集合上面，可以保持接口的所有实现类
     */
    @Autowired
    private Set<CourseService> courseSet;

    @Override
    public void addStudent() {
        System.out.println("addStudent");
        log.info("addStudent");
    }

    @Override
    public Student getStudent(int id) {

        System.out.println("getStudent id=" + id);
        log.info("getStudent id=" + id);

        Student student = new Student();
        student.setId(id);
        student.setAge(22);
        student.setName("张胜男");
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        System.out.println("updateStudent info:" + student);
        log.info("updateStudent info:" + student);
    }

    @Override
    public void attendAllCourse(Student student) {
        if (!CollectionUtils.isEmpty(courseSet)) {
            for (CourseService service : courseSet) {
                service.attendCourse();
            }
        }
    }
}
