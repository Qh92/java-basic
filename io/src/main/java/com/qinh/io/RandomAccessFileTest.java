package com.qinh.io;

import org.junit.Test;

import java.io.*;

/**
 * RandomAccessFile的使用
 * 1.RandomAccessFile 声明在java.io包下，但直接继承于java.lang.Object类。并 且它实现了DataInput、DataOutput这两个接口，也就意味着这个类既可以读也可以写。
 * 2.如果RandowAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建
 *   如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖）
 * 3.可以通过相关操作，实现RandomAccessFile"插入"数据的效果
 *
 *
 * @author Qh
 * @version 1.0
 * @date 2021-01-16-13:43
 */
public class RandomAccessFileTest {

    @Test
    public void t1(){
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            raf1 = new RandomAccessFile(new File("01.jpg"), "r");
            raf2 = new RandomAccessFile(new File("011.jpg"), "rw");

            byte[] buffer = new byte[1024];
            int len;
            while ((len = raf1.read(buffer)) != -1){
                raf2.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(raf1 != null)
                raf1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(raf2 != null)
                raf2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void t2(){

        RandomAccessFile raf1 = null;
        try {
            raf1 = new RandomAccessFile("hello.txt", "rw");

            //void seek(long pos)：将文件记录指针定位到 pos 位置
            raf1.seek(3);
            raf1.write("xyz".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(raf1 != null)
                raf1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    使用RandomAccessFile实现数据的插入效果
     */
    @Test
    public void t3(){
        RandomAccessFile raf1 = null;
        try {
            raf1 = new RandomAccessFile("hello.txt", "rw");

            //void seek(long pos)：将文件记录指针定位到 pos 位置
            raf1.seek(3);

            //保存指针3后面的数据到StringBuilder中
            StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());
            byte[] buffer = new byte[20];
            int len;
            while ((len = raf1.read(buffer)) != -1){
                builder.append(new String(buffer,0,len));
            }
            //调回指针，写入“xyz”
            raf1.seek(3);
            raf1.write("xyz".getBytes());

            //将StringBuilder中的数据写入到文件中
            raf1.write(builder.toString().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(raf1 != null)
                    raf1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //思考：将StringBuilder替换为ByteArrayOutputStream
    @Test
    public void t4(){
        RandomAccessFile raf1 = null;
        ByteArrayOutputStream arrayOutputStream = null;
        try {
            raf1 = new RandomAccessFile("hello.txt", "rw");

            //void seek(long pos)：将文件记录指针定位到 pos 位置
            raf1.seek(3);

            arrayOutputStream = new ByteArrayOutputStream((int) new File("hello.txt").length());
            byte[] buffer = new byte[20];
            int len;
            while ((len = raf1.read(buffer)) != -1){
                arrayOutputStream.write(buffer, 0, len);
            }
            //调回指针，写入“xyz”
            raf1.seek(3);
            raf1.write("xyz".getBytes());

            //将StringBuilder中的数据写入到文件中
            raf1.write(arrayOutputStream.toString().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(raf1 != null)
                    raf1.close();
                if (arrayOutputStream != null) {
                    arrayOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
