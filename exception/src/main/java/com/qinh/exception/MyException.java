package com.qinh.exception;

/**
 * 如何自定义异常类?
 * 1.继承于现有的异常结构：RuntimeException、Exception
 * 2.提供全局常量：serialVersionUID
 * 3.提供重载的构造器
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-15-22:28
 */
public class MyException extends RuntimeException{

    private static final long serialVersionUID = 1246597581234006613L;

    public MyException(){

    }

    public MyException(String msg){
        super(msg);
    }
}
