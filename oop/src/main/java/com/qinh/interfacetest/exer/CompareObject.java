package com.qinh.interfacetest.exer;
/*
 * interface CompareObject{
	public int compareTo(Object o);   
	//若返回值是 0 , 代表相等; 若为正数，代表当前对象大；负数代表当前对象小
 }

 */
public interface CompareObject {
	//若返回值是 0 , 代表相等; 若为正数，代表当前对象大；负数代表当前对象小
	public int compareTo(Object o); 
}
