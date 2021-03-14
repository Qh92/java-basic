package com.qinh.flowcontrol;

import java.util.Scanner;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-10-19:36
 */
public class SwitchCaseTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入2019年的month: ");
        int month = scanner.nextInt();
        System.out.println("请输入2019年的day: ");
        int day = scanner.nextInt();
        int sumDays = 0;
        switch (month){

            case 12:
                sumDays += 30;
            case 11:
                sumDays += 31;
            case 10:
                sumDays += 30;
            case 9:
                sumDays += 31;
            case 8:
                sumDays += 31;
            case 7:
                sumDays += 30;
            case 6:
                sumDays += 31;
            case 5:
                sumDays += 30;
            case 4:
                sumDays += 31;
            case 3:
                sumDays += 28;
            case 2:
                sumDays += 31;
            case 1:
                sumDays += day;
        }
    }


}
