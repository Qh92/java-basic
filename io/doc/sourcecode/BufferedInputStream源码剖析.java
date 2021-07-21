//BufferedInputStream、BufferedInputStream使用
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
        bufferedOutputStream = new BufferedInputStream(fileOutputStream);

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

/*
假设需要读取的文件为10000字节 缓存为8192字节，每次读取 10字节
当FileInputStream/BufferedInputStream每次读取的字节数小于8192，BufferedInputStream效率更高一点
当每次读取的字节数接近8192或者远超这个值，两者效率相差不大

 */
//BufferedInputStream#read(byte[] buffer) -> FilterInputStream#read(byte b[])
public int read(byte b[]) throws IOException {
    //read(byte[10] b,0,10)
    return read(b, 0, b.length);
}

//虚拟方法调用实际调用子类BufferedInputStream的方法 
//BufferedInputStream#read(byte b[], int off, int len)
public synchronized int read(byte b[], int off, int len)
    throws IOException
{
    getBufIfOpen(); // Check for closed stream
    if ((off | len | (off + len) | (b.length - (off + len))) < 0) {
        throw new IndexOutOfBoundsException();
    } else if (len == 0) {
        return 0;
    }

    int n = 0;
    for (;;) {
        //read1(byte[10] b,0,10)
        //nread = 10
        int nread = read1(b, off + n, len - n);
        if (nread <= 0)
            return (n == 0) ? nread : n;
        //n = 10
        n += nread;
        if (n >= len)
            return n;
        // if not closed but no bytes available, return
        InputStream input = in;
        if (input != null && input.available() <= 0)
            return n;
    }
}

//BufferedInputStream#getBufIfOpen()
//获取字节数组 默认byte[] buffer = new byte[8192]
private byte[] getBufIfOpen() throws IOException {
    byte[] buffer = buf;
    if (buffer == null)
        throw new IOException("Stream closed");
    return buffer;
}

//BufferedInputStream#getInIfOpen()
//获取文件流即FileInputStream
private InputStream getInIfOpen() throws IOException {
    InputStream input = in;
    if (input == null)
        throw new IOException("Stream closed");
    return input;
}

//BufferedInputStream#read1(byte[] b, int off, int len)
private int read1(byte[] b, int off, int len) throws IOException {
    //第一次读取时，count = 0(默认值),pos = 0
    //第二次读取时，count = 8192, pos = 10
    //...
    //读取到最后时，count = 8192,pos = 8190
    int avail = count - pos;
    if (avail <= 0) {
        /* If the requested length is at least as large as the buffer, and
           if there is no mark/reset activity, do not bother to copy the
           bytes into the local buffer.  In this way buffered streams will
           cascade harmlessly. */
        if (len >= getBufIfOpen().length && markpos < 0) {
            return getInIfOpen().read(b, off, len);
        }
        fill();
        //avail = 8192
        avail = count - pos;
        if (avail <= 0) return -1;
    }
    //cnt = 10
    //...最后一次 cnt = 2
    int cnt = (avail < len) ? avail : len;
    //从缓冲数组中从下标为0的字节开始读取10个字节到之前传入的buffer数组中
    //第二次读取数据时从缓冲数组的下标为10开始读取10个字节到buffer数组中，依次类推，直到把缓冲数组读取完
    System.arraycopy(getBufIfOpen(), pos, b, off, cnt);
    //pos = 10
    //pos = 20
    //....
    //pos = 8192
    pos += cnt;
    return cnt;
}

//BufferedInputStream#fill()
private void fill() throws IOException {
    //获取缓冲字节数组
    byte[] buffer = getBufIfOpen();
    //第一次读取时，markpos = -1
    if (markpos < 0)
        pos = 0;            /* no mark: throw away the buffer */
    else if (pos >= buffer.length)  /* no room left in buffer */
        if (markpos > 0) {  /* can throw away early part of the buffer */
            int sz = pos - markpos;
            System.arraycopy(buffer, markpos, buffer, 0, sz);
            pos = sz;
            markpos = 0;
        } else if (buffer.length >= marklimit) {
            markpos = -1;   /* buffer got too big, invalidate mark */
            pos = 0;        /* drop buffer contents */
        } else if (buffer.length >= MAX_BUFFER_SIZE) {
            throw new OutOfMemoryError("Required array size too large");
        } else {            /* grow buffer */
            int nsz = (pos <= MAX_BUFFER_SIZE - pos) ?
                    pos * 2 : MAX_BUFFER_SIZE;
            if (nsz > marklimit)
                nsz = marklimit;
            byte nbuf[] = new byte[nsz];
            System.arraycopy(buffer, 0, nbuf, 0, pos);
            if (!bufUpdater.compareAndSet(this, buffer, nbuf)) {
                // Can't replace buf if there was an async close.
                // Note: This would need to be changed if fill()
                // is ever made accessible to multiple threads.
                // But for now, the only way CAS can fail is via close.
                // assert buf == null;
                throw new IOException("Stream closed");
            }
            buffer = nbuf;
        }
    count = pos;
    //从文件中读取8192字节到缓冲字节数组中 n = 8192
    int n = getInIfOpen().read(buffer, pos, buffer.length - pos);
    if (n > 0)
        //count = 8192
        count = n + pos;
}

/*
public static void arraycopy(Object src,int srcPos,Object dest,int destPos,int length)
从指定源数组中复制一个数组，复制从指定的位置开始，到目标数组的指定位置结束。
从 src 引用的源数组到 dest 引用的目标数组，数组组件的一个子序列被复制下来。被复制的组件的编号等于 length 参数。
源数组中位置在 srcPos 到 srcPos+length-1 之间的组件被分别复制到目标数组中的 destPos 到 destPos+length-1 位置。

如果参数 src 和 dest 引用相同的数组对象，则复制的执行过程就好像首先将 srcPos 到 srcPos+length-1 位置的组件复制到一个带有 length 组件的临时数组，然后再将此临时数组的内容复制到目标数组的 destPos 到 destPos+length-1 位置一样。
If 如果 dest 为 null，则抛出 NullPointerException 异常。
如果 src 为 null, 则抛出 NullPointerException 异常，并且不会修改目标数组。
*/
