package com.example.mytest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author zhangchao
 * @Date 2021/12/2
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TaskBodyDTO implements Serializable {

    private static final long serialVersionUID = -2688036129315452769L;
    private String body;
    private String name;
}
