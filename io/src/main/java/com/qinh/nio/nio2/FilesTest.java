package com.qinh.nio.nio2;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-06-14:54
 */
public class FilesTest {

    @Test
    public void t1() throws IOException {
        String file = "E:\\ideaWorkspace\\java-basic\\io\\src\\main\\java\\com\\qinh\\nio\\nio2\\FilesTest.java";
        //复制文件
        Files.copy(Paths.get(file),new FileOutputStream("a.txt"));
        //判断FilesTest.java文件是否为隐藏文件
        System.out.println("FilesTest.java是否为隐藏文件: " + Files.isHidden(Paths.get(file)));
        //一次性读取FilesTest.java文件的所有行
        List<String> lines = Files.readAllLines(Paths.get(file), Charset.forName("utf-8"));
        System.out.println(lines);
        //判断指定文件的大小
        System.out.println("FilesTest.java的大小为: " + Files.size(Paths.get(file)));
        //直接将多个字符串内容写入指定文件中
        ArrayList<String> poem = new ArrayList<>();
        poem.add("水晶潭底银鱼跃");
        poem.add("清徐风中碧竿横");
        Files.write(Paths.get("poem.txt"),poem,Charset.forName("utf-8"));
        Files.write(Paths.get("poem.txt"),"123".getBytes("utf-8"), StandardOpenOption.APPEND);
        //列出当前目录下所有文件和子目录
        Files.list(Paths.get(".")).forEach(path -> System.out.println(path));
        //读取文件内容
        Files.lines(Paths.get(file),Charset.forName("utf-8")).forEach(line -> System.out.println(line));
        //判断C盘的总空间、可用空间
        FileStore cStore = Files.getFileStore(Paths.get("C:"));
        System.out.println("C:共有空间: " + cStore.getTotalSpace());
        System.out.println("C:可用空间： " + cStore.getUsableSpace());
    }

