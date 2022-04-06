package com.example.mytest.common.design.proxy;

import java.lang.reflect.Proxy;

/**
 * @Author zhangchao
 * @Date 2022/3/21
 */
public class ProxyTest {

    public static void main(String[] args) {
//        1.静态代理
//        静态代理的特点是, 为每一个业务增强都提供一个代理类, 由代理类来创建代理对象. 下面我们通过静态代理来实现对转账业务进行身份验证.
        IAccountService target = new AccountServiceImpl();//创建目标对象
        AccountProxy accountProxy = new AccountProxy(target);
        accountProxy.transfer();

//        2.动态代理(JDK动态代理必须要有接口)
//        静态代理会为每一个业务增强都提供一个代理类, 由代理类来创建代理对象, 而动态代理并不存在代理类, 代理对象直接由代理生成工具动态生成.
        IAccountService instance =(IAccountService) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),
                target.getClass().getInterfaces(),
                new AccountAdvice(target));
        instance.transfer();

    }


}
