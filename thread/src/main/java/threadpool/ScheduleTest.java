package threadpool;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * scheduleAtFixedRate 和 scheduleWithFixedDelay 这两个方法容易混淆，下面我们通过一个示例来说明一下这两个方法的区别
 * 每次打印出来 current timestamp 的时间间隔大约等于 1000 毫秒，所以可以断定 `scheduleAtFixedRate` 是以恒定的速率来执行任务的
 * 两个 current timestamp 之间的间隔大约等于 1000(固定时间) + delay(time spend) 的总和，由此可以确定 `scheduleWithFixedDelay` 是以固定时延来执行的
 *
 * @Author Qh
 * @Date 2021/4/9 11:28
 * @Version 1.0
 */
public class ScheduleTest {
    public static void main(String[] args) {
        Runnable command = () -> {
            long startTime = System.currentTimeMillis();
            System.out.println("current timestamp = " + startTime);
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("time spend = " + (System.currentTimeMillis() - startTime));
        };

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        //scheduledExecutorService.scheduleAtFixedRate(command,100,1000,TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(command,10,1000,TimeUnit.MILLISECONDS);
    }

}
