
//AtomicInteger
private volatile int value;
//AtomicInteger#getAndIncrement()
public final int getAndIncrement() {
	/*
	this:当前对象
	valueOffset:内存偏移量
	 */
    return unsafe.getAndAddInt(this, valueOffset, 1);
}

//Unsafe#getAndAddInt(Object var1, long var2, int var4)
public final int getAndAddInt(Object var1, long var2, int var4) {
    int var5;
    do {
    	//var5:主内存中实际的值
        var5 = this.getIntVolatile(var1, var2);
        //当前对象值与主内存中的值进行比较，如果为false则一直与主内存的值进行比较
    } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

    return var5;
}

//Unsafe#getIntVolatile(Object var1, long var2)
//var1:当前对象 var2:内存偏移量
public native int getIntVolatile(Object var1, long var2);

//Unsafe#compareAndSwapInt(Object var1, long var2, int var4, int var5)
//var1:当前对象 var2:对象值的引用地址 var4:需要变动的数量 var5:使用var1,var2找出的主内存中真实的值
//当前对象中的值和主内存中的值进行比较，相等为true
public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);