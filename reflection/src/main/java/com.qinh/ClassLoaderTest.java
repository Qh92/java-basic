package com.qinh;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 了解类的加载器
 *
 * @author Qh
 * @version 1.0
 * @date 2021-01-31-20:54
 */
public class ClassLoaderTest {

    @Test
    public void t1(){
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoader);

        //调用系统类加载器的getParent():获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        //sun.misc.Launcher$ExtClassLoader@4a574795
        System.out.println(classLoader1);

        //调用扩展类加载器的getParent():无法获取引导类加载器
        //引导类加载器主要负责加载java的核心类库，无法加载自定义类的
        ClassLoader classLoader2 = classLoader1.getParent();
        //null
        System.out.println(classLoader2);

        ClassLoader classLoader3 = String.class.getClassLoader();
        //null
        System.out.println(classLoader3);
    }

    /**
     * Properties:用来读取配置文件
     * 
     */
    @Test
    public void t2(){

        FileInputStream fis = null;
        try {
            Properties properties = new Properties();
            //此时的文件默认在当前的module下
            //读取配置文件的方式1：
//            fis = new FileInputStream("jdbc.properties");
//            properties.load(fis);

            /*
            普通的 Java项目,配置文件放置到src下即可
            maven 项目,配置文件放置到main/resources/下
            读取配置文件的方式2：使用ClassLoader
             */
            ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
            InputStream is = classLoader.getResourceAsStream("jdbc1.properties");
            properties.load(new InputStreamReader(is,"UTF-8"));

            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            //user = 马德福,password = 123
            System.out.println("user = " + user + ",password = " + password);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
