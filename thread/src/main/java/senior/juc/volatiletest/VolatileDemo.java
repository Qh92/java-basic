package senior.juc.volatiletest;

import java.util.concurrent.TimeUnit;

/**
 * 分析：
 * 程序中，两个线程：main 线程和 AAA 线程，同时对 myData 数据进行操作
 * 由于 AAA 线程先睡眠了 3s ，所以 main 线程先拿到了 myData.number 的值，将该值拷贝回自己线程的工作内存，此时 myData.number = 0
 * AAA 线程 3s 后醒来，将 myData.number 拷贝回自己线程的工作内存，修改为 60 后，写回主内存
 * 但 AAA 线程将 myData.number 的值写回主内存后，并不会去通知 main 线程，所以 main 线程一直拿着自己线程的工作内存中的 myData.number = 0 ，搁那儿 while 循环呢
 *
 * @author Qh
 * @version 1.0
 * @date 2021/9/10 16:45
 */
public class VolatileDemo {

    public static void main(String[] args){
        volatileVisibilityDemo();
    }

    /**
     * 验证volatile的可见性
     *         1.1 加入int number=0，number变量之前根本没有添加volatile关键字修饰，没有可见性
     *         1.2 添加了volatile，可以解决可见性问题
     */
    private static void volatileVisibilityDemo() {
        System.out.println("可见性测试");
        MyData myData = new MyData();//资源类

        //启动一个线程操作共享数据
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.setTo60();
                System.out.println(Thread.currentThread().getName() + "\t update number value: " + myData.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        while (myData.number == 0) {
            //main线程持有共享数据的拷贝，一直为0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over. main get number value: " + myData.number);
    }
}

/**
 * 请解释一下对象的创建过程（半初始化）
 *
 * 检查类有没有被加载，如果没有则先加载类（加载->链接（验证->准备->解析）->初始化）
 * 创建对象，先会在堆空间中开辟一块空间，成员变量赋默认值，再调用类的构造器赋实际值
 */
class MyData {

    /** 共享变量 */
    int number = 0;

    public void setTo60() {
        this.number = 60;
    }

}
