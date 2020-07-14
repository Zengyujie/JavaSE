package day19;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class CompareTest {
    /*
    Java中的对象正常情况下不能使用<,>的只能==或者！=
    如果要实现就需要实现以下任意一个接口：Comparable和Comparator
    1，Comparable接口的使用:自然排序
        String,包装类实现了Comparable接口，重写了compareTo方法
        compareTo(obj)重写规则：this 大于obj返回正整数，等于返回0，小于返回负整数
    2，Comparator接口，定制排序
    3，两者对比：
        Comparable一旦指定，实现对象任何位置可以比较
        Comparetor临时性比较
     */



    @Test
    public void test(){
        String[] arr = new String[]{"AA", "CC", "KK", "DD", "MM", "GG", "JJ", "DD"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test2(){
        Goods[] arr = new Goods[4];
        arr[0] = new Goods("lenovoMouse", 34);
        arr[1] = new Goods("dellMouse", 37);
        arr[2] = new Goods("xiaomiMouse", 32);
        arr[3] = new Goods("huaweiMouse", 39);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test3(){
        String[] arr = new String[]{"AA", "CC", "KK", "DD", "MM", "GG", "JJ", "DD"};
        Arrays.sort(arr, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof String && o2 instanceof String){
                    String s1 = (String)o1;
                    String s2 = (String)o2;
                    return -s1.compareTo(s2);
                }
                return 0;
            }
        });
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test4(){
        Goods[] arr = new Goods[4];
        arr[0] = new Goods("lenovoMouse", 34);
        arr[1] = new Goods("dellMouse", 37);
        arr[2] = new Goods("xiaomiMouse", 32);
        arr[3] = new Goods("huaweiMouse", 39);
        Arrays.sort(arr, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Goods && o2 instanceof Goods){
                    Goods g1 = (Goods)o1;
                    Goods g2 = (Goods)o2;
                    if(g1.getName().equals(g2.getName())){
                        return Double.compare(g1.getPrice(), g2.getPrice());
                    }else{
                        return g1.getName().compareTo(g2.getName());
                    }
                }
                return 0;
            }
        });
        System.out.println(Arrays.toString(arr));
    }

}




