package com.lami.foodie;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by xjk on 2/22/18.
 */
public class reflect {

    public static void main(String[] args) throws Exception {
        System.out.println(getParameterNames(App.class.getMethod("say", String.class)));
    }

    public static String[] getParameterNames(Method method) {
        Parameter[] parameters = method.getParameters();
        String[] parameterNames = new String[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Parameter param = parameters[i];
            /*if (!param.isNamePresent()) {
                return null;
            }*/
            parameterNames[i] = param.getName();
        }
        return parameterNames;
    }
}
