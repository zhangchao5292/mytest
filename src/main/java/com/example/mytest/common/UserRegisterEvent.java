package com.example.mytest.common;

import com.example.mytest.entity.User;
import org.springframework.context.ApplicationEvent;

/**
 * @Author zhangchao 创建Event事件(listener都是围绕着event来挂起的。)
 * @Date 2021/8/3
 */
public class UserRegisterEvent  extends ApplicationEvent {

    public User user;

    /**
     *
     * @param source 发生事件的对象
     * @param user 注册用户对象
     */
    public UserRegisterEvent(Object source,User user) {
        super(source);
        this.user=user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
