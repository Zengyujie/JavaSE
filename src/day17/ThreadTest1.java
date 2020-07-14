package day17;

//何鸿燊：勤奋，其二不舍，有始有终，有好队友，待人真诚，做事雷厉风行
//      每天复盘，用一个本记下
public class ThreadTest1 {

    /*
    1，重点：线程创建有四种方式，线程同步有三种方式
    2，一个java程序至少有三个线程：main，gc，异常处理
    3，并行与并发：并行：多个CUP同时执行多个任务，并发：一个CUP同时执行多个任务
    4，何时需要多线程：同时执行多个任务，实现一些需要等待的操作，如用户输入，文件读写，网络操作，搜索等
            需要后台的任务
    5，多线程的创建：
        1，继承Thread类，重写run方法，创建子类对象，start方法
            调用start之后会启动当前线程并调用run方法
            1.1不能通过直接调用run方法的方式启动线程，必须使用start方法
            1.2不可以让已经start过的线程再次start，否则会IllegalThreadStateException
                    需要重新创建一个线程对象
        2，实现Runnable接口，实现run方法，创建该类的对象，然后传进Thread构造函数里，调用start方法
            2.1启动过程：对象作为参数构造Thread对象之后，调用start()首先启动线程，然后调用当前线程的run方法
                当前线程的run()调用Runnable中的run()
            2.2将同一个对象作为参数传入多个Thread对象中，执行的时候会同时执行run()方法，如果涉及到
                成员变量的修改，就可能导致脏读问题，即线程不安全
        3，比较以上两种方式：
            3.1，开发中优先选择实现Runnable接口的方式：
                实现方式没有类的单继承性的局限性
                实现的方式更适合来处理多个线程有共享数据的情况
            3.2，两种方法都需要重写run()，将线程需要执行阿逻辑声明在run()中
    6，线程的调度：时间片，抢占式(优先级高的抢占CPU)

     */

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();//start方法会新开一个线程，然后在新的线程中执行run方法
        //t1.run();//只调用run则仍然在主线程中
//        如下方法仍然在main线程中调用
        for(int j = 0; j < 100; j++) {
            if (j % 2 == 1) {
                //System.out.println(j);
                System.out.println(Thread.currentThread().getName() + ":" + j);
            }
        }
        //t1.start();一个线程只能start一次
        System.out.println("**************8");
        new MyThread().start();
        new MyThread1().start();

        //创建Thread的匿名子类
        new Thread(){
            @Override
            public void run() {
                System.out.println("anonymity");
            }
        };

        MyThread4 m4 = new MyThread4();
        Thread t4 = new Thread(m4);
        t4.start();
        Thread t41 = new Thread(m4);
        t41.start();

    }


}


class MyThread extends Thread{

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            if(i % 2 == 0){
                //System.out.println(i);
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}


class MyThread1 extends Thread{

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            if(i % 2 == 1){
                //System.out.println(i);
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}


class MyThread4 implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            if(i % 2 == 1){
                //System.out.println(i);
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}