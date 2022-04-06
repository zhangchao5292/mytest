//package com.example.mytest.kafka;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
///**
// * 小课数据变更同步
// *
// * @Author zhangchao
// * @Date 2021/8/17
// */
//@Slf4j
//@Component
//public class ClassMiddleDataChangeConsumer {
//
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent1(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent2(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent3(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent4(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent5(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent6(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent7(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent12(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent22(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent32(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent42(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent52(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent62(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent72(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent11(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent21(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent31(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent41(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent51(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent61(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//    @KafkaListener(topics = "${spring.kafka.template.wm-class-middle-topic}", groupId = "wm_live_sync_data_cq_class_middle",  concurrency = "3")
//    public void classMiddleEvent71(ConsumerRecord<String, String> record) {
//        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//        kafkaMessage.ifPresent(data -> doKafkaMessageSyncClassMiddleData(data));
//    }
//
//    public void doKafkaMessageSyncClassMiddleData(String data) {
//        log.info("doKafkaMessageSyncClassMiddleData data:{}", data);
//
//    }
//
//
//}
