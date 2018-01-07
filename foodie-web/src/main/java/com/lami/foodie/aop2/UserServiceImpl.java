package com.lami.foodie.aop2;

import java.util.Date;

/**
 * Created by xjk on 10/3/17.
 */
public class UserServiceImpl implements UserService {
    public void print() {
        System.out.println("It's time to : " + new Date());
    }
}
