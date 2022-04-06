//package com.example.mytest;
//
//import com.example.mytest.service.RedisTemplateSignServiceImpl;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
///**
// * @Author zhangchao
// * @Date 2021/12/22
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {MytestApplication.class})
//public class SignInTest {
//
//    @Autowired
//    private RedisTemplateSignServiceImpl redisTemplateSignService;
//
//    @Test
//    public void mainTest() {
//        Long initId = 10001234L;
//        LocalDate today = LocalDate.now();
//        DateTimeFormatter yyyyMMDft = DateTimeFormatter.ofPattern("yyyyMMdd");
//        String dateMonthIndexStr = today.format(yyyyMMDft);
//        String memberSignCacheKey = "member_sign:" + dateMonthIndexStr.substring(0, dateMonthIndexStr.length() - 2) + ":" + initId;
//        List<String> signInfoList = redisTemplateSignService.getSignInfo(memberSignCacheKey, today);
//       System.out.println("本月该ID签到情况:"+ signInfoList.toString());
//        System.out.println("是否签到成功" + redisTemplateSignService.doSign(memberSignCacheKey, today));
//
//        System.out.println("用户:" + initId + "今天" + (redisTemplateSignService.checkSign(memberSignCacheKey, today) ? "已经" : "还未") + "签到");
//        System.out.println("用户:" + initId + "当前签到了" + redisTemplateSignService.getSignCount(memberSignCacheKey) + "次");
////        System.out.println("用户:" + initId + "本月连续签到了" + redisTemplateSignService.getContinuousSignCount( memberSignCacheKey,  today) + "天");
////        System.out.println("用户:" + initId + "本月第一次签到日期为 " + redisTemplateSignService.getFirstSignDate( memberSignCacheKey,  today));
//    }
//
//
//}
