package com.lami.foodie.aop3;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 * Created by xjk on 2/3/18.
 */
public class TestAspectJProxyFactory {
    public static void main(String[] args) {
        MyService myService = new MyService();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(myService);
        proxyFactory.addAspect(MyAspect.class);
        proxyFactory.setProxyTargetClass(true);//是否需要使用CGLIB代理
        MyService myService1 = proxyFactory.getProxy();
        myService1.add();
    }
}
