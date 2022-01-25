package senior.timer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * 测试Timer
 *
 * @author Qh
 * @version 1.0
 * @date 2022/1/25 14:58
 */
public class TimerDemo {
    public static void main(String[] args){
        Timer timer = new Timer("qinhao", false);

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : qinhao test");
            }
        };

        timer.scheduleAtFixedRate(timerTask,0  , TimeUnit.SECONDS.toMillis(1));
    }
}
