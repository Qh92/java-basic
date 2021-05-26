package day01.exer;

/**
 * @author Qh
 * @version 1.0
 * @date 2020-11-04- 23:44
 * @description 练习：创建两个线程，其中一个线程遍历100以内的偶数，另一个线程遍历100以内的奇数
 */
public class ThreadDemo {
    public static void main(String[] args) {
        //方式一
//        MyThread1 myThread1 = new MyThread1();
//        MyThread2 myThread2 = new MyThread2();
//
//        myThread1.start();
//        myThread2.start();

        //方式二 创建Thread类的匿名子类
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if(i % 2 == 0){
                        System.out.println("线程名称"+Thread.currentThread().getName()+"偶数："+i);
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if(i % 2 != 0){
                        System.out.println("线程名称"+Thread.currentThread().getName()+"奇数："+i);
                    }
                }
            }
        }.start();



    }
}

class MyThread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println("线程名称"+Thread.currentThread().getName()+"偶数："+i);
            }
        }
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 != 0){
                System.out.println("线程名称"+Thread.currentThread().getName()+"奇数："+i);
            }
        }
    }
}
