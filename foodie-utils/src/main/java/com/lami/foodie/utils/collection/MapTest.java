package com.lami.foodie.utils.collection;

/**
 * Created by xujiankang on 2017/7/6.
 *
 * 在Java API文档中关于hashCode方法有以下几点规定
 * 1. 在java应用程序执行期间，如果在equals方法比较中所用的信息没有被修改，那么在同一个对象上多次调用hashCode方法时必须一致地返回相同的整数。如果多次执行同一个应用时，不要求该整数必须相同。
 * 2. 如果两个对象通过调用equals方法是相等的，那么这两个对象调用hashCode方法必须返回相同的整数。
 * 3. 如果两个对象通过调用equals方法是不相等的，不要求这两个对象调用hashCode方法必须返回不同的整数。但是程序员应该意识到对不同的对象产生不同的hash值可以提供哈希表的性能。
 */
import java.util.HashMap;
import java.util.Map;
public class MapTest {

    public static void main(String[] args) {
        Map<String,Value> map1 = new HashMap<String,Value>();
        String s1 = new String("key");
        String s2 = new String("key");
        Value value = new Value(2);
        map1.put(s1, value);
        System.out.println("s1.equals(s2):"+s1.equals(s2));
        System.out.println("map1.get(s1):"+map1.get(s1));
        System.out.println("map1.get(s2):"+map1.get(s2));


        Map<Key,Value> map2 = new HashMap<Key,Value>();
        Key k1 = new Key("A");
        Key k2 = new Key("A");
        map2.put(k1, value);
        System.out.println("k1.equals(k2):"+s1.equals(s2));
        System.out.println("map2.get(k1):"+map2.get(k1));
        System.out.println("map2.get(k2):"+map2.get(k2));
    }

    /**
     * 下面的对象 key 比较的 hashCode 是直接调用 Object.hashCode, 直接比较的是内存地址 (Object的HashCode 是一个 native 方法, 直接比较的是对象的内存地址)
     */
    static class Key{
        private String k;
        public Key(String key){
            this.k=key;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Key){
                Key key=(Key)obj;
                return k.equals(key.k);
            }
            return false;
        }

        /*@Override
        public int hashCode() {
            return k != null ? k.hashCode() : 0;
        }*/
    }

    static class Value{
        private int v;

        public Value(int v){
            this.v=v;
        }
        @Override
        public String toString() {
            return "类Value的值－－>"+v;
        }
    }
}