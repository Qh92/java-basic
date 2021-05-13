
package java.lang;


public class Object {

    private static native void registerNatives();
    static {
        registerNatives();
    }

    /**
     * 当前对象属性哪个类
     *
     * @return
     */
    public final native Class<?> getClass();

    /**
     * 计算hash值
     *
     * @return
     */
    public native int hashCode();

    /**
     * 默认也是 == 比较
     * ==和equals的区别：
     * == 基本数据类型，比较值是否相等；引用数据类型，比较地址值是否相等
     * equals 是在Object中定义的方法，如果没有重写该方法，相当于还是==比较
     *          如果重写了，一般都是比较属性是否相等
     *
     * @param obj
     * @return
     */
    public boolean equals(Object obj) {
        return (this == obj);
    }

    
    protected native Object clone() throws CloneNotSupportedException;

    
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    /**
     * 唤醒线程
     */
    public final native void notify();

    
    public final native void notifyAll();

    /**
     * 阻塞线程
     *
     * @param timeout
     * @throws InterruptedException
     */
    public final native void wait(long timeout) throws InterruptedException;

    
    public final void wait(long timeout, int nanos) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                                "nanosecond timeout value out of range");
        }

        if (nanos > 0) {
            timeout++;
        }

        wait(timeout);
    }

    
    public final void wait() throws InterruptedException {
        wait(0);
    }

    /**
     * 跟gc有关
     *
     * @throws Throwable
     */
    protected void finalize() throws Throwable { }
}
