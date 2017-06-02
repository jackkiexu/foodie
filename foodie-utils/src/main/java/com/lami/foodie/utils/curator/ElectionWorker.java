package com.lami.foodie.utils.curator;

/**
 * Created by xujiankang on 2017/5/24.
 */

import org.apache.log4j.Logger;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * http://www.cnblogs.com/shenguanpu/p/4044146.html
 * Created with IntelliJ IDEA.
 *
 * @author guanpu
 *         Date: 14-10-22
 *         Time: 下午5:11
 *         To change this template use File | Settings | File Templates.
 */
public class ElectionWorker extends ZooKeeper implements Runnable, Watcher {

    private static final Logger logger = Logger.getLogger(ElectionWorker.class);

    public static final String NODE_NAME = "/cluster";
    public String znode;
    private boolean leader;

    public ElectionWorker(String connectString, int sessionTimeout, Watcher watcher) throws IOException {
        super(connectString, sessionTimeout, watcher);
    }

    public boolean register() throws InterruptedException, KeeperException {
        if (this.exists(NODE_NAME, null) == null) {
            this.create(NODE_NAME, "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
        }
        znode = this.create(NODE_NAME + "/w-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        znode = znode.replace(NODE_NAME + "/", "");
        String node = watchPrevious();
        if (node.equals(znode)) {
           logger.info("nobody here ,i am leader");
            leader = true;
        } else {
            logger.info("i am watching");
        }
        return true;
    }

    private String watchPrevious() throws InterruptedException, KeeperException {
        List<String> works = this.getChildren(NODE_NAME, this);
        Collections.sort(works);
        logger.info(works);
        int i = 0;
        for (String work : works) {
            if (znode.equals(work)) {
                if (i > 0) {
                    //this.getData(NODE_NAME + "/" + works.get(i - 1), this, null);
                    return works.get(i - 1);
                }
                return works.get(0);
            }
        }
        return "";

    }

    @Override
    public void run() {
        try {
            this.register();
        } catch (InterruptedException e) {
        } catch (KeeperException e) {
        }
        while (true) {
            try {
                if (leader) {
                    logger.info("leading");
                } else {
                    logger.info("following");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        try {
            String hostPort = "192.168.0.65:2182";
            new Thread(new ElectionWorker(hostPort, 3000, null)).start();
        } catch (IOException e) {
        }
    }


    @Override
    public void process(WatchedEvent event) {
        String t = String.format("hello event! type=%s, stat=%s, path=%s", event.getType(), event.getState(), event.getPath());
        logger.info(t);
        logger.info("hello ,my cluster id is :" + znode);
        String node = "";
        try {
            node = this.watchPrevious();
        } catch (InterruptedException e) {
        } catch (KeeperException e) {
        }

        if (node.equals(znode)) {
            logger.info("process: nobody here ,i am leader");
            leader = true;
        } else {
            logger.info("process: i am watching");
        }
    }
}
