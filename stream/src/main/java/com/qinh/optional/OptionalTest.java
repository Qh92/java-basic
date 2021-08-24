package com.qinh.optional;

import com.qinh.entity.Boy;
import com.qinh.entity.Girl;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional类：为了在程序中避免出现空指针异常而创建的
 *
 * @author Qh
 * @version 1.0
 * @date 2021-02-14-22:20
 */
public class OptionalTest {

    /**
     * Optional.of(T t) : 创建一个 Optional 实例，t必须非空；
     * Optional.empty() : 创建一个空的 Optional 实例
     * Optional.ofNullable(T t)：t可以为null
     */
    @Test
    public void t1(){
        Girl girl = new Girl();
        //girl = null;
        Optional<Girl> optionalGirl = Optional.of(girl);
        //Optional[Girl{name='null'}]
        System.out.println(optionalGirl);
    }

    @Test
    public void t2(){
        Girl girl = new Girl();
        girl = null;
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        //Optional.empty
        System.out.println(optionalGirl);
        /*
        T orElse(T other) ：如果有值则将其返回，否则返回指定的other对象
         */
        Girl girl1 = optionalGirl.orElse(new Girl("赵丽颖"));
        //Girl{name='赵丽颖'}
        System.out.println(girl1);

    }

    public String getGirlName(Boy boy){
        return boy.getGirl().getName();
    }

    @Test
    public void t3(){
        Boy boy = new Boy();
        String girlName = getGirlName(boy);
        System.out.println(girlName);
    }

    //优化getGirlName()
    public String getGirlName1(Boy boy){
        if (boy != null){
            Girl girl = boy.getGirl();
            if (girl != null){
                return girl.getName();
            }
        }
        return null;
    }

    @Test
    public void t4(){
        Boy boy = new Boy();
        String girlName = getGirlName1(boy);
        System.out.println(girlName);
    }

    //优化Optional类的getGirlName()
    public String getGirlName2(Boy boy){
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        //此时的boy1一定非空
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("迪丽热巴")));
        Girl girl = boy1.getGirl();
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        //此时的girl1一定非空
        Girl girl1 = girlOptional.orElse(new Girl("高圆圆"));
        return girl1.getName();
    }

    @Test
    public void t5(){
        Boy boy = new Boy();
        boy = null;
        boy = new Boy(new Girl("刘诗诗"));
        String girlName = getGirlName2(boy);
        System.out.println(girlName);
    }

    /**
     * 这两个函数的区别：
     * 当user值不为null时，orElse函数依然会执行createUser()方法，
     * 而orElseGet函数并不会执行createUser()方法
     */
    @Test
    public void t6(){
        Boy boy = new Boy();
        //boy = null;
        boy = Optional.ofNullable(boy).orElse(createUser());
        System.out.println(boy);
        boy = Optional.ofNullable(boy).orElseGet(() -> createUser());
        System.out.println(boy);
    }

    public Boy createUser(){
        Boy boy = new Boy();
        boy.setGirl(new Girl("晶晶妹"));
        return boy;
    }

    @Test
    public void t7() throws Exception {
        Boy boy = new Boy();
        String girl = Optional.ofNullable(boy).map(b -> b.getGirl()).map(g -> g.getName()).orElseThrow(() -> new Exception("取值错误"));
        System.out.println(girl);
    }

    /*
    实战使用

    例一

    在函数方法中
    以前写法
    public String getCity(User user)  throws Exception{
            if(user!=null){
                if(user.getAddress()!=null){
                    Address address = user.getAddress();
                    if(address.getCity()!=null){
                        return address.getCity();
                    }
                }
            }
            throw new Excpetion("取值错误");
        }
    JAVA8写法
    public String getCity(User user) throws Exception{
        return Optional.ofNullable(user)
                       .map(u-> u.getAddress())
                       .map(a->a.getCity())
                       .orElseThrow(()->new Exception("取指错误"));
    }
    例二

    比如，在主程序中
    以前写法
    if(user!=null){
        dosomething(user);
    }
    JAVA8写法
     Optional.ofNullable(user)
        .ifPresent(u->{
            dosomething(u);
    });
    例三

    以前写法
    public User getUser(User user) throws Exception{
        if(user!=null){
            String name = user.getName();
            if("zhangsan".equals(name)){
                return user;
            }
        }else{
            user = new User();
            user.setName("zhangsan");
            return user;
        }
    }
    java8写法
    public User getUser(User user) {
        return Optional.ofNullable(user)
                       .filter(u->"zhangsan".equals(u.getName()))
                       .orElseGet(()-> {
                            User user1 = new User();
                            user1.setName("zhangsan");
                            return user1;
                       });
    }
     */


}
