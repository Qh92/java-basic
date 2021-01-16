package com.qinh;

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 实现TCP的网络编程
 * 例子1：客户端发送消息给服务器端，服务器端将数据显示在控制台上
 *
 * @author Qh
 * @version 1.0
 * @date 2021-01-16-23:29
 */
public class TCPTest1 {

    //客户端
    @Test
    public void client(){

        Socket socket = null;
        OutputStream os = null;
        try {
            //1.创建Socket对象，指明服务器端的ip和端口号
            InetAddress inet = InetAddress.getByName("localhost");
            socket = new Socket(inet,8899);
            //2.获取一个输出流，用于输出数据
            os = socket.getOutputStream();
            //3.写出数据的操作
            os.write("你好，我是客户端mm".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源的关闭
            try {
                if(socket != null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null)
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //服务器端
    @Test
    public void server(){

        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建服务器端的ServerSocket，指明自己的端口号
            ss = new ServerSocket(8899);
            //2.调用accept()表示接收来自于客户端的socket
            socket = ss.accept();
            //3.获取输入流
            is = socket.getInputStream();

            //不建议这样写,可能会存在乱码
            /*byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1){
                String str = new String(buffer, 0, len);
                System.out.println(str);
            }*/

            //4.读取输入流中的数据
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[5];
            int len;
            while ((len = is.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }

            System.out.println(baos.toString());
            System.out.println("收到来自于："+socket.getInetAddress()+" 的数据");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            try {
                if(baos != null)
                baos.close();
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
