package com.qinh.exer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-01-02-9:59
 */
public class FileDemo {

    /*
    利用File构造器，new一个文件目录file
    1)在其中创建多个文件或目录
    2)编写方法，实现删除file中指定文件的操作
     */
    @Test
    public void t1() throws IOException {
        File file = new File("E:\\ideaWorkspace\\project\\java_edu\\javabasic\\io\\test2\\hello.txt");
        //创建一个与file同目录下的另一个文件，文件名为hi.txt
        File file1 = new File(file.getParent(), "hi.txt");
        boolean newFile = file1.createNewFile();
        if (newFile){
            System.out.println("创建成功");
        }
        //创建多个目录
        File de1 = new File(file.getParent(),"de1");
        File de2 = new File(file.getParent(),"de2");
        boolean mkdir1 = de1.mkdir();
        boolean mkdir2 = de2.mkdir();
        if(mkdir1){
            System.out.println("目录de1创建成功");
        }
        if(mkdir2){
            System.out.println("目录de2创建成功");
        }

        File file2 = new File(file.getParent() , "de2");
        boolean delete = file2.delete();
        if(delete){
            System.out.println("de2删除成功");
        }
    }
    /*
    判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称
     */
    @Test
    public void t2(){
        File file = new File("E:\\ideaWorkspace\\project\\java_edu\\javabasic\\io\\test2");
        String[] names = file.list();
        for(String name : names){
            if(name.endsWith(".jpg")){
                System.out.println(name);
            }
        }
    }

    /*
    遍历指定目录所有文件名称，包括子文件目录中的文件。
    扩展1：并计算指定目录占用空间的大小
    扩展2：删除指定文件目录及其下的所有文件
     */
    @Test
    public void t3(){
        File file = new File("E:\\ideaWorkspace\\project\\java_edu\\javabasic\\io\\test2");
        printSubFile(file);
    }

    public static void printSubFile(File dir){
        //打印目录的子文件
        File[] subFiles = dir.listFiles();
        for(File f : subFiles){
            //文件目录
            if(f.isDirectory()){
                printSubFile(f);
            //文件
            }else{
                System.out.println(f.getAbsoluteFile());
            }
        }
    }

}
