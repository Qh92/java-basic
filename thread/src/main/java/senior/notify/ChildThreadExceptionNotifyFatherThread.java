package senior.notify;

/**
 * 子线程异常通知父线程
 *
 * @author Qh
 * @version 1.0
 * @date 2022-01-15 23:44
 */
public class ChildThreadExceptionNotifyFatherThread {
    private volatile static boolean flag = false;

    public static void main(String[] args) {
        Thread childThread = new Thread(() -> {
            throw new RuntimeException("子线程发生异常");
        }, "threadHandler");

        Thread.UncaughtExceptionHandler handler = (thead, e) -> {
            flag = true;
            System.out.println("childThread : " + e);
        };

        childThread.setUncaughtExceptionHandler(handler);

        childThread.start();

        try {
            childThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(flag);

        System.out.println("父线程");

    }
}
