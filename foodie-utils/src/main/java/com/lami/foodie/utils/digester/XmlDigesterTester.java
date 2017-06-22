package com.lami.foodie.utils.digester;

import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * 参考资料
 * http://www.everycoding.com/coding/78.html
 *
 * Created by xujiankang on 2017/6/21.
 */
public class XmlDigesterTester {

    private static final Logger logger = Logger.getLogger(XmlDigesterTester.class);

    public static void main(String[] args) throws Exception{
        String path =  XmlDigesterTester.class.getClassLoader().getResource("Student.xml").getPath();

        File input = new File(path);
        Digester digester = new Digester();

        // 初始化根对象 (本案例的 XML 根目录命名是 ROOT)
        digester.addObjectCreate("root", "com.lami.foodie.utils.digester.Root");

        // 将 root 节点上的属性全部注入到 Root 对象中
        digester.addSetProperties("root");

        // 将所有匹配的目录 root/student 转成 JavaBean 对象 Student
        digester.addObjectCreate("root/student", "com.lami.foodie.utils.digester.Student");

        // 将 节点 root/student 上的属性注入到 Student 对象上
        digester.addSetProperties("root/student");

        digester.addBeanPropertySetter("root/student/name");
        digester.addBeanPropertySetter("root/student/sex");
        digester.addBeanPropertySetter("root/student/age");
        digester.addBeanPropertySetter("root/student/birthday");

        // 将所有匹配的 root/student, 转化成 Student 对象后, 传入 Root 对象的 addStudent 方法
        digester.addSetNext("root/student", "addStudent", "com.lami.foodie.utils.digester.Student");

        // 定义好上面的解析, 则开始解析
        Root root = (Root)digester.parse(input);
        logger.info(root);

    }
}
