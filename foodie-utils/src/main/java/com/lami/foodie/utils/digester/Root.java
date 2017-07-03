package com.lami.foodie.utils.digester;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujiankang on 2017/6/21.
 */
public class Root {

    public List studentList = new ArrayList();

    public void addStudent(Student student){
        this.studentList.add(student);
    }

    @Override
    public String toString() {
        return "Root{" +
                "studentList=" + studentList +
                '}';
    }
}
