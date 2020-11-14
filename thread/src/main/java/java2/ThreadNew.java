package java2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式三：实现Callable接口 --JDK 5.0新增
 *
 * 如何理解实现Callable接口的方式创建多线程比实现Runnable接口创建多线程的方式强大？
 * 1.call()可以有返回值
 * 2.call()可以抛出异常，被外面的操作捕获，获取异常的信息
 * 3.Callable是支持泛型的
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-13- 21:20
 * @description
 */
//1.创建一个实现Callable的实现类
class NumThread implements Callable<Integer>{

    //2.实现call方法，将此线程需要执行的操作声明在call方法中，并且是可以有返回值的
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if(i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }


}

public class ThreadNew {
    public static void main(String[] args) {
        //3.创建Callable接口实现类的对象
        NumThread numThread = new NumThread();
        //4.将此Callable接口实现类的对象作为参数传递到FutureTask构造器中，创建FutureTask对象
        FutureTask<Integer> integerFutureTask = new FutureTask<>(numThread);
        //5.将FutureTask对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()方法
        new Thread(integerFutureTask).start();
        try {
            //6.获取Callable中call方法的返回值
            //get()方法的返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值
            Integer sum = integerFutureTask.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
