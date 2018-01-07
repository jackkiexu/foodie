package com.lami.foodie;

import org.junit.Assert;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by xjk on 10/5/17.
 */
public class SimpleTest {

    @org.junit.Test
    public void simpleTest(){
        List<String> list = mock(List.class);

        when(list.get(0)).thenReturn("helloworld");

        String result = list.get(0);

        verify(list).get(0);

        Assert.assertEquals("helloworld", result);
    }
}
