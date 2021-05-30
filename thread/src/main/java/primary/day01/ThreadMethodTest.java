package primary.day01;

/**
 * 测试Thread中的常用方法
 * 1.start()：启动当前线程，调用当前线程的run()
 * 2.run()：通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中
 * 3.currentThread()：静态方法，返回执行当前代码的线程
 * 4.getName():获取当前线程的名字
 * 5.setName():设置当前线程的名字
 * 6.yield():释放当前cpu的执行权
 * 7.join():在线程a中调用线程b的join(),此时线程a就进入阻塞状态，直到线程b完全执行完以后，线程a才结束阻塞状态
 * 8.stop():已过时，当执行此方法时，强制结束当前线程
 * 9.sleep(long millis)：让当前线程“睡眠”指定的毫秒，在指定的毫秒时间内，当前线程是阻塞状态的
 * 10.isAlive():判断当前线程是否存活
 *
 * 线程的优先级：
 * 1.
 * MIN_PRIORITY = 1
 * NORM_PRIORITY = 5  -->默认的优先级
 * MAX_PRIORITY = 10
 * 2.如何获取和设置当前线程的优先级
 * getPriority():获取线程的优先级
 * setPriority():设置线程的优先级
 * 说明：高线程优先级的线程要抢占低优先级线程cpu的执行权，但是只是从概率上讲，高优先级的线程高概率的情况下被执行，并不意味着只有当高优先级的线程执行完以后，低优先级的线程才执行
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-07- 23:27
 * @description
 */
class HelloThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" : "+Thread.currentThread().getPriority()+" : "+i);
            }
            if(i % 20 == 0){
                yield();
            }
        }
    }
}

public class ThreadMethodTest {
    public static void main(String[] args) {
        HelloThread thread = new HelloThread();
        thread.setName("线程1");
        //设置分线程的优先级
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();

        //主线程
        Thread.currentThread().setName("主线程1");
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0 ){
                System.out.println(Thread.currentThread().getName()+" : "+ +Thread.currentThread().getPriority()+" : "+i);
            }
            if ( i == 20){
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(thread.isAlive());

    }
}
