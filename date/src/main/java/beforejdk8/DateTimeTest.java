package beforejdk8;


import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * JDK8之前日期和时间的API测试
 *
 * @author Qh
 * @version 1.0
 * @date 2021-04-07-23:16
 */
public class DateTimeTest {

    /**
     * 1.System类中的currentTimeMillis()
     */
    @Test
    public void t1(){
        long time = System.currentTimeMillis();
        //返回当前时间与1970年1月1日0时0分0秒之间以毫秒为单位的时间差。
        //称为时间戳
        System.out.println(time);
    }

    /**
     * java.util.Date类
     *     |=== java.sql.Date类
     *
     * 1.两个构造器的使用
     *   > 构造器一：Date()：创建一个对应当前时间的Date对象
     *   > 构造器二:创建指定毫秒数的Date对象
     * 2.两个方法的使用
     *   > toString():显示当前的年、月、日、时、分、秒
     *   > getTime():获取当前Date对象对应的毫秒数
     *
     * 3.java.sql.Date 对应着数据库中的日期类型的变量
     *   > 如何实例化对象
     *   > sql.Date ---> util.Date  直接赋值（多态）
     *   > 如何将java.util.Date对象转换为java.sql.Date对象
     */
    @Test
    public void t2(){
        //构造器一：Date()：创建一个对应当前时间的Date对象
        Date date1 = new Date();
        //Wed Apr 07 23:25:30 CST 2021
        System.out.println(date1);
        //1617809206993
        System.out.println(date1.getTime());

        //构造器二:创建指定毫秒数的Date对象
        Date date2 = new Date(1617809206993l);
        System.out.println(date2);

        //创建java.sql.Date对象
        java.sql.Date date3 = new java.sql.Date(1617809206993l);
        //2021-04-07
        System.out.println(date3);

        //如何将java.util.Date对象转换为java.sql.Date对象
        //情况一
        Date date4 = new java.sql.Date(1617809206993l);
        java.sql.Date date5 = (java.sql.Date)date4;
        //情况二
        Date date = new Date();
        java.sql.Date date6 = new java.sql.Date(date.getTime());

    }

}
