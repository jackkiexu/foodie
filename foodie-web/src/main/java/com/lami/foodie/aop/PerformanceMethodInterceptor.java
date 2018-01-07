package com.lami.foodie.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 * Created by xjk on 10/1/17.
 */

public class PerformanceMethodInterceptor implements MethodInterceptor {

    private static final Logger logger = Logger.getLogger(PerformanceMethodInterceptor.class);

    public Object invoke(MethodInvocation invocation) throws Throwable {

        try {
            logger.info("invoke begin");
            return invocation.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            logger.info("invoke over");
        }
        return null;
    }
}
