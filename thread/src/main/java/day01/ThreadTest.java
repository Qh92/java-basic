package day01;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description 多线程的创建，方式一：继承于Thread类
 * 1.创建一个继承于Thread类的子类
 * 2.重写Thread类的run()方法
 * 3.创建Thread类的子类对象
 * 4.通过此对象调用start()
 * <p>
 * 例子：遍历100以内的所有偶数
 * @author Qh
 * @version 1.0
 * @date 2020-11-04- 22:58
 */

//1.创建一个继承于Thread类的子类
class MyThread extends Thread{
    protected static final Logger log = LoggerFactory.getLogger(MyThread.class);
    //2.重写Thread类的run()方法
    @Override
    public void run() {
        log.info("11");
        for(int i = 0 ;i<100;i++){
            if(i % 2 == 0){
                System.out.println("线程名称："+Thread.currentThread().getName()+" 输出值："+i);
            }
        }
    }
}

public class ThreadTest {


    public static void main(String[] args) {

        //3.创建Thread类的子类对象
        MyThread t1 = new MyThread();//主线程做的
        //通过此对象调用start():1.启动当前线程 2.调用当前线程的run()
        t1.start();//主线程做的
        /*
        问题1：不能通过直接调用run()的方式启动线程
        t1.run();
        问题2：再启动一个线程，遍历100以内的偶数，不可以还让已经start()的线程去执行，会报IllegalThreadStateException异常
        t1.start();
         */
        //我们需要重新创建一个线程对象
        MyThread t2 = new MyThread();
        t2.start();

        //如下操作仍然是在main线程中执行的
        for(int i = 0 ;i<100;i++){
            if(i % 2 == 0){
                System.out.println("线程名称："+Thread.currentThread().getName()+" 输出值："+i+"  **********************main**********************");
            }
        }
    }
}
