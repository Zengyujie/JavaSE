package day21;

import org.junit.Test;

import java.io.FileInputStream;
import java.util.*;

public class MapTest1 {
    /*
    一：Map：存双列数据，key-value数据
    1，HashMap：Map的主要实现类，线程不安全，效率高，可存储null的key和value
        使用Collections方法使其线程安全
        1，HashMap底层：jdk之前是数组加链表，jdk8之后是数组加链表加红黑树
        1.1，LinkedHashMap：HashMap子类，键值对添加了两个指针，可以按照添加顺序实现遍历

    2，TreeMap：按照添加的key-value排序，实现排序遍历，此时考虑key的自然排序
        底层使用红黑树
    3，HashTable：古老的Map实现类，线程安全的，效率低，不可存储null的key，value
        1.1，Properties

    面试题：
        1，HashMap的底层实现原理
        2，HashMap和Hashtable的异同
        3，CurrentHashMap与Hashtable的异同

    二，Map
    1，Map中的key：无序的，不可重复的，使用Set存储key(linked的map就是linkedset)
        HashMap中，key要重写hashCode和equals方法，TreeMap要重写hashCode和定制/自然排序方法
    2，Map中的value：无序的，可重复的，使用Collection存储所有的value，value要重写equals方法
    3，Map中的entry：无序的，不可重复的，使用Set存储，key-value构成了entry
    （HashMap中是Node，LinkedHashMap是Entry）

    三，HashMap底层实现原理
        1，jdk7：
            1.1，空参构造器创建了一个长度为16的Entry[] table
            1.2，map.put(key, value)：
                1，首先调用key的hashcode方法，计算hash值
                经过散列函数之后得到在Entry数组中存放的位置，如果此位置上的数据为null
                此时的key，value添加成功。
                2，如果此位置上的数据不为空，意味着存在一个或者多个(链表形式)数据
                则比较key和存在数据的hash值，如果与数据都不相同，则添加成功。
                3，若key和某一个数据的hash值相同，继续比较equals方法，如果equals返回false，添加成功，如果返回true，使用value去替换相同key的value值
                此时put是修改操作
                补充：关于2，和3，key-value和原来的数据以链表的方式存储，新元素头插
                        扩容方式：扩容为原来的两倍，将元素复制过来
        2，jkd8：
            1.1，在new HashMap()时，底层创建为Node[] table= {}，而不是Entry，Node实现了Map.Entry接口
            1.2，首次调用put时创建长度为16的数组
            1.3，底层结构为数组+链表+红黑树，当索引位置上元素个数超过8且数组长度大于64时，将
                此索引位置上的所有数据改为只用红黑树存储

        3，Map的构造过程：
            1，判断容量是否大于最大容量，2^30，判断负载因子是否为正数
            2，将容量变为大于传入参数最小的2^n数
            3，将扩容门限设置为容量乘以负载因子
            4，1.7是创建entry[capacity]，1.8不创建
        4，1.7put过程：
            1，首先计算key的hash值(调用+一系列右移操作)，不是单纯的hashcode方法，而是map的hash方法
            2，调用indexFor方法，使用hash值和容量-1的与运算(保证结果在容量之内)，获得存放位置
            3，然后通过一个for循环找出应插入的位置，如果待插入元素的hash和已存元素的hash是相等
                且两个key相等(同一个对象或者equals)则修改对应value，将原value返回
            4，最后执行AddEntry：首先看容量是否需要扩容，并且要插入位置是否非空，若都满足，则扩容
                默认扩容为原来的二倍，所有元素重新计算hash。然后再添加
                添加过程是，将新Entry放在插入位置，然后自己链上原来的entry
        5，1.8put过程：
            1，判断table是否为空或者长度是否为0，若是则调用resize函数创建默认值和负载因子的数组
            2，判断数组当前位置是否为空，若为空则添加成功
            3，若当前位置不为空，则判断待加入Node与当前位置第一个Node的key地址值是否一样，或者key是否equals，若一样，则修改
            4，若当前位置不为空，且第一个Node和待加入的Node的key不同，则触发两条支线
                1，树：

                2，链表：
                    1，for循环看Node的下一个节点，如果有key相同的还是触发修改操作，break
                        如果都没有则会默认到最后null的地方，然后再末尾插入，如果插入后链表数量超过门限，就变为红黑树
                        变为红黑树调用treeifyBin函数，
                            该函数首先判断table是否为空或者table长度小于最小的成树门限(64)，则扩容

         6，HashMap中的重要常数：
            1，DEFAULT_INITIAL_CAPACITY：默认容量16
            2，DEFAULT_LOAD_FACTOR：默认负载因子：0.75
            3，threshold：扩容临界值：length*负载因子
            4，TREEIFY_THRESHOLD：链表最大长度默认值
            5，MIN_TREEIFY_CAPACITY：默认Node变为红黑树最小容量

         7，LinkedHashMap底层实现原理
            1，put方法还是调用了HashMap的put方法
            2，重写了newNode方法
            3，使用Entry继承了HashMap的Node，加了两个指针来记录先后顺序

         8，HashSet实际上是把元素放到了HashMap的key位置，value用一个Object对象来代替

     四，map接口中定义的方法

     五，TreeMap
        1，key必须是同一个类创建的对象，要按照key来排序
        2，自然排序，定制排序
        3，只能排序key，不能排value

     六：Properties
        1，Hashtable子类
        2，key，value只能是String


     */


