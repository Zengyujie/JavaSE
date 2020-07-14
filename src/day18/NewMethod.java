package day18;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NewMethod {

    /*
    jdk5.0新增线程创建方式
    1，实现Callable接口
        1，与Runnable方法相比
            1，相比run()，call()方法可以有返回值
            2，call()方法可以抛出异常
            3，支持泛型的返回值
            4，需要借助FutureTask类，比如获取返回结果
        2,Future接口：
            1，可以对Runnable，Callable任务的执行结果进行取消，查询是否完成，获取结果等
            2，FutureTask是Future唯一实现类
            3，FutureTask同时实现了Runnable和Future，既可以作为Runnable被线程执行，又可以作为Future得到
                Callable的返回值
        3,步骤：
            1，创建一个实现Callable的实现类
            2，实现call方法，将线程需要执行的操作声明在call()中
            3，创建Callable接口实现类的对象
            4，将此Callable实现类的对象作为参数传入FutureTask构造器中
            5，将FutureTask对象作为参数传递到Thread构造器中，创建Thread对象，调用start()
            6，调用FutureTask对象的get()方法获取call方法的返回值
        4，如何理解Callable比Runnable更强大：
            1，call()有返回值
            2，call()可以抛出异常
            3，Callable支持泛型




     */

    public static void main(String[] args) {
        NumThread num = new NumThread();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(num);

        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            //get()返回值即为FutureTask构造参数Callable实现重写call()的返回值
            Object sum = futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}

class NumThread implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for(int i = 0; i <= 100; i++){
            sum += i;
            System.out.println(i);
        }
        return sum;//自动装箱
    }

}