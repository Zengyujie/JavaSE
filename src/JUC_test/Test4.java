package JUC_test;


import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Test4 {
    /*
    多线程八锁现象
    1，标准访问，先打印method1还是2?，先1，后2
        1，普通的synchronized锁的是当前对象，同一个时间只有一个能进来
    2，方法一暂停四秒，先打印1还是2？ 先1，后2
        1，同上
    //一个对象如果有多个普通synchronized方法，某一个时刻只要一个线程去调用其中一个
        其他线程只能等待，只能有唯一一个线程去访问这些方法，锁的是当前对象this
    3，2变为普通方法，1还是等四秒，先打印1还是2？先2后1
        1，普通方法和synchronized方法并不相关，锁住了也能运行普通方法
    4，两部手机，先打印1还是2？不一定
        1，两个资源不冲突，不是同一部锁
    5，两个静态同步方法，同一部手机，先打印1还是2？先1后2
    6，两个静态同步方法，两部手机，先打印1还是2？先1后2
    //5，6锁的都是Phone.class
    7，一个静态同步，一个普通同步，先打印1还是2？先2后1
    8，一个静态同步，一个普通同步，两部手机，先打印1还是2？先2后1
    //静态同步方法和同步方法由于锁的是不同对象，因此不存在竞争条件

     */


    @Test
    public void test1(){
        Phone phone = new Phone();
        new Thread(()->{phone.lock1Method1();},"A").start();
        new Thread(()->{phone.lock1Method2();},"B").start();
    }


    @Test
    public void test2(){
        Phone phone = new Phone();
        new Thread(()->{phone.lock2Method1();},"A").start();
        new Thread(()->{phone.lock2Method2();},"B").start();
    }

    @Test
    public void test3(){
        Phone phone = new Phone();
        new Thread(()->{phone.lock3Method1();},"A").start();
        new Thread(()->{phone.lock3Method2();},"B").start();
    }

    @Test
    public void test4(){
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{phone.lock4Method1();},"A").start();
        new Thread(()->{phone2.lock4Method2();},"B").start();
    }

    @Test
    public void test5(){
        Phone phone = new Phone();
        new Thread(()->{phone.lock5Method1();},"A").start();
        new Thread(()->{phone.lock5Method2();},"B").start();
    }

    @Test
    public void test6(){
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{phone.lock6Method1();},"A").start();
        new Thread(()->{phone2.lock6Method2();},"B").start();
    }

    @Test
    public void test7(){
        Phone phone = new Phone();
        new Thread(()->{phone.lock7Method1();},"A").start();
        new Thread(()->{phone.lock7Method2();},"B").start();
    }

    @Test
    public void test8(){
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{phone.lock8Method1();},"A").start();
        new Thread(()->{phone2.lock8Method2();},"B").start();
    }

}


class Phone{
    //
    public synchronized void lock1Method1() {
        System.out.println("lock1method1");
    }

    public synchronized void lock1Method2() {
        System.out.println("lock1method2");
    }

    //
    public synchronized void lock2Method1() {
        try{
            //新的代替Thread.sleep的API
            TimeUnit.SECONDS.sleep(4);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("lock2method1");
    }

    public synchronized void lock2Method2() {
        System.out.println("lock2method2");
    }

    //
    public synchronized void lock3Method1() {
        try{
            //新的代替Thread.sleep的API
            TimeUnit.SECONDS.sleep(4);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("lock3method1");
    }

    public void lock3Method2() {
        System.out.println("lock3method2");
    }

    //
    public synchronized void lock4Method1() {
        try{
            //新的代替Thread.sleep的API
            TimeUnit.SECONDS.sleep(4);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("lock4method1");
    }

    public synchronized void lock4Method2() {
        System.out.println("lock4method2");
    }

    //
    public static synchronized void lock5Method1() {
        try{
            //新的代替Thread.sleep的API
            TimeUnit.SECONDS.sleep(4);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("lock5method1");
    }

    public static synchronized void lock5Method2() {
        System.out.println("lock5method2");
    }

    //
    public static synchronized void lock6Method1() {
        try{
            //新的代替Thread.sleep的API
            TimeUnit.SECONDS.sleep(4);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("lock6method1");
    }

    public static synchronized void lock6Method2() {
        System.out.println("lock6method2");
    }

    //
    public static synchronized void lock7Method1() {
        try{
            //新的代替Thread.sleep的API
            TimeUnit.SECONDS.sleep(4);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("lock7method1");
    }

    public synchronized void lock7Method2() {
        System.out.println("lock7method2");
    }

    //
    public static synchronized void lock8Method1() {
        try{
            //新的代替Thread.sleep的API
            TimeUnit.SECONDS.sleep(4);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("lock8method1");
    }

    public synchronized void lock8Method2() {
        System.out.println("lock8method2");
    }

}