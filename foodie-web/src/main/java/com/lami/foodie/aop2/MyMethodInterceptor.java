package com.lami.foodie.aop2;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by xjk on 10/3/17.
 */
public class MyMethodInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {

        System.out.println(getClass() + " 调用方法前");
        Object ret = invocation.proceed();
        System.out.println(getClass() + " 调用方法后");

        return ret;
    }
}