    @Test
    public void test1(){
        Map map = new HashMap();
        map.put(null, 123);
        Map map1 = new Hashtable();
        map1.put(null, 123);


    }

    @Test
    public void test2(){
        Map map = new LinkedHashMap();
        map.put(1,"A");
        map.put(2,"B");
        map.put(3,"C");
        System.out.println(map);
    }

    @Test
    public void test3(){
        Map map = new HashMap();
        map.put("A",1);
        map.put("B",2);
        map.put("C",3);
        map.put("D",4);
        map.put("A", 7);
        System.out.println(map);
        Map map1 = new HashMap();
        map.put("E", 6);
        map.putAll(map1);
        System.out.println(map);
        //remove
        map1.remove("E");
        System.out.println(map1);
        //clear
        map1.clear();//table还在，元素清空了
        System.out.println(map1.size());
        //get
        System.out.println(map.get("C"));
        //contains
        System.out.println(map.containsKey("C"));
        System.out.println(map.containsValue(1));
        System.out.println(map1.isEmpty());//判断size
        //equals
        System.out.println(map.equals(map1));

        //遍历
        Set keySet = map.keySet();
        for(Iterator it = keySet.iterator(); it.hasNext();){
            System.out.println(map.get(it.next()));
        }
        Collection valueSet = map.values();
        for(Object o: valueSet){
            System.out.println(o);
        }

        Set entrys = map.entrySet();
        for(Iterator it = entrys.iterator(); it.hasNext();){
            Map.Entry en = (Map.Entry)it.next();
            System.out.println(en.getKey() + " : " + en.getValue());

        }


    }

      public void test4(){
        TreeMap map = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Person && o2 instanceof Person){
                    Person p1 = (Person)o1;
                    Person p2 = (Person)o2;
                    return p1.compareTo(p2);
                }
                else{
                    throw new RuntimeException();
                }
            }
        });
        Person p1 = new Person("Tom1",12);
        Person p2 = new Person("Tom2",13);
        Person p3 = new Person("Tom3",16);
        Person p4 = new Person("Tom4",15);



    }

    public static void main(String[] args) throws Exception{
        Properties pros = new Properties();
        FileInputStream fis = new FileInputStream("jdbc.properties");
        //配置文件不要加空格
        pros.load(fis);
        String name = pros.getProperty("name");
        String password = pros.getProperty("password");
        System.out.println(name + " + " + password);
        fis.close();
        //alt+shift+z加try-catch快捷键
    }
    
}
