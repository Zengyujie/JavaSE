package JUC_test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CountDownLatchTest1 {
    /*
    线程同步工具类1，抽出门闩
    1，减法：CountDownLatch：
        1，构造方法中传入的是要统计的线程数
        2，线程执行完后调用countDown()计数器减一
        3，await()方法在计数器为0时才取消阻塞

     */

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(6);
        for(int i = 0; i < 6; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "leave");
                cdl.countDown();
            },String.valueOf(i)).start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all leave");

    }

    public static void myImplement(){
        FutureTask<Integer>[] fts = new FutureTask[6];
        Thread[] threads = new Thread[6];
        for(int i = 0; i < fts.length; i++){
            fts[i] = new FutureTask<Integer>(()->{
                System.out.println(Thread.currentThread().getName() + "leave");
                return 1;
            });
            new Thread(fts[i]).start();
        }

        int res = 0;
        for(int i = 0; i < fts.length; i++){
            try {
                res += fts[i].get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("all leave");
    }

}

