package com.lami.foodie.utils.classloader.test2;

/**
 * Created by xjk on 5/2/17.
 */
public class Hot {

    public void hot(){
        System.out.println(" version 1 : "+this.getClass().getClassLoader());
    }

}
