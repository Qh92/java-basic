package com.qinh.nio;

import org.junit.Test;

import javax.xml.transform.Source;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * 一、通道（Channel）: 用于源节点与目标节点的连接。在Java NIO中负责缓冲区中数据的传输。
 * Channel本身不存储数据，因此需要配合缓冲区进行传输。
 *
 * 二、通道的主要实现类
 *   java.nio.channels.Channel接口：
 *       |--FileChannel
 *       |==SocketChannel
 *       |==ServerSocketChannel
 *       |==DatagramChannel
 *
 * 三、获取通道
 * 1.Java针对支持通道的类提供了 getChannel() 方法
 *      本地IO:
 *      FileInputStream/FileOutputStream
 *      RandomAccessFile
 *      网络IO:
 *      Socket
 *      ServerSocket
 *      DatagramSocket
 *
 * 2.在JDK1.7中的NIO.2针对各个通道提供了静态方法open()
 * 3.在JDK1.7中的NIO.2的Files工具类的newByteChannel()
 *
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 *
 * 五、分散（Scatter）与聚集（Gather）
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *
 * 六、字符集：Charset
 * 编码：字符串 --> 字节数组
 * 解码：字节数组 --> 字符串
 *
 * @author Qh
 * @version 1.0
 * @date 2021-03-06-13:07
 */
public class ChannelTest {


    //1.利用通道完成文件的复制(非直接缓冲区)
    @Test
    public void t1(){
        long start = System.currentTimeMillis();

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("D:\\java学习视频\\11.rar");
            fos = new FileOutputStream("D:\\java学习视频\\111.rar");

            //获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //分配指定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            //将通道中的数据存入缓冲区中
            while (inChannel.read(buffer) != -1){
                //切换读取数据的模式
                buffer.flip();
                //将缓冲区中的数据写入通道中
                outChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outChannel != null)
                outChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (inChannel != null)
                inChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null)
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null)
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));//13089 11106 11588
    }

    //2.使用直接缓冲区完成文件的复制（内存映射文件）
    @Test
    public void t2() throws IOException {
        long start = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get("D:\\java学习视频\\11.rar"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\java学习视频\\111.rar"), StandardOpenOption.WRITE, StandardOpenOption.READ,StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行数据的读写操作
        byte[] dest = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dest);
        outMappedBuf.put(dest);

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));//3385 3636
    }

    //通道之间的数据传输(直接缓冲区)
    @Test
    public void t3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("01.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("0111.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ,StandardOpenOption.CREATE);

//        inChannel.transferTo(0,inChannel.size(),outChannel);
        outChannel.transferFrom(inChannel,0,inChannel.size());

        inChannel.close();
        outChannel.close();
    }

    //分散与聚集
    @Test
    public void t4() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("dbcp.txt", "rw");

        //1.获取通道
        FileChannel channel = raf.getChannel();

        //2.分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        //3.分散读取
        ByteBuffer[] buffer = {buf1,buf2};
        channel.read(buffer);

        for (ByteBuffer b : buffer){
            b.flip();
        }
        System.out.println(new String(buffer[0].array(),0,buffer[0].limit()));
        System.out.println("---------------------");
        System.out.println(new String(buffer[1].array(),0,buffer[1].limit()));

        //4.聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();
        channel2.write(buffer);

        channel.close();
        channel2.close();
    }

    //字符集
    @Test
    public void t5(){
        SortedMap<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = map.entrySet();
        for (Map.Entry<String, Charset> entry : entries){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @Test
    public void t6() throws CharacterCodingException {
        Charset charset1 = Charset.forName("GBK");

        //获取编码器
        CharsetEncoder encoder = charset1.newEncoder();

        //获取解码器
        CharsetDecoder decoder = charset1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("钢铁侠绿巨人");
        cBuf.flip();

        //编码
        ByteBuffer byteBuffer = encoder.encode(cBuf);

        for (int i = 0; i < 12; i++) {
            System.out.println(byteBuffer.get());
        }
        //解码
        byteBuffer.flip();
        CharBuffer charBuffer = decoder.decode(byteBuffer);
        System.out.println(charBuffer.toString());

        System.out.println("--------------------------");
        Charset charset2 = Charset.forName("UTF-8");
        byteBuffer.flip();
        CharBuffer charBuffer1 = charset2.decode(byteBuffer);
        System.out.println(charBuffer1.toString());

    }

    @Test
    public void t11(){
        File file = new File("E:\\ideaWorkspace\\iotest\\a.txt");
        try (
            //创建FileChannelStream,以该文件输入流创建FileChannel
            FileChannel inChannel = new FileInputStream(file).getChannel();
            //以文件输出流创建FileChannel,用以控制输出
            FileChannel outChannel = new FileOutputStream("E:\\ideaWorkspace\\iotest\\b.txt").getChannel()
        )   {
            //将FileChannel里的全部数据映射成ByteBuffer
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            //使用GBK的字符集来创建解码器
            Charset charset = Charset.forName("utf-8");
            //直接将buffer里的数据全部输出
            outChannel.write(buffer);
            //再次调用buffer的clear()方法，复原limit,position的位置
            buffer.clear();
            //创建解码器CharsetDecoder对象
            CharsetDecoder charsetDecoder = charset.newDecoder();
            //使用解码器将ByteBuffer转换为CharBuffer
            CharBuffer charBuffer = charsetDecoder.decode(buffer);
            //CharBuffer的toString()方法可以获取对应的字符串
            System.out.println(charBuffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t21(){
        File file = new File("E:\\ideaWorkspace\\iotest\\a.txt");
        try (
            //创建一个RandomAccessFile对象
            RandomAccessFile raf = new RandomAccessFile(file,"rw");
            //获取RandomAccessFile对应的Channel
            FileChannel randomChannel = raf.getChannel()
        ){
            //将Channel中的所有数据映射成ByteBuffer
            MappedByteBuffer buffer = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            //把Channel的记录指针移动到最后
            randomChannel.position(file.length());
            //将buffer中的所有数据输出
            randomChannel.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多次读取文件数据
     */
    @Test
    public void t31(){
        try (
            //创建文件输入流
            FileInputStream fis = new FileInputStream("E:\\ideaWorkspace\\iotest\\b.txt");
            //创建一个FileChannel对象
            FileChannel channel = fis.getChannel()
        ){
            //定义一个ByteBuffer对象，用于重复取水
            ByteBuffer byteBuffer = ByteBuffer.allocate(256);
            //将FileChannel中的数据放入ByteBuffer中
            while (channel.read(byteBuffer) != -1){
                //锁定Buffer的空白区
                byteBuffer.flip();
                //创建Charset对象
                Charset charset = Charset.forName("UTF-8");
                //创建解码器CharsetDecoder对象
                CharsetDecoder charsetDecoder = charset.newDecoder();
                //将ByteBuffer的内容转码
                CharBuffer charBuffer = charsetDecoder.decode(byteBuffer);
                System.out.println(charBuffer);
                //将Buffer初始化，为下一次读取数据做准备
                byteBuffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
