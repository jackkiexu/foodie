package com.lami.foodie.utils.concurrent.base;

import com.lami.foodie.utils.concurrent.UnSafeClass;
import org.apache.log4j.Logger;

/**
 * Created by xujiankang on 2017/1/16.
 */
public class Loop<E> {

    private static final Logger logger = Logger.getLogger(Loop.class);

    private static void loop1(){
        String tail = "";
        String t = (tail = "oldTail");
        tail = "newTail";
        boolean isEqual = t != (t = tail); // <- 神奇吧
        System.out.println("isEqual : "+isEqual);
        // isEqual : true

        int w = 0;
        restartFromHead:
        for(System.out.println("xx");;){
            w++;
            if (w > 2) break ;
            for(int i = 0; i < 10; i++){
                if (i == 8) continue restartFromHead;
                System.out.println("print i : " + i);
            }
        }

        System.out.println("over");

        w = 0;

        restartFromHead2:
        for(;;){  // <- 为什么两个 for 循环
            for(System.out.println("init for ");;){
                w++;
                if (w > 2) break ;
                for(int i = 0; i < 10; i++){
                    if (i == 8) continue restartFromHead2;
                    System.out.println("print i : " + i);
                }
            }
        }
    }

    private static void loop2(){
        for(int i = 1; i < 3 ; i++){
            logger.info(i);
        }
        int a = 0;
        System.out.println(a++);
    }


    public static void main(String[] args) {
        loop2();
    }

}
