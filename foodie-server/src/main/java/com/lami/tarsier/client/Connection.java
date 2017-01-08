package com.lami.tarsier.client;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created by xjk on 11/11/16.
 */
public class Connection {

    private final Logger logger = Logger.getLogger(Connection.class);

    private IoSession session;

    private SocketConnector connector;

    public Connection connect(ServerInfo serverInfo){
        SocketAddress socketAddress = new InetSocketAddress(serverInfo.getHost(), serverInfo.getPort());
        SocketConnector connector = new NioSocketConnector(1); // default one iowork
        return null;
    }

    public boolean isConnection(){
        if(session != null && connector != null){
            return session.isClosing();
        }
        return false;
    }

    public void closeUninterruptibly(){
        if(session == null || !session.isConnected()) return;
        session.close(true);
        session.getCloseFuture().awaitUninterruptibly(10, TimeUnit.SECONDS);
    }
}
