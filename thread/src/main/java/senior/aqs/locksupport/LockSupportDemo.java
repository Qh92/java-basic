package senior.aqs.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport测试阻塞、唤醒代码
 *
 * @author Qh
 * @version 1.0
 * @date 2021/10/12 16:39
 */
public class LockSupportDemo {
    public static void main(String[] args){
        Thread threadA = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : 阻塞");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " : 唤醒");
        },"线程A");
        threadA.start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " : 通知");
            LockSupport.unpark(threadA);
        },"线程B").start();
    }
}
