public void t2(){
    InputStreamReader isr = null;
    OutputStreamWriter osw = null;
    try {
        File file1 = new File("dbcp.txt");
        File file2 = new File("dbcp_gbk.txt");

        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        isr = new InputStreamReader(fis, "UTF-8");
        osw = new OutputStreamWriter(fos, "gbk");

        char[] cbuf = new char[20];
        int len;
        while ((len = isr.read(cbuf)) != -1){
            osw.write(cbuf,0,len);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if(isr != null)
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(osw != null)
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//InputStreamReader
private final StreamDecoder sd;
public InputStreamReader(InputStream in, String charsetName)
        throws UnsupportedEncodingException
{
    super(in);
    if (charsetName == null)
        throw new NullPointerException("charsetName");
    sd = StreamDecoder.forInputStreamReader(in, this, charsetName);
}

//Reader
protected Object lock;
protected Reader(Object lock) {
    if (lock == null) {
        throw new NullPointerException();
    }
    this.lock = lock;
}

//Reader#read(char cbuf[])
public int read(char cbuf[]) throws IOException {
    return read(cbuf, 0, cbuf.length);
}

//InputStreamReader#read(char cbuf[], int offset, int length)
public int read(char cbuf[], int offset, int length) throws IOException {
    return sd.read(cbuf, offset, length);
}

//StreamDecoder#read(char[] var1, int var2, int var3)
private char leftoverChar;
public int read(char[] var1, int var2, int var3) throws IOException {
    int var4 = var2;
    int var5 = var3;
    synchronized(this.lock) {
        this.ensureOpen();
        if (var4 >= 0 && var4 <= var1.length && var5 >= 0 && var4 + var5 <= var1.length && var4 + var5 >= 0) {
            if (var5 == 0) {
                return 0;
            } else {
                byte var7 = 0;
                if (this.haveLeftoverChar) {
                    var1[var4] = this.leftoverChar;
                    ++var4;
                    --var5;
                    this.haveLeftoverChar = false;
                    var7 = 1;
                    if (var5 == 0 || !this.implReady()) {
                        return var7;
                    }
                }

                if (var5 == 1) {
                    int var8 = this.read0();
                    if (var8 == -1) {
                        return var7 == 0 ? -1 : var7;
                    } else {
                        var1[var4] = (char)var8;
                        return var7 + 1;
                    }
                } else {
                    return var7 + this.implRead(var1, var4, var4 + var5);
                }
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
}