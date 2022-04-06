//package com.example.mytest.common.config;
//
//import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.scheduling.annotation.AsyncConfigurer;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.Executor;
//
///**
// * @Author zhangchao
// * @Date 2021/8/3
// */
//@Configurable
//public class ListenerAsyncConfiguration implements AsyncConfigurer {
//    /**
//     * 获取异步线程池执行对象
//     * @return
//     */
//    @Override
//    public Executor getAsyncExecutor() {
//        //使用Spring内置线程池任务对象
//        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//        //设置线程池参数
//        taskExecutor.setCorePoolSize(5);
//        taskExecutor.setThreadNamePrefix("my-async-");
//        taskExecutor.setMaxPoolSize(10);
//        taskExecutor.setQueueCapacity(25);
//        taskExecutor.initialize();
//        return taskExecutor;
//    }
//
//    @Override
//    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//        return null;
//    }
//}
