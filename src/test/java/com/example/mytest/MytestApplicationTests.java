package com.example.mytest;

import com.example.mytest.entity.User;
import com.example.mytest.service.UserListener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootTest
@EnableAsync
class MytestApplicationTests {

    @Autowired
    private UserListener userListener;


    @Test
    void contextLoads() {
        userListener.register(new User("jack",12));
    }

    @Test
    void save(){

    }

}
