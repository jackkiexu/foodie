package com.lami.foodie.transaction;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xjk on 10/4/17.
 */
@Data
public class User implements Serializable{

    private Address address;
    private Integer id;
    private String name;

}
