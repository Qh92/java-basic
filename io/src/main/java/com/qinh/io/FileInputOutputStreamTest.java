package com.qinh.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 测试FileInputStream和FileOutputStream的使用
 *
 * 结论：
 * 1.对于文本文件（.txt,.java,.c,.cpp），使用字符流处理
 * 2.对于非文本文件（.jpg,.mp3,.mp4,.avi,.doc,.ppt,...），使用字节流处理
 *
 * @author Qh
 * @version 1.0
 * @date 2021-01-06-23:12
 */
public class FileInputOutputStreamTest {
    //使用字节流FileInputStream处理文本文件，可能出现乱码的
    @Test
    public void t1()  {
        FileInputStream fileInputStream = null;
        try {
            //1.造文件
            File file = new File("hello.txt");

            //2.造流
            fileInputStream = new FileInputStream(file);

            //3.读数据
            byte[] buffer = new byte[5];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1){
                String s = new String(buffer, 0, len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭资源
            try {
                if(fileInputStream != null)
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    实现对图片的复制操作
     */
    @Test
    public void t2(){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            File srcFile = new File("01.jpg");
            File destFile = new File("011.jpg");

            fileInputStream = new FileInputStream(srcFile);
            fileOutputStream = new FileOutputStream(destFile);

            byte[] buffer = new byte[5];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fileInputStream != null)
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fileOutputStream != null)
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    复制文件
     */
    public void copyFile(String srcPath,String destPath){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            fileInputStream = new FileInputStream(srcFile);
            fileOutputStream = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void t3(){
        long start = System.currentTimeMillis();
        String srcPath = "01.avi";
        String destPath = "011.avi";
        copyFile(srcPath,destPath);
        long end = System.currentTimeMillis();
        System.out.println("复制操作花费的时间为："+(end - start));//958毫秒
    }

}
