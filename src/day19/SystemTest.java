package day19;

import org.junit.Test;

import java.math.BigInteger;

public class SystemTest {
    /*
    System类，三个private属性：err，in，out都是PrintStream
    方法都是static的，相当于工具类

    Math类，工具类，静态方法

    BigInteger:表示不可变的任意精度的整数

    BigDecimal:表示不可变的任意精度的十进制定点数


     */

    @Test
    public void test1(){
        String osName = System.getProperty("os.path");
    }

    @Test
    public void test2(){
        byte[] arr = new byte[10];
        BigInteger bi = new BigInteger("2343333333332222");
        BigInteger bi2 = new BigInteger("34456765434565");
    }

}
