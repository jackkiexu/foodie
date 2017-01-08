package com.lami.tarsier.client;

import com.lami.tarsier.message.DefaultMessage;
import com.lami.tarsier.message.Message;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by xjk on 11/10/16.
 */
public abstract class AbstractClient {

    protected final Logger logger = Logger.getLogger(AbstractClient.class);

    protected ArrayBlockingQueue<Message<?>> msgQueue = new ArrayBlockingQueue<Message<?>>(10000);

    private List<ServerInfo> serverList = new ArrayList<ServerInfo>();

    public abstract List<ServerInfo> getServerInfoList();

    public abstract void send(Message<?> message);

}
