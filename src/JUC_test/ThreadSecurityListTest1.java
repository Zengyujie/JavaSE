package JUC_test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadSecurityListTest1 {
    /*
    1，故障现象：
        java.util.ConcurrentModificationException，并发修改异常
    2，导致原因：
        啊
    3，解决方案：
        1，把ArrayList改成线程安全的Collections.synchronizedList()
            在小规模数据的时候可以
        //避免回答加锁，会被看成外行，因为有现成的方法
        2，juc的CopyOnWriteArrayList
            1，写时复制机制，添加时先加锁，然后不往数组里直接添加，而是将当前数组复制出一个新的，然后
            往新数组中添加元素，好处是可以并发读，而不用加锁，是一种读写分离的思想
        3，
    4，优化建议：

     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        for(String s: list){
//            System.out.println(s);
//        }

        //list = Collections.synchronizedList(list);
        //在小规模数据量性能能应付

        list = new CopyOnWriteArrayList<>();

        for(int i = 0; i < 3; i++){
            //会报出java.util.ConcurrentModificationException
            //多个线程同时修改，崩了
            List<String> finalList = list;
            //为什么不能直接用list
            //Variable used in lambda expression should be final or effectively final
            //也就是说当后面对list有修改时，这里会报错
            new Thread(()->{
                finalList.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(finalList);
            }).start();
        }

//        list = Collections.synchronizedList(list);
//        //在小规模数据量性能能应付
//
//        list = new CopyOnWriteArrayList<>();
    }

}
