package com.lami.tarsier.bootstrap;

import org.apache.mina.core.future.WriteFuture;

/**
 * Created by xjk on 11/11/16.
 */
public interface Server {

    WriteFuture send ( Long key, Object object ) throws Exception;

    void start () throws Exception;
}
