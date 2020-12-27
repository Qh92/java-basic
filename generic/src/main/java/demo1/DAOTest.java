package demo1;

import org.junit.Test;

import java.util.List;

/**
 * @author Qh
 * @version 1.0
 * @date 2020-12-28-0:35
 */
public class DAOTest {
    @Test
    public void t1(){
        CustomerDAO dao = new CustomerDAO();
        dao.add(new Customer());
        List<Customer> list = dao.getForList(1);

        StudentDAO dao2 = new StudentDAO();
        dao2.getIndex(new Student());

    }
}
