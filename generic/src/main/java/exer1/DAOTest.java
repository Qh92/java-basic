package exer1;

import java.util.List;

/**
 * 创建DAO类的对象，分别调用其save、get、update、list、delete 方法来操作User对象
 * @author Qh
 * @version 1.0
 * @date 2020-12-29-23:47
 */
public class DAOTest {
    public static void main(String[] args) {
        DAO<User> dao = new DAO<>();

        dao.save("1001",new User(1001,34,"jay"));
        dao.save("1002",new User(1002,20,"curry"));
        dao.save("1003",new User(1003,25,"james"));
        dao.save("1004",new User(1004,18,"west"));

        dao.update("1003",new User(1003,30,"allen"));

        dao.delete("1004");

        List<User> list = dao.list();
        list.forEach(System.out::println);

    }
}
