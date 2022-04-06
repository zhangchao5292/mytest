package com.example.mytest;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.example.mytest.entity.LiveSessionEntity;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangchao
 * @Date 2021/11/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MytestApplication.class})
public class TimeTest {

    @Test
    public void test(){
        List<LiveSessionEntity> entities = Lists.newArrayList();
        LocalDateTime localTime1 = LocalDateTime.now().minusMinutes(30);
        LocalDateTime localTime2 = LocalDateTime.now().plusMinutes(1);
        LiveSessionEntity d1 = LiveSessionEntity.builder()
                .presetStartTime(LocalDateTimeToUdate(localTime1))
                .build();
        LiveSessionEntity d2 = LiveSessionEntity.builder()
                .presetStartTime(LocalDateTimeToUdate(localTime2))
                .build();
        entities.add(d1);
        entities.add(d2);

        Date date = new Date();
        long minOffset = 24 * 60;
        LiveSessionEntity targetEntity = null;
        for (LiveSessionEntity entity : entities) {
            long offset = DateUtil.between(date, entity.getPresetStartTime(), DateUnit.MINUTE);
            if (offset < minOffset) {
                minOffset = offset;
                targetEntity = entity;
            }
        }
        System.out.println(targetEntity.getPresetStartTime().toString());
    }

    public Date LocalDateTimeToUdate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

}
