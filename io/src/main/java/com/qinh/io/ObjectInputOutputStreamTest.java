package com.qinh.io;

import com.qinh.entity.Person;
import org.junit.Test;

import java.io.*;

/**
 * 对象流的使用
 * 1.ObjectInputStream 和 ObjectOutputStream
 * 2.作用：用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
 * 3.要想一个java对象是可序列化的，需要满足相应的要求。见Person.java
 *
 *
 * @Author Qh
 * @Date 2021/1/14 23:34
 * @Version 1.0
 */
public class ObjectInputOutputStreamTest {
    /*
    序列化过程：将内存中的java对象保存到磁盘中或通过网络传输出去
    使用ObjectOutputStream实现
     */
    @Test
    public void t1(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));

            oos.writeObject(new String("钢铁侠，蜘蛛侠！"));

            oos.flush();

            oos.writeObject(new Person("詹姆斯",36));

            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(oos != null)
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    反序列化：将磁盘文件中的对象还原为内存中的一个java对象
    使用ObjectInputStream
     */
    @Test
    public void t2(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));

            Object s = ois.readObject();

            String str = (String) s;

            System.out.println(str);

            Person p = (Person) ois.readObject();

            System.out.println(p);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ois != null)
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
