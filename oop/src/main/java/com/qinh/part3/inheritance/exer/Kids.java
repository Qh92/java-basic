package com.qinh.part3.inheritance.exer;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-28-15:52
 */
public class Kids  extends ManKind{
    private int yearsOld;

    public Kids() {
    }

    public Kids(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }


    public void printAge(){
        System.out.println("I am " + yearsOld + " years old.");
    }
}
