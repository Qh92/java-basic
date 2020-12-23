package test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Qh
 * @version 1.0
 * @date 2020-12-15-23:12
 */
public class MyList {
    private static List<String> list = new ArrayList<String>();

    public static void add() {
        list.add("anyString");
    }

    public static int size() {
        return list.size();
    }
}
