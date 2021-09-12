package senior.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 分析CAS：就拿 JMM 模型来说
 *
 * 现在有两个线程：线程 A 和线程 B ，同时操作主内存中的变量 i
 * 线程 A 将变量 i 的副本拷贝回自己线程的工作内存，先记录变量 i 当前的值，记录为期望值
 * 线程 A 修改值后，将 i 的值写回主内存前，先判断一下当前主内存的值是否与期望值相等，相等我才写回，不相等证明别的线程（线程 B）改过了，如果强行写，将出现写覆盖
 *
 * @author Qh
 * @version 1.0
 * @date 2021-09-12-23:57
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        // 期望值与上次相同，修改成功
        System.out.println(atomicInteger.compareAndSet(5,2021) + "\t current data " + atomicInteger.get());

        // 期望值与上次不同，修改失败
        System.out.println(atomicInteger.compareAndSet(5,1024) + "\t current data " + atomicInteger.get());
    }
}
