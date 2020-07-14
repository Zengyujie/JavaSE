package day24;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPITest1 {
    /*
    java.util.stream：Stream API，提供了一种高效的数据处理方式
    使用Stream API处理集合数据类似于SQL查询

    Stream和Collection的区别：Collection是一种静态的内存数据结构，Stream是有关计算的

    Stream是数据通道，用于操作数据所生成的序列，Stream不会改变对象，会返回一个持有结果的新Stream
    Stream操作是可延迟的，意味着可以在需要结果时才执行

    Stream的三个操作：
        1，创建：一个数据源，获取一个流
        2，中间操作：中间操作链用于处理数据(过滤、映射等)
        3，终止操作：一旦执行终止操作就执行中间操作链，并产生结果，之后不会再使用
        注：中间操作是执行终止操作之后才执行


    四种方式创建对象
        1，通过集合的stream()或parallelStream()方法
        2，通过数组Arrays.stream()等方法
        3，通过Stream.of(T ...方法)
        4，创建无限流

    Stream中间操作：
        1，筛选与切片
        2，映射
        3，排序

    Stream终止操作：
        1，匹配与查找
        2，规约


     */

    @Test
    public void test1(){
        String[] strs = {"1a","2b","3c","4d","5e","6f"};
        ArrayList<String> list = (ArrayList)Arrays.asList(strs);
        //返回一个顺序流,按照顺序取
        Stream<String> stream = list.stream();
        //返回一个并行流，多个线程取，不一定顺序一样
        Stream<String> stream1 = list.parallelStream();

        //通过数组
        Stream<String> stream2 = Arrays.stream(strs);

        //通过Stream
        Stream<String> stringStream = Stream.of(strs);

        //无限流
        //1，迭代
        Stream.iterate(0,t->t+2).limit(10).forEach(System.out::println);

        //2，生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);


    }



    @Test
    public void test2(){
        String[] strs = {"1a","2b","3c","4d","5e","6f","1a"};
        Stream<String> stream = Stream.of(strs);
        //过滤
        stream.filter(e -> e.length() > 1).forEach(System.out::println);
        //截断
        //stream.limit(3).forEach(System.out::println);//因为用过一次，所以会报错
        Stream<String> stream1 = Stream.of(strs);//需要重开一个流
        stream1.limit(3).forEach(System.out::println);
        //跳过前n个元素，不足n个返回空流
        System.out.println();
        Stream<String> stream2 = Stream.of(strs);
        stream2.skip(3).forEach(System.out::println);
        //筛选，通过hashCode和equals方法去除重复元素
        Stream<String> stream3 = Arrays.stream(strs);


    }

    @Test
    public void test3(){
        /*
        map(Function f)接受一个函数作为参数，将元素转换成其他形式提取信息，该函数会被用到每个元素上
        并将其映射成一个新的函数
         */
        String[] strs = {"1a","2b","3c","4d","5e","6f","1a"};
        Stream<String> stream = Stream.of(strs);
        stream.map(str->str.toUpperCase()).forEach(System.out::println);
        //flatMap(Function f)接受一个函数作为参数，将流中的每一个值都换成另一个流，然后把所有流连接成一个流
        Stream<Character> sc1 = Stream.of('a','b','c');
        Stream<Character> sc2 = Stream.of('e','f','g');
        Stream<Character>[] scs = new Stream[]{sc1, sc2};
        Stream<Stream<Character>> ssc = Stream.of(scs);
        ssc.forEach(s->{
            s.forEach(System.out::println);
        });
        //或者
        Stream<Character> ssc1 = Stream.of(scs).flatMap(f->{return null;});
        ssc1.forEach(System.out::println);


    }

    @Test
    public void test4(){
        //排序
        List<Integer> list =  Arrays.asList(1,2,3,4,7,5,6);
        list.stream().sorted().forEach(System.out::println);
        //要排序需要实现自然排序或者定制排序
        List<Person> list1 = Arrays.asList(new Person(), new Person(1));
        list1.stream().sorted((p1, p2)->{
            return p1.id - p2.id;
        }).forEach(System.out::println);

    }

    @Test
    public void test5(){
        List<Integer> list =  Arrays.asList(1,2,3,4,7,5,6);
        //是否所有都满足条件
        boolean res = list.stream().allMatch(e-> e > 2);
        //是否存在元素满足条件
        res = list.stream().anyMatch(e-> e > 2);
        //是否存在都不匹配元素
        res = list.stream().noneMatch(e -> e > 9);
        //找第一个元素
        Optional<Integer> num = list.stream().findFirst();
        //找任意一个元素
        num = list.stream().findAny();
        //求个数
        long nums = list.stream().filter(e->e > 1).count();
        //求最大值
        num = list.stream().max(Integer::compare);
        //最小值
        num = list.stream().min(Integer::compare);
        //内部迭代
        list.stream().forEach(System.out::println);
        //使用Collection接口迭代称为外部迭代，Stream API
        //的方法称为内部迭代


    }

    @Test
    public void test6(){
        /*
        reduce
         */
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
        int sum = list.stream().reduce(0,Integer::sum);
        list.stream().map((i)->{return i;}).reduce(Integer::sum);

        //收集
        /*
        collect(Collector c):将流转换为其他形式，接受一个Collector接口实现
        Collectors工具类提供了很多静态方法可创建常用收集器
         */

        List<Integer> list2 = list.stream().filter(e-> e > 1).collect(Collectors.toList());

    }


}
