package senior.juc.lock.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同时读一个资源类没有问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 *
 * 但是写资源只能有一个线程。
 *
 * 写操作：原子+独占，整个过程必须是一个完整的统一体，中间不许被分割，被打断。
 *
 * 小总结：
 * 读-读能共存
 * 读-写不能共存
 * 写-写不能共存
 *
 * 写操作：原子性+独占，整个过程必须是一个完整的统一体，中间不许被分隔，被打断
 *
 * @author Qh
 * @version 1.0
 * @date 2021-09-27-23:04
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
}

class MyCache {
    // 凡缓存，一定要用 volatile 修饰，保证内存可见性
    private volatile Map<String, Object> map = new HashMap<>();
    // ReentrantReadWriteLock：读写锁
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        reentrantReadWriteLock.writeLock().lock(); // 加写锁
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300); // 模拟网络传输，暂停线程一会儿
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.writeLock().unlock(); // 释放写锁
        }
    }

    public void get(String key) {
        reentrantReadWriteLock.readLock().lock(); // 加读锁
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取：" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300); // 模拟网络传输，暂停线程一会儿
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.readLock().unlock(); // 释放读锁
        }
    }
}

