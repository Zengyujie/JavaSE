package day18;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {


    /*

     2，线程池
        1，经常创建和销毁，使用量特别大的资源，比如并发情况下的线程，对性能影响很大
            因此提前创建好多个线程，放入线程池中，使用时直接获取，使用完毕放回池中，
            可以避免频繁创建销毁，实现重复使用
        2，好处，提高了响应速度(创建时间减少)，降低了资源消耗(重复利用线程池中线程，
            不用每次都创建，便于线程管理：corePoolSize线程池大小
            ,maximumPoolSize最大线程数,keepAliveTime线程没有任务时最多保持多长时间终止，等等)
        3，5.0相关API：ExecutorService和Executors
            1，ExecutorService:真正的线程池接口，常见子类ThreadPoolExecutor
                void execute(Runnable)：执行任务，没有返回值
                Future<T> submit(Callable<T>):执行任务，有返回值
                void shutdown():关闭连接池
            2，Executor:工具类，线程池的工厂类，用于创建并返回不同类型的线程池
                newCachedThreadPool():创建一个可根据需要创建新线程的线程池
                newFixedThreadPool():创建一个可以重用固定线程数量的线程池
                newSingleThreadExecutor():创建一个只有一个线程的线程池
                newScheduledThreadPool(n):创建一个线程池，可以安排在给定延迟后运行或者定期执行



     */

    public static void main(String[] args) {
        //1，提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        System.out.println(service.getClass());

        //ThreadPoolExecutor service1 = (ThreadPoolExecutor)service;
        //((ThreadPoolExecutor) service).setCorePoolSize(10);

        //2，提供实现Runnable接口的对象
        service.execute(new NumThread1());//适合Runnable
        //2，或者提供实现Callable接口的对象
        //service.submit();//适合Callable
        //3，关闭连接池
        service.shutdown();//执行完之后需要手动关闭

        ArrayList a = new ArrayList();
        a.add(new Object());
        a.remove(1);

//        HashMap map = new HashMap();
//        map.put();
//        map.
//        Hashtable table = new Hashtable();
//        table.put();
//
//
//        HashSet set = new HashSet();
//        set.add();

//        AtomicInteger i;
//        ReentrantLock lock = new ReentrantLock();
//        lock.lock();
//        Condition con = lock.newCondition();
//        con.await();

    }


}


class NumThread1 implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}