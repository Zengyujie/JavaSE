package JUC_test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkAndJoinTest1 {
    /*
    Fork/Join框架：在必要情况下，将一个大人物进行拆分(fork)成若干个小任务
    (不可再拆分)，再将一个个小任务运算结果进行join汇总
    1，工作窃取模式：执行新任务时，可以将其拆分成更小的任务执行，各个核对应的线程队列加入任务，
        当自己任务执行完或者其他线程阻塞且存在未完成的子任务时，可以从那些线程队列中偷一个并把它放在自己队列中


    2，
     */

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0,200);
        long sum = pool.invoke(task);
        System.out.println(sum);
    }

}

//RecursiveTask有返回值
//RecursiveAction没有返回值

class ForkJoinSumCalculate extends RecursiveTask<Long>{

    //private static final long serialVersionUID = 523245395270L;

    private long start;
    private long end;

    private static final long THURSHOLD = 100L;//临界值

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if(length <= THURSHOLD){
            long sum = 0L;
            for(long i = start; i < end; i++){
                sum += i;
            }
            return sum;
        }else{
            long middle = (start + end) / 2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            left.fork();//拆分，同时压入线程对队列
            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
