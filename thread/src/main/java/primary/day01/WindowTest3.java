package primary.day01;

/**
 * 使用同步方法解决实现Runnable接口的线程安全问题
 *
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-08- 23:20
 * @description
 */
class Window3 implements Runnable{

    private int ticket = 100;
    Object object = new Object();
    @Override
    public void run() {
        //Object object = new Object();//多个线程必须要用同一把锁
        while (true){
            show();
        }
    }

    private synchronized void show(){//默认同步监视器：this
        if(ticket > 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" 卖票，票号为："+ticket);
            ticket--;
        }
    }
}

public class WindowTest3 {
    public static void main(String[] args) {
        Window3 window1 = new Window3();

        Thread thread1 = new Thread(window1);
        Thread thread2 = new Thread(window1);
        Thread thread3 = new Thread(window1);

        thread1.setName("窗口1");
        thread2.setName("窗口2");
        thread3.setName("窗口3");

        thread1.start();
        thread2.start();
        thread3.start();
    }


}
