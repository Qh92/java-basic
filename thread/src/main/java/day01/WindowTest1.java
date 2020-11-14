package day01;

/**
 * 例子：创建三个窗口卖票，总票数为100张 使用实现Runnable接口的方式
 *
 * 1.问题：卖票过程中，出现了重票、错票 -->出现线程安全问题
 * 2.问题出现的原因：当某个线程操作车票过程中，尚未操作完成时，其它线程参与进来，也操作车票
 * 3.如何解决：当一个线程在操作共享数据时（ticket）的时候，其它线程不能参与进来，直到当前线程操作完成共享数据时（ticket）时
 *            其它线程才可以开始操作共享数据，这种情况即使当前线程出现了阻塞，也不能被改变
 * 4.在java中，我们通过同步机制，来解决线程安全问题
 * 方式一：同步代码块 eg.WindowTest1,WindowTest2
 * synchronized(同步监视器){
 *     //需要被同步的代码
 * }
 * 说明：1.操作共享数据的代码，即为需要被同步的代码 -->不能包含代码多了（1.达不到目的，2.效率低等等），也不能包含代码少了
 *       2.共享数据；多个线程共同操作的变量。eg.ticket就是共享数据
 *       3.同步监视器，俗称：锁。任何一个类的对象，都可以充当锁
 *         要求：多个线程必须要共用同一把锁。
 *         补充：在实现Runnable接口创建多线程的方式中，可以考虑使用this来充当同步监视器
 *
 * 方式二:同步方法 eg.WindowTest3,WindowTest4
 * 如果操作的共享数据的代码完整的声明在一个方法中，我们不妨将此方法声明同步的
 * 关于同步方法的总结：
 * 1.同步方法仍然涉及到同步监视器，只是不需要我们显示的声明
 * 2.非静态的同步方法，同步监视器默认this
 *   静态的同步方法，同步监视器默认为当前类本身
 *
 * 5.同步的方式，解决了线程的安全问题。-->好处
 *   操作同步代码时，只能有一个线程参与，其它线程等待。相当于是一个单线程的过程，效率低。 -->坏处
 *
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-08- 0:50
 * @description
 */

class Window1 implements Runnable{

    private int ticket = 100;
    Object object = new Object();
    @Override
    public void run() {
        //Object object = new Object();//多个线程必须要用同一把锁
        while (true){
            synchronized(this){  //this：唯一的Window对象  方式二：synchronized(object)
                if(ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" 卖票，票号为："+ticket);
                    ticket--;
                }else{
                    break;
                }
            }

        }
    }
}

public class WindowTest1 {
    public static void main(String[] args) {
        Window1 window1 = new Window1();

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
