package java.util.concurrent;


public interface ScheduledExecutorService extends ExecutorService {


    /**
    `schedule` 方法能够延迟一定时间后执行任务，并且只能执行一次。可以看到，schedule 方法也返回了一个 `ScheduledFuture` 对象，
    ScheduledFuture 对象扩展了 Future 和 Delayed 接口，它表示异步延迟计算的结果。
    schedule 方法支持零延迟和负延迟，这两类值都被视为立即执行任务。
    还有一点需要说明的是，schedule 方法能够接收相对的时间和周期作为参数，而不是固定的日期，你可以使用 **date.getTime - System.currentTimeMillis()** 来得到相对的时间间隔。    
    */
    public ScheduledFuture<?> schedule(Runnable command,
                                       long delay, TimeUnit unit);

    
    public <V> ScheduledFuture<V> schedule(Callable<V> callable,
                                           long delay, TimeUnit unit);


    //scheduleAtFixedRate 表示任务会根据固定的速率在时间 `initialDelay` 后不断地执行。    
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
                                                  long initialDelay,
                                                  long period,
                                                  TimeUnit unit);

    //这个方法和上面的方法很类似，它表示的是以固定延迟时间的方式来执行任务。
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,
                                                     long initialDelay,
                                                     long delay,
                                                     TimeUnit unit);

}
