package com.qinh.bigxx;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * BigInteger:
 *  Integer类作为int的包装类，能存储的最大整型值为2^31-1，
 * Long类也是有限的，最大为2^63-1。
 * 如果要表示再大的整数，不管是基本数据类型还是他们的包装类都无能为力，更不用说进行运算了。
 *  java.math包的BigInteger可以表示不可变的任意精度的整数。BigInteger 提供
 * 所有 Java 的基本整数操作符的对应物，并提供 java.lang.Math 的所有相关方法。
 * 另外，BigInteger 还提供以下运算：模算术、GCD计算、质数测试、素数生成、
 * 位操作以及一些其他操作。
 *  构造器
 *    BigInteger(String val)：根据字符串构建BigInteger对象
 *
 * BigDecimal:
 *  一般的Float类和Double类可以用来做科学计算或工程计算，但在商业计算中，
 * 要求数字精度比较高，故用到java.math.BigDecimal类。
 *  BigDecimal类支持不可变的、任意精度的有符号十进制定点数。
 *  构造器
 *     public BigDecimal(double val)
 *     public BigDecimal(String val)
 *
 * @author Qh
 * @version 1.0
 * @date 2021-04-10-20:52
 */
public class BigIntegerAndBigDecimalTest {

    /**
     *  常用方法
     *  public BigInteger abs()：返回此 BigInteger 的绝对值的 BigInteger。
     *  BigInteger add(BigInteger val) ：返回其值为 (this + val) 的 BigInteger
     *  BigInteger subtract(BigInteger val) ：返回其值为 (this - val) 的 BigInteger
     *  BigInteger multiply(BigInteger val) ：返回其值为 (this * val) 的 BigInteger
     *  BigInteger divide(BigInteger val) ：返回其值为 (this / val) 的 BigInteger。整数相除只保留整数部分。
     *  BigInteger remainder(BigInteger val) ：返回其值为 (this % val) 的 BigInteger。
     *  BigInteger[] divideAndRemainder(BigInteger val)：返回包含 (this / val) 后跟(this % val) 的两个 BigInteger 的数组。
     *  BigInteger pow(int exponent) ：返回其值为 (thisexponent) 的 BigInteger。
     */
    @Test
    public void t1(){
        BigInteger bi = new BigInteger("124332411231243324112312433241123124332411231243324112312433241123");
        System.out.println(bi);
    }

    /**
     *  public BigDecimal add(BigDecimal augend)
     *  public BigDecimal subtract(BigDecimal subtrahend)
     *  public BigDecimal multiply(BigDecimal multiplicand)
     *  public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode)
     */
    @Test
    public void t2(){
        // 值要使用字符串 1/2 = 0.5 0.5/2 = 0.25 0.25/2 = 0.125 ...不能精确表达0.1
        BigDecimal decimal = new BigDecimal(2.1);
        // 2.100000000000000088817841970012523233890533447265625
        System.out.println(decimal);
        BigDecimal bd = new BigDecimal("12435.351");
        System.out.println(bd);
        BigDecimal bd2 = new BigDecimal("11");
        System.out.println(bd2);
        //System.out.println(bd.divide(bd2));
        System.out.println(bd.divide(bd2, BigDecimal.ROUND_HALF_UP));
        System.out.println(bd.divide(bd2, 35, BigDecimal.ROUND_HALF_UP));
    }
}
