package com.lami.foodie.utils.reflect;

/**
 * Created by xujiankang on 2017/7/14.
 */
public class IntrospectionUtilsTest {

    public static void main(String[] args) {
        Student student = new Student();

        IntrospectionUtils.setProperty(student, "name", "xjk");

        System.out.println(student);
    }

}
