package senior.juc.lock.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁的好处：循环比较获取直到成功为止，没有类似wait的阻塞。
 *
 * 通过CAS操作完成自旋锁：
 * A线程先进来调用myLock方法自已持有锁5秒钟
 * B随后进来后发现当前有线程持有锁，不是null，
 * 所以只能通过自旋等待，直至A释放锁后B随后抢到
 *
 * @author Qh
 * @version 1.0
 * @date 2021-09-27-22:55
 */
public class SpinLockDemo {
    // 泛型为 Thread
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        // 获取当前线程
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in ");
        /*
         自旋：
            期望值为 null 表示当前没有线程
            新值为 thread ，即 Thread.currentThread()
          */
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void myUnLock() {
        // 获取当前线程
        Thread thread = Thread.currentThread();
        // 解锁当前线程
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoked myUnLock()");
    }

    public static void main(String[] args) {
        // 原子引用线程
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock(); // 加锁
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock(); // 解锁
        }, "AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockDemo.myLock(); // 加锁
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock(); // 解锁
        }, "BB").start();
    }
}
