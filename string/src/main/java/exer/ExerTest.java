package exer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1. 模拟一个trim方法，去除字符串两端的空格。
 * 2. 将一个字符串进行反转。将字符串中指定部分进行反转。比如“abcdefg”反
 * 转为”abfedcg”
 * 3. 获取一个字符串在另一个字符串中出现的次数。
 * 比如：获取“ ab”在 “abkkcadkabkebfkabkskab” 中出现的次数
 * 4.获取两个字符串中最大相同子串。比如：
 * str1 = "abcwerthelloyuiodef“;str2 = "cvhellobnm"
 * 提示：将短的那个串进行长度依次递减的子串与较长的串比较。
 * 5.对字符串中字符进行自然顺序排序。
 * 提示：
 * 1）字符串变成字符数组。
 * 2）对数组排序，选择，冒泡，Arrays.sort();
 * 3）将排序后的数组变成字符串。
 *
 * @Author Qh
 * @Date 2021/4/8 14:50
 * @Version 1.0
 */
public class ExerTest {

    /**
     * 1. 模拟一个trim方法，去除字符串两端的空格。
     */
    @Test
    public void t1(){
        String s = "  java String match  ";
        int start = 0 ,end = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' '){
                start = i;
                break;
            }
        }
        for (int i = s.length() - 1; i >= 0 ; i--) {
            if (s.charAt(i) != ' '){
                end = i;
                break;
            }
        }
        String newStr = s.substring(start, end + 1);
        System.out.println(newStr);
    }

    /**
     * 2. 将一个字符串进行反转。将字符串中指定部分进行反转。比如“abcdefg”反转为”abfedcg”
     */
    @Test
    public void t2(){
        String s = "abcdefg";
        //表明反转2-5之间的字符
        int start = 2,end = 5;
        String s1 = s.substring(start,end + 1);
        char[] chars = s1.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = temp;
        }
        String newStr = s.substring(0,start) + new String(chars) + s.substring(end + 1);
        System.out.println(newStr);
    }


    @Test
    public void t21(){
        String s = "abcdefg";
        String s1 = reverse1(s,2,5);
        System.out.println(s1);
        String s2 = reverse2(s,2,5);
        System.out.println(s2);
        String s3 = reverse3(s,2,5);
        System.out.println(s3);
    }
    /**
     * 方式1：转换为char[]
     */
    public String reverse1(String str,int startIndex,int endIndex){
        if (str != null ){
            char[] arr = str.toCharArray();
            for (int i = startIndex,j = endIndex;i < j; i++,j--){
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            return new String(arr);
        }
        return null;
    }

    /**
     * 方式2：使用String的拼接操作
     */
    public String reverse2(String str,int startIndex,int endIndex){
        if (str != null){
            String reverseStr = str.substring(0,startIndex);
            for (int i = endIndex; i >= startIndex ; i--) {
                reverseStr += str.charAt(i);
            }
            reverseStr += str.substring(endIndex + 1);
            return reverseStr;
        }
        return null;
    }

    /**
     * 方式3：使用StringBuilder / StringBuffer替换String
     */
    public String reverse3(String str,int startIndex,int endIndex){
        if (str != null){
            StringBuilder sb = new StringBuilder(str.length());
            sb.append(str.substring(0,startIndex));
            for (int i = endIndex; i >= startIndex ; i--) {
                sb.append(str.charAt(i));
            }
            sb.append(str.substring(endIndex + 1));
            return sb.toString();
        }
        return null;
    }


    /**
     * 3. 获取一个字符串在另一个字符串中出现的次数。
     * 比如：获取“ab”在 “abkkcadkabkebfkabkskab” 中出现的次数
     */
    @Test
    public void t3(){
        String s = "abkkcadkabkebfkabkskab";
        String s1 = "ab";
        String newStr = s.replace(s1, "@");
        System.out.println(newStr);
        String[] split = newStr.split("@",-1);

        System.out.println(Arrays.toString(split));
        int count = split.length - 1;
        System.out.println(count);
    }

    @Test
    public void t31(){
        String s = "abkkcadkabkebfkabkskab";
        String s1 = "ab";
        int count = getCount(s, s1);
        System.out.println(count);
    }

    public int getCount(String mainStr,String subStr){
        int mainLen = mainStr.length();
        int subLen = subStr.length();
        int count = 0;
        if (mainLen >= subLen){
            int index = 0;
            while ((index = mainStr.indexOf(subStr,index)) != -1){
                count++;
                index = index + subLen;
            }
        }
        return count;
    }

    /**
     * 4.获取两个字符串中最大相同子串。比如：
     *  str1 = "abcwerthelloyuiodef“;str2 = "cvhellobnm";
     *  提示：将短的那个串进行长度依次递减的子串与较长的串比较。
     */
    @Test
    public void t4(){
        String s1 = "abcwerthelloyuiodef";
        String s2 = "cvhelloyubnm";
        String maxLenStr = "";
        for (int i = s2.length() - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++){
                String s = s2.substring(j, i);
                if (s1.contains(s)){
                    if (s.length() > maxLenStr.length()){
                        maxLenStr = s;
                    }
                }
            }
        }
        System.out.println(maxLenStr);
    }

    /**
     * 5.对字符串中字符进行自然顺序排序。
     * 提示：
     * 1）字符串变成字符数组。
     * 2）对数组排序，选择，冒泡，Arrays.sort();
     * 3）将排序后的数组变成字符串。
     */
    @Test
    public void t5(){
        String s = "adfgreretdfdkxcvzloimncvwrt";
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String string = new String(chars);
        System.out.println(string);
    }
    @Test
    public void t6(){
        String s = "adfgreretdfdkxcvzloimncvwrt";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < i; j++) {
                if (chars[j] > chars[i]){
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
            }
        }
        String newStr = String.valueOf(chars);
        System.out.println(newStr);
    }
}
