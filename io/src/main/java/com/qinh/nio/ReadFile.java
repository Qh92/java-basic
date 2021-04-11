package com.qinh.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Channel和Buffer传统的"用竹筒多次重复取水"的方式
 *
 * @Author Qh
 * @Date 2021/2/7 23:06
 * @Version 1.0
 */
public class ReadFile {
    /*public static void main(String[] args){
        try {
            FileChannel channel = new FileInputStream("data.txt").getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (channel.read(buffer) != -1) {
                buffer.flip();
                Charset charset = Charset.forName("GBK");
                CharsetDecoder charsetDecoder = charset.newDecoder();
                CharBuffer decode = charsetDecoder.decode(buffer);
                System.out.println(decode);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }*/
    @Test
    public void t1(){
        try {
            FileChannel channel = new FileInputStream("data.txt").getChannel();
            //定义一个ByteBuffer对象，用于重复取水
            ByteBuffer buffer = ByteBuffer.allocate(8);
            //将FileChannel中的数据放入ByteBuffer中
            while (channel.read(buffer) != -1) {
                //锁定Buffer的空白区
                buffer.flip();
                Charset charset = Charset.forName("GBK");
                //创建解码器
                CharsetDecoder charsetDecoder = charset.newDecoder();
                //将ByteBuffer的内容转码
                CharBuffer decode = charsetDecoder.decode(buffer);
                System.out.println(decode);
                //将Buffer初始化，为下一次读取数据做准备
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
