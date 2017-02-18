package com.lami.foodie.utils.concurrent.concurrentlinkedqueue;

import com.lami.foodie.utils.concurrent.UnSafeClass;
import org.apache.log4j.Logger;
import sun.misc.Unsafe;

/**
 * Created by xujiankang on 2017/1/16.
 */
public class KConcurrentLinkedQueue<E> {

    private static final Logger logger = Logger.getLogger(KConcurrentLinkedQueue.class);

    private static final long serialVersionUID = -980957881043363309L;


    private transient volatile Node<E> head;

    private transient volatile Node<E> tail;

    public KConcurrentLinkedQueue() {

    }

    // Unsafe mechanics
    private static Unsafe unsafe;
    private static long headOffset;
    private static long tailOffset;

    static {
        try{
            unsafe = UnSafeClass.getInstance();
            Class<?> k = KConcurrentLinkedQueue.class;
            headOffset = unsafe.objectFieldOffset(k.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset(k.getDeclaredField("tail"));
        }catch (Exception e){
            throw new Error(e);
        }
    }

    static class Node<E> {
        volatile E item;
        volatile Node<E> next;

        /**
         * Constructs a new Node, Uses relaxed write because item can
         * only be seen after publication vis casNext
         */
        Node(E item) {
            unsafe.putObject(this, itemOffset, item);
        }

        boolean casItem(E cmp, E val){
            return unsafe.compareAndSwapObject(this, itemOffset, cmp, val);
        }

        void lazySetNext(Node<E> val){
            unsafe.putOrderedObject(this, nextOffset, val);
        }

        boolean casNext(Node<E> cmp, Node<E> val){
            return unsafe.compareAndSwapObject(this, nextOffset, cmp, val);
        }

        private static Unsafe unsafe;
        private static long itemOffset;
        private static long nextOffset;

        static {
            try{
                unsafe = UnSafeClass.getInstance();
                Class<?> k = Node.class;
                itemOffset = unsafe.objectFieldOffset(k.getDeclaredField("item"));
                nextOffset = unsafe.objectFieldOffset(k.getDeclaredField("next"));
            }catch (Exception e){

            }
        }
    }
}
