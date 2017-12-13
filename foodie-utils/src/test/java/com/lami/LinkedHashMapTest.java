package com.lami;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

/**
 * Created by xujiankang on 2017/11/21.
 */
public class LinkedHashMapTest {

    @Test
    public void testInvocationHandler() throws Exception {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        linkedHashMap.put("1", "a");
    }
}
