package day21;

import org.junit.Test;

import java.util.*;

public class ListTest1 {
    //ctrl+shift+T看源码
    /*
    List接口
    1，元素有序，且可重复，元素对应一个序号，可以根据序号取出
    2，实现接口的类有ArrayList、LinkedList和Vector
    3，ArrayList、LinkedList和Vector的异同：
        1，同：三者都实现了list接口，存储数据特点相同，有序可重复
                ArrayList,Vector底层都使用Object[]
        2，异：
            1，ArrayList：List接口的主要实现类，是一个线程不安全的，效率高
            2，Vector：list接口的古老实现类，线程不安全的，效率低，也是线程不安全的
            3，LinkedList：底层使用双向链表，对频繁的插入删除操作效率高

    1，ArrayList源码分析：
        1，jkd1.7：底层创建了长度为10的数组，默认情况下添加时超范围会扩容为1.5倍
        2，jkd1.8：默认构造器是一个空的数组{}，懒汉式构造，第一次调用add时才创建
                    后续添加和扩容操作和以前一致
    2，LinkedList源码分析：
        1，内部声明了Node内部类
        2，成员有first和last两个node，是双向链表
    3，Vector：已经不被使用了，ArrayList可以通过Collections的方法转换为线程安全的

    4，List接口常用方法
     */

    @Test
    public void test1(){
        ArrayList coll = new ArrayList();
        coll.add("aa");
        coll.add("bb");
        coll.add(123);
        coll.add(new Date());
        System.out.println(coll);
        coll.add(2,"cc");
        System.out.println(coll);
        List list1 = Arrays.asList(1,2,3);
        coll.addAll(list1);
        System.out.println(coll.size());
        System.out.println(coll.get(1));
        int index = coll.indexOf(123);//没有就返回-1，可支持null
        //linkedList也可以支持null
        int lastIndex = coll.lastIndexOf(123);
        //remove重载了索引的方法，注意删除索引和对象的区别
        coll.remove(1);
        coll.remove(new Integer(123));
        coll.set(1,"dd");//修改元素
        List list2 = coll.subList(0,2);//返回左闭右开区间的子集合
        System.out.println(list2);//返回的子列表还是本体
        coll.set(0, "AA");
        System.out.println(list2);
        Iterator it = coll.iterator();
        //三种遍历方法
        while(it.hasNext()){
            System.out.println(it.next());
        }
        for(Object o : coll){
            System.out.println(o);
        }

        for(int i = 0; i < coll.size(); i++){
            System.out.println(coll.get(i));
        }
    }

}
