package day21;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsTest1 {
    /*
    Collections：操作Collection和Map的工具类
    1，Collection和Collections的区别：前者是接口，后者是工具类
    2，Collections提供了synchronizedXXX()方法，解决多线程并发访问集合时的线程安全问题
        其实就是拿synchronized包了一下

     */

    @Test
    public void test1(){
        List list = new ArrayList();
        list.add(1);
        list.add(4);
        list.add(2);
        System.out.println(list);
        Collections.reverse(list);
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        Collections.swap(list, 1,2);
        System.out.println(list);
        //Collections.max(),min，sort//可以定制排序
        System.out.println(Collections.frequency(list,1));
        //List des = new ArrayList(list.size());//底层数组的长度，size还是0
        List des = Arrays.asList(new Object[list.size()]);
        Collections.copy(des, list);//des的大小不能小于list
        System.out.println(des);

        List list1 = Collections.synchronizedList(list);
    }
}
