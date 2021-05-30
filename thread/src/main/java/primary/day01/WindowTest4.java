package primary.day01;

/**
 * 使用同步方法处理继承Thread类的方式中的线程安全问题
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-08- 23:32
 * @description
 */
class Window4 extends Thread{
    private static int ticket = 100;

    private static Object object = new Object();
    @Override
    public void run() {
        while (true){
            show();
        }
    }


    private static synchronized void show(){ //同步监视器：Window4.class
        //private synchronized void show(){ //同步监视器：window1,window2, window3 此种解决方式是错误的
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

public class WindowTest4 {
    public static void main(String[] args) {
        Window4 window1 = new Window4();
        Window4 window2 = new Window4();
        Window4 window3 = new Window4();

        window1.setName("窗口1");
        window2.setName("窗口2");
        window3.setName("窗口3");

        window1.start();
        window2.start();
        window3.start();
    }
}
