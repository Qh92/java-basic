package com.qinh.part3.inheritance.exer2;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-28-23:49
 */
public class Account {
    /**
     * 账户
     */
    private int id;
    /**
     * 余额
     */
    private double balance;
    /**
     * 年利率
     */
    private double annualInterestRate;

    public Account(int id, double balance, double annualInterestRate) {
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    /**
     * 返回月利率
     *
     * @return
     */
    public double getMonthlyInterest(){
        return annualInterestRate / 12;
    }

    /**
     * 取钱
     *
     * @param amount
     */
    public void withdraw (double amount){
        if (balance >= amount){
            balance -= amount;
            return;
        }
        System.out.println("余额不足");
    }

    /**
     * 存钱
     *
     * @param amount
     */
    public void deposit (double amount){
        if (amount > 0){
            balance += amount;
        }
    }
}
