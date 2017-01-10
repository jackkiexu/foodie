package com.lami.foodie.utils.concurrent;

import com.jwetherell.algorithms.numbers.Integers;
import lombok.Data;
import org.apache.log4j.Logger;

/**
 * Created by xujiankang on 2017/1/10.
 */
public class NewABA {

    private static final Logger logger = Logger.getLogger(NewABA.class);

    public static void main(String[] args) throws Exception{
        final Person p1 = new Person("xjk", 28);
        final Person p2 = new Person("xjk", 29);
        final Person p3 = new Person("xjk", 30);
        final Person p4 = new Person("xjk", 31);
        final Person p5 = new Person("xjk", 33);

        final Node node1 = new Node<Person>(p1);
        final Node node2 = new Node<Person>(p2);
        final Node node3 = new Node<Person>(p3);
        final Node node4 = new Node<Person>(p4);
        final Node node5 = new Node<Person>(p5);

/*        logger.info(node1.casNext(null, node2));
        logger.info(node1.casNext(node2, node3));
        logger.info(node1.casNext(node3, node4));*/

        logger.info(node1);

        Thread t1 = new Thread(){
            @Override
            public void run() {
                logger.info(node1.casNext(null, node2));
                logger.info(node1.casNext(node2, node3));
                logger.info(node1.casNext(node3, node2));
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
//                logger.info(node1.casNext(node2, node5));
                Node nodexx = new Node<Person>(p2);
                logger.info(node1.casNext(nodexx, node5));
            }
        };

        t1.start();
        t1.join();

        t2.start();

        logger.info("******************************************************");
        logger.info("");

        final Node<Integer> node10 = new Node<Integer>(10);
        final Node<Integer> node11 = new Node<Integer>(11);
        final Node<Integer> node12 = new Node<Integer>(12);
        final Node<Integer> node13 = new Node<Integer>(13);
        final Node<Integer> node14 = new Node<Integer>(14);

        final Object one1 = (Integer)1;

        Thread t3 = new Thread(){
            @Override
            public void run() {
                logger.info(node10.item);
                logger.info(node10.casItem(10, 1));
            }
        };

        Thread t4 = new Thread(){
            @Override
            public void run() {
                Object one = (Integer)1;
                logger.info(node10.casItem( 1,  (Integer)one));
            }
        };

        t3.start();
        t3.join();

        t4.start();
        t4.join();

    }

    private static class Node<E> {
        volatile E item;
        volatile Node<E> next;

        /**
         * Constructs a new node.  Uses relaxed write because item can
         * only be seen after publication via casNext.
         */
        Node(E item) {
            UNSAFE.putObject(this, itemOffset, item);
        }

        boolean casItem(E cmp, E val) {
            return UNSAFE.compareAndSwapObject(this, itemOffset, cmp, val);
        }

        void lazySetNext(Node<E> val) {
            UNSAFE.putOrderedObject(this, nextOffset, val);
        }

        boolean casNext(Node<E> cmp, Node<E> val) {
            return UNSAFE.compareAndSwapObject(this, nextOffset, cmp, val);
        }

        // Unsafe mechanics

        private static final sun.misc.Unsafe UNSAFE;
        private static final long itemOffset;
        private static final long nextOffset;

        static {
            try {
                UNSAFE = UnSafeClass.getInstance();
                Class<?> k = Node.class;
                itemOffset = UNSAFE.objectFieldOffset
                        (k.getDeclaredField("item"));
                nextOffset = UNSAFE.objectFieldOffset
                        (k.getDeclaredField("next"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
        }
    }

    @Data
    static final class Person{
        String name;
        Integer age;

        public Person(String name, Integer age){
            this.name = name;
            this.age = age;
        }
    }
}
