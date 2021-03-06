package com.qinh.nio;

import org.junit.Test;

import java.nio.CharBuffer;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-06-12:53
 */
public class BufferTest {

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
