import org.junit.Test;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-04-07-19:23
 */
public class StringTest {

    /**
     * String:字符串，使用一对""引起来表示
     * 1.String声明为final的，不可被继承
     * 2.String实现了Serializable接口，表示字符串是支持序列化的
     *          实现了Comparable接口，表示String是可比较大小的
     * 3.String内部定义了final char[] value用于存储字符串数据
     * 4.String:代表不可变的字符序列。简称:不可变性
     *   体现：1.当对字符串重新赋值时，需要重新指定内存区域赋值，不能使用原有的value进行赋值
     *        2.当对现有字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值
     *        3.当调用String的replace()方法修改指定字符或字符串时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值
     * 5.通过字面量的方式(区别于new)给一个字符串赋值，此时的字符串声明在字符串常量池中
     * 6.字符串常量池中是不会存储相同内容的字符串的
     */
    @Test
    public void t1(){
        //字面量的定义方式
        String s1 = "abc";
        String s2 = "abc";
        //true
        System.out.println(s1 == s2);
        s1 = "hello";
        //hello
        System.out.println(s1);
        //abc
        System.out.println(s2);
        System.out.println("****");
        String s3 = "abc";
        s3 += "def";
        //abcdef
        System.out.println(s3);
        System.out.println(s2);
        System.out.println("*****");
        String s4 = "abc";
        String s5 = s4.replace('a','m');
        //abc
        System.out.println(s4);
        //mbc
        System.out.println(s5);
    }

    /**
     * String实例化的方式
     * 方式1：通过字面量定义的方式
     * 方式2：通过new + 构造器的放式
     *
     * 面试题：String s = new String("abc");方式创建对象，在内存中创建了几个对象?
     * 如果字符串常量池中没有"abc"时，创建了两个对象，一个是堆空间中new结构，另一个是char[]对应的常量池中的数据："abc"
     */
    @Test
    public void t2(){
        //通过字面量定义的方式：此时的s1和s2的数据javaEE声明在方法区中的字符串常量池中
        String s1 = "javaEE";
        String s2 = "javaEE";
        //通过new + 构造器的方式：此时的s3和s4保存的地址值，是数据在堆空间中开辟空间以后对应的地址值
        String s3 = new String("javaEE");
        String s4 = new String("javaEE");
        //true
        System.out.println(s1 == s2);
        //false
        System.out.println(s1 == s3);
        //false
        System.out.println(s1 == s4);
        //false
        System.out.println(s3 == s4);
        System.out.println("******");
        Person p1 = new Person("Tom",12);
        Person p2 = new Person("Tom",12);
        //true
        System.out.println(p1.name.equals(p2.name));
        //true
        System.out.println(p1.name == p2.name);
        p1.name = "Jerry";
        //Tom
        System.out.println(p2.name);
    }

    /**
     * 结论：
     * 1.常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量
     * 2.如果其中有一个是变量，结果就在堆中
     * 3.如果拼接的结果调用intern()方法，返回值就在常量池中
     */
    @Test
    public void t3(){
        String s1 = "javaEE";
        String s2 = "hadoop";
        String s3 = "javaEEhadoop";
        String s4 = "javaEE" + "hadoop";
        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;
        //true
        System.out.println(s3 == s4);
        //false
        System.out.println(s3 == s5);
        //false
        System.out.println(s3 == s6);
        //false
        System.out.println(s3 == s7);
        //false
        System.out.println(s5 == s6);
        //false
        System.out.println(s5 == s7);
        //false
        System.out.println(s6 == s7);

        String s8 = s6.intern();
        //true
        System.out.println(s3 == s8);
    }

    @Test
    public void t4(){
        String s1 = "javaEEhadoop";
        String s2 = "javaEE";
        String s3 = s2 + "hadoop";
        //false
        System.out.println(s1 == s3);
        //s4:常量
        final String s4 = "javaEE";
        String s5 = s4 + "hadoop";
        //true
        System.out.println(s1 == s5);
    }
}
