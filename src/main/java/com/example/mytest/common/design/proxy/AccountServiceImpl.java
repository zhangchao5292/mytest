package com.example.mytest.common.design.proxy;

/**
 * @Author zhangchao
 * @Date 2022/3/21
 */
public class AccountServiceImpl implements IAccountService{
    @Override
    public void transfer() {
        System.out.println("调用dao层,完成转账主业务.");
    }
}
