package com.lami.tarsier.consumer;

/**
 * Created by xjk on 11/9/16.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;


public class UserCache {
    /**ip管理                                      key:ip value:count */
    private HashMap<String,Integer> _ipMap = new HashMap<String,Integer>();
    /**用户对象管理                          key:userId      */
    private HashMap<Integer, Consumer> _userMap = new HashMap<Integer, Consumer>();
    /**session管理                   key:sessionId value:userId        */
    private HashMap<Long, Integer> _sessionMap = new HashMap<Long, Integer>();

    private ReentrantLock _sync = new ReentrantLock();

    public void addIp(String ip){
        try {
            _sync.lock();
            int num = 1;
            if(_ipMap.containsKey(ip)){
                num = _ipMap.get(ip);
                num++;
            }
            _ipMap.put(ip, num);
        } finally {
            _sync.unlock();
        }
    }
    public void closeIp(String ip){
        try {
            _sync.lock();
            if(_ipMap.containsKey(ip)){
                int num = _ipMap.get(ip);
                num--;
                if(num>0)
                    _ipMap.put(ip, num);
                else
                    _ipMap.remove(ip);
            }
        } finally {
            _sync.unlock();
        }
    }
    public int getIpNum(String ip){
        if(_ipMap.containsKey(ip)){
            return _ipMap.get(ip);
        }
        return 0;
    }

    public Map<Long, Integer> getSession(){
        return _sessionMap;
    }
    public void addSession(Long sessionId,Integer key){
        try {
            _sync.lock();
            _sessionMap.put(sessionId, key);
        } finally {
            _sync.unlock();
        }
    }
    public void set(Long sessionId, Integer key, Consumer user) throws Exception {
        try {
            _sync.lock();
            _userMap.put(key, user);
            _sessionMap.put(sessionId, key);
        } finally {
            _sync.unlock();
        }
    }

    /** remove userId -> remove sessionId and MUser
     * @param key
     */
    public void remove(Integer key) {
        try {
            _sync.lock();
            if (_userMap != null) {
                Consumer mu = _userMap.get(key);
                if (mu != null && mu.getSession() != null) {
                    _sessionMap.remove(mu.getSession());
                }
                _userMap.remove(key);
            }
        } catch (Exception e) {

        } finally {
            _sync.unlock();
        }
    }

    /** 当另一个账号在其他设备登录
     * @param sessionId
     */
    public void removeLoginInfo(Long sessionId){
        try {
            _sync.lock();
            _sessionMap.remove(sessionId);
        } catch (RuntimeException e) {
        }finally{
            _sync.unlock();
        }
    }

    /** remove sessionId -> remove userId and MUser
     * @param sessionId
     */
    public void remove(Long sessionId) {
        try {
            _sync.lock();
            if (_sessionMap != null) {
                Integer key = _sessionMap.get(sessionId);
                if (key != null) {
                    Consumer mu = _userMap.get(key);
                    if (mu != null && mu.getSession().longValue() == sessionId.longValue()) {
                        _userMap.remove(key);
                    }
                }
                _sessionMap.remove(sessionId);
            }
        } catch (Exception e) {

        } finally {
            _sync.unlock();
        }
    }

    /**
     * @param key userId
     * @return MUser
     */
    public Consumer get(Integer key) {
        if(key!=null){
            return _userMap.get(key);
        }else{
            return null;
        }
    }

    /**
     * @param sessionId
     * @return throught session -> userId -> MUser
     */
    public Consumer get(Long sessionId) {
        Integer key = _sessionMap.get(sessionId);
        return _userMap.get(key);
    }

    /**
     * @return  userMap
     */
    public HashMap<Integer, Consumer> get() {
        return _userMap;
    }

    /**
     * @return userMap.size
     */
    public int getUserKeySize(){
        if(_userMap!=null)
            return _userMap.size();
        else
            return 0;
    }

    /**
     * @return sessionMap.size
     */
    public int getSessionKeySize(){
        if(_sessionMap!=null)
            return _sessionMap.size();
        else
            return 0;
    }

    /**
     * @return ipMap.size();
     */
    public int getIpKeySize(){
        if(_ipMap!=null)
            return _ipMap.size();
        else
            return 0;
    }

}