package com.qinh.project3.domain;

/**
 * 抽象工厂
 *
 * @author Qh
 * @version 1.0
 * @date 2021-04-06-19:26
 */
public interface AbstractFactory {
    Equipment product(String nameOrModel,String typeOrPrice);
}
