/**
 * 接口泛型测试
 * 接口定义了泛型，如果实现类没有写明具体的泛型，则默认泛型为Object
 * 如果实现类写明了具体泛型，则用具体的泛型
 *
 * @author Qh
 * @version 1.0
 * @date 2021/7/5 15:12
 */
public class InterfaceGenericTest implements Person<String>{
    @Override
    public void accept(String s) {

    }

    public static void main(String[] args){
        Person p = System.out::println;
        p.accept("this");
    }
}

@FunctionalInterface
interface Person<T> {
    void accept(T t);
}
