package com.lami.tarsier.client;

import lombok.Data;

import java.util.Properties;

/**
 * Created by xjk on 11/10/16.
 */
@Data
public class SyncClientConfig {

    /******* socket config ************/

    private Integer bufferSize = 100 * 1024;

    private Integer connectTimeoutMs = 5000;

    private Integer socketTimeoutMs = 30000;

    private Integer reconnectInterval = 30000;

    private Integer reconnectTimeInterval = 1000 * 30;

    private Integer maxMessageSize = 1000 * 1000;

    /******* auth config ************/
    private String account = "";

    private String passwd = "";

    private String signature = "";

}
