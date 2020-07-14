package day24;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaTest1 {

    /*
    Lambda:
    1，lambda表达式格式：
        ->:lambda操作符
        ->左边是形参列表（接口中的抽象方法形参列表）
        ->右边是lambda体，是重写方法的方法体
    2，lambda表达式的使用：六种形式
    3，lambda表达式的本质：作为接口的实例
    4，如果一个接口只有一个方法，就叫做函数式接口
    5，lambda表达式的本质就是函数式接口的实例
    6，java.util.function种定义了若干函数时接口
        内置四大函数式接口：
            Consumer<T> void accept(T t)
            Supplier<T> T get()
            Function<T,R> R apply(T t)
            Predicate<T> boolean test(T t)

    方法引用：
    1，如果传递给lambda体的操作已经有实现方法了，可以使用方法引用
    2，格式：使用::将类与方法名分隔开
    3，主要使用情况：
        1，对象::非静态方法
        2，类::静态方法
        3，类::非静态方法
    4，使用情况：要传递给lambda方法已经有类实现了
    5，方法引用的使用要求：要求接口中的抽象方法形参列表与返回值类型与引用方法
        的形参列表和返回值类型相同，名字可以不同


    构造器引用：
        和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致
        抽象方法返回值类型为构造器所属类的类型即可


    数组引用：
        可以把数组看成是一个特殊的类，类似构造器引用
     */

    @Test
    public void test1(){

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("test");
            }
        };

        r1.run();

        //第一种形式
        r1 = () -> System.out.println("test2");
        r1.run();
        //只有一句话大括号可省略

        //::方法引用
        Comparator<Integer> c2 = Integer::compare;

        //第二种形式：有参数
        Consumer<String> con = (String s)->{
            System.out.println(s);
        };

        //第三种形式：类型推断，根据声明的泛型来确定
        con = (s)-> System.out.println(s);

        //第四种格式：只有一个参数小括号可省，空参小括号不能省略
        con = s-> System.out.println(s);

        //第五种形式：不只有一个语句，要使用大括号
        Comparator<Integer> c1 = (o1, o2)->{
            System.out.println("test");
            return o1.compareTo(o2);
        };

        //第六种格式：只有一条执行语句可以省略大括号，return也可以省略
        c1 = (o1, o2)-> o1.compareTo(o2);

        //接口声明不一定非要加上@FunctionalInterface
        Comsumer<Integer> com = s ->{};


    }

    @Test
    public void test2(){
        testLambda(1.0, d->{
            System.out.println(d);
        });
    }

    public void testLambda(double money, Consumer<Double> con){
        con.accept(money);
    }

    @Test
    public void test3(){
        //方式一：
        PrintStream ps = System.out;
        Consumer<String> con = ps::println;
        Comsumer<String> com = (str)->{
            System.out.println(str);
        };
        com = ps::println;
        //方式二：
        Comparator<Integer> c4 = Integer::compare;
        Supplier<String> sup1 = ()->{return "test";};
        Function<Double, Long> func1 = d->Math.round(d);
        func1 = Math::round;
        System.out.println(func1.apply(1.1));
        //方式3
        Comparator<String> com1 = (s1, s2)->s1.compareTo(s2);
        com1 = String::compareTo;
        //对于以上方法，由于s1是执行者，故可以这样写

        BiPredicate<String, String> pre1 = (s1,s2)->s1.equals(s2);
        pre1 = String::equals;
        System.out.println(pre1.test("a","b"));



    }

    @Test
    public void test4(){
        Supplier<Person> sup = new Supplier<Person>() {
            @Override
            public Person get() {
                return new Person();
            }
        };
        sup = ()->new Person();
        //构造器引用
        sup = Person::new;

        Function<Integer, Person> fun = Person::new;
        //相当于调用Person(int id)构造器




    }

    @Test
    public void test5(){
        Function<Integer, String[]> fun = length-> new String[length];
        String[] arr1 = fun.apply(5);
        //数组引用
        fun = String[]::new;

    }

}


interface Comsumer<T>{
    void accept(T s);
}

@FunctionalInterface
interface MyInter{
    void method1();

}

