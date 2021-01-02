import java.util.ArrayList;
import java.util.List;

/**
 *
 * 自定义的泛型类
 * 注意点：
 * 1.泛型类可能有多个参数，此时应将多个参数一起放在尖括号内。比如：<E1,E2,E3>
 * 2.泛型类的构造器如下：public GenericClass(){} 这样是错误的：public GenericClass<E>(){}
 * 3.实例化后，操作原来的泛型位置的结构必须与指定的泛型类型一致
 * 4.泛型不同的引用不能相互赋值。尽管在编译时ArrayList<String>和ArrayList<Integer>是两种类型，但是，在运行时只有一个ArrayList被加载到JVM中。
 * 5.泛型如果不指定，将被擦除，泛型对应的类型均按照Object处理，但不等价于Object.经验：泛型要使用一路都用，要不用，一路都不要用。
 * 6.如果泛型结构是一个接口或抽象类，则不可创建泛型类的对象。
 * 7.jdk1.7，泛型的简化操作：ArrayList<Fruit>first = new ArrayList<>();
 * 8.泛型的指定中不能使用基本数据类型，可以使用包装类替换。
 * 9.在类/接口上声明的泛型，在本类或本接口中即代表某种类型，可以作为非静态属性的类型、非静态方法的参数类型、非静态方法的返回值类型。
 * 但在静态方法中不能使用类的泛型。
 * 10.异常类不能是泛型的
 * 11.不能使用new E[].但是可以：E[] elements = (E[])new Object[capacity]
 * 参考：ArrayList源码中声明：Object[] elementData,而非泛型参数类型数组。
 * 12.父类有泛型，子类可以选择保留泛型也可以选择指定泛型类型：
 * 1）子类不保留父类的泛型：按需实现
 *    没有类型 擦除
 *    具体类型
 * 2）子类保留父类的泛型：泛型子类
 *    全部保留
 *    部分保留
 * 结论：子类必须是“富二代”，子类除了指定或保留父类的泛型，还可以增加自己的泛型。
 * @author Qh
 * @version 1.0
 * @date 2020-12-27-15:16
 */
public class Order<T> {
    String orderName;
    int orderId;
    //类的内部结构就可以使用类的泛型
    T orderT;

    //静态方法中不能使用类的泛型
    /*public static void show(T orderT){
        System.out.println(orderT);
    }*/

    public Order() {
        //编译不能通过
        //T[] arr = new T[10];
        //编译可通过
        T[] arr = (T[]) new Object[10];
    }

    public Order(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }

    /*
    泛型方法：在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系
    换句话说，泛型方法所属的类是不是泛型类都没有关系
    泛型方法可以声明为静态的。原因：泛型参数是在调用方法时确定的。并非在实例化类时确定的。
     */
    public static <E> List<E> copyFromArrayToList(E[] arr){
        ArrayList<E> list = new ArrayList<>();
        for(E e : arr){
            list.add(e);
        }
        return list;
    }

}

class Father<T1,T2>{
}
//子类不保留父类的泛型
//1)没有类型 擦除
class Son1 extends Father{//等价于class Son extends Father<Object,Object>
}
//2)具体类型
class Son2 extends Father<Integer,String>{
}
//子类保留父类的泛型
//1)全部保留
class Son3<T1,T2> extends Father<T1,T2>{
}
//2)部分保留
class Son4<T2> extends Father<Integer,T2>{
}
//子类不保留父类的泛型
//1)没有类型 擦除
class Son11<A,B> extends Father{//等价于class Son extends Father<Object,Object>
}
//2)具体类型
class Son22<A,B> extends Father<Integer,String>{
}
//子类保留父类的泛型
//1)全部保留
class Son33<T1,T2,A,B> extends Father<T1,T2>{
}
//2)部分保留
class Son44<T2,A,B> extends Father<Integer,T2>{
}