package com.lami.foodie.utils.privileged;

import com.lami.Person2;
import org.apache.log4j.Logger;

import java.io.FilePermission;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;

/**
 * Created by xujiankang on 2017/5/15.
 */
public class Client implements Person, Person2 {


    public static Chick chick;

    static {
        Click2 click2 = new Click2();
    }

    public void doCheck(){
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                check();
                return null;
            }
        });
    }

    public void sayHello(String name){
        System.out.println("say:" + name);
    }

    private void check(){
        Permission perm = new FilePermission("/1.txt", "read");
        AccessController.checkPermission(perm);
        System.out.println("TestService has permission");
    }

    public String setName(String name) {
        System.out.println("setName:" + name);
        return name;
    }


    public void doClick2(Click2 click2){
        click2.say("ss");
    }

    public void say2(String something) {
        System.out.println("say2");
    }
}
