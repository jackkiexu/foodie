package com.lami.foodie.utils.json;

/**
 * Created by xujiankang on 2017/5/18.
 */
public class School {

    private String name;
    private String age;


    public School(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
