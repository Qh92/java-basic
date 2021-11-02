package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印问题
 *
 * @author Qh
 * @version 1.0
 * @date 2021/11/1 23:18
 */
public class AlternatePrint {
    public static void main(String[] args){
        Resource resource = new Resource();
        PrintNum printNum = new PrintNum(resource,26);
        Thread thread1 = new Thread(printNum,"thread-print-num");
        PrintWord printWord = new PrintWord(resource,26);
        Thread thread2 = new Thread(printWord,"thread-print-word");
        thread1.start();
        thread2.start();
    }
}

class Resource{
    /** 如果是1 输出数字，如果是2 输出字母*/
    String flag = "1";
    int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
    char[] words = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    int numIndex = 0;
    int wordIndex = 0;

    void printNum(){
        try{
            lock.lock();
            while (!"1".equals(flag)){
                condition1.await();
            }
            if (numIndex > numbers.length){
                return;
            }
            System.out.println(numbers[numIndex++]);
            flag = "2";
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void printWord(){
        try{
            lock.lock();
            while (!"2".equals(flag)){
                condition2.await();
            }
            if (wordIndex > words.length){
                return;
            }
            System.out.println(words[wordIndex++]);
            flag = "1";
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

class PrintNum implements Runnable{
    private Resource resource;
    private int count;

    public PrintNum(Resource resource, int count) {
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

class PrintWord implements Runnable{
    private Resource resource;
    private int count;

    public PrintWord(Resource resource, int count) {
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
