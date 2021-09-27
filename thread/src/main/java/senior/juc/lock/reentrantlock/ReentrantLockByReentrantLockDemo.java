package senior.juc.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁（也就是递归锁）
 *
 * 指的是同一个线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 * 在同一线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
 *
 * 也就是说，线程可以进入任何一个它已经拥有的锁所有同步着的代码块。
 *
 * @author Qh
 * @version 1.0
 * @date 2021-09-27-22:40
 */
public class ReentrantLockByReentrantLockDemo {
    public static void main(String[] args) {
        Phone2 phone2 = new Phone2();
        new Thread(() -> {
            try {
                phone2.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                phone2.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }

}

class Phone2 implements Runnable {
    //Reentrant TEST
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        //锁两次，释放两次
        lock.lock();
        lock.lock();
        //锁两次，释放一次，会造成死锁
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "get()");
            set();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "set()");
        } finally {
            lock.unlock();
        }
    }
}
