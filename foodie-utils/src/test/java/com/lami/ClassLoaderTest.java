package com.lami;

import com.lami.foodie.utils.classloader.test4.*;
import com.lami.foodie.utils.tomcat.classloader.HandleUtils;
import com.lami.foodie.utils.tomcat.classloader.Param;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by xujiankang on 2017/6/2.
 */
public class ClassLoaderTest {

    @Test
    public void test() throws Exception {
        int a1 = 2 & 3;
        boolean a = true;
        boolean b = true;

        System.out.println(!a && b);
        // cl1在加载HandleUtils和Param时将会使用AppClassLoader
        URLClassLoader cl1 = new URLClassLoader(new URL[] {new File("target/test-classes").toURI().toURL()}, null) {

            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                if ("com.lami.foodie.utils.tomcat.classloader.HandleUtils".equals(name)) {
                    return ClassLoader.getSystemClassLoader().loadClass(name);
                }

                if ("com.lami.foodie.utils.tomcat.classloader.Param".equals(name)) {
                    return ClassLoader.getSystemClassLoader().loadClass(name);
                }

                return super.loadClass(name);
            }

        };

        ClassLoader.getSystemClassLoader().loadClass("com.lami.foodie.utils.tomcat.classloader.Param2");
        HandleUtils hu = (HandleUtils) cl1.loadClass("com.lami.foodie.utils.tomcat.classloader.HandleUtils").newInstance();
        // 下面的 Param 是由 ClassLoader.getSystemClassLoader() 加载出来的
        Param param = (Param) cl1.loadClass("com.lami.foodie.utils.tomcat.classloader.Param2").newInstance();
        hu.m(param);
    }

    @Test
    public void testInvocationHandler() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(com.lami.Person.class);
        enhancer.setCallback(new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "Hello cglib!";
                } else {
                    throw new RuntimeException("Do not know what to do.");
                }
            }
        });
        com.lami.Person proxy = (com.lami.Person) enhancer.create();
        System.out.println(proxy.getName());
    }

}
