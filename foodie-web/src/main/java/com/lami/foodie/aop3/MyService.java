package com.lami.foodie.aop3;

/**
 * Created by xjk on 2/3/18.
 */
public class MyService implements Service{
    public  String add(){
        System.out.println("add");
        return "OK";
    }
}
