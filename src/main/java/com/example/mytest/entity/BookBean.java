package com.example.mytest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @Author zhangchao
 * @Date 2021/8/4
 */
@Data
@AllArgsConstructor
public class BookBean implements Serializable {
    private String id;
    private String title;
    private String author;
    private String postDate;
}
