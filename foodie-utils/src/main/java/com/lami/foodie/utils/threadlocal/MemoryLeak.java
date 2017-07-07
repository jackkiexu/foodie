package com.lami.foodie.utils.threadlocal;

import java.lang.reflect.Field;

/**
 * Created by xujiankang on 2017/7/7.
 */
public class MemoryLeak {

    private static ThreadLocal<TestClass> threadLocal = new ThreadLocal<TestClass>();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    TestClass t = new TestClass(i);
                    t.printId();
                    t = null;
                }
            }
        }).start();
    }

    static class TestClass{
        private int id;
        private int[] arr;

        TestClass(int id){
            this.id = id;
            arr = new int[1000000];
            threadLocal.set(this);
        }

        public void printId(){
            System.out.println(threadLocal.get().id);
            reflect(threadLocal);
            Thread thread = Thread.currentThread();
            reflect(thread);
//            threadLocal.remove();
//            System.out.println(id);
        }
    }

    public static void reflect(Object obj) {
        if (obj == null) return;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int j = 0; j < fields.length; j++) {
            fields[j].setAccessible(true);
            // 字段名
            System.out.print(fields[j].getName() + ",");
            // 字段值
            if (fields[j].getType().getName().equals(
                    java.lang.String.class.getName())) {
                // String type
                try {
                    System.out.print(fields[j].get(obj));
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (fields[j].getType().getName().equals(
                    java.lang.Integer.class.getName())
                    || fields[j].getType().getName().equals("int")) {
                // Integer type
                try {
                    System.out.println(fields[j].getInt(obj));
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // 其他类型。。。
        }
        System.out.println();
    }
}