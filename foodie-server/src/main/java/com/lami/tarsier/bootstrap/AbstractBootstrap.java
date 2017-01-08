package com.lami.tarsier.bootstrap;

import com.lami.tarsier.core.util.SpringContextLoader;

/**
 * Created by xjk on 11/9/16.
 */
public class AbstractBootstrap {


    public void loader ( String password ) throws Exception  {

    }

    public void clean () throws Exception {
        SpringContextLoader.getInstance().destrory () ;
    }
}

