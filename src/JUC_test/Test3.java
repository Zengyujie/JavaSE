package JUC_test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test3 {
    /*
    三个线程A，B，C调用顺序如下：
    A打印五次，B打印10次，C打印六次，循环十次

    4，注意标志位的修改和定位

     */

    public static void main(String[] args) {
//        ShareResource sr = new ShareResource();
//        new Thread(()->{for(int i = 0; i < 10; i++)sr.printA();},"A").start();
//        new Thread(()->{for(int i = 0; i < 10; i++)sr.printB();},"B").start();
//        new Thread(()->{for(int i = 0; i < 10; i++)sr.printC();},"C").start();
        NewShareResource sr = new NewShareResource(3,0);
        new Thread(()->{for(int i = 0; i < 10; i++)sr.printInfo(5,0,1);},"A").start();
        new Thread(()->{for(int i = 0; i < 10; i++)sr.printInfo(15,1,2);},"B").start();
        new Thread(()->{for(int i = 0; i < 10; i++)sr.printInfo(10,2,0);},"C").start();

    }
}


class ShareResource{

    private int number = 1;//1:A,2:B,3:C

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();


    public void printA(){
        lock.lock();
        try{
            //判断
            while(number != 1){
                condition1.await();
            }
            //干活
            for(int i = 0; i < 5; i++){
                System.out.println(Thread.currentThread().getName() + " " + number);
            }
            //通知
            number = 2;
            condition2.signal();
        }catch(Exception e){

        }finally{
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try{
            //判断
            while(number != 2){
                condition2.await();
            }
            //干活
            for(int i = 0; i < 15; i++){
                System.out.println(Thread.currentThread().getName() + " " + number);
            }
            //通知
            number = 3;
            condition3.signal();
        }catch(Exception e){

        }finally{
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try{
            //判断
            while(number != 3){
                condition3.await();
            }
            //干活
            for(int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + " " + number);
            }
            //通知
            number = 1;
            condition1.signal();
        }catch(Exception e){

        }finally{
            lock.unlock();
        }
    }



}

class NewShareResource{

    private Lock lock = new ReentrantLock();
    private int order = 0;
    private int numberOfThread = 0;
    private Condition[] conditions;

    public NewShareResource(int numberOfThread, int initOrder) {
        this.numberOfThread = numberOfThread;
        this.order = initOrder;
        this.conditions = new Condition[this.numberOfThread];
        for(int i = 0; i < this.numberOfThread; i++){
            this.conditions[i] = lock.newCondition();
        }
    }

    public void printInfo(int printNum, int now, int next){
        lock.lock();
        try{
            while(order != now){
                conditions[now].await();
            }
            for(int i = 0; i < printNum; i++){
                System.out.println(Thread.currentThread().getName() + " " + order);
            }
            order = next;
            conditions[next].signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
