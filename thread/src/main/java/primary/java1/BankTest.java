package primary.java1;

/**
 * 使用同步机制将单例模式中的懒汉模式改写为线程安全的
 *
 * @author Qh
 * @version 1.0
 * @date 2020-11-08- 23:45
 * @description
 */
public class BankTest {
}

class Bank{

    private Bank(){
    }

    private static Bank instance = null;

    public static Bank getInstance(){
        //方式一：效率稍差
//        synchronized (Bank.class) {
//            if (instance == null){
//                instance = new Bank();
//            }
//            return instance;
//        }

        //方式二：效率更高
        if(instance == null){
            synchronized (Bank.class){
                if (instance == null){
                    instance = new Bank();
                }
            }

        }
        return instance;
    }
}
