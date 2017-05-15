package com.lami.foodie.utils.privileged;

import java.io.FilePermission;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;

/**
 * Created by xujiankang on 2017/5/15.
 */
public class Client {

    public void doCheck(){
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                check();
                return null;
            }
        });
    }

    private void check(){
        Permission perm = new FilePermission("/1.txt", "read");
        AccessController.checkPermission(perm);
        System.out.println("TestService has permission");
    }
}
