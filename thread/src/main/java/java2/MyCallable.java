package java2;

import java.util.concurrent.*;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-13-21:41
 */
public class MyCallable implements Callable<Integer> {

    private int number;

    public MyCallable(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int x = 1; x <= number; x++) {
            sum += x;
        }
        return sum;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 创建线程池对象
        ExecutorService pool = Executors.newFixedThreadPool(2);

        // 可以执行Runnable对象或者Callable对象代表的线程
        Future<Integer> f1 = pool.submit(new MyCallable(100));
        Future<Integer> f2 = pool.submit(new MyCallable(200));

        int num = 0;
        for (int i = 0; i < 100; i++){
            num += i;
        }
        System.out.println("num : " + num);
        // V get()
        Integer i1 = f1.get();
        Integer i2 = f2.get();

        System.out.println("调用结束");

        System.out.println(i1);
        System.out.println(i2);

        // 结束
        pool.shutdown();
    }

}