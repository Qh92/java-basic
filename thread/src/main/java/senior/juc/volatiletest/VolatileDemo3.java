package senior.juc.volatiletest;

/**
 * 测试volatile是否支持原子性
 *
 * @author Qh
 * @version 1.0
 * @date 2021/9/10 17:06
 */
public class VolatileDemo3 {
    public static void main(String[] args){
        atomicDemo();
    }

    /**
     * 2 验证volatile不保证原子性
     *         2.1 原子性是不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者分割。
     *             需要整体完成，要么同时成功，要么同时失败。
     *
     *         2.2 volatile不可以保证原子性演示
     *
     *         2.3 如何解决原子性
     *             1）加sync
     *             2）使用我们的JUC下AtomicInteger
     */
    private static void atomicDemo() {
        System.out.println("原子性测试");
        MyData3 myData = new MyData3();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }
        /*
        需要等待上述20个线程都计算完成后，再用main线程去的最终的结果是多少？
        只要上述20个线程还有在执行的，main线程便礼让，让他们执行，直至最后只剩main线程
         */
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t int type finally number value: " + myData.number);
    }

}

class MyData3 {

    // volatile可以保证可见性，及时通知其它线程主物理内存的值已被修改
    volatile int number = 0;

    public void setTo60() {
        this.number = 60;
    }

    //此时number前面已经加了volatile，但是不保证原子性
    public void addPlusPlus() {
        number++;
    }

}
