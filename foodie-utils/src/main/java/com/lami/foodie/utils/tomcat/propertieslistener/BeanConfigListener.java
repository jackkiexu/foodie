package com.lami.foodie.utils.tomcat.propertieslistener;

import org.apache.log4j.Logger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

/**
 * Created by xujiankang on 2017/6/26.
 */
public class BeanConfigListener {

    private static final Logger logger = Logger.getLogger(BeanConfigListener.class);

    public static void main(String[] args) {
        final BetaConfig config = new BetaConfig();
        config.addPropertyChangeListener("serverPort", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                logger.info( evt.getPropertyName() + "| " + evt.getOldValue() + " update to " + evt.getNewValue());
            }
        });
        config.setServerPort(8080);
        config.setServerPort(8081);
        config.setServerPort(8082);
        config.setServerPort(8083);



        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        List<String> jvmArgs = runtimeMXBean.getInputArguments();
        for (String arg : jvmArgs) {
            System.out.println(arg);
        }
    }

}
