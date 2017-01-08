package com.lami.tarsier.client;

import com.lami.tarsier.message.Message;
import com.lami.tarsier.utils.Scheduler;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * Created by xjk on 11/10/16.
 */
public class DefaultClient extends AbstractClient implements Closeable{

    private ClientConfig clientConfig;

    /** to handler resend msg */
    private Thread monitor = null;

    private Scheduler scheduler = new Scheduler(1, "client-scheduler", false);

    public DefaultClient(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;

    }

    public DefaultClient startup() throws InterruptedException{
        logger.info("DefaultClient start up");
        return null;
    }

    @Override
    public List<ServerInfo> getServerInfoList() {
        return null;
    }

    @Override
    public void send(Message<?> message) {

    }

    public void close() throws IOException {

    }
}
