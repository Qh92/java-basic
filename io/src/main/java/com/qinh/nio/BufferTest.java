package com.qinh.nio;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;

/**
 * 一、缓冲区（Buffer）：在Java NIO 中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据
 *
 * 根据数据类型不同（boolean除外），提供了相应类型的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * 上述缓冲区的管理方式几乎一致，通过allocate()获取缓冲区
 *
 * 二、缓冲区存储数据的两个核心方法：
 * put():存入数据到缓冲区中
 * get():获取缓冲区中的数据
 *
 * 三、缓冲区中的四个核心属性：
 * capacity : 容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变。
 * limit : 界限，表示缓冲区中可以操作数据的大小。（limit 后数据不能进行读写）
 * position : 位置，表示缓冲区中正在操作数据的位置。
 * mark : 标记，表示记录当前position的位置。可以通过reset()恢复position到mark的位置
 *
 * 0 <= mark <= position <= limit <= capacity
 *
 * 四、直接缓冲区与非直接缓冲区：
 * 非直接缓冲区：通过allocate()方法分配缓冲区，将缓冲区建立在JVM的内存中
 * 直接缓冲区：通过allocateDirect()方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率。
 *
 * @author Qh
 * @version 1.0
 * @date 2021-03-06-12:53
 */
public class BufferTest {

    @Test
    public void t2(){
        String str = "abcde";

        //1.分配一个指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("----------allocate()---------");
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//1024
        System.out.println(buffer.capacity());//1024

        //2.利用put()存入数据到缓冲区中
        buffer.put(str.getBytes());
        System.out.println("----------put()---------");
        System.out.println(buffer.position());//5
        System.out.println(buffer.limit());//1024
        System.out.println(buffer.capacity());//1024

        //3.切换成读取数据模式
        buffer.flip();
        System.out.println("----------flip()---------");
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//5
        System.out.println(buffer.capacity());//1024

        //4.利用get()读取缓冲区中的数据
        byte[] dest = new byte[buffer.limit()];
        buffer.get(dest);
        System.out.println("读取数据");
        System.out.println(new String(dest,0,dest.length));

        System.out.println("----------get()---------");
        System.out.println(buffer.position());//5
        System.out.println(buffer.limit());//5
        System.out.println(buffer.capacity());//1024

        //5.rewind() : 可重复读
        buffer.rewind();
        System.out.println("----------rewind()---------");
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//5
        System.out.println(buffer.capacity());//1024

        //6.clear() : 清空缓冲区，但是缓冲区中的数据依然存在，但是处于"被遗忘"状态
        buffer.clear();
        System.out.println("----------clear()---------");
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//1024
        System.out.println(buffer.capacity());//1024

        System.out.println((char) buffer.get());//a
        System.out.println("单个字节读取完毕: position : " + buffer.position());//1

        str = "xyz";
        buffer.put(str.getBytes());
        buffer.flip();
        System.out.println("----------put() again---------");
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//4
        System.out.println(buffer.capacity());//1024
        for (int i = 0; i < buffer.limit(); i++) {
            System.out.println((char) buffer.get(i));
        }
    }

    @Test
    public void t3(){
        String str = "abcde";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());
        buffer.flip();
        byte[] dest = new byte[buffer.limit()];
        buffer.get(dest,0,2);
        System.out.println(new String(dest,0,2));//ab
        System.out.println(buffer.position());//2

        //mark() : 标记
        buffer.mark();
        buffer.get(dest,2,2);
        System.out.println(new String(dest,2,2));//cd
        /*for (byte b : dest){
            System.out.println((char)b);
        }*/

        //reset() : 恢复到mark的位置
        buffer.reset();
        System.out.println(buffer.position());//2

        //判断缓冲区中是否还有剩余数据
        if (buffer.hasRemaining()){
            //获取缓冲区中可以操作的数据
            System.out.println(buffer.remaining());//3
        }

    }

    @Test
    public void t4(){
        //分配直接缓冲区
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println(buffer.isDirect());//true
    }

    @Test
    public void t1(){
        CharBuffer buffer = CharBuffer.allocate(8);
        System.out.println("capacity: " + buffer.capacity());
        System.out.println("limit: " + buffer.limit());
        System.out.println("position: " + buffer.position());

        buffer.put('a');
        buffer.put('b');
        buffer.put('c');
        System.out.println("加入三个元素后,position: " + buffer.position());

        buffer.flip();
        System.out.println("执行flip()后，limit: " + buffer.limit());
        System.out.println("position: " + buffer.position());

        System.out.println("第一个元素: " + buffer.get());
        System.out.println("取出第一个元素后,position: " + buffer.position());

        buffer.clear();
        System.out.println("执行clear()后,limit: " + buffer.limit());
        System.out.println("执行clear()后,position: " + buffer.position());
        System.out.println("执行clear()后，缓冲区内容并没有被清除: " + " 第三个元素为: " + buffer.get(2));
        System.out.println("执行绝对读取后,position: " + buffer.position());

    }


}
