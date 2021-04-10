package com.qinh.date.exer;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-04-10-22:24
 */
public class ExerTest {

    /**
     * 1.将字符串”2017-08-16”转换为对应的java.sql.Date类的对象。
     * （使用JDK8之前或JDK8中的API皆可）
     */
    @Test
    public void t1() throws ParseException {
        String s = "2017-08-16";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(s);
        java.sql.Date date = new java.sql.Date(parse.getTime());
        System.out.println(date);
        System.out.println(date.getClass());
    }

    @Test
    public void t2(){
        String s = "2017-08-16";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime localDateTime = LocalDate.parse(s,formatter).atStartOfDay();
        System.out.println(localDateTime);
        long milli = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        java.sql.Date date = new java.sql.Date(milli);
        System.out.println(date);
    }
    
    @Test
    public void t3(){
        Calendar instance = Calendar.getInstance();
        Date date1 = instance.getTime();
        java.sql.Date date = new java.sql.Date(date1.getTime());
        System.out.println(date);
        System.out.println(date.getClass());
    }
}
