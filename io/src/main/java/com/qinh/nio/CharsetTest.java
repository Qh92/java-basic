package com.qinh.nio;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-06-14:09
 */
public class CharsetTest {

    @Test
    public void t1(){
        //获取java支持的全部字符集
        SortedMap<String, Charset> map = Charset.availableCharsets();
        for (String alias : map.keySet()){
            System.out.println(alias + "------> " + map.get(alias));
        }
    }

    @Test
    public void t2() throws CharacterCodingException {
        //创建简体中文对应的Charset
        Charset cn = Charset.forName("GBK");
        //获取cn对象对应的编码器和解码器
        CharsetEncoder cnEncoder = cn.newEncoder();
        CharsetDecoder cnDecoder = cn.newDecoder();
        //创建一个CharBuffer对象
        CharBuffer charBuffer = CharBuffer.allocate(8);
        charBuffer.put('孙');
        charBuffer.put('悟');
        charBuffer.put('空');
        charBuffer.flip();
        //将CharBuffer中的字符序列转换为字节序列
        ByteBuffer byteBuffer = cnEncoder.encode(charBuffer);
        //循环访问ByteBuffer中的每个字节
        for (int i = 0; i < byteBuffer.capacity(); i++){
            System.out.println(byteBuffer.get(i) + " ");
        }
        //将ByteBuffer的数据解码成字符序列
        System.out.println("\n" + cnDecoder.decode(byteBuffer));
    }
}
