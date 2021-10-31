package senior.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 线程中断测试
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-29 20:25
 */
public class ThreadInterruptDemo {

    public static void main(String[] args) {

//        Thread1 thread1 = new Thread1();
//        thread1.start();
        new Thread2().start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //thread1.flag = false;

    }


    static class Thread1 extends Thread{
        public volatile boolean flag = true;

        @Override
        public void run() {
            System.out.println("线程运行");
            while (flag){
                System.out.println("运行");
            }
            System.out.println("线程终止");
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            System.out.println("线程运行");
            while (!isInterrupted()){
                try {
                    TimeUnit.SECONDS.sleep(5);
                    interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            System.out.println("线程终止");
        }
    }
}


