package JUC_test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarricTest1 {
    /*
    线程同步工具类2，围上栅栏
    2，加法：CyclicBarric
        1，构造函数中指明要执行的线程数量和这些线程执行完之后要执行的Runable内容
        2，每个线程运行结束位置加上await方法，自动阻塞
        3，当所有线程执行完之后才会解除阻塞继续执行

     */

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(7,()->{
            System.out.println("test");
        });
        for(int i = 0; i < 7; i++){
            final int in = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "number" + in);
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }
    
}
