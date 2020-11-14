package org.qinh.edu.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  CAS:compare and swap / compare and exchange (比较并交换)
 */
public class T01 {
    public static void main(String[] args){
        /**
         * AtomicInteger
         * 无锁，自旋锁，可以实现自增
         */
        AtomicInteger ai = new AtomicInteger();
        ai.incrementAndGet();
        /*
        public final int incrementAndGet() {
            return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
        }
        public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

        return var5;
        }
        //CAS操作
        public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
        */

        /*Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized(o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        User u  = new User(1,"张三");
        System.out.println(ClassLayout.parseInstance(u).toPrintable());*/
        int count = 0;
        for(int i=0;i<1000;i++){
            count++;
        }

        System.out.println(count);

    }

    public void add(String str1,String str2){
        StringBuffer sb = new StringBuffer();
        sb.append(str1).append(str2);
    }
}
