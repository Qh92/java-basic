package org.qinh.edu.annotationtest;

public class RunTest {
    public static void main(String[] args) throws ClassNotFoundException {
        //System.out.println(Class.forName("cn.qh.edu.annotationtest.MyTest"));

        ProcessorTest.process("cn.qh.edu.annotationtest.MyTest");
        /**
         * 运行结果
         * 方法public static void cn.qh.edu.annotationtest.MyTest.m7()运行失败，异常:java.lang.RuntimeException: 程序业务出现异常！
         * 方法public static void cn.qh.edu.annotationtest.MyTest.m3()运行失败，异常:java.lang.IllegalArgumentException: 参数出错了！
         * 共运行了：4个方法，其中：
         * 失败了：2个，
         * 成功了：2个！
         */
    }
}
