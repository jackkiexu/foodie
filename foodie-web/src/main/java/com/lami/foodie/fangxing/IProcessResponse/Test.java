package com.lami.foodie.fangxing.IProcessResponse;

import com.google.gson.Gson;
import lombok.Data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by xjk on 2/23/18.
 */
public class Test {

    public static void main(String[] args) {

        Gson gson = new Gson();
        Person person = new Person();
        person.setName("xjk");
        System.out.println(gson.toJson(person));

        String json = "{\"name\":\"xjk\"}";

        process(new MyProcessResponse());
    }

    public static <T> void process(Class<T> reponseClass
            ,IProcessResponse<T> processResponseListener){
        String content = runProcess();

        Gson gson = new Gson();
        T responese = gson.fromJson(content, reponseClass);
        processResponseListener.onProcessCompleted(responese);
    }

    public static <T> void process(IProcessResponse<T> processResponseListener){
        String content = runProcess();

        Gson gson = new Gson();

        Type[] types = processResponseListener.getClass().getGenericInterfaces();
        Type[] params = ((ParameterizedType) types[0]).getActualTypeArguments();
        Class<T> reponseClass = (Class) params[0];


        T responese = gson.fromJson(content, reponseClass);

        processResponseListener.onProcessCompleted(responese);
    }

    public static  <T> void process(AbstractProcessResponse<T> processResponseListener){
        String content = runProcess();

        Gson gson = new Gson();

        ParameterizedType type = (ParameterizedType)MyProcessResponse.class.getClass().getGenericSuperclass(); // getGenericSuperclass
        Type[] params = type.getActualTypeArguments();                              // getGenericSuperclass
        Class<T> reponseClass = (Class) params[0];

        T responese = gson.fromJson(content, reponseClass);

        processResponseListener.onProcessCompleted(responese);
    }

    public static String runProcess(){
        return "{\"name\":\"xjk\"}";
    }

}

@Data
class Person {
    String name;
}

class MyProcessResponse extends AbstractProcessResponse{

    @Override
    public void onProcessCompleted(Object result) {
        System.out.println(result);
    }
}

