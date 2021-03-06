package com.qinh.nio.nio2;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Path接口代表一个平台无关的平台路径
 *
 * @author Qh
 * @version 1.0
 * @date 2021-03-06-14:42
 */
public class PathTest {

    @Test
    public void t1(){
        //以当前路径来创建Path对象
        Path path = Paths.get(".");
        System.out.println("path里包含的路径数量: " + path.getNameCount());
        System.out.println("path的根路径: " + path.getRoot());
        //获取path对应的绝对路径
        System.out.println(path.toAbsolutePath());
        //获取绝对路径的根路径
        System.out.println("absolutePath的根路径: " + path.toAbsolutePath().getRoot());
        //获取绝对路径所包含的路径数量
        System.out.println("absolutePath里包含的路径数量: " + path.toAbsolutePath().getNameCount());
        System.out.println(path.toAbsolutePath().getName(0));
        //以多个String来构建Path对象
        Path path1 = Paths.get("g:", "publish", "codes");
        System.out.println(path1);
        System.out.println(path1.getNameCount());
    }
}
