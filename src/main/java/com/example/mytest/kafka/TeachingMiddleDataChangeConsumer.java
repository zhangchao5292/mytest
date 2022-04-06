//package com.example.mytest.kafka;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
///**
// * @Author zhangchao
// * @Date 2021/9/27
// */
//@Slf4j
//@Component
//public class TeachingMiddleDataChangeConsumer {
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-teaching-middle-topic}", groupId = "wm_live_sync_data_cq_teaching_middle", concurrency = "3")
//    public void teachingMiddleEvent(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncTeachingMiddleData(data));
//    }
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-teaching-middle-topic}", groupId = "wm_live_sync_data_cq_teaching_middle", concurrency = "3")
//    public void teachingMiddleEvent1(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncTeachingMiddleData(data));
//    }
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-teaching-middle-topic}", groupId = "wm_live_sync_data_cq_teaching_middle", concurrency = "3")
//    public void teachingMiddleEvent2(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncTeachingMiddleData(data));
//    }
//    @KafkaListener(topics = "${spring.kafka.template.wm-teaching-middle-topic}", groupId = "wm_live_sync_data_cq_teaching_middle", concurrency = "3")
//    public void teachingMiddleEvent3(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncTeachingMiddleData(data));
//    }
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-teaching-middle-topic}", groupId = "wm_live_sync_data_cq_teaching_middle", concurrency = "3")
//    public void teachingMiddleEvent4(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncTeachingMiddleData(data));
//    }
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-teaching-middle-topic}", groupId = "wm_live_sync_data_cq_teaching_middle", concurrency = "3")
//    public void teachingMiddleEvent5(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncTeachingMiddleData(data));
//    }
//
//    public void doKafkaMessageSyncTeachingMiddleData(String data) {
//        log.info("doKafkaMessageSyncTeachingMiddleData data:{}", data);
//
//
//    }
//}
