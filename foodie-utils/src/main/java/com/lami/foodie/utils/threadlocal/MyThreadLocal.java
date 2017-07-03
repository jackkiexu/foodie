package com.lami.foodie.utils.threadlocal;

/**
 * Created by xujiankang on 2017/1/12.
 */
public class MyThreadLocal {

    public static final ThreadLocal<Object> userThreadLocal = new ThreadLocal<Object>(){
        @Override
        protected Object initialValue() {
            return "q";
        }
    };

    public static void main(String[] args) {
        Object object = userThreadLocal.get();

        userThreadLocal.set("b");
        userThreadLocal.remove();
    }



}
