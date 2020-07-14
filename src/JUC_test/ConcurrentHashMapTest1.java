package JUC_test;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest1 {
    /*
    ConcurrentHashMap:并发hash操作
    1，jdk1.8之前分段锁机制，对底层结构分段，每一段加锁
    2，jdk1.8取消了分段锁，采用了CAS，不涉及上下文切换，效率高

    其他适用于多线程上下文中的Collection结构：
    ConcurrentHashMap, ConcurrentSkipListMap, ConcurrentSkipListSet
    CopyOnWriteArrayList, CopyOnWriteArraySet,
    当多线程访问时ConcurrentHashMap优于同步的HashMap，ConcurrentSkipListMap由于同步的
    TreeMap，当读和遍历大于更新时CopyOnwriteArrayList由于同步的ArrayList，
    同步指使用Collections工具类包装的类
     */

    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("test","test");

    }

}
