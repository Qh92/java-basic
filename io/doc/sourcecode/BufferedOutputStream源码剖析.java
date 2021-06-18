public void t1(){
    BufferedInputStream bufferedInputStream = null;
    BufferedOutputStream bufferedOutputStream = null;
    try {
        //1.造文件
        File srcFile = new File("01.jpg");
        File destFile = new File("011.jpg");
        //2.造流
        //2.1 造节点流
        FileInputStream fileInputStream = new FileInputStream(srcFile);
        FileOutputStream fileOutputStream = new FileOutputStream(destFile);
        //2.2 造缓冲流
        bufferedInputStream = new BufferedInputStream(fileInputStream);
        bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

        //3.复制的细节：读取、写入
        byte[] buffer = new byte[10];
        int len;
        while ((len = bufferedInputStream.read(buffer)) != -1){
            bufferedOutputStream.write(buffer,0,len);
            //bufferedOutputStream.flush();//刷新缓冲区
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        //4.资源关闭
        //要求：先关闭外层的流，再关闭内层的流
        try {
            if(bufferedOutputStream != null)
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(bufferedInputStream != null)
            bufferedInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //说明：关闭外层流的同时，内层流也会自动的进行关闭。关于内层流的关闭，可以省略
        /*fileOutputStream.close();
        fileInputStream.close();*/
    }
}

//初始化输出流，默认缓冲流数组长度8192字节
//BufferedOutputStream构造器
protected byte buf[];
public BufferedOutputStream(OutputStream out) {
    this(out, 8192);
}
public BufferedOutputStream(OutputStream out, int size) {
    super(out);
    if (size <= 0) {
        throw new IllegalArgumentException("Buffer size <= 0");
    }
    buf = new byte[size];
}

//FilterOutputStream#FilterOutputStream(OutputStream out)
protected OutputStream out;
public FilterOutputStream(OutputStream out) {
    this.out = out;
}


//BufferedOutputStream#write(byte b[], int off, int len)
public synchronized void write(byte b[], int off, int len) throws IOException {
    //如果自己设置的缓冲数组buffer长度大于缓冲流中的缓冲数组长度，则直接将数据写入到磁盘等
    if (len >= buf.length) {
        /* If the request length exceeds the size of the output buffer,
           flush the output buffer and then write the data directly.
           In this way buffered streams will cascade harmlessly. */
        flushBuffer();
        out.write(b, off, len);
        return;
    }
    //缓冲流中的缓冲数组快被写满时，则一次将缓冲数组中的数据写入到磁盘
    if (len > buf.length - count) {
        flushBuffer();
    }
    //将读取的数据buffer写入到缓冲数组中
    System.arraycopy(b, off, buf, count, len);
    count += len;
}

//一次将缓冲区中的数据写入到磁盘
//BufferedOutputStream#flushBuffer()
private void flushBuffer() throws IOException {
    if (count > 0) {
        out.write(buf, 0, count);
        count = 0;
    }
}