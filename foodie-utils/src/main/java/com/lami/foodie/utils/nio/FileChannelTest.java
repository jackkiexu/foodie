package com.lami.foodie.utils.nio;

import org.apache.log4j.Logger;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * http://kakajw.iteye.com/blog/1797073
 * https://my.oschina.net/flashsword/blog/159613
 *
 * Created by xujiankang on 2017/2/18.
 */
public class FileChannelTest {

    private static final Logger logger = Logger.getLogger(FileChannelTest.class);

    public static void main(String[] args) throws Exception{

        RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\xujiankang\\Desktop\\interview\\mysql.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);

        int bytesRead = inChannel.read(buf);

        while(bytesRead != -1){
            logger.info("Read " + bytesRead);
            buf.flip();

            while(buf.hasRemaining()){
                logger.info((char)buf.get());
            }
            buf.clear();
            buf.mark();
            bytesRead = inChannel.read(buf);
        }



        aFile.close();
    }

}
