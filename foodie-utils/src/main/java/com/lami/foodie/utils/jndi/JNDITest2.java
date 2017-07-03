package com.lami.foodie.utils.jndi;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.util.Properties;

/**
 * Created by xujiankang on 2017/7/3.
 */
public class JNDITest2 {

    private static final Logger logger = Logger.getLogger(JNDITest2.class);

    public static void main(String[] args) throws Exception {
        Properties env= new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
        //此IP一定要为要访问的DNS服务器的IP,可通过网络设置查看
        env.put(Context.PROVIDER_URL, "dns://10.17.45.239");
        DirContext ctx= new InitialDirContext(env);
        System.out.println("a:" + ctx);
        DirContext ctx1 =(DirContext) ctx.lookup("www.sina.com");
        System.out.println("b:" + ctx1);
        logger.info("c:" + ctx1.getAttributes(""));
        //从ctx.getAttributes("www.sina.com")与ctx1.getAttributes("")结果一样
        logger.info("d:" + ctx.getAttributes("www.sina.com"));
        Attributes attrs1 = ctx.getAttributes("www.sina.com", new String[] { "a" });
        Attributes attrs2 = ctx.getAttributes("www.163.com", new String[] { "a" });
        Attributes attrs3 = ctx1.getAttributes("", new String[] { "a" });
        Attributes attrs4 = ctx.getAttributes("www.baidu.com", new String[] { "a" });
        logger.info("e:" + attrs1);
        logger.info("f:" + attrs2);
        logger.info("g:" + attrs3);
        logger.info("attrs4:" + attrs4);
        System.out.println("nameParse:"+ctx1.getNameInNamespace());
        //list,此方法会导致程序lock
        //listEnumation("list:",ctx.list(""));
        //----------------------search
        Attributes matchAttrs =new BasicAttributes(true);
        matchAttrs.put(new BasicAttribute("a", "61.172.201.13"));
        NamingEnumeration answer = ctx1.search("www.sina.com", matchAttrs);
        logger.info("search :" + answer);
    }
}
