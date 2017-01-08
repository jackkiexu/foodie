package com.lami.foodie.utils.concurrent;

/**
 * Created by xjk on 12/26/16.
 */
public class StringTest {

    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        System.out.println(a.compareTo(b));
        System.out.println(b.compareTo(a));
        System.out.println(a.compareTo(a));
    }

}
