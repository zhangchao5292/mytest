package com.example.mytest.common.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 通常在一个jvm进程中，若想实现延迟逻辑，可以使用jdk自带的延迟队列DelayQueue来实现。
 * DelayQueue中的元素PriorityQueue来实现的，DelayQueue中的元素会实现
 * 即可在DelayQueue进行poll操作时候获取最近需要的元素。但是这种延时队列是保存在内存中，
 * 所以一旦进程关闭或崩溃，队列中的数据都会丢失，所以只有配合持久化才可以保证数据不丢失。
 * @Author zhangchao
 * @Date 2021/12/1
 */
public class MyDelayedTask implements Delayed {

    private String name;
    private long start = System.currentTimeMillis();
    private long time;

    public MyDelayedTask(String name, long time) {
        this.name = name;
        this.time = time;
    }

    /**
     * 需要实现的接口，获得延迟时间   用过期时间-当前时间
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((start + time) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 用于延迟队列内部比较排序   当前时间的延迟时间 - 比较对象的延迟时间
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        MyDelayedTask o1 = (MyDelayedTask) o;
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "MyDelayedTask{" +
                "name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}
