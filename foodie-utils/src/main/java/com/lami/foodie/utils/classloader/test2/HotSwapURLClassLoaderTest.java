package com.lami.foodie.utils.classloader.test2;


import java.io.File;

/**
 * Created by xjk on 5/2/17.
 */
public class HotSwapURLClassLoaderTest {

    public static void main(String[] args) {

        String path = "com.lami.foodie.utils.classloader.test2.Hot";
        File file = HotSwapURLClassLoader.findClassFile(path);
        System.out.println(file);
        System.out.println(file.getAbsolutePath());

    }

}
