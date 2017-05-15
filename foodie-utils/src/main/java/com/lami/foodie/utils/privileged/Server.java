package com.lami.foodie.utils.privileged;

import java.io.*;

/**
 * java 权限控制
 * http://huangyunbin.iteye.com/blog/1942509
 * 以以下这种命令行来运行
 * java -Djava.security.manager -Djava.security.policy=/tmp/2017051501/my.policy -classpath foodie-utils.jar com.lami.foodie.utils.privileged.Server
 * java  -classpath foodie-utils.jar com.lami.foodie.utils.privileged.Server
 *
 * 通过这个例子说明 对文件的读写权限等于在 操作系统上面再做了一层
 * Created by xujiankang on 2017/5/15.
 */
public class Server {

    public static void main(String[] args) {

        readFileByLines("/1.txt");
        System.out.println("--------- read over------------");

        appendMethodB("/1.txt", "wangwang");
        System.out.println("--------- write over------------");


        Client client = new Client();
        client.doCheck();
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        file.exists();
        System.out.println("File:" + fileName + " is exists: " + file.exists());
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }


    /**
     * B方法追加文件：使用FileWriter
     */
    public static void appendMethodB(String fileName, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
