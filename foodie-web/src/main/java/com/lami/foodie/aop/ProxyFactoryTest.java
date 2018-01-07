package com.lami.foodie.aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

/**
 * Created by xjk on 10/1/17.
 */
public class ProxyFactoryTest {

    public static void main(String[] args) {
        MockTask mockTask = new MockTask();


        ProxyFactory weaver = new ProxyFactory(mockTask);
        weaver.setProxyTargetClass(true);
        weaver.setInterfaces(new Class[]{ITask.class});
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        advisor.setMappedName("execute");
        advisor.setAdvice(new PerformanceMethodInterceptor());
        weaver.addAdvisor(advisor);
        ITask proxyObject = (ITask)weaver.getProxy();
        proxyObject.execute(new Object());

    }


    public static void main1(String[] args) {
        MockTask mockTask = new MockTask();


        ProxyFactory weaver = new ProxyFactory(mockTask);
        weaver.setInterfaces(new Class[]{ITask.class});
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        advisor.setMappedName("execute");
        advisor.setAdvice(new PerformanceMethodInterceptor());
        weaver.addAdvisor(advisor);
        ITask proxyObject = (ITask)weaver.getProxy();
        proxyObject.execute(new Object());

    }

}
