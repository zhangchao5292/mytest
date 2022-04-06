package com.example.mytest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author zhangchao
 * @Date 2021/8/3
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {

    @NotBlank
    private String name;

    @NotNull
    private Integer age;
}
