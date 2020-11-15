package com.qinh.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 异常处理的方式二：throws + 异常类型
 *
 * 1."throws + 异常类型"写在方法的声明处。指明此方法执行时，可能会抛出异常类型
 *    一旦当方法体执行时，出现异常，仍会在异常代码处生成一个异常类的对象，此对象满足throws后面的异常类型时
 *    就会被抛出。异常代码后续的代码，就不再执行！
 *
 * 2.体会:try-catch-finally,真正的将异常给处理掉
 *        throws的方式只是将异常抛给了方法的调用者，并没有真正将异常处理掉
 *
 * 3.开发中如何选择使用try-catch-finally还是使用throws?
 *   3.1 如果父类中被重写的方法没有throws方式处理异常，则子类重写的方法也不能使用throws
 *       意味着如果子类重写的方法中有异常，必须使用try-catch-finally方式处理
 *   3.2 执行的方法a中，先后又调用了另外的几个方法，这几个方法是递进关系执行的。我们建议这几个方法使用throws的方式进行处理。
 *       而执行的方法a可以考虑使用try-catch-finally方式进行处理。
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-15-21:38
 */
public class ExceptionTest2 {

    public static void main(String[] args) {
        try {
            method2();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //method3();
    }

    public static void method3(){
        try {
            method2();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void method2() throws IOException {
        method1();
    }

    public static void method1() throws FileNotFoundException,IOException {
        File file = new File("hello1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);//不处理异常时，会有编译异常

        int read = fileInputStream.read();//不处理异常时，会有编译异常
        while (read != -1){
            System.out.print((char) read);
            read = fileInputStream.read();//不处理异常时，会有编译异常
        }
        fileInputStream.close();//不处理异常时，会有编译异常

        System.out.println("最后执行代码");
    }

}
