package com.example.mytest.service;

import com.example.mytest.common.UserRegisterEvent;
import com.example.mytest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @Author zhangchao  事件发布
 * @Date 2021/8/3
 */
@Service
public class UserListener {
    @Autowired
    private ApplicationContext applicationContext;


    public void register(User user) {
        applicationContext.publishEvent(new UserRegisterEvent(this, user));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 300; i++) {
            System.out.println("INSERT INTO `wm_xby`.`dm_live_user_chapter_detail_xby` (`mid`, `course_ext_id`, `course_ext_seq`, `course_ext_class_times_seq`, `class_id`, `period_id`, `add_wechat`, `in_group`, `add_wechat_before_class_start`, `current_is_friend_status`, `add_group_before_class_start`, `current_in_group_status`, `live_view_len`, `replay_view_len`, `live_first_in_time`, `live_last_out_time`, `replay_first_in_time`, `replay_last_out_time`, `interact_cnt`, `click_cnt`, `cashier_cnt`, `transactions_amount`, `transactions_cnt`, `ts`, `source`) VALUES ('1597985335742734', '0', '1', '2', '1547', '274', '-1', '-1', '-1', '0', '-1', '0', '0', '0', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0', '0', '0', '0', '0', '2021-08-11 15:31:45', '7');");
        }
    }
}
