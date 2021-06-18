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


//BufferedReader
private static int defaultCharBufferSize = 8192;
private Reader in;
private boolean skipLF = false;
private int markedChar = UNMARKED;

public BufferedReader(Reader in) {
    this(in, defaultCharBufferSize);
}
public BufferedReader(Reader in, int sz) {
    super(in);
    if (sz <= 0)
        throw new IllegalArgumentException("Buffer size <= 0");
    this.in = in;
    cb = new char[sz];
    nextChar = nChars = 0;
}

//Reader
protected Reader(Object lock) {
    if (lock == null) {
        throw new NullPointerException();
    }
    this.lock = lock;
}

//BufferedReader#readLine()
public String readLine() throws IOException {
    return readLine(false);
}

//BufferedReader#readLine(boolean ignoreLF)
String readLine(boolean ignoreLF) throws IOException {
    StringBuffer s = null;
    int startChar;

    synchronized (lock) {
        ensureOpen();
        //omitLF = false
        boolean omitLF = ignoreLF || skipLF;

    bufferLoop:
        for (;;) {

            if (nextChar >= nChars)
            	//填充缓冲区，nextChar = 0,nChars = 8192
                fill();
            if (nextChar >= nChars) { /* EOF */
                if (s != null && s.length() > 0)
                    return s.toString();
                else
                    return null;
            }
            boolean eol = false;
            char c = 0;
            int i;

            /* Skip a leftover '\n', if necessary */
            if (omitLF && (cb[nextChar] == '\n'))
                nextChar++;
            skipLF = false;
            omitLF = false;

        charLoop:
        	//检查是否有换行或者回车，如果有就将读取到换行或回车的数据返回回去
        	//如果缓冲区里没有换行或回车，startChar = 0,nextChar = 8192,s里面含有整个缓冲区字符串
        	//然后再次外层循环判断，nextChar = 8192 , nChars = 8192,再次开始往缓冲区填充数据
            for (i = nextChar; i < nChars; i++) {
                c = cb[i];
                if ((c == '\n') || (c == '\r')) {
                    eol = true;
                    break charLoop;
                }
            }

            startChar = nextChar;
            nextChar = i;

            if (eol) {
                String str;
                if (s == null) {
                    str = new String(cb, startChar, i - startChar);
                } else {
                    s.append(cb, startChar, i - startChar);
                    str = s.toString();
                }
                nextChar++;
                if (c == '\r') {
                    skipLF = true;
                }
                return str;
            }

            if (s == null)
                s = new StringBuffer(defaultExpectedLineLength);
            s.append(cb, startChar, i - startChar);
        }
    }
}

//BufferedReader#ensureOpen()
//判断输入流是否关闭
private void ensureOpen() throws IOException {
    if (in == null)
        throw new IOException("Stream closed");
}


//BufferedReader#fill()
//一次将缓冲区填充满
private void fill() throws IOException {
    int dst;
    if (markedChar <= UNMARKED) {
        /* No mark */
        dst = 0;
    } else {
        /* Marked */
        int delta = nextChar - markedChar;
        if (delta >= readAheadLimit) {
            /* Gone past read-ahead limit: Invalidate mark */
            markedChar = INVALIDATED;
            readAheadLimit = 0;
            dst = 0;
        } else {
            if (readAheadLimit <= cb.length) {
                /* Shuffle in the current buffer */
                System.arraycopy(cb, markedChar, cb, 0, delta);
                markedChar = 0;
                dst = delta;
            } else {
                /* Reallocate buffer to accommodate read-ahead limit */
                char ncb[] = new char[readAheadLimit];
                System.arraycopy(cb, markedChar, ncb, 0, delta);
                cb = ncb;
                markedChar = 0;
                dst = delta;
            }
            nextChar = nChars = delta;
        }
    }

    int n;
    do {
    	//将缓冲区填充满 n = 8192
        n = in.read(cb, dst, cb.length - dst);
    } while (n == 0);
    if (n > 0) {
    	//nChars = 8192
        nChars = dst + n;
        //nextChar = 0
        nextChar = dst;
    }
}