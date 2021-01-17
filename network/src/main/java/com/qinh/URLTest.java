package com.qinh;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL网络编程
 * 1.URL：统一资源定位符，对应着互联网的某一资源地址
 * 2.格式：
 *   http://192.168.1.100:8080/helloworld/index.jsp#a?username=shkstart&password=123
 *   协议   主机名        端口号  资源地址              参数列表
 *
 * @author Qh
 * @version 1.0
 * @date 2021-01-17-21:52
 */
public class URLTest {

    /*
    public String getProtocol(  )     获取该URL的协议名
    public String getHost(  )        获取该URL的主机名
    public String getPort(  )        获取该URL的端口号
    public String getPath(  )        获取该URL的文件路径
    public String getFile(  )         获取该URL的文件名
    public String getQuery(   )      获取该URL的查询名
     */
    public static void main(String[] args) {
        try {
            URL url = new URL("http://192.168.1.100:8080/helloworld/index.jsp");
            System.out.println(url.getProtocol());//获取该URL的协议名 http
            System.out.println(url.getHost());//获取该URL的主机名  192.168.1.100
            System.out.println(url.getPort());//获取该URL的端口号  8080
            System.out.println(url.getPath());//获取该URL的文件路径  /helloworld/index.jsp
            System.out.println(url.getFile());//获取该URL的文件名   /helloworld/index.jsp?username=shkstart&password=123
            System.out.println(url.getQuery());//获取该URL的查询名  username=shkstart&password=123
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
