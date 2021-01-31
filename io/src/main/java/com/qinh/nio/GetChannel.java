package com.qinh.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-01-31-22:17
 */
public class GetChannel {
    private static String name = "data.txt";
    private static final int BSIZE = 1024;
    public static void main(String[] args) {
        // 写入一个文件:
        try(
                FileChannel fc = new FileOutputStream(name)
                        .getChannel()
        ) {
            fc.write(ByteBuffer
                    .wrap("Some text ".getBytes()));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        // 在文件尾添加：
        try(
                FileChannel fc = new RandomAccessFile(
                        name, "rw").getChannel()
        ) {
            // 移动到结尾
            fc.position(fc.size());
            fc.write(ByteBuffer
                    .wrap("Some more".getBytes()));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        // 读取文件e:
        try(
                FileChannel fc = new FileInputStream(name)
                        .getChannel()
        ) {
            ByteBuffer buff = ByteBuffer.allocate(BSIZE);
            fc.read(buff);
            buff.flip();
            while(buff.hasRemaining()) {
                System.out.write(buff.get());
                //System.out.print(buff.get());
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        System.out.flush();
    }

}
