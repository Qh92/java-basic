package senior.juc;

import java.util.concurrent.RecursiveTask;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-07-03-15:38
 */
public class Fibonacci extends RecursiveTask<Integer> {

    final int n;
    public Fibonacci(int n) { this.n = n; }

    @Override
    public Integer compute() {
        if (n <= 1)
            return n;
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }

    public static void main(String[] args) {
        Integer result = new Fibonacci(10).compute();
        System.out.println(result);
    }

}
