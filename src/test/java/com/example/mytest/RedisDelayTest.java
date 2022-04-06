package com.example.mytest;

import com.example.mytest.entity.TaskBodyDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.redisson.codec.SerializationCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/**
 * redission的分布式对象，集合，分布式锁，同步器  http://www.mianshigee.com/tutorial/redisson-doc-cn/distributed_locks.md
 * <p>
 * redisTemplate、jedis、lettuce、redission的对比  https://blog.csdn.net/qq_40925189/article/details/109580439
 *
 * @Author zhangchao
 * @Date 2021/12/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MytestApplication.class})
@Slf4j
public class RedisDelayTest {

    @Autowired
    RedissonClient redissonClient;

    public void test() {
        String key = "user_info";
//        Redisson 分布式的 RBucket 对象可用作任意类型对象的通用容器。
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set("abc");
        System.out.println(bucket.get());
    }

    //    Redisson 分布式的 RBitSet 对象具有类似于 java.util.BitSet 的结构，
//    且表示的位向量会根据需要增长。BitSet 的大小由 Redis 限制为 4 294 967 295
    @Test
    public void RBitSet() {
        RBitSet set = redissonClient.getBitSet("simpleBitset");
        set.set(0, true);
        set.set(1812, false);
        set.clear(0);
        set.xor("anotherBitset");
    }

    @Test
    public void RAtomicLong() {
        RAtomicLong atomicLong = redissonClient.getAtomicLong("myAtomicLong");
        atomicLong.set(3);
        atomicLong.incrementAndGet();
        System.out.println(atomicLong.get());
    }

    @Test
    public void AtomicDouble() {
        RAtomicDouble atomicDouble = redissonClient.getAtomicDouble("myAtomicDouble");
        atomicDouble.set(2.81);
        atomicDouble.addAndGet(4.11);
        System.out.println(atomicDouble.get());
    }

    @Test
    public void Topic() {
        RTopic topic = redissonClient.getTopic("anyTopic", new SerializationCodec());
        long clientsReceivedMessage = topic.publish(new TaskBodyDTO("jason", "jack"));
        System.out.println(clientsReceivedMessage);
    }

    /**
     * Redisson 分布式的 Map 对象，实现了 java.util.concurrent.ConcurrentMap
     * 和 java.util.Map 接口。
     * Map 的大小由 Redis 限制为 4 294 967 295。
     */
    @Test
    public void TopicPublic() {
        RMap<String, TaskBodyDTO> map = redissonClient.getMap("user.info");
        TaskBodyDTO taskBodyDTO = new TaskBodyDTO("bbb", "ddd");
        TaskBodyDTO prevObject = map.put("123", taskBodyDTO);
        TaskBodyDTO currentObject = map.putIfAbsent("323", new TaskBodyDTO("ccc", "jjj"));
        TaskBodyDTO obj = map.remove("123");
        map.fastPut("321", new TaskBodyDTO("awe", "we"));
        map.fastRemove("321");
        Future<TaskBodyDTO> putAsyncFuture = map.putAsync("321", taskBodyDTO);
        RFuture<Boolean> booleanRFuture = map.fastPutAsync("321", taskBodyDTO);
        map.fastPutAsync("321", new TaskBodyDTO());
        map.fastRemoveAsync("321");
    }

    /**
     * Redisson分布式无界双端队列（Deque）结构的RDeque Java对象实现了java.util.Deque接口。
     * 尽管RDeque对象无初始大小（边界）限制，但对象的最大容量受Redis限制，最大元素数量是4 294 967 295个。
     */
    @Test
    public void Deque() {
        RDeque<TaskBodyDTO> queue = redissonClient.getDeque("anyDeque");
        queue.addFirst(new TaskBodyDTO("1", "1"));
        queue.addLast(new TaskBodyDTO("2", "2"));
        TaskBodyDTO obj = queue.removeFirst();
        TaskBodyDTO someObj = queue.removeLast();
        System.out.println(obj.toString());
        System.out.println(someObj.toString());
    }

    /**
     * Redisson分布式无界阻塞队列（Blocking Queue）结构的RBlockingQueue Java对象实现了java.util.concurrent.BlockingQueue接口。
     * 尽管RBlockingQueue对象无初始大小（边界）限制，但对象的最大容量受Redis限制，最大元素数量是4 294 967 295个。
     */
    @Test
    public void BlockingQueue() throws InterruptedException {
        RBlockingQueue<TaskBodyDTO> blockingFairQueue = redissonClient.getBlockingQueue("anyQueue");
        RDelayedQueue<TaskBodyDTO> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
        delayedQueue.offer(new TaskBodyDTO("拉流", "abc"), 1, TimeUnit.MINUTES);
        TaskBodyDTO obj = delayedQueue.peek();
        System.out.println(obj.toString());
    }


    @Test
    public void rlock() throws Exception {
        RLock lock = redissonClient.getLock("anyLock");
        try{
            // 1. 最常见的使用方法
            //lock.lock();
            // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
            //lock.lock(10, TimeUnit.SECONDS);
            // 3. 尝试加锁，最多等待3秒，上锁以后10秒自动解锁
            boolean res = lock.tryLock(3, 10, TimeUnit.SECONDS);
            if(res){ //成功
                // do your business
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
