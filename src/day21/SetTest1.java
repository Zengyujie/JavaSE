package day21;

import org.junit.Test;

import java.util.*;

public class SetTest1 {
    /*
    Set接口：无序不可重复
        1，无序性：不等于随机性，数据在底层数组中并非按照数组索引的顺序添加
            而是根据hash值添加
        2，数据不可重复添加，基本类型看值，引用类型默认是看地址值
            添加元素时先计算hashcode，如果不同则失败，如果相同则在拉链中按照equals判断，
            返回为true则无法加入

    加入冲突后在开链法中的放置问题：jdk7与jdk8：七上八下（头插和尾插）

    要求向Set中添加的数据一定要重写hashCode和equals方法，重写的两个方法尽可能保持一致性
        即：保证相等的对象有相同的散列码

    1，HashSet：
        1，主要实现类，线程不安全的，可以存储null值
        2，底层是数组加链表

    2，LinkedHashSet：
        1，作为HashSet的子类
        2，遍历内部数据可以按照添加顺序遍历
        3，额外提供了两个引用记录数据的添加顺序
        4，优点：对于频繁遍历的操作，效率高于HashSet
        本质：其实并没有实现什么东西，而是HashSet中已经提供了
            使用LinkedHashMap的构造方法，直接调用罢了，添加的两个引用
            是LinkedHashMap中的内部类Entry，继承了HashMap中的Node，并添加了两个引用而已
    3，TreeSet：
        1，放入数据需要是同一类的对象
        2，需要排序:自然排序和定制排序需要选择一种实现
        3，在自然排序中判断两个对象相同的标准是compareTo返回0，而不是equals
        4，在定制排序中判断两个对象相同的标准是compare返回0，而不是equals
        5，定制排序的优先级要高于自然排序

    1，Set接口中没有重新定义方法，都是collection中定义的

     */

    @Test
    public void test1(){
        Set set = new HashSet();
        set.add(123);
        set.add(456);
        set.add("AA");
        set.add("CC");
        set.add(new Person("zhangsan", 12));
        System.out.println(set);


        Set set1 = new LinkedHashSet();
        set1.add(123);
        set1.add(456);
        set1.add("AA");
        set1.add("CC");
        set1.add(new Person("zhangsan", 12));
        set1.add(123);
        System.out.println(set1);


    }


    @Test
    public void test2(){
        TreeSet set = new TreeSet();
        set.add(new Person("zhansan", 12));
        set.add(new Person("lisi", 22));
        set.add(new Person("wangwu", 17));
        set.add(new Person("zhhanlii", 11));
        for(Iterator it = set.iterator(); it.hasNext();){
            System.out.println(it.next());
        }
    }

    @Test
    public void test3(){
        TreeSet set = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Person &&  o2 instanceof Person){
                    Person p1 = (Person)o1;
                    Person p2 = (Person)o2;
                    return Integer.compare(p1.getAge(), p2.getAge());
                }else{
                    throw new RuntimeException();
                }
            }
        });
        set.add(new Person("zhansan", 12));
        set.add(new Person("lisi", 22));
        set.add(new Person("wangwu", 17));
        set.add(new Person("zhhanlii", 11));
        for(Iterator it = set.iterator(); it.hasNext();){
            System.out.println(it.next());
        }

    }

    @Test
    public void test4(){
        HashSet set = new HashSet();
        Person p1 = new Person("AA",1001);
        set.add(p1);
        Person p2 = new Person("BB",1002);
        set.add(p2);
        System.out.println(set);
        p1.setName("CC");
        set.remove(p1);//会重新计算hashcode，发现没有，删除失败
        System.out.println(set);
        set.add(new Person("CC", 1001));//重新计算hashcode，发现没有冲突，加入成功
        System.out.println(set);
        set.add(new Person("AA", 1001));//原来的AA替换成了CC，但是hashcode没有变化，
            // 加入时调用equals方法，发现不等，加入成功
        System.out.println(set);
    }

}
