package com.lami.foodie.utils.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Date;

/**
 *
 * 参考资料地址
 * http://www.blogjava.net/liudecai/archive/2010/06/20/323900.html
 *
 *
 * Created by xjk on 7/1/17.
 */
public class Demo {
    public static void initDate() throws NamingException, RemoteException {
        // 设置参数
        LocateRegistry.createRegistry(1099);
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        System.setProperty(Context.PROVIDER_URL, "rmi://localhost:1099");
        InitialContext ctx = new InitialContext();
        // 在RMI中绑JNDI的限制是，绑定的对象必须是Remote类型
        // 内部扩展,可以内部扩展也可以外部扩展
        class RemoteDate extends Date implements Remote {
        }
        ;
        ctx.bind("java:comp/env/systemStartTime", new RemoteDate());
        ctx.close();
    }

    public static void initDate2() throws NamingException, RemoteException {
        // 设置参数
        LocateRegistry.createRegistry(1099);
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        System.setProperty(Context.PROVIDER_URL, "rmi://localhost:1099");
        InitialContext ctx = new InitialContext();
        // 自己扩展,可以内部扩展也可以外部扩展
        // class RemoteDate extends Date implements Remote {
        // }
        // ;
        ctx.bind("java:comp/env/systemStartTime", new RemoteDate());
        ctx.close();
    }

    public static void findDate() throws NamingException, RemoteException {
        // 直接使用
        InitialContext ctx = new InitialContext();
        Date startTime = (Date) ctx.lookup("java:comp/env/systemStartTime");
        System.out.println("+++++++++++++++++++++++" + startTime.toString());
        ctx.close();
    }

    public static void jndiDate() throws NamingException, RemoteException {
        // Demo.initDate();
        Demo.initDate2();
        Demo.findDate();
    }

    public static void initPerson() throws NamingException, RemoteException {
        // 设置参数
        LocateRegistry.createRegistry(1099);
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        System.setProperty(Context.PROVIDER_URL, "rmi://localhost:1099");
        InitialContext ctx = new InitialContext();
        // Person person = new Person();
        // person.setName("ellen");
        // person.setPass("000727");
        ctx.bind("java:comp/env/person", new Person());
        ctx.close();
    }

    public static void initPerson2() throws NamingException, RemoteException {
        // 设置参数
        LocateRegistry.createRegistry(1099);
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        System.setProperty(Context.PROVIDER_URL, "rmi://localhost:1099");
        InitialContext ctx = new InitialContext();
        Person person = new Person();
        person.setName("ellen");
        person.setPass("000727");
        ctx.bind("java:comp/env/person", person);
        ctx.close();
    }

    public static void findPerson() throws NamingException, RemoteException {
        // 直接使用
        InitialContext ctx = new InitialContext();
        Person person = (Person) ctx.lookup("java:comp/env/person");
        System.out.println("------" + person.toString());
        System.out.println("------" + person.getName());
        System.out.println("------" + person.getPass());
        ctx.close();
    }

    public static void jndiPerson() throws NamingException, RemoteException {
        // Demo.initPerson();
        Demo.initPerson2();
        Demo.findPerson();
    }

    public static void main(String[] args) throws NamingException, RemoteException {
        // 为什么两个jndi的例子不能同时运行
        // internal error: ObjID already in use
        // Demo.jndiDate();
        Demo.jndiPerson();
    }

}
