package senior.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * awaitTermination 方法会阻塞，直到发出调用 shutdown 请求后所有的任务已经完成执行后才会解除
 *
 * @Author Qh
 * @Date 2021/4/9 11:01
 * @Version 1.0
 */
public class AwaitTerminationTest {
    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        //如果在调用 executorService.shutdown() 之后，所有线程完成任务，isTermination 返回 true，程序才会打印出 All Thread Done ，
        // 如果注释掉 executorService.shutdown() 或者在任务没有完成后 awaitTermination 就超时了，那么 isTermination 就会返回 false。
        executorService.shutdown();
        System.out.println("Waiting...");
        boolean isTermination = executorService.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("Waiting...Done");
        if(isTermination){
            System.out.println("All Thread Done");
        }
        System.out.println(Thread.currentThread().getName());
    }
}
