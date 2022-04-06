package com.example.mytest.common.utils;

/**
 * @Author zhangchao
 * @Date 2022/3/16
 */
public class BuilderBuffer {

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        builder.append("abc").append(12);
        System.out.println(builder.toString());

        StringBuffer buffer= new StringBuffer();
        buffer.append("cde").append(13);
        System.out.println(buffer.toString());

    }
}
