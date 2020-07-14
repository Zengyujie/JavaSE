package JUC_test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest1 {

    /*
    callable与runnable区别
    1，callable有返回值
    2，callable可以抛出异常
    3，callable可以支持泛型
    4，callable方法名字叫call，runnable叫run

    2，futureTask
        1，get()放在最后
        2，get()调用之后会阻塞，等待call执行结束之后获得返回值再继续
        3，无论new多少个线程，FutureTask只调用一次call，第二次会因为已经得到
            call的返回值而略过(FutureTask中的State不为New)
     */


    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        FutureTask ft = new FutureTask(t1);
        new Thread(ft,"A").start();
        new Thread(ft,"B").start();//call只调用一次
        try {
            System.out.println(ft.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
        }
    }

}


class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("call");
        return 1024;
    }

}