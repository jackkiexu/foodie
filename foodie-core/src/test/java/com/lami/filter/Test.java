package com.lami.filter;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * Created by xujiankang on 2017/11/29.
 */
public class Test {
    public String hello(){
        byte[] byteArray = new byte[10 *1024*1024];
        return "hello";
    }

    public void testInvocationHandler() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(com.lami.filter.Person.class);
        enhancer.setCallback(new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
                    threadLocal.set("" + System.currentTimeMillis());
                    return "Hello cglib!";
                } else {
                    throw new RuntimeException("Do not know what to do.");
                }
            }
        });
        com.lami.filter.Person proxy = (com.lami.filter.Person) enhancer.create();
        System.out.println(proxy.getName());
    }
}
