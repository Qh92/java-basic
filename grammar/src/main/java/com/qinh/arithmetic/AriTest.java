package com.qinh.arithmetic;
/*
运算符之一：算术运算符
+ - + - * / % (前)++ (后)++ (前)-- (后)-- +


*/
public class AriTest {
	public static void main(String[] args) {

		//除号：/
		/*
		如果除法运算符的两个操作数都是整数类型，则计算结果也是整数，就是将自然除法的结果截断取整
		如果除法运算符的两个操作都是整数类型，则除数不可以是0，否则将引发除以0异常
		如果除法运算符的两个操作数有一个是浮点数，或者两个都是浮点数，则计算结果也是浮点数，这个结果就是自然除法的结果
		此时允许除数是0，或者0.0，得到结果是正无穷大或负无穷大。
		 */
		int num1 = 12;
		int num2 = 5;
		int result1 = num1 / num2;
		System.out.println(result1);//2

		int result2 = num1 / num2 * num2;
		System.out.println(result2);//10

		double result3 = num1 / num2;
		System.out.println(result3);//2.0

		double result4 = num1 / num2 + 0.0;//2.0
		double result5 = num1 / (num2 + 0.0);//2.4
		double result6 = (double)num1 / num2;//2.4
		double result7 = (double)(num1 / num2);//2.0
		System.out.println(result5);
		System.out.println(result6);
		System.out.println("无穷大: " + 1.0 / 0);

		// %:取余运算
		//结果的符号与被模数的符号相同
		//开发中，经常使用%来判断能否被除尽的情况。
		int m1 = 12;
		int n1 = 5;
		System.out.println("m1 % n1 = " + m1 % n1);

		int m2 = -12;
		int n2 = 5;
		System.out.println("m2 % n2 = " + m2 % n2);

		int m3 = 12;
		int n3 = -5;
		System.out.println("m3 % n3 = " + m3 % n3);

		int m4 = -12;
		int n4 = -5;
		System.out.println("m4 % n4 = " + m4 % n4);


		//(前)++ :先自增1，后运算
		//(后)++ :先运算，后自增1
		int a1 = 10;
		int b1 = ++a1;
		System.out.println("a1 = " + a1 + ",b1 = " + b1);

		int a2 = 10;
		int b2 = a2++;
		System.out.println("a2 = " + a2 + ",b2 = " + b2);

		int a3 = 10;
		++a3;//a3++;
		int b3 = a3;

		//注意点：
		short s1 = 10;
		//s1 = s1 + 1;//编译失败
		//s1 = (short)(s1 + 1);//正确的
		s1++;//自增1不会改变本身变量的数据类型
		System.out.println(s1);

		//问题：
		byte bb1 =127;
		bb1++;
		System.out.println("bb1 = " + bb1);

		//(前)-- :先自减1，后运算
		//(后)-- :先运算，后自减1

		int a4 = 10;
		int b4 = a4--;//int b4 = --a4;
		System.out.println("a4 = " + a4 + ",b4 = " + b4);

	}
}
