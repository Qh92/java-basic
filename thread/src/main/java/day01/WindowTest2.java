package day01;

/**
 * 例子：创建三个窗口卖票，总票数为100张 使用继承Thread类的方法创建
 * 存在线程的安全问题，待解决
 *
 * 使用同步代码块的方式来解决继承Thread类的方式的安全问题
 * 说明：在继承Thread类创建多线程的方式中，慎用this充当同步监视器。可以考虑使用当前类来充当同步监视器
 *
 *
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-08- 0:21
 * @description
 */

class Window2 extends Thread{
    private static int ticket = 100;

    private static Object object = new Object();
    @Override
    public void run() {
        while (true){
            synchronized(Window2.class){ //Window2.class 类也是对象 此时唯一 类只会加载一次，意味着只有一个
            //synchronized(object){ //此时this 表示的对象不唯一  synchronized(this) 有问题
                if(ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName()+" 卖票，票号为："+ticket);
                    ticket--;
                }else{
                    break;
                }
            }

        }
    }
}

public class WindowTest2 {
    public static void main(String[] args) {
        Window2 window1 = new Window2();
        Window2 window2 = new Window2();
        Window2 window3 = new Window2();

        window1.setName("窗口1");
        window2.setName("窗口2");
        window3.setName("窗口3");

        window1.start();
        window2.start();
        window3.start();
    }
}
