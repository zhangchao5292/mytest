package com.example.mytest.common.design.observe;

/**
 * @Author zhangchao
 * @Date 2022/3/18
 */
public class StudentObserver implements Observer {

    private String name;

    /**
     * 每一个学生有一个唯一的名称和一位老师
     *
     * @param name
     * @param t
     */
    public StudentObserver(String name, TeacherSubject t) {
        this.name = name;
        //每新建一个学生对象,默认添加到观察者的行列
        t.addObserver(name, this);
    }

    @Override
    public void update(Object msg) {
        System.out.println("学生" + this.name + "收到消息:" + msg);
    }

}
