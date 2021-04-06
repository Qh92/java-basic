package com.qinh.project3.domain;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-04-06-19:32
 */
public class NoteBookFactory implements AbstractFactory{
    @Override
    public Equipment product(String nameOrModel, String typeOrPrice) {
        double price = Double.parseDouble(typeOrPrice);
        return new NoteBook(nameOrModel,price);
    }
}
