package com.qinh.map;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Qh
 * @version 1.0
 * @date 2020-12-15-20:49
 */
public class PropertiesTest {

    //Properties:常用来处理配置文件。key和value都是String类型
    public static void main(String[] args)  {
        FileInputStream fis = null;
        try {
            Properties properties = new Properties();
            fis = new FileInputStream("jdbc.properties");
            properties.load(fis);//加载流对应的文件
            String name = properties.getProperty("name");
            String password = properties.getProperty("password");
            System.out.println("name = "+name+" password = "+password);
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
