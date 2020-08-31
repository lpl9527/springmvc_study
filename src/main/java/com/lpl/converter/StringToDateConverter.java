package com.lpl.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义类型转换器--字符串转日期
 */
public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {

        if (StringUtils.isEmpty(s)){
            throw new RuntimeException("类型转换异常：请输入日期！");
        }
        DateFormat df = null;
        //判断日期的格式
        if (s.split("-").length > 1){
            df = new SimpleDateFormat("yyyy-MM-dd");
        }else if (s.split("/").length > 1){
            df = new SimpleDateFormat("yyyy/MM/dd");
        }else{
            df = new SimpleDateFormat("yyyyMMdd");
        }
        //将字符串转换为日期
        try {
            Date date = df.parse(s);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("日期转换异常：请输入正确的日期格式！");
        }

    }
}
