package com.qinh.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-01-31-23:29
 */
public class BufferToText {
    private static final int BSIZE = 1024;
    public static void main(String[] args) {

        try(
                FileChannel fc = new FileOutputStream(
                        "data2.txt").getChannel()
        ) {
            fc.write(ByteBuffer.wrap("Some text".getBytes()));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        ByteBuffer buff = ByteBuffer.allocate(BSIZE);

        try(
                FileChannel fc = new FileInputStream(
                        "data2.txt").getChannel()
        ) {
            System.out.println(fc.read(buff));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        buff.flip();
        // 无法运行
        System.out.println(buff.asCharBuffer());
        System.out.println(buff.toString());
        // 使用默认系统默认编码解码
        buff.rewind();
        String encoding =
                System.getProperty("file.encoding");
        System.out.println("Decoded using " +
                encoding + ": "
                + Charset.forName(encoding).decode(buff));

        // 编码和打印
        try(
                FileChannel fc = new FileOutputStream(
                        "data2.txt").getChannel()
        ) {
            fc.write(ByteBuffer.wrap(
                    "Some text".getBytes("UTF-16BE")));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        // 尝试再次读取：
        buff.clear();
        try(
                FileChannel fc = new FileInputStream(
                        "data2.txt").getChannel()
        ) {
            fc.read(buff);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        buff.flip();
        System.out.println(buff.asCharBuffer());
        // 通过 CharBuffer 写入：
        buff = ByteBuffer.allocate(24);
        buff.asCharBuffer().put("Some text");

        try(
                FileChannel fc = new FileOutputStream(
                        "data2.txt").getChannel()
        ) {
            fc.write(buff);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        // 读取和显示：
        buff.clear();

        try(
                FileChannel fc = new FileInputStream(
                        "data2.txt").getChannel()
        ) {
            fc.read(buff);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        buff.flip();
        System.out.println(buff.asCharBuffer());
    }
}
