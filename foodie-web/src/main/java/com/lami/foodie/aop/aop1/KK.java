package com.lami.foodie.aop.aop1;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.interceptor.SimpleTraceInterceptor;

import java.lang.reflect.Method;

/**
 * Created by xjk on 2/3/18.
 */
// 代理类
public class KK implements Person {
    @Override
    public String doSomething() {
        return "OK";
    }
}
// 代理类实现的接口
interface Person {
    String doSomething();
}

// 执行的 MethodBeforeAdvice
class SimpleBeforeAdvice implements MethodBeforeAdvice{
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Currently Processing " + method);
    }
}

// 执行的 MethodInterceptor
class SimpleMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        invocation.proceed();
        return "SimpleBeforeAdvice";
    }
}

class ProxyFactoryTest {
    public static void main(String[] args) throws Exception {
        // 被代理的类
        KK kk = new KK();
        // 创建 Proxy 的工厂类
        ProxyFactory proxyFactory = new ProxyFactory(kk);
        // 代理类中要运用的 Advice
        SimpleBeforeAdvice simpleBeforeAdvice = new SimpleBeforeAdvice();
        SimpleMethodInterceptor simpleMethodInterceptor = new SimpleMethodInterceptor();
        // 给 proxyFactory 添加 Advice
        proxyFactory.addAdvice(simpleBeforeAdvice);
        proxyFactory.addAdvice(simpleMethodInterceptor);
        // 通过 proxyFactory 创建 代理类
        Person nKK = (Person)proxyFactory.getProxy();
        // 执行代理类的方法
        nKK.doSomething();
        Thread.sleep(10000000000l);
    }
}