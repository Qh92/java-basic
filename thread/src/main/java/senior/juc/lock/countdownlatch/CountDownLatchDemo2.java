package senior.juc.lock.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-09-28-22:12
 */
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        county();
    }

    private static void county() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6); // 初始化次数为 6
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 国被灭");
                countDownLatch.countDown(); // 计数器减 1
            }, CountryEnum.list(i).getRetMsg()).start();
        }
        countDownLatch.await(); // 等待上述线程执行完成（等待计数减为 0）
        System.out.println(Thread.currentThread().getName() + "\t ******秦国一统华夏");
    }
}
