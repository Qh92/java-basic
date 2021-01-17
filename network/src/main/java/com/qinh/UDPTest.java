package com.qinh;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP协议的网络编程
 *
 * @author Qh
 * @version 1.0
 * @date 2021-01-17-21:34
 */
public class UDPTest {

    //发送端
    @Test
    public void sender(){
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();

            String str = "我是UDP方式发送的导弹";
            byte[] data = str.getBytes();
            InetAddress inet = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(data,0,data.length,inet,9090);

            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    //接收端
    @Test
    public void receiver(){

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(9090);

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
            socket.receive(packet);
            System.out.println(new String(packet.getData(),0,packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
