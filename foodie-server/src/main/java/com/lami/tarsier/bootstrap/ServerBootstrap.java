package com.lami.tarsier.bootstrap;

import com.lami.tarsier.consumer.UserCache;
import com.lami.tarsier.core.util.SpringContextLoader;
import com.lami.tarsier.core.util.SpringContextUtil;
import org.apache.log4j.Logger;

/**
 * Created by xjk on 11/9/16.
 */
public class ServerBootstrap extends AbstractBootstrap{

    private static final Logger logger = Logger.getLogger(ServerBootstrap.class);

    public SocketServer init(){
        try{
            SpringContextLoader.getInstance().init(new String[]{"classpath:/*.xml"});
            return (SocketServer) SpringContextUtil.getBean("tcpLoader");
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        try {
            new ServerBootstrap().init().start();
            UserCache userCache = (UserCache)SpringContextUtil.getBean("userCache");
            for (;;) {
                Thread.sleep(10000);
                logger.info("server running : client="+userCache.getUserKeySize()+",ip="+userCache.getIpKeySize()+",session="+userCache.getSessionKeySize());
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
