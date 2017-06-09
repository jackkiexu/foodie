package com.lami.foodie.utils.privileged;

import com.lami.Person2;

/**
 * Created by xujiankang on 2017/6/5.
 */
public interface Person extends Person2 {
    // 设置姓名
    String setName(String name);

    // say something
    void sayHello(String name);

}
