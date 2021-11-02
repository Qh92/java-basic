package test;

import java.util.concurrent.locks.LockSupport;

/**
 * 交替打印解决2
 *
 * @author Qh
 * @version 1.0
 * @date 2021/11/2 16:40
 */
public class AlternatePrint2 {
    public static void main(String[] args){

        Resource2 resource = new Resource2();
        PrintNum2 printNum2 = new PrintNum2(resource, 26);
        Thread thread1 = new Thread(printNum2,"print-num");
        PrintWord2 printWord2 = new PrintWord2(resource, 26);
        Thread thread2 = new Thread(printWord2,"print-word");

        resource.setThread1(thread1);
        resource.setThread2(thread2);

        thread1.start();
        thread2.start();

    }
}

class Resource2{
    /** 如果是1 输出数字，如果是2 输出字母*/
    String flag = "1";
    int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
    char[] words = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    Thread thread1;
    Thread thread2;

    int numIndex = 0;
    int wordIndex = 0;

    public void setThread1(Thread thread1) {
        this.thread1 = thread1;
    }

    public void setThread2(Thread thread2) {
        this.thread2 = thread2;
    }

    void printNum(){
        while (!"1".equals(flag)){
            LockSupport.park();
        }
        System.out.println(numbers[numIndex++]);
        flag = "2";
        LockSupport.unpark(thread2);
    }

    void printWord(){
        while (!"2".equals(flag)){
            LockSupport.park();
        }
        System.out.println(words[wordIndex++]);
        flag = "1";
        LockSupport.unpark(thread1);
    }
}

class PrintNum2 implements Runnable{
    private Resource2 resource;
    private int count;

    public PrintNum2(Resource2 resource,int count) {
        this.resource = resource;
        this.count = count;
    }


    @Override
    public void run() {
        for (int i = 1; i <= count; i++) {
            resource.printNum();
        }
    }
}

class PrintWord2 implements Runnable{
    private Resource2 resource;
    private int count;

    public PrintWord2(Resource2 resource, int count) {
        this.resource = resource;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 1; i <= count; i++) {
            resource.printWord();
        }
    }
}
