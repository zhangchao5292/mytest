package com.example.mytest.common.design.template;

/**
 * @Author zhangchao
 * @Date 2022/3/11
 */
public class CustomerTest {

    public static void main(String[] args) {
        //客户点单
        Tea tea = new Tea();
        tea.setAddCondiments(false);
        tea.prepareRecipe();
        System.out.println("-------------------");
        Coffe coffe = new Coffe();
        coffe.prepareRecipe();
    }
}
