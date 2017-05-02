package com.lami;

import com.lami.foodie.utils.classloader.test2.HotSwapURLClassLoader;
import org.junit.Test;

import java.io.File;

/**
 * Created by xjk on 5/2/17.
 */
public class HotSwapURLClassLoaderTest {

    @Test
    public void testPath(){
        String path = "com.lami.foodie.utils.classloader.test2.Hot";
        File file = HotSwapURLClassLoader.findClassFile(path);
        System.out.println(file);
        System.out.println(file.getAbsolutePath());
    }

}
