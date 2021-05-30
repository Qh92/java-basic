package primary.java1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式三：Lock锁 -->JDK5.0新增
 * 1.面试题：synchronized与lock的异同
 * 相同：二者都可以解决线程安全问题
 * 不同：synchronized机制在执行完相应的代码逻辑后，自动的释放同步监视器
 *       Lock需要手动的启用同步（senior.juc.lock()）,同时结束同步也需要手动的实现（unlock()）
 * 2.优先使用顺序：
 * Lock -> 同步代码块（已经进入了方法体，分配了相应的资源） -> 同步方法（在方法体之外）
 *
 * 2.面试题：如何解决线程安全问题？有几种方式？
 * @author Qh
 * @version 1.0
 * @date 2020-11-09- 0:38
 * @description
 */
class Window implements Runnable{

    private int ticket = 100;
    //1.实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true){
            try{
                //2.调用锁定的lock方法
                lock.lock();
                if (ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" : 售票，票号为："+ticket);
                    ticket--;
                }else {
                    break;
                }
            }finally {
                //3.调用解锁的方法，unlock()
                lock.unlock();
            }

        }
    }
}

public class LockTest {
    public static void main(String[] args) {
        Window window = new Window();

        Thread thread1 = new Thread(window);
        Thread thread2 = new Thread(window);
        Thread thread3 = new Thread(window);

        thread1.setName("窗口1");
        thread2.setName("窗口2");
        thread3.setName("窗口3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
