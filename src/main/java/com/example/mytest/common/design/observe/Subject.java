package com.example.mytest.common.design.observe;

/**
 * 被观察者（发布者）
 * @Author zhangchao
 * @Date 2022/3/18
 */
public interface Subject {

    /**
     * 添加观察者
     * @param id    观察者id
     * @param obj    观察者本身对象
     */
    void addObserver(String id, Observer obj);

    /**
     * 移除观察者
     * @param id    观察者id
     */
    void deleteObserver(String id);

    /**
     * 当主题方法改变时,这个方法被调用,通知所有的观察者
     * @param msg 通知消息
     */
    void notifyObservers(Object msg);

    /**
     * 当主题方法改变时,这个方法被调用,通知指定观察者
     * @param id    观察者id
     * @param msg    通知消息
     */
    void notifyObserver(String id,Object msg);

}
