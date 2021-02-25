package org.qinh.edu.localdate;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTest {
    //LocalDate
    @Test
    public void t1(){
        // 取当前日期：
        LocalDate now = LocalDate.now();
        System.out.println(now);//2021-01-07
        // 根据年月日取日期，12月就是12：
        LocalDate of = LocalDate.of(2021, 12, 31);
        System.out.println(of);//2021-12-31
        // 根据字符串取：
        // 严格按照ISO yyyy-MM-dd验证，02写成2都不行，当然也有一个重载方法允许自己定义格式
        LocalDate parse = LocalDate.parse("2021-01-02");
        parse = LocalDate.parse("20220101", DateTimeFormatter.ofPattern("yyyyMMDD"));
        System.out.println(parse);//2022-01-01
        // 取本月第1天：
        LocalDate with = now.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(with);//2021-01-01
        LocalDate day1 = now.withDayOfMonth(1);
        System.out.println(day1);//2021-01-01
        // 取本月第2天：
        LocalDate day2 = now.withDayOfMonth(2);
        System.out.println(day2);//2021-01-02
        // 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate endDay = now.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(endDay);//2021-01-31
        // 取下一天：
        LocalDate nextDay = endDay.plusDays(1);
        System.out.println(nextDay);//2021-02-01
        // 取2015年1月第一个周一，这个计算用Calendar要死掉很多脑细胞：
        LocalDate with1 = LocalDate.parse("2015-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println(with1);//2015-01-05
    }
    //LocalTime
    @Test
    public void t2(){
        //`LocalTime`包含毫秒：
        LocalTime now = LocalTime.now();
        System.out.println(now);//10:48:47.671
        //清除毫秒数：
        LocalTime localTime = LocalTime.now().withNano(0);
        System.out.println(localTime);//10:53:50
        //构造时间
        LocalTime zero = LocalTime.of(0, 0, 0);
        System.out.println(zero);
        LocalTime mid = LocalTime.parse("12:00:00");
        System.out.println(mid);


    }
}
