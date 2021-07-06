package com.qinh.nio;

import com.sun.org.apache.bcel.internal.generic.Select;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 一、使用NIO 完成网络通信的三个核心：
 *
 * 1. 通道（Channel）: 负责连接
 *
 *      java.nio.channels.Channel接口：
 *         |--SelectableChannel
 *            |--SocketChannel
 *            |--ServerSocketChannel
 *            |--DatagramChannel
 *
 *            |--Pipe.SinkChannel
 *            |--Pipe.SourceChannel
 *
 * 2. 缓冲区（Buffer） : 负责数据的存取
 *
 * 3. 选择器（Selector） : 是SelectableChannel的多路复用器。用于监控SelectableChannel的IO状况
 *
 * @author Qh
 * @version 1.0
 * @date 2021-04-11-22:16
 */
public class TestNonBlockingNIO {
    
    public static void main(String[] args) throws IOException {

        //1.获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9998));

        //2.切换非阻塞模式
        socketChannel.configureBlocking(false);

        //FileChannel inChannel = FileChannel.open(Paths.get("01.jpg"), StandardOpenOption.READ);

        //3.分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //4.发送数据到服务器端
        /*buffer.put(LocalDateTime.now().toString().getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();*/

        //4. 发送数据给服务端
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            String str = scan.next();
            buffer.put((LocalDateTime.now().toString() + "\n" + str).getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        //5.关闭通道
        //inChannel.close();
        socketChannel.close();
    }

    //客户端
    @Test
    public void client() throws IOException {
        //1.获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9998));

        //2.切换非阻塞模式
        socketChannel.configureBlocking(false);

        FileChannel inChannel = FileChannel.open(Paths.get("01.jpg"), StandardOpenOption.READ);

        //3.分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //4.发送数据到服务器端
        /*buffer.put(LocalDateTime.now().toString().getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();*/

        //4. 发送数据给服务端
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            String str = scan.next();
            buffer.put((LocalDateTime.now().toString() + "\n" + str).getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        //5.关闭通道
        inChannel.close();
        socketChannel.close();
    }

    //服务端
    @Test
    public void server() throws IOException {
        //1. 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //2. 切换非阻塞模式
        serverSocketChannel.configureBlocking(false);

        //3. 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9998));

        //4. 获取选择器
        Selector selector = Selector.open();

        //5. 将通道注册到选择器上,并且指定”监听接收事件“
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        
        //6. 轮询式的获取选择器上已经”准备就绪“的事件
        while (selector.select() > 0){
            //7. 获取当前选择器中所有注册的”选择键（已就绪的监听事件）“
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()){
                //8. 获取准备”就绪“的事件
                SelectionKey key = iterator.next();

                //9. 判断具体是什么事件准备就绪
                if (key.isAcceptable()){
                    //10. 若”接收就绪“，获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //11. 切换非阻塞模式
                    socketChannel.configureBlocking(false);
                    //12. 将该通道注册到选择器上
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if (key.isReadable()){
                    //13. 获取当前选择器上”读就绪“状态的通道
                    SocketChannel channel = (SocketChannel)key.channel();
                    //14. 读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    int len = 0;
                    while ((len = channel.read(buffer)) > 0){
                        buffer.flip();
                        System.out.println(new String(buffer.array(),0,len));
                        buffer.clear();
                    }
                }

                //15. 取消选择键 SelectionKey
                iterator.remove();
            }
        }

        //6. 关闭通道
        serverSocketChannel.close();
    }
}
