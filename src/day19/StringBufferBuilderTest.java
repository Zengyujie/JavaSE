package day19;

import org.junit.Test;

public class StringBufferBuilderTest {

    /*
    String,StringBuffer,StringBuilder异同
    String:不可变的字符序列，底层结构final char[]
    StringBuffer:可变的字符序列：线程安全的，效率低jdk1.0,底层结构char[]
    StringBuilder:可变的字符序列：线程不安全的，效率高jdk1.5，底层结构char[]
    StringBuffer和StringBuilder继承了同一个父类，底层是父类的char[] value

    源码分析//
    String str = new String()//new char[0]
    String s1 = new String("abc")//new char[]{'a','b','c'}
    StringBuffer sb1 = new StringBuffer()//new char[16]
    StringBuffer sb1 = new StringBuffer("abc")//new char["abc".length + 16],每次新增都会加16个空位
    sb1.length//3//因为重写了length()
    底层数组存不下了，就需要扩容底层数组，默认情况下扩容为原容量的2倍+2，将原数组复制到新数组
    再开发中建议使用StringBuffer(int capacity)构造

    StringBuilder同理

    效率排列:StringBuilder > StringBuffer >>> String



     */

    @Test
    public void test1(){
        StringBuffer sb1 = new StringBuffer("abc");
        sb1.setCharAt(0,'m');
        System.out.println(sb1);
        sb1.append(5);

        sb1.append(1);
        sb1.delete(1,3);//左闭右开
        System.out.println(sb1);
        sb1.replace(0,1,"hello");
        System.out.println(sb1);
        sb1.insert(0,false);
        System.out.println(sb1);
        System.out.println(sb1.reverse());
        System.out.println(sb1.indexOf("m"));
        String str = sb1.substring(0,2);
        System.out.println(str);


    }




}
