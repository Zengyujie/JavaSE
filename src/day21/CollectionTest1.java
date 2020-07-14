package day21;

import org.junit.Test;

import java.util.*;

public class CollectionTest1 {

    /*
    1，集合的概述：
        1，集合，数组都是对多个数据进行存储操作的结构，简称java容器，主要是针对内存层面的存储
        2，数组再存储多个数据方面的特点：
            1.一旦初始化之后，长度确定
            2，指明数组的元素类型一旦定义好，其元素的类型就指定了，只能操作指定类型的元素
            缺点：
            1，数组在存储多个数据方面的缺点：一旦初始化长度不可修改
            2，获取实际元素个数没有现成方法可用
            3，存储数据有序，可重复
        3，集合可分为Collection和Map体系
            1，Collection接口：
                1.1list：有序，可重复集合
                1.2Set：无序，不可重复集合
            2，Map接口：双列数据，key-value对集合
    2，集合框架
        1，Collection接口：单列集合，存储一个一个对象
            1.1，List接口：有序可重复
                ArrayList
                LinkedList
                Vector
            1.2，Set接口：无序不可重复
                HashSet
                LinkedHashSet
                TreeSet
        2，Map接口：双列集合，存一对的数据(key-value)
                HashMap
                LinkedHashMap
                TreeMap
                Hashtable
                Properties

        3，向Collection接口实现类中添加对象，对象要重写equals方法

     3，使用Iterator遍历集合
        1，为了访问容器而不暴露对象的内部细节

     */

    @Test
    public void test1(){
        //添加元素
        Collection coll = new ArrayList();
        coll.add("aa");
        coll.add("bb");
        coll.add(123);
        coll.add(new Date());
        //获取元素个数
        System.out.println(coll.size());
        //添加集合
        Collection coll1 = new ArrayList();
        coll1.add(456);
        coll1.add("cc");
        coll.addAll(coll1);
        //判空
        System.out.println(coll.isEmpty());
        //清空
        coll1.clear();
        boolean contains = coll.contains(123);
        coll.add(new String("Tom"));
        //判断是否包含时，调用的equals方法
        System.out.println(coll.contains(new String("Tom")));
        Person p1 = new Person("Jerry", 10);
        coll.add(new Person("Jerry", 10));
        System.out.println(coll.contains(p1));//用p1的equals方法和list中所有成员比一下，从第一个元素开始
        System.out.println(coll.containsAll(coll1));
        Collection coll2 = Arrays.asList(123,456);
        System.out.println(coll.containsAll(coll2));
        //删除
        boolean isRemove = coll.remove(123);//成功返回true
        //删除coll1和coll的交集
        boolean isRemoveAll = coll.removeAll(coll1);
        //求交集
        boolean isCor = coll.retainAll(coll1);
        //判断集合相等
        boolean isEqual = coll.equals(coll1);
        //ArrayList是有序的，如果顺序不同则返回false
        //HashSet是无序的，不考虑顺序

        //hash值
        System.out.println(coll.hashCode());

        //集合转数组
        Object[] os = coll.toArray();
        //数组转集合
        List cTemp = Arrays.asList(123,456,678);
        List list1 = Arrays.asList(new int[]{1,2,3});
        //这样会把int[]当成一个元素，应该写成包装类
        List list2 = Arrays.asList(new Integer[]{1,2,3});

        //iterator()方法用于遍历集合元素

    }

    @Test
    public void test2(){
        ArrayList coll = new ArrayList();
        coll.add("aa");
        coll.add("bb");
        coll.add(123);
        coll.add(new Date());

        Iterator it = coll.iterator();

        for(;it.hasNext();){
            Object o = it.next();
            if("tom".equals(o)){
                it.remove();
                //连续调用两次remove会出异常
                //没调用next时就调用remove会出错
            }
        }
        //增强的for循环，可以遍历集合和数组
        //内部仍然调用了迭代器
        for(Object o : coll){
            System.out.println(o);
        }

        String[] arr = new String[]{"MM1","MM2","MM3"};
//        for(int i = 0; i < arr.length; i++){
//            arr[i] = "GG";
//        }
        for(String s: arr){
            s = "GG";//修改后元素不变
            //赋值给s之后修改s的值
        }
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }


    }

}


class Person implements Comparable{
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getAge() == person.getAge() &&
                getName().equals(person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge());
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Person))
            throw new RuntimeException();
        Person p = (Person)o;
        int res = this.name.compareTo(p.getName());
        if(res == 0){
            return Integer.compare(((Person) o).getAge(), this.age);
        }
        else{
            return res;
        }
    }
}
