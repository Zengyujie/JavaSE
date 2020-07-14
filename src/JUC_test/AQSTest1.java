package JUC_test;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;


public class AQSTest1 {
    /*
    synchronized 重量级锁：
        1，调用本地操作系统的方法
        2，native方法
            java.exe->hotspot项目
            native方法对应hotspot中的c/c++方法
        3，jvm中的一个线程对应OS中的一个额线程
        4，jdk1.6以前调用时会有一个CPU切换到内核态然后再切到用户态
        5，jdk1.7之后synchronized优化了，性能已经与ReentrantLock接近

    2，CAS：Compare And Set

    3，ReentrantLock底层使用了AbstractQueueSynchronizer(AQS)
        有两个实现类：
        1，非公平锁NonFireSync()
        2，公平锁FireSync()

    4，AQS底层：双向链表，节点时AQS中的静态内部类Node，每个node存线程
        一个state

    5，reentrantLock调用java层面的，synchronized，park都是os层面的

    6，对于交替执行线程，jdk1.8之后synchronized也优化到java层面执行了

    
     */

    public static void main(String[] args) {
        ReentrantLock lock = null;
        ReadWriteLock lock1 = null;
        MyList list = new MyList();
        new Thread(()->{ list.add("A"); }).start();
        new Thread(()->{list.printString(); }).start();

    }
}

//失败的实现
class MyList{
    ArrayList<String> list = new ArrayList<>();
    MyCASLock lock = new MyCASLock();
    public void add(String s){
        lock.lock();
        for(int i = 0; i < 10; i++){
            list.add("A");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock.unlock();
    }

    public void printString() {
        while(!lock.compareAndSet(0,0)){
            for(int i = 0; i < 10; i++) {
                System.out.println(list);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}


//自己写的一个渐进的锁过程
class MyCASLock implements Lock {
    private volatile int state = 0;
    BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(3);

    public boolean compareAndSet(int oldState, int newState){
        if(state == oldState){
            state = newState;
            return true;
        }
        return false;
    }

    public void myPark(){
        //park伪实现
        //先将某个线程放入队列//不一定是current线程
        queue.offer(Thread.currentThread());
        //然后让出cpu
        releaseCpu();

    }

    public void releaseCpu(){}

    public void unlock1(){
        queue.poll();
        //然后再unpark
    }


    @Override
    public void lock() {


        //1，自旋
        //while(true)
        //2，yield让出cpu+自旋
        //对两个线程有用，但是多个就不适用了
        //因为CPU的调度不知道
        while(!compareAndSet(0,1)){
            Thread.yield();
        }
        //3，睡眠+自旋
        //但是睡眠时间不确定
        while(!compareAndSet(0,1)){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //4，park加自旋
        while(!compareAndSet(0,1)){
            //java.util.concurrent.locks.LockSupport.park();
            //调用Unself中的park
            //对应有一个unpark

            myPark();
        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        state = 0;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}