package com.lami.foodie.fangxing.IProcessResponse;

import java.lang.reflect.ParameterizedType;

/**
 * Created by xjk on 2/23/18.
 */
public class Person2<T> {
    private Class<T> clazz;
    public Person2() {
        // 使用反射技术得到T的真实类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
        System.out.println("clazz ---> " + clazz);
    }

}
