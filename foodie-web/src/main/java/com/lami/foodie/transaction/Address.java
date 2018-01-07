package com.lami.foodie.transaction;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xjk on 10/4/17.
 */
@Data
public class Address implements Serializable {

    private String city;
    private Integer id;
    private String province;
    private String street;
    private Integer userId;

}
