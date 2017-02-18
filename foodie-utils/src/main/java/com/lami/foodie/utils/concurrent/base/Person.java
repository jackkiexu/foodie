package com.lami.foodie.utils.concurrent.base;

import lombok.Data;

/**
 * Created by xujiankang on 2017/1/16.
 */
@Data
public class Person {
    Integer age;
    String name;

    public Person(Integer age,String name){
        this.age = age;
        this.name = name;
    }
}
