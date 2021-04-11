package java.util.concurrent;
import java.util.List;
import java.util.Collection;


public interface ExecutorService extends Executor {

    
    //`shutdown` 方法调用后，ExecutorService 会有序关闭正在执行的任务，但是不接受新任务。如果任务已经关闭，那么这个方法不会产生任何影响
    void shutdown();

    
    //会尝试停止关闭所有正在执行的任务，停止正在等待的任务，并返回正在等待执行的任务列表
    List<Runnable> shutdownNow();

    //表示执行器是否已经关闭，如果已经关闭，返回 true，否则返回 false
    boolean isShutdown();

    //表示判断所有任务在关闭后是否已完成，如果完成返回 false，这个需要注意一点，除非首先调用 shutdown 或者 shutdownNow 方法，否则 isTerminated 方法永远不会为 true
    boolean isTerminated();

    //方法会阻塞，直到发出调用 shutdown 请求后所有的任务已经完成执行后才会解除
    boolean awaitTermination(long timeout, TimeUnit unit)
        throws InterruptedException;

    
    //submit 方法会返回一个 `Future`对象，`<T>` 表示泛型，它是对 Callable 产生的返回值来说的，
    //submit 方法提交的任务中的 call 方法如果返回 Integer，那么 submit 方法就返回 `Future<Integer>`，依此类推
    <T> Future<T> submit(Callable<T> task);

    
    <T> Future<T> submit(Runnable task, T result);

    
    Future<?> submit(Runnable task);


    //nvokeAll` 方法用于执行给定的任务结合，执行完成后会返回一个任务列表，
    //任务列表每一项是一个任务，每个任务会包括任务状态和执行结果，同样 invokeAll 方法也会返回 Future 对象    
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
        throws InterruptedException;

    
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                  long timeout, TimeUnit unit)
        throws InterruptedException;

    
    //invokeAny 会获得最先完成任务的结果，即`Callable<T>` 接口中的 call 的返回值，在获得结果时，会中断其他正在执行的任务，具有`阻塞性`
    <T> T invokeAny(Collection<? extends Callable<T>> tasks)
        throws InterruptedException, ExecutionException;

    
    <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                    long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException;
}
