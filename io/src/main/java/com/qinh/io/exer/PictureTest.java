package com.qinh.io.exer;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-01-09-14:57
 */
public class PictureTest {
    //图片加密
    @Test
    public void t1(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("01.jpg");
            fos = new FileOutputStream("01secret.jpg");

            byte[] buffer = new byte[20];
            int len;
            while ((len = fis.read(buffer)) != -1){
                //字节数组进行修改
                //错误
                /*for(byte b : buffer){
                    b = (byte) (b ^ 5);
                }*/
                //正确
                for(int i = 0; i < len; i++){
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }
                fos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos != null)
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fis != null)
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //图片解密
    @Test
    public void t2(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("01secret.jpg");
            fos = new FileOutputStream("011.jpg");

            byte[] buffer = new byte[20];
            int len;
            while ((len = fis.read(buffer)) != -1){
                //字节数组进行修改
                //错误
                /*for(byte b : buffer){
                    b = (byte) (b ^ 5);
                }*/
                //正确
                for(int i = 0; i < len; i++){
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }
                fos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
