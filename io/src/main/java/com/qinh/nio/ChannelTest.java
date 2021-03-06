package com.qinh.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-06-13:07
 */
public class ChannelTest {

    @Test
    public void t1(){
        File file = new File("E:\\ideaWorkspace\\iotest\\a.txt");
        try (
            //创建FileChannelStream,以该文件输入流创建FileChannel
            FileChannel inChannel = new FileInputStream(file).getChannel();
            //以文件输出流创建FileChannel,用以控制输出
            FileChannel outChannel = new FileOutputStream("E:\\ideaWorkspace\\iotest\\b.txt").getChannel()
        )   {
            //将FileChannel里的全部数据映射成ByteBuffer
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            //使用GBK的字符集来创建解码器
            Charset charset = Charset.forName("utf-8");
            //直接将buffer里的数据全部输出
            outChannel.write(buffer);
            //再次调用buffer的clear()方法，复原limit,position的位置
            buffer.clear();
            //创建解码器CharsetDecoder对象
            CharsetDecoder charsetDecoder = charset.newDecoder();
            //使用解码器将ByteBuffer转换为CharBuffer
            CharBuffer charBuffer = charsetDecoder.decode(buffer);
            //CharBuffer的toString()方法可以获取对应的字符串
            System.out.println(charBuffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t2(){
        File file = new File("E:\\ideaWorkspace\\iotest\\a.txt");
        try (
            //创建一个RandomAccessFile对象
            RandomAccessFile raf = new RandomAccessFile(file,"rw");
            //获取RandomAccessFile对应的Channel
            FileChannel randomChannel = raf.getChannel()
        ){
            //将Channel中的所有数据映射成ByteBuffer
            MappedByteBuffer buffer = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            //把Channel的记录指针移动到最后
            randomChannel.position(file.length());
            //将buffer中的所有数据输出
            randomChannel.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多次读取文件数据
     */
    @Test
    public void t3(){
        try (
            //创建文件输入流
            FileInputStream fis = new FileInputStream("E:\\ideaWorkspace\\iotest\\b.txt");
            //创建一个FileChannel对象
            FileChannel channel = fis.getChannel()
        ){
            //定义一个ByteBuffer对象，用于重复取水
            ByteBuffer byteBuffer = ByteBuffer.allocate(256);
            //将FileChannel中的数据放入ByteBuffer中
            while (channel.read(byteBuffer) != -1){
                //锁定Buffer的空白区
                byteBuffer.flip();
                //创建Charset对象
                Charset charset = Charset.forName("UTF-8");
                //创建解码器CharsetDecoder对象
                CharsetDecoder charsetDecoder = charset.newDecoder();
                //将ByteBuffer的内容转码
                CharBuffer charBuffer = charsetDecoder.decode(byteBuffer);
                System.out.println(charBuffer);
                //将Buffer初始化，为下一次读取数据做准备
                byteBuffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
