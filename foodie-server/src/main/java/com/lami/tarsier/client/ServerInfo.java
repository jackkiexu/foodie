package com.lami.tarsier.client;

import lombok.Data;

/**
 * Created by xjk on 11/10/16.
 */
@Data
public class ServerInfo {

    private String  host;
    private Integer port;

    /** online client **/
    private Long connectCount;
    /** server performance **/
    private Long performance;
    /** online time **/
    private Long onlineTime;
}
