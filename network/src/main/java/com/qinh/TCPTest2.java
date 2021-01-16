package com.qinh;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例题2：客户端发送文件给服务器端，服务器端将文件保存在本地
 *
 * @author Qh
 * @version 1.0
 * @date 2021-01-16-23:57
 */
public class TCPTest2 {

    @Test
    public void client(){
        Socket socket = null;
        OutputStream os = null;
        FileInputStream fis = null;
        try {
            socket = new Socket(InetAddress.getByName("localhost"), 9090);

            os = socket.getOutputStream();

            fis = new FileInputStream(new File("01.jpg"));

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1){
                os.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis != null)
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(os != null)
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket != null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void server(){

        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            ss = new ServerSocket(9090);

            socket = ss.accept();

            is = socket.getInputStream();

            fos = new FileOutputStream(new File("011.jpg"));

            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1){
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
                if(is != null)
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(socket != null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(ss != null)
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }
}
