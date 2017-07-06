package com.lami.foodie.utils.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiankang on 2017/7/6.
 */
public class ListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        for(String str : list){
            System.out.println("str :" + str);
            list.remove(1);
        }
    }

    // 下面是输出日志
/*
    str :a
    Exception in thread "main" java.util.ConcurrentModificationException
    at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:859)
    at java.util.ArrayList$Itr.next(ArrayList.java:831)
    at com.lami.foodie.utils.collection.ListTest.main(ListTest.java:19)
*/

}
