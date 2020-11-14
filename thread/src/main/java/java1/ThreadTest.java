package java1;

/**
 * 演示线程的死锁问题
 * 1.死锁的理解：不同的线程分别占用对方需要同步资源不放弃，都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
 * 2.说明：
 * 1）出现死锁后，不会出现异常，不会出现提示，只是所有的线程都处于阻塞状态，无法继续
 * 2）使用同步时要避免出现死锁。
 *
 * 解决办法
 * 1.专门的算法、原则
 * 2.尽量减少同步资源的定义
 * 3.尽量避免嵌套同步
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-09- 0:08
 * @description
 */
public class ThreadTest {
    public static void main(String[] args) {
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        new Thread(){
            @Override
            public void run() {
                synchronized(sb1){
                    sb1.append("a");
                    sb2.append("1");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (sb2){
                        sb1.append("b");
                        sb2.append("2");

                        System.out.println(sb1);
                        System.out.println(sb2);
                    }
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(sb2){
                    sb1.append("c");
                    sb2.append("3");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (sb1){
                        sb1.append("d");
                        sb2.append("4");

                        System.out.println(sb1);
                        System.out.println(sb2);
                    }
                }
            }
        }).start();
    }
}
