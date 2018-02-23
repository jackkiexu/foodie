package com.lami.foodie.converter;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.validation.DataBinder;

import java.util.Date;

/**
 * Created by xjk on 2/22/18.
 */
public class ConversionServiceWithDataBinderExample {

    public static void main (String[] args) {

        MutablePropertyValues mpv = new MutablePropertyValues();
        mpv.add("date", new Date());

        DataBinder dataBinder = new DataBinder(new MyObject());
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new DateToLocalDateTimeConverter());
        //commenting the following line will not populate date field
        dataBinder.setConversionService(service);


        dataBinder.bind(mpv);
        dataBinder.getBindingResult().getModel().entrySet().forEach(System.out::println);
    }

}
