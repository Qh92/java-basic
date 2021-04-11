package threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Qh
 * @Date 2021/4/9 10:12
 * @Version 1.0
 */
public class RunnableTask {

    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(10);
        executor.execute(new RunnableTask1());
        executor.execute(new RunnableTask2());
        //shutdown方法调用后，ExecutorService 会有序关闭正在执行的任务，但是不接受新任务。如果任务已经关闭，那么这个方法不会产生任何影响。
        //((ExecutorService) executor).shutdown();
        //executor.execute(new RunnableTask3());
        //shutdownNow 会尝试停止关闭所有正在执行的任务，停止正在等待的任务，并返回正在等待执行的任务列表
        ((ExecutorService) executor).shutdownNow();
    }
}

class RunnableTask1 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "task1 begin ...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "task1 end ...");
    }
}

class RunnableTask2 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "task2 begin ...");
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "task2 end ...");
    }
}
class RunnableTask3 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "task3 begin ...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "task3 end ...");
    }
}