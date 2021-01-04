package com.qinh.io;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 一、流的分类
 * 1.操作数据单位：字节流、字符流
 * 2.数据的流向：输入流、输出流
 * 3.流的角色：节点流、处理流
 *
 * 二、流的体系结构
 * 抽象基类            节点流（或文件流）        缓冲流（处理流的一种）
 * InputStream         FileInputStream         BufferedInputStream
 * OutputStream        FileOutputStream        BufferedOutputStream
 * Reader              FileReader              BufferedReader
 * Writer              FileWriter              BufferedWriter
 *
 * @author Qh
 * @version 1.0
 * @date 2021-01-04-21:56
 */
public class FileReaderWriterTest {

    public static void main(String[] args) {
        File file = new File("hello.txt");//相较于当前Module
        System.out.println(file.getAbsoluteFile());//E:\ideaWorkspace\project\java_edu\javabasic\hello.txt

        File file2 = new File("io\\hello.txt");
        System.out.println(file2.getAbsoluteFile());//E:\ideaWorkspace\project\java_edu\javabasic\io\hello.txt
    }

    /*
    将io下的hello.txt文件内容读入程序中，并输出到控制台
    说明点：
    1.read()的理解：返回读入的一个字符。如果达到文件末尾，返回-1
    2.异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理
    3.读入的文件一定要存在，否则就会报FileNotFoundException
     */
    @Test
    public void t1()  {
        FileReader fr = null;
        try {
            //1.实例化File类的对象，指明要操作的文件
            File file = new File("hello.txt");//相较于当前Module
            //2.提供具体的流
            fr = new FileReader(file);
            //3.数据的读入
            //read():返回读入的一个字符。如果达到文件末尾，返回-1
            /*int data = fr.read();
            while (data != -1){
                System.out.print((char)data);
                data = fr.read();
            }*/
            //语法上针对上述方法的优化
            int data;
            while ((data = fr.read()) != -1){
                System.out.print((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流的关闭操作
            try {
                if(fr != null){
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
