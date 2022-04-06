package com.example.mytest.common.design.observe;

/**
 * @Author zhangchao
 * @Date 2022/3/18
 */
public class PubSubTest {

    /**
     * 观察者模式=发布者+订阅者
     * 老师学生观察者模式测试类
     *
     * @author liuyck
     */
    public static void main(String[] args) {
        TeacherSubject teacher = new TeacherSubject();

        new StudentObserver("jack", teacher);
        new StudentObserver("john", teacher);

        teacher.notifyObserver("jack", "你考了99分");
        teacher.notifyObserver("john", "你考了59分");
    }
}
