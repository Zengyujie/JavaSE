package JUC_test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test2 {
    /*
    题目：两个线程。可操作初始值为0的一个变量，实现一个线程加一，另一个减一交替10轮
    1，多线程通信口诀2：判断 干活 通知
    2，多线程交互中，必须防止多线程的虚假唤醒，也即(wait-notify)
    3，wait不能用if，只能用while，否则会造成虚假唤醒
    4，synchronized对应的方法有Object自带的wait和notify，notifyAll
        lock中有对应的Condition方法await，signal和signalAll
     */
    public static void main(String[] args) {
        MyResource mr = new MyResource();
        new Thread(()->{for(int i = 0; i < 10; i++)mr.addOne();},"A").start();
        new Thread(()->{for(int i = 0; i < 10; i++)mr.subOne();},"B").start();
        new Thread(()->{for(int i = 0; i < 10; i++)mr.addOne();},"C").start();
        new Thread(()->{for(int i = 0; i < 10; i++)mr.subOne();},"D").start();

    }
}

class MyResource{
    private int number;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public MyResource() {
        this.number = 0;
    }

    public MyResource(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void addOne(){
        this.lock.lock();
        try{
            while(number != 0){
                condition.await();
            }
            System.out.println("current Thread is" + Thread.currentThread().getName() + "addOne,curr is" + (this.number++));
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.lock.unlock();

        }
    }

    public synchronized void increase(){
        //判断
        while(number != 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //干活
        number++;
        System.out.println(Thread.currentThread().getName() + number);
        //通知
        this.notifyAll();
    }

    public void subOne(){
        this.lock.lock();
        try{
            while(number == 0){
                condition.await();
            }
            System.out.println("current Thread is" + Thread.currentThread().getName() + "subOne,curr is" + (this.number--));
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.lock.unlock();

        }

    }

    public synchronized void decrease(){
        //判断
        while(number == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //干活
        number--;
        System.out.println(Thread.currentThread().getName() + number);
        //通知
        this.notifyAll();
    }
}