package com.example.mytest.common.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author zhangchao
 * @Date 2022/3/21
 */
public class AccountAdvice implements InvocationHandler {
    //目标类
    private IAccountService target;

    public AccountAdvice(IAccountService accountService) {
        this.target = accountService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        return method.invoke(target, args);
    }

    /**
     * 前置增强
     */
    private void before() {
        System.out.println("对转账人身份进行验证.");
    }


}
