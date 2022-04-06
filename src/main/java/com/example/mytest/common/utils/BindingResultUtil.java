package com.example.mytest.common.utils;

import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

/**
 * @Author zhangchao
 * @Date 2021/8/3
 */
public class BindingResultUtil {
    public static String getMessage(BindingResult result) {
        if (result == null) {
            return "";
        }

        if (result.hasErrors()) {
            return result.getAllErrors().stream().map(v1 -> v1.getDefaultMessage()).collect(Collectors.joining(", "));
        }
        return "";
    }
}
