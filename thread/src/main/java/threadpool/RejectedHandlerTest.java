package threadpool;

import java.util.concurrent.*;

/**
 * 线程池拒绝策略测试
 * 如果核心线程数、阻塞队列、最大线程数都满了的话，就会执行线程池的拒绝策略，一共有4种方式：
 *
 *   (1)直接丢弃任务 DiscardPolicy
 *
 *   (2)丢弃任务并抛出异常 AbortPolicy
 *
 *   (3) 将阻塞队列的头节点丢弃，然后尝试将任务放入队列中 DiscardOldestPolicy
 *
 *   (4) 将任务交由主线程即调用者来执行该任务 CallerRunsPolicy
 *
 * @Author Qh
 * @Date 2021/5/28 16:00
 * @Version 1.0
 */
public class RejectedHandlerTest {

    public static void main(String[] args){
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 15, 100,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        int count = 30;
        Runnable[] threads = new Runnable[count];
        for (int i = 0; i < count; i++) {
            threads[i] = () -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName() + " : " + j);
                }
            };
        }
        for (Runnable t : threads){
            poolExecutor.execute(t);
        }
        poolExecutor.shutdown();
        poolExecutor.execute(threads[20]);
    }
}

class RejectedHandlerTest2 {

    public static void main(String[] args){
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 15, 100,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        int count = 25;
        Runnable[] threads = new Runnable[count];
        for (int i = 0; i < count; i++) {
            threads[i] = () -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " : " + j);
                }
            };
        }
        for (Runnable t : threads){
            poolExecutor.execute(t);
        }
        //poolExecutor.shutdown();
        //poolExecutor.execute(threads[25]);
    }
}
