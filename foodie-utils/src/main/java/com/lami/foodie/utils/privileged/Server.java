package com.lami.foodie.utils.privileged;

import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * java 权限控制
 * http://huangyunbin.iteye.com/blog/1942509
 * 以以下这种命令行来运行
 * java -Djava.security.manager -Djava.security.policy=/tmp/2017051501/my.policy -classpath foodie-utils.jar com.lami.foodie.utils.privileged.Server
 * java  -classpath foodie-utils.jar com.lami.foodie.utils.privileged.Server
 *
 * 通过反射 invoke 方法
 * http://azrael6619.iteye.com/blog/429797
 *
 * 通过这个例子说明 对文件的读写权限等于在 操作系统上面再做了一层
 * Created by xujiankang on 2017/5/15.
 */
public class Server {

    private static final Logger logger = Logger.getLogger(Server.class);

    public static void main(String[] args) {
        logger.info("init client begin ");
        Client client = new Client();
        logger.info("init client over ");
//        logger.info("client:" + client);
    }


    public static void main1(String[] args) throws Exception{

       /* readFileByLines("/1.txt");
        System.out.println("--------- read over------------");

        appendMethodB("/1.txt", "wangwang");
        System.out.println("--------- write over------------");


        Client client = new Client();
        client.doCheck();*/
//        testClassLoader();

        Class<?> clazz = Server.class.getClassLoader().loadClass("com.lami.foodie.utils.privileged.Person");
        logger.info("getMethods:" + Arrays.toString(clazz.getMethods()));
        logger.info("getDeclaredMethods:" + Arrays.toString(clazz.getDeclaredMethods()));

        /**
         * 1. 定义统一的 处理接口
         * 2. 传入 invoke 的 各种各样 obj 来激活对应的逻辑
         */
        try {
            logger.info("clazz :" + clazz.getMethod("sayHello", String.class).invoke(new Client(), "hello"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            logger.info("clazz :" + clazz.getMethod("setName", String.class).invoke(new Client(), "hello"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
        Class ownerClass = owner.getClass();
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = ownerClass.getMethod(methodName,argsClass);
        return method.invoke(owner, args);
    }

    private static void testClassLoader(){
        logger.info("init testClassLoader");
        logger.info(Client.class);
        logger.info("getURLs:" + Arrays.asList(((URLClassLoader) Client.class.getClassLoader()).getURLs()));
        logger.info("over testClassLoader");
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
