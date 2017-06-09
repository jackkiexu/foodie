package com.lami.foodie.utils.proxy;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * Created by xujiankang on 2017/5/3.
 */
public class JavassistProxy {

    private static final Logger logger = Logger.getLogger(JavassistProxy.class);

    public static Object getProxy(Class<?> type) throws IllegalAccessException, InstantiationException{
        ProxyFactory f = new ProxyFactory();
        f.setSuperclass(type);
        f.setFilter(new MethodFilter() {
            public boolean isHandled(Method method) {
                return !method.getName().equals("finalize");
            }
        });

        Class<?> c = f.createClass();
        MethodHandler mi = new MethodHandler() {
            public Object invoke(Object self, Method method, Method proceed, Object[] objects) throws Throwable {
                logger.info("method name :" + method.getName() + " exec");
                return proceed.invoke(self, objects);
            }
        };

        Object proxy = c.newInstance();
        ((Proxy)proxy).setHandler(mi);
        return proxy;
    }


    public static void main(String[] args) throws Exception{
        JavassistProxy.getProxy(Student.class);
    }
}
