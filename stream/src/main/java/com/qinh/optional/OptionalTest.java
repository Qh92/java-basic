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
}
