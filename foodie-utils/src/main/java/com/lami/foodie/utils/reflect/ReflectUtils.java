package com.lami.foodie.utils.reflect;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xujiankang on 2017/6/22.
 */
public class ReflectUtils {

    private static final Logger logger = Logger.getLogger(ReflectUtils.class);

    private static Object invokeMethod(
            Method method,
            Object bean,
            Object[] values)
            throws IllegalAccessException, InvocationTargetException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified " + "- this should have been checked before reaching this method");
        }

        try {

            return method.invoke(bean, values);

        } catch (NullPointerException cause) {

        }
        return null;
    }


    public static void main1(String[] args) throws Exception{
        Student student1 = new Student();
        Student student2 = new Student();

        Class[] param = {Long.class};
        Method method = Student.class.getMethod("setId", param);
        logger.info(method);
        Object[] params = {0l};
        ReflectUtils.invokeMethod(method, student1, params);
        logger.info("student1:" + student1);
    }

    public static void main(String[] args) {
        Student student = new Student(null,"a", "a", "a", null, "a" );
        StudentCopy studentCopy = new StudentCopy();
        try {
            BeanUtils.copyProperties(studentCopy, student);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info(studentCopy);

    }

}
