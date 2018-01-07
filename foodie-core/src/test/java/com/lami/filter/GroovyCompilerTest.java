package com.lami.filter;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xujiankang on 2017/11/29.
 */
public class GroovyCompilerTest {

    @Test
    public void test(){
//        GroovyCompiler compiler = new GroovyCompiler();
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

        try {
            for(int i = 10; i< 1000000; i++){
                String code = "class test { public String hello(){return \"hello\" } } ";
                code = "package com.lami.filter;\n" +
                        "\n" +
                        "import net.sf.cglib.proxy.Enhancer;\n" +
                        "import net.sf.cglib.proxy.InvocationHandler;\n" +
                        "\n" +
                        "import java.lang.reflect.Method;\n" +
                        "\n" +
                        "/**\n" +
                        " * Created by xujiankang on 2017/11/29.\n" +
                        " */\n" +
                        "public class Test {\n" +
                        "    public String hello(){\n" +
                        "        byte[] byteArray = new byte[10 *1024*1024];\n" +
                        "        return \"hello\";\n" +
                        "    }\n" +
                        "\n" +
                        "    public void testInvocationHandler() throws Exception {\n" +
                        "        Enhancer enhancer = new Enhancer();\n" +
                        "        enhancer.setSuperclass(com.lami.filter.Person.class);\n" +
                        "        enhancer.setCallback(new InvocationHandler() {\n" +
                        "            public Object invoke(Object proxy, Method method, Object[] args)\n" +
                        "                    throws Throwable {\n" +
                        "                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {\n" +
                        "                    ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();\n" +
                        "                    threadLocal.set(\"\" + System.currentTimeMillis());\n" +
                        "                    return \"Hello cglib!\";\n" +
                        "                } else {\n" +
                        "                    throw new RuntimeException(\"Do not know what to do.\");\n" +
                        "                }\n" +
                        "            }\n" +
                        "        });\n" +
                        "        com.lami.filter.Person proxy = (com.lami.filter.Person) enhancer.create();\n" +
                        "        System.out.println(proxy.getName());\n" +
                        "    }\n" +
                        "}\n";
//                Class clazz = compiler.compile(code, "test");
                Class clazz = groovyClassLoader.parseClass(code);
                assertNotNull(clazz);

//                assertEquals(clazz.getName(), "Test");
                GroovyObject groovyObject = (GroovyObject) clazz.newInstance();
                Object[] args = {};
                String s = (String) groovyObject.invokeMethod("testInvocationHandler", args);
                System.out.println("S:" + s + ", i:" + i);
       /*         assertEquals(s, "hello");
                code = "class test { public String hello(){return \"hello1\" } } ";
                Class clazz2 = compiler.compile(code, "test");
                System.out.println(clazz == clazz2);
                String result = (String)((GroovyObject) clazz.newInstance()).invokeMethod("hello", args);
                System.out.println("result :" + result + ", i:" + i);*/
                Thread.sleep(10);
                check();
            }

        } catch (Exception e) {
            assertFalse(true);
        }
    }


    public void check() {
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        long maxMemory = runtime.maxMemory();
        boolean ok = (maxMemory - (totalMemory - freeMemory) > 2048); // 剩余空间小于2M报警
        String msg = "Max:" + (maxMemory / 1024 / 1024) + "M, Total:"
                + (totalMemory / 1024 / 1024) + "M, Free:" + (freeMemory / 1024 / 1024)
                + "M, Use:" + ((totalMemory / 1024 / 1024) - (freeMemory / 1024 / 1024)) + "M";
        System.out.println(msg);
    }
}
