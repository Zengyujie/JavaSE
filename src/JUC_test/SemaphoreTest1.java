package JUC_test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest1 {
    /*
    线程同步工具类3，
    3，信号量
        1，acquire操作：当一个线程调用时，要么成功获取信号量(信号量建议)
            要么一直等下去
        2，release操作：会使信号量加一，然后唤醒等待线程
        3，信号量主要用于两个目的，一是多个资源的互斥使用，另一个是用于并发线程数的控制

     */

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        //模拟资源类有三个空车位

        for(int i = 0; i < 6; i++){
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "get arrive");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "get leave");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }


            }).start();
        }
    }
}
