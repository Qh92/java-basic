public void t3(){
    BufferedReader br = null;
    BufferedWriter bw = null;
    try {
        //创建文件和相应的流
        br = new BufferedReader(new FileReader(new File("dbcp.txt")));
        bw = new BufferedWriter(new FileWriter(new File("dbcp1.txt")));

        //读写操作
        //方式1：使用char[]数组
        /*char[] buffer = new char[1024];
        int len;
        while ((len = br.read(buffer)) != -1){
            bw.write(buffer,0,len);
            //bw.flush();
        }*/
        //方式2：使用String
        String data;
        while ((data = br.readLine()) != null){
            //方式1
            //bw.write(data + "\n");//data中不包含换行符
            //方式2
            System.out.println(data);
            bw.write(data);
            bw.newLine();//提供换行的操作
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if(br != null)
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(bw != null)
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//BufferedWriter
private Writer out;
private char cb[];
private int nChars, nextChar;
private static int defaultCharBufferSize = 8192;
private String lineSeparator;

public BufferedWriter(Writer out) {
    this(out, defaultCharBufferSize);
}

public BufferedWriter(Writer out, int sz) {
    super(out);
    if (sz <= 0)
        throw new IllegalArgumentException("Buffer size <= 0");
    this.out = out;
    cb = new char[sz];
    nChars = sz;
    nextChar = 0;

    lineSeparator = java.security.AccessController.doPrivileged(
        new sun.security.action.GetPropertyAction("line.separator"));
}

//Writer
protected Object lock;
protected Writer(Object lock) {
    if (lock == null) {
        throw new NullPointerException();
    }
    this.lock = lock;
}

//Writer#write(String str)
public void write(String str) throws IOException {
    write(str, 0, str.length());
}

//BufferedWriter#write(String s, int off, int len)
public void write(String s, int off, int len) throws IOException {
    synchronized (lock) {
        ensureOpen();

        //b = 0,t = str.length()
        int b = off, t = off + len;
        while (b < t) {
            //nChars = 8192,nextChar = 0
            int d = min(nChars - nextChar, t - b);
            //将s字符串填充进缓冲区
            s.getChars(b, b + d, cb, nextChar);
            b += d;
            //将nextChar移动至当前字符串长度
            nextChar += d;
            //依次类推，直到缓冲区数据即将填满，然后一次性写入到磁盘
            if (nextChar >= nChars)
                flushBuffer();
        }
    }
}

//BufferedWriter#ensureOpen()
private void ensureOpen() throws IOException {
    if (out == null)
        throw new IOException("Stream closed");
}

//BufferedWriter#min(int a, int b)
private int min(int a, int b) {
    if (a < b) return a;
    return b;
}

//String#getChars(int srcBegin, int srcEnd, char dst[], int dstBegin)
public void getChars(int srcBegin, int srcEnd, char dst[], int dstBegin) {
    if (srcBegin < 0) {
        throw new StringIndexOutOfBoundsException(srcBegin);
    }
    if (srcEnd > value.length) {
        throw new StringIndexOutOfBoundsException(srcEnd);
    }
    if (srcBegin > srcEnd) {
        throw new StringIndexOutOfBoundsException(srcEnd - srcBegin);
    }
    System.arraycopy(value, srcBegin, dst, dstBegin, srcEnd - srcBegin);
}

//BufferedWriter#flushBuffer()
void flushBuffer() throws IOException {
    synchronized (lock) {
        ensureOpen();
        if (nextChar == 0)
            return;
        out.write(cb, 0, nextChar);
        nextChar = 0;
    }
}