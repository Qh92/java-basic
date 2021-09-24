package senior.juc.cas;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS导致的ABA问题,采用添加版本号或布尔类型等标识来解决ABA问题
 *
 * @author Qh
 * @version 1.0
 * @date 2021/9/24 21:48
 */
public class ABADemo {
    // 初始值为 100
    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    // 初始值为 100 ，初始版本号为 1
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {

        System.out.println("======ABA问题的产生======");
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            // 暂停1秒钟线程2，保证上面t1线程完成一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
        }, "t2").start();

        // 保证上面的操作执行完成
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println("======以下是ABA问题的解决=====");
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t第1次版本号：" + atomicStampedReference.getStamp());
            // 暂停1秒钟t3线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第2次版本号：" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第3次版本号：" + atomicStampedReference.getStamp());
        }, "t3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第1次版本号：" + stamp);
            // 暂停3秒钟t4线程，保证上面t3线程完成一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t修改成功否： " + result + "\t当前最新实际版本号：" + atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + "\t当前实际值：" + atomicStampedReference.getReference());
        }, "t4").start();
    }
}
