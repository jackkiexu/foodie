package com.lami.foodie.utils.jndi;

/**
 * Created by xjk on 7/1/17.
 */
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//在RMI中绑JNDI的限制是，绑定的对象必须是Remote类型
class Person implements Remote, Serializable {
    private static final long serialVersionUID = -8592182872966400365L;

    private String name;
    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String toString() {
        return "name=" + this.getName() + "&pass=" + this.getPass();
    }

}