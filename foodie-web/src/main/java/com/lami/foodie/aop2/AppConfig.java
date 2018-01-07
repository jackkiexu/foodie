package com.lami.foodie.aop2;

import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xjk on 10/3/17.
 */
@Configuration
public class AppConfig {

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public Advice myMethodInterceptor(){
        return new MyMethodInterceptor();
    }

    @Bean
    public NameMatchMethodPointcutAdvisor nameMatchMethodPointAdvisor(){
        NameMatchMethodPointcutAdvisor nameMatchMethodPointcutAdvisor = new NameMatchMethodPointcutAdvisor();
        nameMatchMethodPointcutAdvisor.setMappedName("pri*");
        nameMatchMethodPointcutAdvisor.setAdvice(myMethodInterceptor());

        return nameMatchMethodPointcutAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }




}
