package senior.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 关键字synchronized与notify()/notifyAll()方法相结合可以实现等待/通知模式，
 * 类ReentrantLock也可以实现同样的功能，但需要借助于Condition对象。
 * 在使用notify()/notifyAll()方法进行通知时，被通知的线程却是由JVM随机选择的。
 * 但使用ReentrantLock结合Condition类是可以实现“选择性通知”，这个功能是非常重要的，而且在Condition类中是默认提供的。
 * synchronized就相当于整个Lock对象中只有一个单一的Condition对象，所有的线程都注册在它一个对象的身上。
 * 线程开始notifyAll()时，需要通知所有的waiting线程，没有选择权，会出现相当大的效率问题。
 *
 * @author: Qh
 * @version: 1.0
 * @date: 2021/3/25 15:19
 */
public class ConditionTest {

    /**
     * 非公平锁，即线程之间获取锁的方式是抢占的，先启动的线程不一定先获得锁，这个方式可能造成某些线程一直拿不到锁，结果就是不公平的。
     * 还存在一种叫公平锁，即表示线程获取锁的顺序是按照线程加锁的顺序来分配的，即先来先得的FIFO模式
     * 从源码可以看出，默认的无参构造函数创建的是非公平锁，要想创建公平锁可以通过`new ReentrantLock(true)`方式创建
     *
     */
    private Lock lock = new ReentrantLock(true);//可重入锁
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();

    public void awaitA() {
        try{
            lock.lock();
            System.out.println("begin awaitA() 时间为 " + System.currentTimeMillis()
                    + " ThreadName=" + Thread.currentThread().getName());
            conditionA.await();
            System.out.println("end awaitA() 时间为 " + System.currentTimeMillis()
                    + " ThreadName=" + Thread.currentThread().getName());
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void awaitB() {
        try{
            lock.lock();
            System.out.println("begin awaitB() 时间为 " + System.currentTimeMillis()
                    + " ThreadName=" + Thread.currentThread().getName());
            conditionB.await();
            System.out.println("end awaitB() 时间为 " + System.currentTimeMillis()
                    + " ThreadName=" + Thread.currentThread().getName());
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll_A() {
        try{
            lock.lock();
            System.out.println("signalAll_A 时间为 " + System.currentTimeMillis()
                    + " ThreadName=" + Thread.currentThread().getName());
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll_B() {
        try{
            lock.lock();
            System.out.println("signalAll_B 时间为 " + System.currentTimeMillis()
                    + " ThreadName=" + Thread.currentThread().getName());
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }
    
    public static void main(String[] args){
        try{
            ConditionTest service = new ConditionTest();
            ThreadA ta = new ThreadA(service);
            ta.setName("A");
            ta.start();

            Thread.sleep(1000);

            ThreadC tc = new ThreadC(service);
            tc.setName("C");
            tc.start();

            ThreadB tb = new ThreadB(service);
            tb.setName("B");
            tb.start();

            Thread.sleep(3000);
            service.signalAll_A();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        /*
        begin awaitA() 时间为 1616658466740 ThreadName=A
        begin awaitB() 时间为 1616658466742 ThreadName=B
        signalAll_A 时间为 1616658469742 ThreadName=main
        end awaitA() 时间为 1616658469743 ThreadName=A
         */
    }
}

class ThreadA extends Thread {

    private ConditionTest thread;

    public ThreadA(ConditionTest thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        thread.awaitA();
    }
}

class ThreadB extends Thread {

    private ConditionTest thread;

    public ThreadB(ConditionTest thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        thread.awaitB();
    }
}

class ThreadC extends Thread {

    private ConditionTest thread;

    public ThreadC(ConditionTest thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        thread.awaitA();
    }
}
