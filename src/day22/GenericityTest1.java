package day22;

import org.junit.Test;

import java.util.*;

public class GenericityTest1 {
    /*
    1，不用泛型的缺点：
        1，类型不安全
        2，强转时容易出现classCastException
    2，集合中使用泛型：
        1，不能使用八种基本数据类型，只能使用包装类
        2，在实例化集合时可以指明具体泛型类型
        3，之明完之后，内部结构使用到的类的泛型位置都要指定为相应泛型
        4，jkd5.0之后集合接口都改为泛型的结构
        5，没有使用泛型默认使用Object
    3，如何自定义泛型结构：泛型类，泛型接口，泛型方法
        1，class Name<T>{}
        2，子类继承父类时，指明了了泛型类型，则实例化子类对象时不需要之明泛型，即子类成为了普通类
        3，子类继承父类时，如果不知名具体泛型类型，则定义名称和extends都需要带上<T>
        4，泛型类型可以有多个参数
        5，声明构造器不要加<T>
        6，泛型不同的引用不能相互赋值
        7，静态方法不能使用泛型
        8，异常类不能使用泛型
        9，jdk1.7之后简化了泛型的声明ArrayList<Integer> list2 = new ArrayList<>();，可以不写后面的<>
        10，try-catch中不可以有泛型声明
        11，在数组泛型中//T[] arr = new T[10];//编译不过
             需要T[] arr = (T[]) new Object[10];
        12，泛型的继承问题：
            class Father<T1, T2>{}
            //子类不保留父类的泛型
            1，没有写父类泛型类型等价于
            class Son extends Father
            class Son<A,B> extends Father//Father<Object,Object>
            2，具体类型
            class Son extends Father<C,D>
            class Son<A,B> extends Father<C,D>
            //子类保留父类泛型
            1，全部保留
            class Son<C,D> extends Father<C,D>
            class Son<C,D,A,B> extends Father<C,D>
            2，部分保留
            class Son<T2> extends Father<C, T2>
            class Son<T2,A,B> extends Father<C, T2>
        13，泛型方法：
            1，泛型方法所在的接口或者类是否带泛型，无所谓
            2，//如下的方法不是泛型方法：public T getOrderT()
            3，泛型方法在调用时，指明泛型的参数类型
            4，泛型方法中的泛型和类的泛型没有关系
            5，泛型方法可以是static的，因为泛型参数是在调用方法时确定的，并非在实例化时确定的
    4，泛型的继承：
        1，A是B的父类，G<A>和G<B>不具有继承关系，进一步，类所使用的泛型不同都不具有字符类关系List<Object>，ArrayList<String>
            同样的可以是继承关系：List<Object>与ArrayList<Object>
    5，通配符的使用：
        1，A是B的父类，G<A>和G<B>没有关系，它们的共同父类是G<?>
        2，有限制条件的通配符:
            1，? extends A 可以作为G<T>的父类，T是A或者A的子类，可以取，只能add空值
            2，? super A 可以作为G<T>的父类，T是A的父类，不包括A，可以取，可以add A以及A的子类

     */


    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        Iterator<Integer> it = list.iterator();
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("Tom",12);
        Set<Map.Entry<String, Integer>> entrys = map.entrySet();
        Iterator<Map.Entry<String, Integer>> its = entrys.iterator();
        while(its.hasNext()){
            Map.Entry<String, Integer> en = its.next();
            String s = en.getKey();
            Integer in = en.getValue();
        }
    }

    @Test
    public void test2(){
        Order<String> order = new Order<String>();
        order.setOrderT("test");

    }

    @Test
    public void test3(){
        ArrayList<String> list1 = null;
        ArrayList<Integer> list2 = new ArrayList<>();//省略了后面的<String>
        list1.add("test");
        //list1 = list2;//error
    }

    @Test
    public void test4(){
        Object o = null;
        String str = null;
        o = str;

        List<Object> l1 = null;
        List<String> l2 = null;
        //l1 = l2error
        ArrayList<String> a1 = null;
        l2 = a1;
        //l1 = a1;error
    }

    @Test
    public void test5(){
        List<Object> l1 = null;
        List<String> l2 = null;
        List<?> l3 = null;
        l3 = l1;
        l2 = new ArrayList();
        l3 = l2;
        //l1 = l3//error
        for(Iterator<?> it = l3.iterator(); it.hasNext();){
            System.out.println(it.next());
        }
        //l3.add("A");//编译不过
        //对于list<?>不能添加数据，只能加null
        l3.add(null);
        Object o = l3.get(0);
        //允许读取

    }

    @Test
    public void test6(){
        List<? extends Person> l1 = null;
        List<? super Person> l2 = null;

        List<Student> l3 = null;
        List<Person> l4 = null;
        List<Object> l5 = null;

        l1 = l3;
        l1 = l4;

        l2 = l5;
        l2 = l4;

        Person p1 = l1.get(0);
        //p1 = l2.get(0);//error
        Object o1 = l2.get(0);

        //l1.add(new Student());//error

        l2.add(new Person());
        l2.add(new Student());


    }


}


class Person{

}

class Student extends Person{

}

