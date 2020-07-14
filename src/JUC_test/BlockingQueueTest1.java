package JUC_test;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest1 {
    /*
    BlockingQueue接口：
    1，当队列是空的，获取数据会被阻塞
    2，当队列是满的，添加元素会被阻塞

    实现类：
        1，ArrayBlockingQueue:由数组组成的有界阻塞队列
        2, LinkedBlockingQueue:由链表组成的有界阻塞队列，界为Integer.MAX_VALUE
        3, PriorityBlockingQueue:支持优先级排序的无界阻塞队列
        4，DelayQueue:使用优先级队列实现的延迟无界阻塞爹列
        5，SynchronousQueue:不存储元素的阻塞队列，即单个元素的队列
        6，LinkedTransferQueue:由链表组成的无界阻塞队列
        7，LinkedBlockingDeque:由链表组成的双向阻塞队列

    方法类型    抛出异常    特殊值     阻塞      超时
    插入          add(e)    offer(e)  put(e)    offer(e.time,unit)
    移除         remove()   poll()    take()    poll(time,unit)
    检查         element()  peek()    不可用    不可用


    抛出异常：当阻塞队列满时，add数据会抛出IllegalStateException: Queue full
             当阻塞队列空时，remove数据会抛出NoSuchElementException，element返回队首第一个元素，也会抛该异常
    特殊值：插入方法，成功返回true，失败返回false
            移除方法，成功返回元素，失败返回null
    一直阻塞：阻塞队列满时，生产者线程往队列里put元素，队列会一直阻塞直到put数据或者响应中断退出
             当队列空时，消费者线程从队列take元素，队列会一直阻塞消费者线程直到队列可用
    超时退出：队列满时，队列阻塞生产者线程一定的时间，超过限制后生产者线程会退出

     */

    public static void main(String[] args) {
        BlockingQueue<String> bq = new ArrayBlockingQueue(3);

        try {
            bq.put("a");
            bq.put("b");
            bq.put("c");
            //bq.put("d");//一直阻塞
            bq.offer("d",3, TimeUnit.SECONDS);
            System.out.println(bq.peek());
            bq.take();
            bq.take();
            bq.take();
            bq.poll(3,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public static void exceptionTest1(){
        Collection collection = null;
        //List, Set, Queue<-BlockingQueue, Stack,
        BlockingQueue<String> bq = new ArrayBlockingQueue(3);
        System.out.println(bq.add("a"));
        System.out.println(bq.add("b"));
        System.out.println(bq.add("c"));
        System.out.println(bq.element());
        try {
            System.out.println(bq.add("s"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String str = bq.remove();
        System.out.println(str);
        str = bq.remove();
        str = bq.remove();
        try {
            str = bq.remove();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(bq.element());
    }

    public static void SpecialValueTest1(){
        BlockingQueue<String> bq = new ArrayBlockingQueue(3);
        System.out.println(bq.offer("a"));
        System.out.println(bq.offer("b"));
        System.out.println(bq.offer("c"));
        System.out.println(bq.offer("d"));
        System.out.println(bq.poll());
        System.out.println(bq.poll());
        System.out.println(bq.poll());
        System.out.println(bq.poll());
    }

}
