package java.util.concurrent;


public interface Executor {

    
    /**
    execute方法接收一个 `Runnable` 实例，它用来执行一个任务，而任务就是一个实现了 Runnable 接口的类，
    但是 execute 方法不能接收实现了 `Callable` 接口的类，也就是说，execute 方法不能接收具有返回值的任务
    execute 方法创建的线程是异步执行的，也就是说，你不用等待每个任务执行完毕后再执行下一个任务。
    */
    void execute(Runnable command);
}
