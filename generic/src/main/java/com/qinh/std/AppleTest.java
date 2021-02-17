package com.qinh.std;

import org.junit.Test;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-02-15-20:43
 */
public class AppleTest {
    @Test
    public void t1(){
        Apple<String> apple1 = new Apple<>("500克");
        Apple<Integer> apple2 = new Apple<>(500);
        Apple<Double> apple3 = new Apple<>(500.00);

    }
}
