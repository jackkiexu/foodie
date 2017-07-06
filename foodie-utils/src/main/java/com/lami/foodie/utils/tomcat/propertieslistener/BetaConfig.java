package com.lami.foodie.utils.tomcat.propertieslistener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by xujiankang on 2017/6/26.
 */
public class BetaConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private int serverPort = 8012;


    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);


    public void addPropertyChangeListener(final String propertyName,final PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        //将属性的改变通知给监听，让监听器可以得知此改变
        changeSupport.firePropertyChange("serverPort", this.serverPort, serverPort);
        this.serverPort = serverPort;
    }
}
