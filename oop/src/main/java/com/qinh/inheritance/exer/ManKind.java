package com.qinh.inheritance.exer;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-28-15:48
 */
public class ManKind {

    private int sex;
    private int salary;

    public ManKind() {
    }

    public ManKind(int sex, int salary) {
        this.sex = sex;
        this.salary = salary;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void manOrWoman(){
        if (sex == 1){
            System.out.println("man");
        }else if (sex == 0){
            System.out.println("woman");
        }
    }

    public void employeed(){
        String jobInfo = (salary == 0) ? "no job" : "job";
        System.out.println(jobInfo);
    }
}
