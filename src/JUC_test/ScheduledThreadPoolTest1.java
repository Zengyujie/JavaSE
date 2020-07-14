package JUC_test;

import java.util.Random;
import java.util.concurrent.*;

public class ScheduledThreadPoolTest1 {
    /*

     */

    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        int loopNum = 10;
        Future[] futureSet = new Future[loopNum];
        for(int i = 0; i < 10; i++){
            futureSet[i] = pool.schedule(()->{
                int num = new Random().nextInt(100);
                System.out.println(Thread.currentThread().getName() + ":" + num);
                return num;
            },200, TimeUnit.MILLISECONDS);
        }

        for(int i = 0; i < loopNum; i++){
            try {
                System.out.println(futureSet[i].get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        pool.shutdown();
    }


}
