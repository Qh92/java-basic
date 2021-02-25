package org.qinh.edu.thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程通信的应用：经典例题：生产者/消费者问题
 * 生产者（Productor）将产品交给店员（Clerk）,而消费者（Customer）从店员处取走产品，
 * 店员一次只能持有固定数量的产品（比如：20），
 * 如果生产者试图生产更多的产品，店员会叫停生产者
 * 店中有空位放产品了再通知生产者继续生产
 * 如果店中没有产品了，店员会告诉消费者等一下
 * 店中有产品了再通知消费者来取走产品
 */
public class ProductTest {
    public static void main(String[] args){
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Customer customer = new Customer(clerk);
        productor.setName("生产者");
        customer.setName("消费者");
        productor.start();
        customer.start();
    }
}

class Clerk {
    private int productNum = 0;

    public synchronized void product(){
        if(productNum < 20){
            productNum++;
            System.out.println("开始生产产品："+productNum);
            notifyAll();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void customer(){
        if(productNum > 0){
            productNum--;
            System.out.println("开始消费产品: "+productNum);
            notifyAll();
        }else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Productor extends Thread{
    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" 开始生产产品。");
        while (true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.product();
        }
    }

}

class Customer extends Thread{
    private Clerk clerk;

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" 开始消费产品。");
        while (true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.customer();
        }
    }

}
