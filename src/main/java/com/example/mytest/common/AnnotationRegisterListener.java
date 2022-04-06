package com.example.mytest.common;

import com.example.mytest.entity.User;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author zhangchao
 * @Date 2021/8/3
 */
@Component
public class AnnotationRegisterListener {

    @Async
    @EventListener
    public void listerRegister(UserRegisterEvent event){
        System.out.println("start---1");
        User user = event.getUser();
        System.out.println("1==============>> "+user.getName());
    }

    @Async
    @EventListener
    public void listerRegister2(UserRegisterEvent event){
        System.out.println("start---2");
        User user = event.getUser();
        System.out.println("2==============>> "+user.getName());
    }

    @Async
    @EventListener
    public void listerRegister3(UserRegisterEvent event) throws InterruptedException {
        System.out.println("start---3"+Thread.currentThread().getName());
        User user = event.getUser();
        Thread.sleep(15000);
        System.out.println("3==============>> "+user.getName());

    }
}
