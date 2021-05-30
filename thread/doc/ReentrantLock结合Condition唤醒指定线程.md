# ReentrantLock结合Condition唤醒指定线程



关键字synchronized与notify()/notifyAll()方法相结合可以实现等待/通知模式，类ReentrantLock也可以实现同样的功能，但需要借助于Condition对象。

在使用notify()/notifyAll()方法进行通知时，被通知的线程却是由JVM随机选择的。但使用ReentrantLock结合Condition类是可以实现“选择性通知”，这个功能是非常重要的，而且在Condition类中是默认提供的。

synchronized就相当于整个Lock对象中只有一个单一的Condition对象，所有的线程都注册在它一个对象的身上。线程开始notifyAll()时，需要通知所有的waiting线程，没有选择权，会出现相当大的效率问题。

示例代码：



```java

public class Service02 {

    private Lock senior.juc.lock = new ReentrantLock();
    private Condition condition = senior.juc.lock.newCondition();

    public void await() {
        try{
            senior.juc.lock.senior.juc.lock();
            System.out.println("begin await() 时间为 " + System.currentTimeMillis());
            condition.await();
            System.out.println("end await() 时间为 " + System.currentTimeMillis());
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            senior.juc.lock.unlock();
            System.out.println("锁被释放了！ await() 时间为 " + System.currentTimeMillis());
        }
    }

    public void signal() {
        try{
            senior.juc.lock.senior.juc.lock();
            System.out.println("signal() 时间为 " + System.currentTimeMillis());
            condition.signal();
        } finally {
            senior.juc.lock.unlock();
            System.out.println("锁被释放了！ signal() 时间为 " + System.currentTimeMillis());
        }
    }
}

public class Thread02 extends Thread {

    private Service02 service;

    public Thread02(Service02 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.await();
    };
}

public static void main(String[] args) {
    thread02();
}

private static void thread02() {
    try{
        Service02 service = new Service02();
        Thread02 thread = new Thread02(service);
        thread.start();
        Thread.sleep(5000);
        service.signal();
    } catch(InterruptedException e) {
        e.printStackTrace();
    }
}

```


输出结果：

begin await() 时间为 1496893238642
signal() 时间为 1496893243640
锁被释放了！ signal() 时间为 1496893243640
end await() 时间为 1496893243641
锁被释放了！ await() 时间为 1496893243641

从输出结果我们可以看出，子线程在执行Service02 对象的await()方法的condition.await();这一行时释放掉对象锁并进入等待状态，主线程睡眠了5秒后执行了Service02对象的signal()方法，该方法的功能是获取Service02对象的对象锁，并执行condition.signal()唤醒等待的子线程，随后释放Service02对象的对象锁。子线程被唤醒后接着condition.await()后的代码继续执行，执行完自身任务后释放掉Service02对象的对象锁。

以上代码成功实现了等待/通知模式。
1. Object类中的wait()方法相当于Condition类中的await()方法。
2. Object类中的wait(long timeout)方法相当于Condition类中的await(long time, TimeUnit unit)方法。
3. Object类中的notify()方法相当于Condition类中的signal()方法。
4. Object类中的notifyAll()方法相当于Condition类中的signalAll()方法。

使用多个Condition实现通知部分线程
示例代码：

```java
// class Service03
public class Service03 {

    private Lock senior.juc.lock = new ReentrantLock();
    private Condition conditionA = senior.juc.lock.newCondition();
    private Condition conditionB = senior.juc.lock.newCondition();

    public void awaitA() {
        try{
            senior.juc.lock.senior.juc.lock();
            System.out.println("begin awaitA() 时间为 " + System.currentTimeMillis() 
                    + " ThreadName=" + Thread.currentThread().getName());
            conditionA.await();
            System.out.println("end awaitA() 时间为 " + System.currentTimeMillis() 
            + " ThreadName=" + Thread.currentThread().getName());
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            senior.juc.lock.unlock();
        }
    }

    public void awaitB() {
        try{
            senior.juc.lock.senior.juc.lock();
            System.out.println("begin awaitB() 时间为 " + System.currentTimeMillis() 
                    + " ThreadName=" + Thread.currentThread().getName());
            conditionB.await();
            System.out.println("end awaitB() 时间为 " + System.currentTimeMillis() 
            + " ThreadName=" + Thread.currentThread().getName());
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            senior.juc.lock.unlock();
        }
    }

    public void signalAll_A() {
        try{
            senior.juc.lock.senior.juc.lock();
            System.out.println("signalAll_A 时间为 " + System.currentTimeMillis()
                    + " ThreadName=" + Thread.currentThread().getName());
            conditionA.signalAll();
        } finally {
            senior.juc.lock.unlock();
        }
    }

    public void signalAll_B() {
        try{
            senior.juc.lock.senior.juc.lock();
            System.out.println("signalAll_B 时间为 " + System.currentTimeMillis()
                    + " ThreadName=" + Thread.currentThread().getName());
            conditionB.signalAll();
        } finally {
            senior.juc.lock.unlock();
        }
    }

}
// class ThreadA
public class ThreadA extends Thread {

    private Service03 service;

    public ThreadA(Service03 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.awaitA();
    }
}
// class ThreadB
public class ThreadB extends Thread {

    private Service03 service;

    public ThreadB(Service03 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.awaitB();
    }
}
// main method
public static void main(String[] args) {
    service03();
}

private static void service03() {
    try{
        Service03 service = new Service03();
        ThreadA ta = new ThreadA(service);
        ta.setName("A");
        ta.start();

        ThreadB tb = new ThreadB(service);
        tb.setName("B");
        tb.start();

        Thread.sleep(3000);
        service.signalAll_A();
    } catch(InterruptedException e) {
        e.printStackTrace();
    }
}

```

输出结果（程序并没有停止，B线程还在等待状态）：

begin awaitA() 时间为 1496901189607 ThreadName=A
begin awaitB() 时间为 1496901189610 ThreadName=B
signalAll_A 时间为 1496901192610 ThreadName=main
end awaitA() 时间为 1496901192610 ThreadName=A

从以上输出可以看出，调用service.signalAll_A()方法后只是唤醒了处于等待状态的A线程，B线程还在等待状态。

以上我们测试的都是非公平锁，即线程之间获取锁的方式是抢占的，先启动的线程不一定先获得锁，这个方式可能造成某些线程一直拿不到锁，结果就是不公平的。还存在一种叫公平锁，即表示线程获取锁的顺序是按照线程加锁的顺序来分配的，即先来先得的FIFO模式。

我们查看一下类ReentrantLock构造函数：

```java
public ReentrantLock() {
    sync = new NonfairSync();
}

public ReentrantLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
}
```

从源码可以看出，默认的无参构造函数创建的是非公平锁，要想创建公平锁可以通过`new ReentrantLock(true)`方式创建