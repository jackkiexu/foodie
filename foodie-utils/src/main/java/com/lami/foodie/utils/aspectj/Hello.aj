package com.lami.foodie.utils.aspectj;

/**
 * Created by xujiankang on 2017/11/15.
 */
public aspect Hello {

    // 定义切点, 日志记录切点
    pointcut recordLog():call(* HelloWorld.sayHello(..));

    // 定义后置通知
    after():recordLog(){
        System.out.println("sayHello方法执行后的记录日志");
    }
}
