package com.lami.foodie.utils.proxy;

/**
 * Created by xujiankang on 2017/5/3.
 */
public class JavassistTest {

    public static void main(String[] args) throws Exception{
        JavassistProxyFactory<Student> javassistProxyFactory = new JavassistProxyFactory<Student>();
        javassistProxyFactory.setTarget(new Student());

        Student proxy = javassistProxyFactory.getProxy();
        proxy.sayHello();
    }

}
