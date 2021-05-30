package senior.juc.lock;

/**
 * @author: Qh
 * @version: 1.0
 * @date: 2021/3/25 15:23
 */
public class SynchrnizedTest{
    public static void main(String[] args){
        Car car = new Car();
        Thread thread1 = new Thread(new Thread1(car));
        Thread thread2 = new Thread(new Thread2(car));
        Thread thread3 = new Thread(new Thread3(car));
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread3.start();
    }
}

class Thread1 implements Runnable{
    private Car car;

    public Thread1(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        car.move();
    }
}

class Thread2 implements Runnable{
    private Car car;

    public Thread2(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        car.addPeople();
    }
}

class Thread3 implements Runnable{
    private Car car;

    public Thread3(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        car.notityPeople();
    }
}

class Car {

    public synchronized void move(){
        System.out.println("启动");
        try {
            Thread.sleep(1);
            this.wait();
            System.out.println("结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void addPeople(){
        System.out.println("上车");
        try {
            Thread.sleep(1);
            this.wait();
            System.out.println("下车");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void notityPeople(){
        System.out.println("通知所有乘客");
        this.notifyAll();
    }
}
