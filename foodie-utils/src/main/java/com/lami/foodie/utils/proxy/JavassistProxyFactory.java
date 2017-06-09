package com.lami.foodie.utils.proxy;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * Created by xujiankang on 2017/5/3.
 */
public class JavassistProxyFactory<T> {

    private static final Logger logger = Logger.getLogger(JavassistProxyFactory.class);

    private T target;

    public void setTarget(T target) {
        this.target = target;
    }

    public T getProxy() throws Exception{
        // 代理工厂
        ProxyFactory proxyFactory = new ProxyFactory();
        // 设置需要创建子类的父类
        proxyFactory.setSuperclass(target.getClass());
        /**
         * 定义一个拦截器, 在调用目标方法时, Javassist 会回调 MethodHandler 接口方法拦截
         * 来实现你自己的代理逻辑
         * 类似于 JDK 中的 InvocationHandler 接口
         */
        proxyFactory.setHandler(new MethodHandler() {
            public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
                logger.info("-----------------------------------------------");
                logger.info("动态生成的类的名字 : " + o.getClass());
                logger.info("要调用的方法名:" + method.getName());
                logger.info("要调用的方法名 method1:" + method1.getName());

                Object result = method1.invoke(o, objects);

                return result;
            }
        });
        // 通过字节码生成子类
        return (T)proxyFactory.createClass().newInstance();
    }
}
