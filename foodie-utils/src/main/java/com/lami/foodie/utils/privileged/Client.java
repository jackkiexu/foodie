package com.lami.foodie.utils.privileged;

import org.apache.log4j.Logger;

import java.io.FilePermission;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;

/**
 * Created by xujiankang on 2017/5/15.
 */
public class Client implements Person {

    private static final Logger logger = Logger.getLogger(Client.class);

    public void doCheck(){
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                check();
                return null;
            }
        });
    }

    public void sayHello(String name){
        logger.info("say:" + name);
    }

    private void check(){
        Permission perm = new FilePermission("/1.txt", "read");
        AccessController.checkPermission(perm);
        System.out.println("TestService has permission");
    }

    public String setName(String name) {
        logger.info("setName:" + name);
        return name;
    }
}
