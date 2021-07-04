package senior.juc;

import java.util.concurrent.CompletableFuture;

/**
 * 异步回调
 * 同步：调用方一直等着结果，最终也是调用方去拿这个结果
 * 异步：调用方不需要一直等着结果，被调用方采用回调的方式，将结果告知调用方
 *
 * @author Qh
 * @version 1.0
 * @date 2021-07-03-15:46
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        //同步，异步，异步回调

        //异步
        //没有返回值
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t completableFuture1");
        });
        completableFuture1.get();

        //异步回调
        //有返回值
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t completableFuture2");
            //double i = 10.0/0.0;
            int i = 10 / 0;
            return 1024;
        });

        //正确值 1024 异常 444
        Integer result = completableFuture2.whenComplete((t, u) -> {
            System.out.println("-------t=" + t);
            System.out.println("-------u=" + u);
        }).exceptionally(f -> {
            System.out.println("-----exception:" + f.getMessage());
            return 444;
        }).get();


    }
}
