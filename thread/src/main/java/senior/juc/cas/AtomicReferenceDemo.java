package senior.juc.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用
 *
 * @author Qh
 * @version 1.0
 * @date 2021-09-24-0:19
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();

        User user1 = new User("zs", 23);
        User user2 = new User("ls", 24);
        User user3 = new User("ww", 25);

        atomicReference.set(user1);

        System.out.println(atomicReference.compareAndSet(user1, user2) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(user1, user3) + "\t" + atomicReference.get().toString());
    }
}

class User{
    private String userName;
    private int age;

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
