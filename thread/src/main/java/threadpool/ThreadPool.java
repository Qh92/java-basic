package threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 创建线程的方式四：使用线程池
 *
 * 好处：
 * 1.提高响应速度（减少了创建新线程的时间）
 * 2.降低资源消耗（重复利用线程池中的线程，不需要每次都创建）
 * 3.便于线程管理
 *  corePoolSize：核心池的大小
 *  maximumPoolSize：最大线程数
 *  keepAliveTime：线程没有任务时最多保持多长时间后会终止
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-13- 22:21
 * @description
 */
class NumberThread1 implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <=100 ; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+" : "+i);
            }
        }
    }
}


class NumberThread2 implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <=100 ; i++) {
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName()+" : "+i);
            }
        }
    }
}

public class ThreadPool {
    public static void main(String[] args) {
        //1.提供指定线程数量的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service = (ThreadPoolExecutor) executorService;
        System.out.println(executorService.getClass());
        //设置线程池的属性
        service.setCorePoolSize(2);
        service.setKeepAliveTime(10, TimeUnit.SECONDS);

        //2.执行指定的线程的操作，需要提供实现Runnable接口或Callable接口实现类的对象
        int count = 20;
        Runnable[] threads = new Runnable[count];
        for (int i = 0; i < count; i++) {
            NumberThread1 r = new NumberThread1();
            Thread t = new Thread(r);
            //t.setName("线程"+i);
            threads[i] = t;
        }
        for (Runnable t : threads){
            service.execute(t);
        }
        //service.execute(new NumberThread1());//适用于Runnable
        //service.execute(new NumberThread2());//适用于Runnable
//        executorService.submit();//适用于Callable

        //3.关闭线程池
        service.shutdownNow();


    }
}
