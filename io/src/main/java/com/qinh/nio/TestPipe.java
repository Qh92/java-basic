package com.qinh.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-04-11-23:51
 */
public class TestPipe {

    @Test
    public void test1() throws IOException {
        //1. 获取管道
        Pipe pipe = Pipe.open();

        //2. 将缓冲区中的数据写入管道
        ByteBuffer buf = ByteBuffer.allocate(1024);

        Pipe.SinkChannel sinkChannel = pipe.sink();
        buf.put("通过单向管道发送数据".getBytes());
        buf.flip();
        sinkChannel.write(buf);

        //3. 读取缓冲区中的数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        //buf.flip();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int len = sourceChannel.read(buffer);
        System.out.println(new String(buffer.array(), 0, len));

        sourceChannel.close();
        sinkChannel.close();
    }
}
