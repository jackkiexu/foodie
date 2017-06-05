package com.lami.foodie.utils.tomcat.classloader;

import org.apache.log4j.Logger;
import sun.misc.*;
import sun.net.www.ParseUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.*;
import java.security.cert.*;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by xujiankang on 2017/6/2.
 */
public class TomcatClassLoader {

    private static final Logger logger = Logger.getLogger(TomcatClassLoader.class);

    public static void main(String[] args)throws Exception{
/*        InputStream is = TomcatClassLoader.class.getClassLoader().getResourceAsStream("com.lami.foodie.utils.tomcat.classloader.TomcatClassLoader.class");
        File file = new File("C:\\Users\\xujiankang\\AppData\\Local\\Temp\\TestWebappClassLoaderWeaving\\WEB-INF\\classes\\org\\apache\\catalina\\loader.TomcatClassLoader.class");
        if(!file.exists()) file.mkdirs();
        FileOutputStream os = new FileOutputStream(file);
        try {
            for (int b = is.read(); b >= 0; b = is.read()) {
                os.write(b);
            }
        } finally {
            is.close();
            os.close();
        }*/

        ExtClassLoader extClassLoader = ExtClassLoader.getExtClassLoader();

        test();

    }

    // 获取当前运行的堆栈信息
    public static void test(){
        Throwable ex = new Throwable();
        StackTraceElement[] stackElements = ex.getStackTrace();

        if (stackElements != null) {
            for (int i = 0; i < stackElements.length; i++) {
                logger.info(stackElements[i].getClassName());//返回类的完全限定名，该类包含由该堆栈跟踪元素所表示的执行点。
                logger.info(stackElements[i].getFileName());//返回源文件名，该文件包含由该堆栈跟踪元素所表示的执行点。
                logger.info(stackElements[i].getLineNumber());//返回源行的行号，该行包含由该堆栈该跟踪元素所表示的执行点。
                logger.info(stackElements[i].getMethodName());//返回方法名，此方法包含由该堆栈跟踪元素所表示的执行点。
                logger.info("-------------第"+i+"级调用-------------------");
            }
        }
    }


    static class ExtClassLoader extends URLClassLoader {
        public static ExtClassLoader getExtClassLoader() throws IOException {
            final File[] var0 = getExtDirs();

            try {
                return (ExtClassLoader) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                    public ExtClassLoader run() throws IOException {
                        int var1 = var0.length;

                        for (int var2 = 0; var2 < var1; ++var2) {
                            MetaIndex.registerDirectory(var0[var2]);
                        }

                        return new ExtClassLoader(var0);
                    }
                });
            } catch (PrivilegedActionException var2) {
                throw (IOException)var2.getException();
            }
        }

        void addExtURL(URL var1) {
            super.addURL(var1);
        }

        public ExtClassLoader(File[] var1) throws IOException {
            super(getExtURLs(var1));
        }

        private static File[] getExtDirs() {
            String var0 = System.getProperty("java.ext.dirs");
            File[] var1;
            if(var0 != null) {
                StringTokenizer var2 = new StringTokenizer(var0, File.pathSeparator);
                int var3 = var2.countTokens();
                var1 = new File[var3];

                for(int var4 = 0; var4 < var3; ++var4) {
                    var1[var4] = new File(var2.nextToken());
                }
            } else {
                var1 = new File[0];
            }

            return var1;
        }

        private static URL[] getExtURLs(File[] var0) throws IOException {
            Vector var1 = new Vector();

            for(int var2 = 0; var2 < var0.length; ++var2) {
                String[] var3 = var0[var2].list();
                if(var3 != null) {
                    for(int var4 = 0; var4 < var3.length; ++var4) {
                        if(!var3[var4].equals("meta-index")) {
                            File var5 = new File(var0[var2], var3[var4]);

                        }
                    }
                }
            }

            URL[] var6 = new URL[var1.size()];
            var1.copyInto(var6);
            return var6;
        }

        public String findLibrary(String var1) {
            var1 = System.mapLibraryName(var1);
            URL[] var2 = super.getURLs();
            File var3 = null;

            for(int var4 = 0; var4 < var2.length; ++var4) {
                File var5 = (new File(var2[var4].getPath())).getParentFile();
                if(var5 != null && !var5.equals(var3)) {
                    String var6 = VM.getSavedProperty("os.arch");
                    File var7;
                    if(var6 != null) {
                        var7 = new File(new File(var5, var6), var1);
                        if(var7.exists()) {
                            return var7.getAbsolutePath();
                        }
                    }

                    var7 = new File(var5, var1);
                    if(var7.exists()) {
                        return var7.getAbsolutePath();
                    }
                }

                var3 = var5;
            }

            return null;
        }


        static {
            ClassLoader.registerAsParallelCapable();
        }
    }
}