    /**
     * 使用FileVisitor遍历文件和目录
     * Files类提供如下两个方法来遍历文件和子目录
     * walkFileTree(Path start ,FileVisitor<? super Path> visitor):遍历start路径下所有的文件和子目录
     * walkFileTree(Path start,Set<FileVisitOption> options,int maxDepth,FileVisitor<? super Path> visitor):与上一个方法的功能类似，该方法最多遍历maxDepth深度的文件
     * 上面两个方法都需要FileVisitor参数，FileVisitor代表一个文件访问器
     * walkFileTree()方法会自动遍历start路径下的所有文件和子目录，遍历文件和子目录都会"触发"FileVisitor中相应的方法
     * FileVisitResult postVisitDirectory(T dir,IOException exc):访问子目录之后触发该方法
     * FileVisitResult visitDirectory(T dir,BasicFileAttribute attrs):访问子目录之前触发该方法
     * FileVisitResult visitFile(T file,BasicFileAttribute attrs):访问file文件时触发该方法
     * FileVisitResult visitFileFailed(T file,IOException exc):访问file文件失败时触发该方法
     *
     * 实际编程通过继承SimpleFileVisitor来实现自己的"文件访问器"
     */
    @Test
    public void t2() throws IOException {

        //遍历E:\ideaWorkspace\project\java_edu\javabasic\io目录下的所有文件和子目录
        Files.walkFileTree(Paths.get("E:","ideaWorkspace","project","java_edu","javabasic","io"),new SimpleFileVisitor<Path>(){

            //访问文件时触发该方法
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("正在访问: " + file + "文件");
                //找到FilesTest.java文件
                if (file.endsWith("FilesTest.java")){
                    System.out.println("目标文件已经找到");
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }

            //开始访问目录时触发该方法
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("正在访问: " + dir + " 路径" );
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * 使用WatchService监控文件变化
     * Path类提供如下方法来监听文件系统的变化
     * register(WatchService watcher,WatchEvent.Kind<?>...events):用watcher监听该path代表的目录下的文件变化。events参数指定要监听哪些类型的事件
     * 在这个方法中WatchService代表一个文件系统监听服务，它负责监听path代表的目录下的文件的变化
     * 一旦使用register()方法完成注册之后，接下来就可调用WatchService的如下三个方法来获取被监听目录的文件变化事件
     * WatchKey poll():获取下一个WatchKey，如果没有WatchKey发生就立即返回null
     * WatchKey poll(long timeout , TimeUnit unit):尝试等待timeout时间去获取下一个WatchKey
     * WatchKey take():获取下一个WatchKey，如果没有WatchKey发生就一直等待
     */
    @Test
    public void t3() throws IOException, InterruptedException {
        //获取文件系统的WatchService对象
        WatchService watchService = FileSystems.getDefault().newWatchService();
        //为E:\ideaWorkspace\project\java_edu\javabasic\io路径注册监听
        Paths.get("E:\\ideaWorkspace\\project\\java_edu\\javabasic\\io")
                .register(watchService
                        ,StandardWatchEventKinds.ENTRY_CREATE
                        ,StandardWatchEventKinds.ENTRY_MODIFY
                        ,StandardWatchEventKinds.ENTRY_DELETE);
        while (true){
            //获取下一个文件变化事件
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()){
                System.out.println(event.context() + " 文件发生了 " + event.kind() + " 事件!");
            }
            //重设WatchKey
            boolean valid = key.reset();
            //如果重设失败，退出监听
            if (!valid){
                break;
            }
        }
    }

    /**
     * 访问文件属性
     */
    @Test
    public void t4() throws Exception{
        //获取将要操作的文件
        Path path = Paths.get("E:\\ideaWorkspace\\project\\java_edu\\javabasic\\io\\src\\main\\java\\com\\qinh\\nio\\nio2\\FilesTest.java");
        //获取访问基本属性的BasicFileAttributeView
        BasicFileAttributeView basicView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        //访问文件的基本属性
        BasicFileAttributes basicFileAttributes = basicView.readAttributes();
        System.out.println("创建时间: " + new Date(basicFileAttributes.creationTime().toMillis()));
        System.out.println("最后访问时间: " + new Date(basicFileAttributes.lastAccessTime().toMillis()));
        System.out.println("最后修改时间: " + new Date(basicFileAttributes.lastModifiedTime().toMillis()));
        System.out.println("文件大小: " + basicFileAttributes.size());
        //获取访问文件属主信息的FileOwnerAttributeView
        FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
        //获取文件所属的用户
        System.out.println(ownerAttributeView.getOwner());
        //获取系统中guest对应的用户
        //UserPrincipal user = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("Qh");
        //修改用户
        //ownerAttributeView.setOwner(user);
        //获取访问自定义属性的UserDefinedFileAttributeView
        UserDefinedFileAttributeView userDefinedFileAttributeView = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
        //添加一个自定义属性
        userDefinedFileAttributeView.write("发行者",Charset.defaultCharset().encode("java联盟"));
        List<String> attrNames = userDefinedFileAttributeView.list();
        //遍历所有的自定义属性
        for (String name : attrNames){
            ByteBuffer byteBuffer = ByteBuffer.allocate(userDefinedFileAttributeView.size(name));
            userDefinedFileAttributeView.read(name,byteBuffer);
            byteBuffer.flip();
            String value = Charset.defaultCharset().decode(byteBuffer).toString();
            System.out.println(name + " ----> " + value);
        }

        //访问DOS属性的DosFileAttributeView
        DosFileAttributeView dosFileAttributeView = Files.getFileAttributeView(path, DosFileAttributeView.class);
        System.out.println("文件是否隐藏: " + dosFileAttributeView.readAttributes().isHidden());
        //将文件设置隐藏、只读
        dosFileAttributeView.setHidden(true);
        dosFileAttributeView.setReadOnly(false);

    }

}
