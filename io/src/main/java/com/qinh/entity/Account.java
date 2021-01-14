package com.qinh.entity;

import java.io.Serializable;

/**
 * @Author Qh
 * @Date 2021/1/15 0:26
 * @Version 1.0
 */
public class Account implements Serializable {

    private static final long serialVersionUID = -8714423769678268623L;
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}
