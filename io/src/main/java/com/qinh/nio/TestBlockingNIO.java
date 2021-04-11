package com.qinh.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
public class TestBlockingNIO {

    //客户端
    @Test
    public void client() throws IOException {
        //1.获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9998));

        FileChannel inChannel = FileChannel.open(Paths.get("01.jpg"), StandardOpenOption.READ);

        //2.分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //3.读取本地文件，并发送到服务器端
        while (inChannel.read(buffer) != -1){
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        //4. 关闭通道
        inChannel.close();
        socketChannel.close();
    }

    //服务端
    @Test
    public void server() throws IOException {
        //1. 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //2. 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9998));

        //3. 获取客户端连接的通道
        SocketChannel socketChannel = serverSocketChannel.accept();

        //4. 分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //5. 接收客户端的数据，并保存到本地
        while (socketChannel.read(buffer) != -1){
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        //6. 关闭通道
        socketChannel.close();
        outChannel.close();
        serverSocketChannel.close();
    }
}
