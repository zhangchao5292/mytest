package com.example.mytest.common.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author zhangchao
 * @Date 2022/3/25
 */
public class FutureTaskTest {
    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        // 建立任务集合
        List<FutureTask<Integer>> taskList = new ArrayList<>();
        // 建立线程池
        for (int i = 0; i < 10; i++) {
            // 传入Callable对象建立FutureTask对象
            FutureTask<Integer> ft = new FutureTask<>(new ComputeTask(i, "" + i));
            taskList.add(ft);
            // 提交给线程池执行任务，也能够经过exec.invokeAll(taskList)一次性提交全部任务;
            executorService.submit(ft);
        }
        System.out.println("全部计算任务提交完毕, 主线程接着干其余事情！");

        Integer totalResult = 0;
        for (int i = 0; i < taskList.size(); i++) {
            try {
                //FutureTask的get方法会自动阻塞,直到获取计算结果为止
                totalResult += taskList.get(i).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(totalResult);
        }
        // 关闭线程池
        executorService.shutdown();
        System.out.println("多任务计算后的总结果是:" + totalResult);
    }

}
