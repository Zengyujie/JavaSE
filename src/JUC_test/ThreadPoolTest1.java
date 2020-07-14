package JUC_test;

import java.util.concurrent.*;

public class ThreadPoolTest1 {
    /*
    1，线程池的优势：
        线程池的工作是控制运行的线程数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务，
        如果线程数量超过了最大数量，超出数量的线程排队等候，等待其他线程执行完毕，再从队列中取出任务来执行
    2，它的主要特点是：线程复用，控制最大并发数，管理线程
    3，优点有：降低线程创建销毁造成的开销，提高响应速度，提高可管理性

    4，Executor->ExecutorService接口，可以看作是线程池，主要的实现类是ThreadPoolExecutor类
        一个辅助工具类Executors，可以通过这个类来获取线程池
        Executor中new的线程池几乎都是对ThreadPoolExecutor传入不同的参数

    5，线程池的参数：ThreadPoolExecutor的七个参数
        1，corePoolSize(int):线程池中的常驻核心线程数
        2，maximumPoolSize(int):线程池中能够容纳同时执行的最大线程数，必须大于1
        3，keepAliveTime(long)：多余空闲线程存活时间：即大于corePoolSize的线程超过时间
            会被销毁直到剩下corePoolSize个线程
        4，unit(TimeUnit)：keepAliveTime的单位
        5，workQueue(BlockingQueue<Runnable>)：任务队列，被提交但尚未执行的任务
        6，threadFactory(ThreadFactory)：表示生成线程池工作线程的线程工厂，用于创建线程，一般默认即可
        7，handler(RejectedExecutionHandler)：拒绝策略，当队列满了并且工作线程大于等于最大线程数时如何拒绝执行
            请求的runnable策略
    6，如何在线程池中设置合理参数
        1，工作中用哪个线程池方法呢？
            答案是一个都不用，只使用自定义的，因为Executor中提供的方法由于最大值为int的最大值
                可能造成OOM，因此要避免资源耗尽的风险
            要通过ThreadPoolExecutor的方式自己填参数
    7，拒绝策略：
        1，当线程池线程数达到了最大的数量且阻塞队列也满了，就会执行拒绝策略
        2，拒绝策略都实现了RejectedExecutionHandler接口
        3，四种jdk拒绝策略：
            1，AbortPolicy:默认策略，直接抛出RejectedExecutionException阻止系统正常运行
            2，CallerRunsPolicy:“调用者运行”，一种调节机制，不会抛弃也不会抛异常，而是将任务回退到调用者从而降低流量
                谁找我，我回退
            3，DiscardOldestPolicy:抛弃队列中等待最久的任务，然后把当前任务加入到队列中尝试提交
            4，DiscardPolicy:丢弃无法处理的任务，不抛异常
     8，线程池最大线程数一般设置为：
        CPU密集型：处理器核数+1或2
        IO密集型：CPU核数量/阻塞系数
     */

    public static void main(String[] args) {
        //注意阻塞队列的初始值，默认长度爆炸(Integer.MAX)

        int maximumPoolSize = Runtime.getRuntime().availableProcessors();

        ExecutorService threadpool = new ThreadPoolExecutor(
                2,
                maximumPoolSize+1,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        try{
            //十个顾客，五个工作人员
            for(int i = 0; i < 10; i++){
                threadpool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t service");
                });
                //TimeUnit.MILLISECONDS.sleep(1000);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadpool.shutdown();
        }

    }

    public void test1(){
        ExecutorService threadpool = Executors.newFixedThreadPool(5);
        //创建固定线程的线程池

        try{
            //十个顾客，五个工作人员
            for(int i = 0; i < 10; i++){
                threadpool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t service");
                });
                TimeUnit.MILLISECONDS.sleep(1000);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadpool.shutdown();
        }
    }

    public void test2(){
        ExecutorService threadpool = Executors.newSingleThreadExecutor();
        //创建只有一个线程的线程池

        try{
            //十个顾客，五个工作人员
            for(int i = 0; i < 10; i++){
                threadpool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t service");
                });
                //TimeUnit.MILLISECONDS.sleep(1000);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadpool.shutdown();
        }

    }

    public void test3(){
        ExecutorService threadpool = Executors.newCachedThreadPool();
        //创建可伸缩的线程池

        try{
            //十个顾客，五个工作人员
            for(int i = 0; i < 10; i++){
                threadpool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t service");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadpool.shutdown();
        }
    }

}
