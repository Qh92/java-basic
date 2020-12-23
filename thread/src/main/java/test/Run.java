package test;

/**
 * @author Qh
 * @version 1.0
 * @date 2020-12-15-23:13
 */
public class Run {
    public static void main(String[] args) {

        try {
            Object lock = new Object();

            ThreadA a = new ThreadA(lock);
            a.start();

            Thread.sleep(50);

            ThreadB b = new ThreadB(lock);
            b.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
