package com.qinh.date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-04-10-17:51
 */
public class JDK8DateTimeTest {

    @Test
    public void testDate(){
        //偏移量
        Date date1 = new Date(2020 - 1900,9 - 1,8);
        System.out.println(date1);//Tue Sep 08 00:00:00 GMT+08:00 2020
    }

    /**
     * LocalDate、LocalTime、LocalDateTime 的使用
     * 说明：
     *     1.LocalDateTime相较于LocalDate、LocalTime，使用频率要高
     *     2.类似于Calendar
     */
    @Test
    public void t1(){
        //now():获取当前的日期、时间、日期+时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        //of():设置指定的年月日时分秒，没有偏移量
        LocalDateTime localDateTime1 = LocalDateTime.of(2021, 1, 1, 12, 12, 12);
        System.out.println(localDateTime1);

        //getXxx():获取相关属性
        //10
        System.out.println(localDateTime.getDayOfMonth());
        //SATURDAY
        System.out.println(localDateTime.getDayOfWeek());
        //APRIL
        System.out.println(localDateTime.getMonth());
        //4
        System.out.println(localDateTime.getMonthValue());
        //3
        System.out.println(localDateTime.getMinute());

        //体现不可变性
        //withXxx():设置相关的属性
        LocalDateTime localDateTime2 = localDateTime.withDayOfMonth(22);
        System.out.println(localDateTime2);
        System.out.println(localDateTime);

        LocalDateTime localDateTime3 = localDateTime.withHour(22);
        System.out.println(localDateTime3);
        System.out.println(localDateTime);

        //不可变性
        LocalDateTime localDateTime4 = localDateTime.plusMonths(2);
        System.out.println(localDateTime4);
        LocalDateTime localDateTime5 = localDateTime.minusDays(9);
        System.out.println(localDateTime5);
    }

    /**
     * Instant的使用
     * 类似于 java.util.Date类
     */
    @Test
    public void t2(){
        //now():获取本初子午线对应的标准时间
        Instant instant = Instant.now();
        //2021-04-10T10:16:09.182Z
        System.out.println(instant);

        //添加时间的偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        //2021-04-10T18:18:53.176+08:00
        System.out.println(offsetDateTime);

        //toEpochMilli():获取自1970年1月1日0时0分0秒（UTC）开始的毫秒数  ---> Date类的getTime()
        long epochMilli = instant.toEpochMilli();
        System.out.println(epochMilli);

        //ofEpochMilli(Long time):通过给定的毫秒数，获取Instant实例  -->Date(long millis)
        Instant instant1 = Instant.ofEpochMilli(1618050105335L);
        System.out.println(instant1);
    }

    /**
     * DateTimeFormatter:格式化或解析日期、时间
     * 类似于SimpleDateFormat
     */
    @Test
    public void t3(){
        //方式一：预定义的标准格式。如：ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        //格式化
        LocalDateTime localDateTime = LocalDateTime.now();
        String date1 = formatter.format(localDateTime);
        //2021-04-10T18:30:18.751
        System.out.println(localDateTime);
        //2021-04-10T18:30:18.751
        System.out.println(date1);

        //解析：字符串 --> 日期
        TemporalAccessor parse = formatter.parse("2021-04-10T18:30:18.751");
        //{},ISO resolved to 2021-04-10T18:30:18.751
        System.out.println(parse);

        /*
         * 方式二：
         * 本地化相关的格式。如：ofLocalizedDateTime()
         *      FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT :适用于LocalDateTime
         * 本地化相关的格式。如：ofLocalizedDate()
         *      FormatStyle.FULL / FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT : 适用于LocalDate
         */
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        //格式化
        String date2 = formatter1.format(localDateTime);
        //21-4-10 下午6:40
        //2021年4月10日 下午06时41分23秒
        System.out.println(date2);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        String date3 = formatter2.format(LocalDate.now());
        //2021年4月10日 星期六
        System.out.println(date3);

        //重点： 方式三：自定义的格式。如：ofPattern(“yyyy-MM-dd hh:mm:ss”)
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //格式化
        String date4 = formatter3.format(LocalDateTime.now());
        //2021-04-10 06:47:14
        System.out.println(date4);

        //解析
        TemporalAccessor parse1 = formatter3.parse("2021-04-10 06:47:14");
        //{MilliOfSecond=0, MicroOfSecond=0, HourOfAmPm=6, SecondOfMinute=14, NanoOfSecond=0, MinuteOfHour=47},ISO resolved to 2021-04-10
        System.out.println(parse1);
        LocalDateTime localDateTime1 = LocalDateTime.parse("2021-04-10 06:47:14", formatter3);
        System.out.println(localDateTime1);
    }
}
