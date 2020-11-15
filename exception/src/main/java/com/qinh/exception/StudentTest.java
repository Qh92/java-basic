package com.qinh.exception;

/**
 * @author Qh
 * @version 1.0
 * @date 2020-11-15-22:15
 */
public class StudentTest {
    public static void main(String[] args) {

        Student student = new Student();
        student.regist(-1001);
        System.out.println(student);
        /*try {
            Student student = new Student();
            student.regist(-1001);
            System.out.println(student);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }*/
    }
}


class Student{
    private int id;

    public  void regist(int id)  {
        if(id > 0){
            this.id = id;
        }else {
            //System.out.println("输入的数据非法!");
            //throw new RuntimeException("输入的数据非法！");
            //throw new Exception("输入的数据非法！");
            throw new MyException("不能输入负数！");
        }
    }
}
