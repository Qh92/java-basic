package senior.juc.lock;

import java.util.concurrent.CountDownLatch;

/**
 * 6个同学陆续离开教室后值班同学才可以关门。
 * main主线程必须要等前面6个线程完成全部工作后，自己才能开干 
 *
 * @author Qh
 * @version 1.0
 * @date 2021/7/2 16:55
 */
public class CountDownLatchDemo {
    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "同学离开教室");
                latch.countDown();
            },String.valueOf(i)).start();
        }
        try {
            //会一直阻塞，直到数量减少至0
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "值日生离开教室");
    }
}
