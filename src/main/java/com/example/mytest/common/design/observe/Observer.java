package com.example.mytest.common.design.observe;

/**
 * 观察者（订阅者）
 * @Author zhangchao
 * @Date 2022/3/18
 */
public interface Observer {
    /**
     * 当主题状态改变时,观察者更新数据
     * @param msg
     */
    void update(Object msg);
}
