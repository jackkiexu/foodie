package com.lami.foodie.utils.classloader.test1;

import org.apache.log4j.Logger;

/**
 * Created by xjk on 5/1/17.
 */
public class PrintClassLoaderTree {

    private static final Logger logger = Logger.getLogger(PrintClassLoaderTree.class);

    public static void main(String[] args) {
        ClassLoader classLoader = PrintClassLoaderTree.class.getClassLoader();
        while(classLoader != null){
            logger.info(classLoader);
            classLoader = classLoader.getParent();
        }

        logger.info("-------------------");
        logger.info(String.class.getClassLoader()); // 因为String 是Bootstrap 来加载的, 所以获取不到
    }

}
