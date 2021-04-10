package com.qinh.string;

import org.junit.Test;

/**
 * 关于StringBuffer和StringBuilder的使用
 *
 * @author Qh
 * @version 1.0
 * @date 2021-04-07-22:14
 */
public class StringBufferBuilderTest {

    /**
     * String、StringBuffer、StringBuilder三者的异同?
     * String:不可变的字符序列;底层结构使用char[]数组
     * StringBuffer:可变的字符序列;线程安全的,效率低;底层结构使用char[]数组
     * StringBuilder:可变的字符序列;JDK5.0新增的,线程不安全的,效率高;底层结构使用char[]数组
     *
     * 源码分析:
     * String str = new String();//char[] value = new char[0];
     * String str1 = new String("abc");//char[] value = new char[]{'a','b','c'};
     *
     * StringBuffer sb = new StringBuffer();//char[] value = new char[16];底层创建了一个长度是16的数组
     * System.out.println(sb.length());//0
     * sb.append('a');//value[0] = 'a';
     * sb.append('b');//value[1] = 'b';
     *
     * StringBuffer sb1 = new StringBuffer("abc");//char[] value = new char["abc".length() + 16];
     *
     * 问题1：System.out.println(sb1.length());//3
     * 问题2：扩容问题：如果要添加的数据底层数组盛不下了，那就需要扩容底层的数组
     *       默认情况下，扩容为原来容量的2倍 + 2，同时将原有数组中的元素复制到新的数组中
     *
     *       指导意义：开发中建议大家使用：StringBuffer(int capacity) 或 StringBuilder(int capacity)
     *
     */

    @Test
    public void t1(){
        StringBuffer sb = new StringBuffer("abc");
        sb.setCharAt(0,'m');
        //mbc
        System.out.println(sb);

        StringBuffer sb1 = new StringBuffer();
        //0
        System.out.println(sb1.length());

    }

    /**
     * StringBuffer append(xxx)：提供了很多的append()方法，用于进行字符串拼接
     * StringBuffer delete(int start,int end)：删除指定位置的内容
     * StringBuffer replace(int start, int end, String str)：把[start,end)位置替换为str
     * StringBuffer insert(int offset, xxx)：在指定位置插入xxx
     * StringBuffer reverse() ：把当前字符序列逆转
     * public int indexOf(String str)
     * public String substring(int start,int end):返回一个从start开始到end索引结束的左闭右开区间的子字符串
     * public int length()
     * public char charAt(int n )
     * public void setCharAt(int n ,char ch)
     *
     * 总结：
     * 增：append(xxx)
     * 删：delete(int start,int end)
     * 改：replace(int start, int end, String str) / setCharAt(int n ,char ch)
     * 查：charAt(int n )
     * 插：insert(int offset, xxx)
     * 长度：length()
     * 遍历：for() + charAt() / toString()
     */
    @Test
    public void t2(){
        StringBuffer sb = new StringBuffer("abc");
        sb.append(1);
        sb.append('a');
        //abc1a
        System.out.println(sb);
        //sb.delete(3,4);
        //abca
        //System.out.println(sb);
        //sb.replace(2,4,"hello");
        //abhelloa
        //System.out.println(sb);
        //sb.insert(2,false);
        //abfalsec1a
        //System.out.println(sb);
        //10
        //System.out.println(sb.length());
        //sb.reverse();
        //a1cba
        //System.out.println(sb);
        String s1 = sb.substring(1, 3);
        //bc
        System.out.println(s1);
    }

    /**
     * 对比String、StringBuffer、StringBuilder三者的效率
     * 从高到低排列:StringBuilder > StringBuffer > String
     */
    @Test
    public void t3(){

        long startTime = 0L;
        long endTime = 0L;
        String text = "";
        StringBuffer buffer = new StringBuffer("");
        StringBuilder builder = new StringBuilder("");
        //开始对比
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            buffer.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer的执行时间：" + (endTime - startTime));
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            builder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder的执行时间：" + (endTime - startTime));
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            text = text + i; }
        endTime = System.currentTimeMillis();
        System.out.println("String的执行时间：" + (endTime - startTime));

    }
}
