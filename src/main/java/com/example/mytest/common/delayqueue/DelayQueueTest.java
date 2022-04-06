package com.example.mytest.common.delayqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * @Author zhangchao
 * @Date 2021/12/1
 */
public class DelayQueueTest {
    private static DelayQueue delayQueue  = new DelayQueue();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {

            delayQueue.offer(new MyDelayedTask("task1",30000));
            delayQueue.offer(new MyDelayedTask("task2",3900));
            delayQueue.offer(new MyDelayedTask("task3",1900));
            delayQueue.offer(new MyDelayedTask("task4",5900));
            delayQueue.offer(new MyDelayedTask("task5",6900));
            delayQueue.offer(new MyDelayedTask("task6",7900));
            delayQueue.offer(new MyDelayedTask("task7",1));

        }).start();

        while (true) {
            Delayed take = delayQueue.take();
            System.out.println(take);
        }
    }

}
