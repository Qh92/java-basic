package com.qinh.io;

import org.junit.Test;

import java.io.*;

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

    //对read()操作升级，使用read的重载方法
    @Test
    public void t2() {
        FileReader fileReader = null;
        try {
            //1.File类的实例化
            File file = new File("hello.txt");
            //2.FileReader流的实例化
            fileReader = new FileReader(file);
            //3.读入的操作
            //read(char[] cbuffer):返回每次读入cbuffer数组中的字符的个数。如果达到文件末尾，返回-1
            char[] cbuffer = new char[5];
            int len;
            while ((len = fileReader.read(cbuffer)) != -1){
                //错误的写法
                /*for(int i = 0 ; i < cbuffer.length; i++){
                    System.out.print(cbuffer[i]);
                }*/
                //正确写法
                /*for(int i = 0 ; i < len; i++){
                    System.out.print(cbuffer[i]);
                }*/
                //方式2
                //错误写法
                /*String s = new String(cbuffer);
                System.out.print(s);*/
                //正确写法
                String s = new String(cbuffer, 0, len);
                System.out.print(s);
            };
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源的关闭
            try {
                if(fileReader != null){
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    从内存中写出数据到硬盘的文件里
    说明：
    1.输出操作，对应的File可以不存在，并不会报异常
    2.
        File对应的硬盘中的文件，如果不存在，在输出的过程中，会自动创建此文件。
        File对应的硬盘中的文件,如果存在，
            如果流使用的构造器是：new FileWriter(file,false)/new FileWriter(file),对原有文件进行覆盖
            如果流使用的构造器是：new FileWriter(file,true),不会对原有文件进行覆盖，而是在原有文件基础上追加内容
     */
    @Test
    public void t3()  {
        FileWriter fileWriter = null;
        try {
            //1.提供File类的对象，指明写出到的文件
            File file = new File("hello1.txt");
            //2.提供FileWriter的对象，用于数据的写出
            fileWriter = new FileWriter(file,true);
            //3.写出的操作
            fileWriter.write("I hava a dream!\n");
            fileWriter.write("you need to have a dream!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流资源的关闭
            try {
                if(fileWriter != null){
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    public void t4(){
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            //1.创建File类的对象，指明读入和写入的文件
            File srcFile = new File("hello.txt");
            File destFile = new File("hello2.txt");
            //不能使用字符流来处理图片等字节数据
            /*File srcFile = new File("01.jpg");
            File destFile = new File("011.jpg");*/
            //2.创建输入流和输出流的对象
            fileReader = new FileReader(srcFile);
            fileWriter = new FileWriter(destFile);
            //3.数据的读入和写出操作
            char[] cbuf = new char[5];
            int len;//记录每次读入到cbuf数组中的字符的个数
            while ((len = fileReader.read(cbuf)) != -1){
                //每次写出len个字符
                fileWriter.write(cbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流资源
            try {
                if(fileReader != null){
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(fileWriter != null){
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


}
