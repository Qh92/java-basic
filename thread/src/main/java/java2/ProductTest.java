package java2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信的应用：经典例题：生产者/消费者问题
 * 生产者（Productor）将产品交给店员（Clerk）,而消费者（Customer）从店员处取走产品，
 * 店员一次只能持有固定数量的产品（比如：20），
 * 如果生产者试图生产更多的产品，店员会叫停生产者
 * 店中有空位放产品了再通知生产者继续生产
 * 如果店中没有产品了，店员会告诉消费者等一下
 * 店中有产品了再通知消费者来取走产品
 *
 * 分析：
 * 1.是否多线程问题？是，生产者线程，消费者线程
 * 2.是否有共享数据？是，店员（或产品）
 * 3.如何解决线程安全问题？同步机制，有三种方法
 * 4.是否涉及线程通信？是
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-13- 0:02
 * @description
 */
class Clerk {

    private int productCount = 0;
    private ReentrantLock lock = new ReentrantLock();
    //生产产品
    public synchronized void produceProduct() {
        if(productCount < 20){
            productCount++;
            System.out.println(Thread.currentThread().getName()+" : 开始生产第"+productCount+"个产品。。。");
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //消费产品
    public synchronized void consumeProduct() {
        if (productCount > 0){
            System.out.println(Thread.currentThread().getName()+" : 开始消费第"+productCount+"个产品。。。");
            productCount--;
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer extends Thread {
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" : 开始生产产品。。。。。。。。。");
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }
    }
}

class Consumer extends Thread{
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" : 开始消费产品。。。。。。。。。");
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }
}




public class ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer = new Producer(clerk);
        Consumer consumer = new Consumer(clerk);
        producer.setName("生产者");
        consumer.setName("消费者");
        producer.start();
        consumer.start();
    }
}
