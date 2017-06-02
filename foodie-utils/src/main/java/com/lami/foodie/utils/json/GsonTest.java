package com.lami.foodie.utils.json;

import org.apache.commons.collections.map.HashedMap;

import java.util.*;

/**
 * Created by xujiankang on 2017/5/18.
 */
public class GsonTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();


        map.put("aa", "bb");
        map.put("cc", "dd");

        System.out.println(GsonUtils.toGson(map));

        List<String> list = new ArrayList<String>();
        list.add("5.14");
        list.add("5.16");
        System.out.println(GsonUtils.toGson(list));

        Person person = new Person("xx", "11");
        System.out.println(GsonUtils.toGson(person));

        Map<String, List<Person>> map2 = new HashMap<String, List<Person>>();
        map2.put("person", Arrays.asList(person));
        System.out.println(GsonUtils.toGson(map2));

        School school = new School("ss", "00");

        XM xm = new XM();
        xm.setPerson(person);
        xm.setSchool(school);
        System.out.println(GsonUtils.toGson(xm));


    }

}
