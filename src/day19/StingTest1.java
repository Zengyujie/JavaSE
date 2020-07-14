package day19;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class StingTest1 {
    /*
    String的使用
    1，String是final类，不可被继承
    2，String实现了Serializable接口：表示字符串支持序列化
    3，String实现了Comparable接口：表示String可以比较大小
    4，String内部定义了Final的char[]，用于存储字符串数据，代表不可变的字符序列
    5，通过字面量给一个字符串赋值，此时的字符串值声明在字符串常量池中，字符串常量池
        中式不会存储相同内容的字符串的。字符串常量池被包含在方法区中
    6，当对字符串重新赋值时，需要重写指定内存区域（常量池中）赋值，不能使用原有的value
            连接操作也是重新造数据了，调用replace方法修改字符或字符串时也要重造数据
    7，String的实例化
        1，通过定义字面量的方式
        2，通过new + 构造器的方式
    8，面试题，String s = new String("abd");在内存中创建了几个对象：
        两个：一个是空间中的new结构，另一个是char[]对应的常量池中的“abc",如果常量池中有了，就不会造了
    9，jdk1.6及以前，字符串常量池在方法区(永久带)中，1.7防到了堆中，1.8又放回了方法区中(改名为元空间)


     */



    @Test
    public void test1(){

        String s1 = "abc";//字面量定义方式，不用new
        String s2 = "abc";


        System.out.println(s1 == s2);//true

        s1 = "hello";//此时常量池中新建了一个字符串hello，s1指向新的地址
        System.out.println(s1);
        System.out.println(s2);

        String s3 = "abc";
        s3 += "def";
        System.out.println(s2);
        System.out.println(s3);

        String s4 = "abc";
        String s5 = s4.replace("a","def");
        System.out.println(s4);
        System.out.println(s5);

    }

    @Test
    public void test2(){
        //s1,s2直接声明在方法区的字符串常量池中
        String s1 = "javaee";
        String s2 = "javaee";
        //:s3,s4在堆空间开辟后，开辟的部分是一个引用，指向常量池中的字符串
        String s3 = new String("javaee");
        String s4 = new String("javaee");
        System.out.println(s1 == s2);//true
        System.out.println(s1 == s3);//false
        System.out.println(s1 == s4);//false
        System.out.println(s3 == s4);//false

        System.out.println("--------");

        Person p1 = new Person(12,"Tom");
        Person p2 = new Person(12,"Tom");

        p1.change(p1.str, p1.ch);
        System.out.println(p1.str);
        System.out.println(p1.ch);

        System.out.println(p1.name.equals(p2.name));//true
        System.out.println(p1.name == p2.name);//true
        p1.name = "jerry";
        System.out.println(p1 + ":" + p2);//p2不变，String不变性


    }

    @Test
    public void test3(){
        String s1 = "javaee";
        String s2 = "hadoop";
        String s3 = "javaeehadoop";
        String s4 = "javaee" + "hadoop";
        String s5 = s1 + "hadoop";
        String s6 = "javaee" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);//true
        //字面量连接，还是在常量池中找
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s5 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false
        //有变量参与就是在堆空间中，相当于new

        String s8 = s5.intern();//返回得到的s8使用的常量池中已经存在的javaeehadoop
        //intern方法要求返回值必须在常量池中
        System.out.println(s3 == s8);//true

    }

    @Test
    public void test4(){
        String s1 = "helloworld";
        System.out.println(s1.length());
        System.out.println(s1.charAt(0));
        System.out.println(s1.isEmpty());
        System.out.println("".isEmpty());//true
        System.out.println(s1.toLowerCase());//s1不可变，仍未原来的
        String s2 = s1.toUpperCase();
        System.out.println(s2);
        System.out.println(s1.toUpperCase());
        System.out.println(s1);
        String s3 = " hell old ";
        String s4 = s3.trim();//去除前后空格
        System.out.println(s3);
        System.out.println(s4);
        String s5 = "Helloworld";
        System.out.println(s1.equals(s5));
        System.out.println(s1.equalsIgnoreCase(s5));
        String s6 = s1.concat("test");
        System.out.println(s6);
        String s7 = "abc";
        String s8 = new String("abd");
        System.out.println(s7.compareTo(s8));//涉及到字符串排序
        String s9 = "test";
        String s10 = s9.substring(1);//默认结尾
        System.out.println(s10);
        System.out.println(s9.substring(1,3));//左闭右开
        System.out.println("test".endsWith("t"));//true
        System.out.println("test".startsWith("t"));
        System.out.println("test".startsWith("e",1));
        System.out.println("test".contains("es"));
        System.out.println("test".indexOf("e"));
        System.out.println("test".indexOf('t',2));
        System.out.println("test".lastIndexOf("t"));//从后往前找：3
        System.out.println("test".lastIndexOf("t",3));


    }

    @Test
    public void test5(){
        //替换
        String s1 = "hello1world";
        String s2 = s1.replace("l","t");
        System.out.println(s1);
        System.out.println(s2);
        //匹配
        System.out.println(s1.replaceAll("\\d+",","));
        String s3 = "021-123455";
        System.out.println(s3.matches("\\d+"));//判断是否全部是数字
        System.out.println(s3.substring(5).matches("\\d+"));
        System.out.println(s3.matches("021-\\d{5,6}"));
        //分裂
        String[] str = s3.split("-");
        for(String s : str){
            System.out.println(s);
        }

    }

    @Test
    public void test6(){
        //string与基本数据类型的包装类转换
        String s1 = "123";
        int num = Integer.parseInt(s1);
        s1 = String.valueOf(num);
        String s2 = "" + num;
        System.out.println(s1 == s2);
    }


    @Test
    public void test7(){
        //String 与 char[]
        String s1 = "abc123";
        char[] chars = s1.toCharArray();
        for(char c: chars){
            System.out.print(c);
        }
        String s2 = new String(chars);
        System.out.println(s2);
        System.out.println(new StringBuffer(s2).reverse().toString());

    }

    @Test
    public void test8() throws UnsupportedEncodingException{
        //String 与 byte[]
        String s1 = "123中国";
        byte[] bytes = s1.getBytes();//使用默认的字符集转化
        System.out.println(Arrays.toString(bytes));

        bytes = s1.getBytes("gbk");
        System.out.println(Arrays.toString(bytes));

        String s2 = new String(bytes, "gbk");
        System.out.println(s2);
        s2 = new String(bytes, "utf-8");
        System.out.println(s2);//乱码：编码集和解码集不一样

    }

    @Test
    public void test9(){
        String s1 = "javaeehadoop";
        String s2 = "javaee";
        String s3 = s2 + "hadoop";
        System.out.println(s1 == s3);//false
        final String s4 = "javaee";//常量
        String s5 = s4 + "hadoop";
        System.out.println(s1 == s5);//true
    }

}


class Person{

    int age;
    String name;

    String str = new String("good");
    char[] ch = {'t', 'e', 's', 't'};

    //alt+shift+s：选择自动生成构造器
    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person() {

    }

    public void change(String str, char ch[]){
        str = "test ok";
        ch[0] = 'b';
    }
}

