package com.lami.foodie.aop3;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by xjk on 2/3/18.
 */
@Aspect
public class MyAspect {

    @Pointcut("execution(* add(..))")
    private void beforeAdd() {}

    @Before("beforeAdd()")
    public void before1() {
        System.out.println("-----------before-----------");
    }

}