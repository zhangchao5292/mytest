//package com.example.mytest.rocketmq;
//
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.client.exception.MQBrokerException;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.apache.rocketmq.remoting.exception.RemotingException;
//
//import java.util.List;
//
///**
// * @Author zhangchao
// * @Date 2021/9/6
// */
//public class RocketMqUtil {
//    private RocketMqUtil(){
//
//    }
//
//    // 默认生产者组名称
//    private static final String DEFAULT_ROCKETMQ_PRODUCER_GROUPNAME = "test_producer";
//    // 默认连接地址
//    private static final String DEFAULT_ROCKETMQ_ADDRESS = "localhost:9876";
//    // 默认生产者应用名称
//    private static final String DEFAULT_ROCKETMQ_INSTANCENAME = "test";
//    // 默认生产者最大消息长度
//    private static final Integer DEFAULT_ROCKETMQ_MAXMESSAGESIZE = Integer.MAX_VALUE;
//    // 默认编码
//    public static final String DEFAULT_ROCKETMQ_ENCODING = "UTF-8";
//    // 默认消费者组名称
//    private static final String DEFAULT_ROCKETMQ_CONSUMER_GROUPNAME = "test_consumer";
//    // 默认监听主题
//    private static final String DEFAULT_ROCKETMQ_TOPIC = "test";
//    // 默认监听过滤条件
//    private static final String DEFAULT_ROCKETMQ_SUB_EXPRESSION = "test1";
//
//    /**
//     * 初始化生产者服务
//     * 默认配置
//     * @return
//     */
//    private static synchronized DefaultMQProducer initProducer() {
//        return initProducer(
//                DEFAULT_ROCKETMQ_PRODUCER_GROUPNAME,
//                DEFAULT_ROCKETMQ_ADDRESS,
//                DEFAULT_ROCKETMQ_INSTANCENAME,
//                DEFAULT_ROCKETMQ_MAXMESSAGESIZE
//        );
//    }
//
//    /**
//     * 初始化生产者服务
//     * 可选配置
//     * @param rocketmqAddress
//     * @return
//     */
//    private static synchronized DefaultMQProducer initProducer(
//            String groupName, String rocketmqAddress,String instanceName, int maxMessageSize) {
//        DefaultMQProducer producer = new DefaultMQProducer(groupName);
//        producer.setNamesrvAddr(rocketmqAddress);
//        producer.setInstanceName(instanceName);
//        producer.setMaxMessageSize(maxMessageSize);
//        try {
//            producer.start();
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        }
//        return producer;
//    }
//
//    /**
//     * 关闭生产者服务
//     */
//    public static synchronized void closeProducer(DefaultMQProducer producer){
//        if (producer != null) {
//            producer.shutdown();
//        }
//    }
//
//    /**
//     * 初始化消费者服务
//     * 使用默认配置
//     * @return
//     */
//    private static DefaultMQPushConsumer initConsumer(){
//        return initConsumer(DEFAULT_ROCKETMQ_ADDRESS, DEFAULT_ROCKETMQ_CONSUMER_GROUPNAME, DEFAULT_ROCKETMQ_TOPIC, DEFAULT_ROCKETMQ_SUB_EXPRESSION);
//    }
//
//    /**
//     * 初始化消费者服务
//     * 使用自定义配置
//     * @param topic
//     * @param subExpression
//     * @return
//     */
//    private static DefaultMQPushConsumer initConsumer(String namesrvAddr, String consumerGroup, String topic, String subExpression) {
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
//        consumer.setNamesrvAddr(namesrvAddr);
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//        try {
//            consumer.subscribe(topic, subExpression);
//        } catch (MQClientException e1) {
//            e1.printStackTrace();
//        }
//        return consumer;
//    }
//
//    /**
//     * 关闭消费者服务
//     */
//    public static void closeConsumer(DefaultMQPushConsumer consumer){
//        if (consumer != null) {
//            consumer.shutdown();
//        }
//    }
//
//    /**
//     * 生产消息
//     * 使用默认配置
//     * @param message
//     * @param close 生产完毕是否关闭,若不关闭则会阻塞
//     * @return
//     */
//    public static SendResult send(Message message, boolean close){
//        SendResult sendResult = null;
//        DefaultMQProducer producer = initProducer();
//        try {
//            sendResult = producer.send(message);
//            if(close){
//                closeProducer(producer);
//            }
//        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        return sendResult;
//    }
//
//    /**
//     * 生产消息
//     * 使用自定义配置
//     * @param message
//     * @param groupName
//     * @param rocketmqAddress
//     * @param instanceName
//     * @param maxMessageSize
//     * @param close 生产完毕是否关闭,若不关闭则会阻塞
//     * @return
//     */
//    public static SendResult send(Message message, String groupName,String rocketmqAddress,String instanceName, int maxMessageSize, boolean close){
//        SendResult sendResult = null;
//        try {
//            DefaultMQProducer producer = initProducer(groupName, rocketmqAddress, instanceName, maxMessageSize);
//            sendResult = producer.send(message);
//            if(close){
//                closeProducer(producer);
//            }
//        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sendResult;
//    }
//
//    /**
//     * 消费消息
//     * 使用默认配置
//     * @param messageListenerConcurrently
//     * @throws MQClientException
//     */
//    public static void consumer(MessageListenerConcurrently messageListenerConcurrently) throws MQClientException{
//        DefaultMQPushConsumer consumer = initConsumer();
//        consumer.registerMessageListener(messageListenerConcurrently);
//        consumer.start();
//    }
//
//    /**
//     * 消费消息
//     * 使用自定义配置
//     * @param topic
//     * @param subExpression
//     * @param messageListenerConcurrently
//     * @throws MQClientException
//     */
//    public static void consumer(String namesrvAddr, String consumerGroup, String topic, String subExpression, MessageListenerConcurrently messageListenerConcurrently) throws MQClientException, MQClientException {
//        DefaultMQPushConsumer consumer = initConsumer(namesrvAddr, consumerGroup, topic, subExpression);
//        consumer.registerMessageListener(messageListenerConcurrently);
//        consumer.start();
//    }
//
//    public static void main(String[] args) {
//        try {
//            consumer((msgs, context) -> {
//                try {
//                    for (MessageExt messageExt : msgs) {
//                        System.out.println("收到消息："+new String(messageExt.getBody(), "UTF-8"));
//                    }
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            });
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
////        String data = "[{\"id\":\"001\",\"name\":\"张三\",\"age\":\"21\"},{\"id\":\"002\",\"name\":\"李四\",\"age\":\"22\"}]";
////        for (int i = 0; i <10 ; i++) {
////            SendResult send = send(new Message("test", "test1", data.getBytes()), true);
////            System.out.println(send.toString());
////        }
//    }
//}
