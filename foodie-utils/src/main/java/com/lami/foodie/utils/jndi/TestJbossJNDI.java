package com.lami.foodie.utils.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Created by xjk on 7/1/17.
 */
public class TestJbossJNDI {

    public static void main(String[] args) throws Exception {
        Properties env= new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
        //此IP一定要为要访问的DNS服务器的IP,可通过网络设置查看
        env.put(Context.PROVIDER_URL, "dns://10.17.45.239");
        DirContext ctx= new InitialDirContext(env);
        System.out.println("a:" + ctx);
        DirContext ctx1 =(DirContext) ctx.lookup("www.sina.com");
        System.out.println("b:" + ctx1);
        System.out.println("c:"+ ctx1.getAttributes(""));
        //从ctx.getAttributes("www.sina.com")与ctx1.getAttributes("")结果一样
        System.out.println("d:"+ ctx.getAttributes("www.sina.com"));
        Attributes attrs1 = ctx.getAttributes("www.sina.com", new String[] { "a" });
        Attributes attrs2 = ctx.getAttributes("www.163.com", new String[] { "a" });
        Attributes attrs3 = ctx1.getAttributes("", new String[] { "a" });
        Attributes attrs4 = ctx.getAttributes("www.baidu.com", new String[] { "a" });
        System.out.println("e:"+ attrs1);
        System.out.println("f:"+ attrs2);
        System.out.println("g:"+ attrs3);
        System.out.println("attrs4:"+ attrs4);
        System.out.println("nameParse:"+ctx1.getNameInNamespace());
        //list,此方法会导致程序lock
        //listEnumation("list:",ctx.list(""));
        //----------------------search
        Attributes matchAttrs =new BasicAttributes(true);
        matchAttrs.put(new BasicAttribute("a", "61.172.201.13"));
        NamingEnumeration answer = ctx1.search("www.sina.com", matchAttrs);
        System.out.println("search :"+ answer);
    }

    public static void main4(String[] args) {

        if (args.length != 1) {
            System.out.println(
                    "\nUsage: ALEX: java " +
                            "-classpath " +
                            "\"$JAVALIB/fscontext.jar;$JAVALIB/providerutil.jar;.\" " +
                            "JNDIFileSystemDemo1 /temp\n");
            System.exit(1);
        }

        String name = args[0];

        System.out.println();
        System.out.println("Running JNDI File System Demo #1");
        System.out.println("File / Directory : " + name);
        System.out.println("==================================\n");

        System.out.println("  - Creating the environment Hashtable");
        Hashtable env = new Hashtable();
        env.put(     Context.INITIAL_CONTEXT_FACTORY
                , "com.sun.jndi.fscontext.RefFSContextFactory");

        try {

            System.out.println("  - Creating the initial context");
            Context ctx = new InitialContext(env);


            System.out.println("  - Look up an object");
            Object obj = ctx.lookup(name);


            System.out.println("  - Print it");
            System.out.println("  - " + name + " is bound to: " + obj);

        } catch (NamingException e) {
            System.err.println("Problem looking up " + name + ":" + e);
        }


        System.out.println();

    }


    public static void main3(String[] args) {
        String name = "";
        if (args.length > 0)
            name = args[0];

        name = "boot.ini";

        try {
            // Create a Properties object and set properties appropriately
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.fscontext.RefFSContextFactory");
            props.put(Context.PROVIDER_URL, "file:///");

            // Create the initial context from the properties we just created
            Context initialContext = new InitialContext(props);

            // Look up the object
            Object obj = initialContext.lookup(name);
            if (name.equals(""))
                System.out.println("Looked up the initial context");
            else
                System.out.println(name + " is bound to: " + obj);
        }
        catch (NamingException nnfe) {
            System.out.println("Encountered a naming exception");
        }
    }



    public static void main2(String[] args) {
        //
        //This example creates a subcontext in a namespace
        //
        try{
            Properties prop = new Properties();
            prop.setProperty("java.naming.factory.initial",
                    "com.sun.jndi.cosnaming.CNCtxFactory");
            prop.setProperty("java.naming.provider.url",
                    "iiop://mymachine:900");
            InitialContext ic = new InitialContext(prop);
            ic.createSubcontext("Test");
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }

    public static void main1(String[] args) throws Exception {

        Properties env = new Properties();
        // 载入 jboss的 SPI 相关参数, 包括初始上下文工厂, 服务 URL 等等
        URL inputStream = TestJbossJNDI.class.getClassLoader().getResource("jbossJndi.properties");

        InputStream inputStream1 = new FileInputStream(inputStream.getPath());

        env.load(inputStream1);
        env.list(System.out);

        // 通过 JNDI api 初始化上下文
        InitialContext ctx = new javax.naming.InitialContext(env);
        System.out.println("Got context");

        // create a subContext
        ctx.createSubcontext("/sylilzy");
        ctx.createSubcontext("sylilzy/sily");

        // rebind a object
        ctx.rebind("sylilzy/sily/a", "I'm sily a!");
        ctx.rebind("sylilzy/sily/b", "I'm sily b!");

        // lookup context
        Context ctx1 = (Context)ctx.lookup("sylilzy");
        Context ctx2 = (Context)ctx.lookup("/sylilzy/sily");
        ctx2.bind("/sylilzy/g", "this is g");

        // lookup binded object
        Object o;
        o = ctx1.lookup("sily/a");
        System.out.println("get object from jndi :" + o);

        // rename the object
        ctx2.rename("/sylilzy/g", "g1");
        o = ctx2.lookup("g1");
        System.out.println("get object from jndi:" + o);



    }

}
