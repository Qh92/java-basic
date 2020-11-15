package com.qinh.exception;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 一、异常的处理：抓抛模型
 *
 * 过程一："抛"：程序在正常执行的过程中，一旦出现异常，就会在异常代码处生成一个对应异常类的对象
 *              并将此对象抛出。
 *              一旦抛出对象以后，其后的代码就不再执行。
 *
 *         关于异常对象的产生：1.系统自动生成的异常对象
 *                            2.手动的生成一个异常对象，并抛出(throw)
 *
 * 过程二："抓"：可以理解为异常的处理方式：1.try-catch-finally 2.throws
 *
 * 二、try-catch-finally的使用
 *
 * try{
 * //可能出现异常的代码
 * }catch(){
 * //处理异常的方式1
 * }catch(){
 * //处理异常的方式2
 * }
 * .。。
 * finally{
 * //一定会执行的代码
 * }
 *
 * 说明：
 * 1.finally是可选的
 * 2.使用try将可能出现异常代码包装起来，在执行过程中，一旦出现异常，就会生成一个对应异常的对象
 *   根据此对象的类型，去catch中进行匹配
 * 3.一旦try中的异常对象匹配到某一个catch时，就进入catch中进行异常的处理，
 *   一旦处理完成,就跳出当前try-catch结构(在没有写finally的情况)，继续执行其后的代码
 * 4.catch中的异常类型如果没有子父类关系，则谁声明在上，谁声明在下无所谓
 *   catch中的异常类型如果满足子父类关系，则要求子类一定声明在父类的上面。否则报错。
 *
 * 5.常用的异常对象处理的方式：1.String getMessage() 2.printStackTrace()
 * 6.在try结构中声明的变量，在出了try结构以后，就不能再被调用
 * 7.try-catch-finally结构可以嵌套
 *
 * 体会1:在使用try-catch-finally处理编译时异常，使得程序在编译时就不会报错，但是运行时仍可能报错
 *       相当于我们使用try-catch-finally将一个编译时可能出现的异常，延迟到运行时出现
 *
 * 体会2:开发中，由于运行时异常比较常见，所以我们通常就不针对运行时异常编写try-catch-finally了
 *       针对于编译时异常，我们一定要考虑异常的处理。
 * @author Qh
 * @version 1.0
 * @date 2020-11-14-23:49
 */
public class ExceptionTest1 {

    //编译时异常
    @Test
    public void test2() {
        try {
            File file = new File("hello.txt");
            FileInputStream fileInputStream = new FileInputStream(file);//不处理异常时，会有编译异常

            int read = fileInputStream.read();//不处理异常时，会有编译异常
            while (read != -1){
                System.out.print((char) read);
                read = fileInputStream.read();//不处理异常时，会有编译异常
            }
            fileInputStream.close();//不处理异常时，会有编译异常
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Test
    public void test1(){
        String str = "abc";
        try{
            int i = Integer.parseInt(str);
            System.out.println("hello-------------1");
        }/*catch (Exception e){
            System.out.println(e);
        }*/ catch (NullPointerException e){
            System.out.println("出现空指针异常了，不要着急...........");
        } catch (NumberFormatException e){
            // String getMessage()
            System.out.println(e.getMessage());
            //printStackTrace()
            e.printStackTrace();
            System.out.println("出现数值转换异常了，不要着急..........");
        }
        //System.out.println(i);
        System.out.println("hello---------------------2");

    }
}
