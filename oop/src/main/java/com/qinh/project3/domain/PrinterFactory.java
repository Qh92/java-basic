package com.qinh.project3.domain;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-04-06-19:35
 */
public class PrinterFactory implements AbstractFactory{
    @Override
    public Equipment product(String nameOrModel, String typeOrPrice) {
        return new Printer(nameOrModel,typeOrPrice);
    }
}
