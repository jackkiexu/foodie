package com.lami.foodie.utils.concurrent.base;

import org.apache.log4j.Logger;

import java.util.Random;

/**
 * Created by xujiankang on 2017/1/19.
 */
public class RandomTest {

    private static final Logger logger = Logger.getLogger(RandomTest.class);

    private static final Random seedGenerator = new Random();
    private static int randomSeed = seedGenerator.nextInt() | 0x0100; // ensure nonzero


    private static int randomLevel() {
        int x = randomSeed;
        x ^= x << 13;
        x ^= x >>> 17;
        randomSeed = x ^= x << 5;
        if ((x & 0x8001) != 0) // test highest and lowest bits
            return 0;
        int level = 1;
        while (((x >>>= 1) & 1) != 0) ++level;
        return level;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++){
            int level = randomLevel();
            if(level != 0){
                logger.info(level + " ******************");
            }else{
//                logger.info(level);
            }

        }
        logger.info(randomLevel());
    }

}
