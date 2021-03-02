package org.qinh.edu.comparator;

import java.util.*;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-01-17-22:56
 */
public class T01 {

    public static void main(String[] args) {
        /*List<String> strings = Arrays.asList("AA", "BB", "C");

        for(int i = 0; i < strings.size(); i++){
            System.out.println(strings.get(i));
        }*/
        /*Map<Integer,String> map = new HashMap<>();
        map.put(1,"1");
        map.put(2,"2");
        map.put(3,"3");
        map.put(4,"4");
        Collection<String> c = map.values();
        //List<String> l = (List<String>) c;
        c.forEach(System.out::println);*/

        // 1、使用时：类似于Object，不等同于Object
        ArrayList list = new ArrayList();
        /*list.add(new Date());//有风险
        list.add("hello");
        test(list);// 泛型擦除，编译不会类型检查*/
        ArrayList<Object> list2 = new ArrayList<Object>();
        //test(list2);//一旦指定Object，编译会类型检查，必须按照Object处理

        test(new Person());
        test(new Man());
        //The method test(T) in the type PersonTest is not //applicable for the arguments (Creature)
        //test(new Creature());



    }

    /*public String(char value[], int offset, int count) {
        if (offset < 0) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        if (count <= 0) {
            if (count < 0) {
                throw new StringIndexOutOfBoundsException(count);
            }
            if (offset <= value.length) {
                this.value = "".value;
                return;
            }
        }
        // Note: offset or count might be near -1>>>1.
        if (offset > value.length - count) {
            throw new StringIndexOutOfBoundsException(offset + count);
        }
        this.value = Arrays.copyOfRange(value, offset, offset+count);
    }*/

    /*public static void test(ArrayList<String> list) {
        String str = "";
        for (String s : list) {
            str += s + ",";
        }
        System.out.println("元素:" + str);
    }*/

    public static <T extends Person> void test(T t){ System.out.println(t); }



}

class Creature{}
class Person extends Creature{}
class Man extends Person{}


