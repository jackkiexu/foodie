package com.lami.foodie.utils.tomcat.classloader;

import java.util.ArrayList;
import java.util.List;

/**
 * 对 JVM 指令集的测试
 * javap -c InvokeExamples.class
 *
 * http://thinkinmylife.iteye.com/blog/443900
 * http://www.infoq.com/cn/articles/Invokedynamic-Javas-secret-weapon
 *
 * JVM 指令集
 * https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html
 *
 * Created by xujiankang on 2017/6/5.
 */
public class InvokeExamples {
    public static void main(String[] args) {
        InvokeExamples sc = new InvokeExamples();
        sc.run();
    }

    private void run() {
        List ls = new ArrayList();
        ls.add("Good Day");

        ArrayList als = new ArrayList();
        als.add("Dydh Da");
    }
}