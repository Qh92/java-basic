package senior.juc.volatiletest;

import java.util.concurrent.TimeUnit;

/**
 * 分析：由于有volatile 关键字的存在，当 AAA 线程修改了 myData.number 的值后，main 线程会受到通知，从而刷新自己线程工作内存中的值
 *
 * @author Qh
 * @version 1.0
 * @date 2021/9/10 17:01
 */
public class VolatileDemo2 {
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
        MyData2 myData = new MyData2();//资源类

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
class MyData2 {

    /**
     * volatile可以保证可见性，及时通知其它线程主物理内存的值已被修改
     */
    volatile int number = 0;

    public void setTo60() {
        this.number = 60;
    }

}
