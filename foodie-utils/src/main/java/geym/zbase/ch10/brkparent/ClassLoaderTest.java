package geym.zbase.ch10.brkparent;

import java.io.File;

/**
 * ��ʹapp classloader���Լ��أ�����Ҳ������أ���ΪOrderClassLoader�ƻ���˫��ģʽ
 * @author Administrator
 *
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {

        String className = "geym.zbase.ch10.findorder.HelloLoader";
        className = className.replace('.', File.separatorChar) + ".class";
        className = "";
        String classFile = System.getProperty("user.dir") + "/foodie-utils/target/classes/" + className ;

        OrderClassLoader myLoader=new OrderClassLoader(classFile);
        Class clz=myLoader.loadClass("geym.zbase.ch10.brkparent.DemoA");
        System.out.println(clz.getClassLoader());
        
        System.out.println("==== Class Loader Tree ====");
        ClassLoader cl=clz.getClassLoader();
        while(cl!=null){
            System.out.println(cl);
            cl=cl.getParent();
        }
    }
}
