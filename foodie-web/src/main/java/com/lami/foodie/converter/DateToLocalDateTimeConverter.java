package com.lami.foodie.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by xjk on 2/22/18.
 */
public class DateToLocalDateTimeConverter
        implements Converter<Date, LocalDateTime> {

    @Override
    public LocalDateTime convert (Date source) {
        return LocalDateTime.ofInstant(source.toInstant(),
                ZoneId.systemDefault());
    }
}
