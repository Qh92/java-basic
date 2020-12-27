package java2;

/**
 * 线程通信的例子：使用两个线程打印1-100，线程1与线程2交替打印
 * 涉及到的三个方法：
 * wait():一旦执行此方法，当前线程就进入阻塞状态，并释放同步监视器
 * notify():一旦执行此方法，就会唤醒被wait的一个线程，如果有多个线程wait,就唤醒优先级高的那个
 * notifyAll():一旦执行此方法，就会唤醒所有被wait的线程
 *
 * 说明：
 * 1.wait()，notify(),notifyAll() 三个方法必须使用在同步代码块或同步方法中
 * 2.wait()，notify(),notifyAll() 三个方法的调用者必须是同步代码块或同步方法中的同步监视器
 *   否则报错：java.lang.IllegalMonitorStateException
 * 3.wait()，notify(),notifyAll() 三个方法是定义在java.lang.Object类中
 *
 * 面试题：sleep()和wait()方法的异同？
 * 1.相同点：一旦执行方法都可以使得当前线程进入阻塞状态。
 * 2.不同点：1）两个方法声明的位置不同：Thread类中声明sleep(),Object类中声明wait()
 *          2）调用的要求不同：sleep()可以在任何需要的场景下调用，wait()必须使用在同步代码块或同步方法中
 *          3）关于是否释放同步监视器：如果两个方法都使用在同步代码块或同步方法中，sleep()不会释放锁，wait()会释放锁
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-11- 23:57
 * @description
 */

class Number implements Runnable{
    private int number = 1;
    private Object object = new Object();
    @Override
    public void run() {
        while (true){
            synchronized (object) {
                //唤醒一个线程
                object.notify();
                if(number <= 100){
                    try {
                        Thread.sleep(10);//不会释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" : "+number);
                    number++;
                    try {
                        //使得调用如下wait()方法的线程进入阻塞状态，wait会释放锁
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    break;
                }
            }
        }
    }
}

public class CommunicationTest {
    public static void main(String[] args) {
        Number number = new Number();
        Number number2 = new Number();

        Thread thread1 = new Thread(number);
        Thread thread2 = new Thread(number);
        Thread thread3 = new Thread(number2);

        thread1.setName("线程1");
        thread2.setName("线程2");
        thread3.setName("线程3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
