package com.lami.foodie.utils.classloader.test2;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * http://www.blogjava.net/heavensay/archive/2012/11/07/389685.html
 * https://github.com/jackkiexu/JCL
 * https://github.com/jackkiexu/classloader
 *
 * 主要功能是重新加载更改过的.class文件，达到热替换的作用
 *
 * @author banana
 */
public class HotSwapURLClassLoader extends URLClassLoader {

    // 工程class类所在的路径
    private static String classPath = HotSwapURLClassLoader.class.getResource("/").getPath();

    private static HotSwapURLClassLoader classLoader = new HotSwapURLClassLoader();
    /**
     * 默认路径为当前工作空间
     */
    public HotSwapURLClassLoader() {
        super(getURLs(classPath));
    }

    /**
     * 加载指定路径下的class
     * @param classPath
     */
    public HotSwapURLClassLoader(String classPath) {
        // 设置ClassLoader加载的路径
        super(getURLs(classPath));
    }

    public static HotSwapURLClassLoader getClassLoader() {
        return classLoader;
    }

    private static URL[] getURLs(String classPath) {
        try {
            URL url = new File(classPath).toURI().toURL();
            HotSwapURLClassLoader.classPath = classPath;
            return new URL[] { url };
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 重写loadClass，不采用双亲委托机制
     */
    @Override
    public Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        // 查看HotSwapURLClassLoader实例缓存下，是否已经加载过class
        // 不同的HotSwapURLClassLoader实例是不共享缓存的
        Class<?> clazz = findLoadedClass(name);
        if (clazz != null) {
            if (resolve) {
                resolveClass(clazz);
            }
            classLoader = new HotSwapURLClassLoader();
            clazz = customLoad(name);
            return clazz;
        }

        // 如果类的包名为"java."开始，则有系统默认加载器AppClassLoader加载
        if (name.startsWith("java.")) {
            try {
                // 得到系统默认的加载cl，即AppClassLoader
                ClassLoader system = ClassLoader.getSystemClassLoader();
                clazz = system.loadClass(name);
                if (clazz != null) {
                    if (resolve)
                        resolveClass(clazz);
                    return (clazz);
                }
            } catch (ClassNotFoundException e) {
            }
        }
        return customLoad(name);
    }

    public Class<?> load(String name) throws Exception {
        return loadClass(name);
    }

    /**
     * 自定义加载
     *
     * @param name
     * @param cl
     * @return
     * @throws ClassNotFoundException
     */
    public Class<?> customLoad(String name) throws ClassNotFoundException {
        return customLoad(name, false);
    }

    /**
     * 自定义加载
     *
     * @param name
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */
    public Class<?> customLoad(String name, boolean resolve) throws ClassNotFoundException {
        // findClass()调用的是URLClassLoader里面重载了ClassLoader的findClass()方法
        Class<?> clazz = classLoader.findClass(name);
        if (resolve)
            classLoader.resolveClass(clazz);
        return clazz;
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    /**
     * 根据类名找到class文件对象 如果类名称中不包括允许reload的关键字,则返回空，委托给上一层的classloader去查找
     */
    public static File findClassFile(String name) {
        StringBuilder sb = new StringBuilder(classPath);
        name = name.replace('.', File.separatorChar) + ".class";
        sb.append(File.separator + name);
        return new File(sb.toString());
    }
}
