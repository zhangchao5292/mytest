package com.example.mytest.common.utils;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhangchao
 * @Date 2021/12/20
 */
public class Java8MethodUtil {

    public static void main(String[] args) {

//        List<ExcelUtil.User> userList = Lists.newArrayList(new User("apple", 6),
//                new User("apple1", 6),
//                new User("banana", 7),
//                new User("banana1", 7),
//                new User("banana2", 7),
//                new User("grape",8));
//
//        Map<String, List<User>> collect = userList.stream().collect(Collectors.groupingBy(User::getName));
//        System.out.println(collect.toString());
//        System.out.println(collect.get("grape").toString());

        List<Integer> list = Lists.newArrayList(1, 2, 4, 5, 7, 8, 9);
        List<Integer> collect = list.stream().filter(a -> a > 3).collect(Collectors.toList());
        List<Integer> collect1 = list.stream().filter(a -> {
            if (a > 3) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
        System.out.println(JSON.toJSONString(collect1));
    }

}
