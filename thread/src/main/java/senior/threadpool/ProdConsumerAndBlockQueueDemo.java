package senior.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞队列版：消费者生产者模式
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-02-23:57
 */
public class ProdConsumerAndBlockQueueDemo {
    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){

        }

        System.out.println("5秒钟到，main停止");
        myResource.stop();
    }
}

class MyResource {
    private volatile boolean FLAG = true; // 默认开启，进行生产 + 消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    private BlockingQueue<String> blockingQueue = null; // 通配、适用：传接口，不能传具体实现类

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName()); // 查看接口具体的落地实现类名称
    }

    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        while (FLAG) {
            //TimeUnit.SECONDS.sleep(5);
            data = atomicInteger.incrementAndGet() + ""; // 原子整形对象加 1
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS); // 将数据放入阻塞队列
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t生产停止");
    }

    public void myConsumer() throws Exception {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS); // 从阻塞队列中取出数据
            if (null == result || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2秒，消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费队列" + result + "成功");
        }
        System.out.println(Thread.currentThread().getName() + "\t消费停止");
    }

    public void stop() throws Exception {
        this.FLAG = false; // 停止生产与消费
    }
}
