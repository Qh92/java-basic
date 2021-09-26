package senior.juc.concurrentcollection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ArrayList是线程不安全，请编码一个不安全的案例并给出解决方案
 *
 * 1 故障现象
 *   java.util.ConcurrentModificationException
 * 2 导致原因
 *   并发争抢修改导致，参考我们的花名册签名情况。
 *   一个人正在写入，另一个同学过来抢夺，导致数据不一致异常。并发修改异常。
 * 3 解决方案
 *   3.1 new Vector<>();
 *   3.2 集合工具类：Collections.synchronizedList(new ArrayList<>());
 *   3.3 new CopyOnWriteArrayList<>()
 *       写时复制：CopyOnWrite容器即写时复制的容器。
 *       往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前object[]进行Copy，
 *       复制出一个新的容器Object[] newElements，然后新的容器Object[] newElements里添加元素，
 *       添加完元素之后，再将原容器的引用指向新的容器setArray(newElements);
 *       这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
 *       所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
 * 4 优化建议（同样的错误不犯两次）
 *
 * @author Qh
 * @version 1.0
 * @date 2021/9/24 22:39
 */
public class ContainerNotSafeDemo {


    public static void main(String[] args){
        //listNotSafe();
        //setNotSafe();
        mapNotSafe();
    }

    private static void listNotSafe(){
        //java.util.ConcurrentModificationException
        //List<Integer> c = new ArrayList<>();
        List<Integer> c = new CopyOnWriteArrayList<>();
        //List<Integer> c = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 30; i++) {
            final int value = i;
            new Thread(() -> {
                c.add(value);
                System.out.println(Thread.currentThread().getName() + " : " + c);
            },String.valueOf(i)).start();
        }
    }

    private static void setNotSafe(){
        //java.util.ConcurrentModificationException
        //Set<Integer> c = new HashSet<>();
        //Set<Integer> c = new CopyOnWriteArraySet<>();
        Set<Integer> c = Collections.synchronizedSet(new HashSet<>());

        for (int i = 0; i < 30; i++) {
            final int value = i;
            new Thread(() -> {
                c.add(value);
                System.out.println(Thread.currentThread().getName() + " : " + c);
            },String.valueOf(i)).start();
        }
    }

    private static void mapNotSafe(){
        //java.util.ConcurrentModificationException
        //Map<String,String> map = new HashMap<>();
        //Map<String,String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName() + "\t" + map);
            }, String.valueOf(i)).start();
        }
    }
}
