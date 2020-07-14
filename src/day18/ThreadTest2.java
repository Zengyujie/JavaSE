package day18;

public class ThreadTest2 {

    /*
    1，线程的生命周期：
        1.1，Thread内部类State(枚举)定义了：
            新建(NEW):刚造出来，没有start()
            就绪(WAITING,TIMED_WAITING，是否有参数的区别):进入等待CPU时间片，已经具备运行条件，只是没有被分配CPU资源
            运行(RUNNABLE):调用start()之后，执行run()
            阻塞(BLOCKED):让出CPU临时中止执行
            死亡(TERMINATED):完成工作后或者强制退出
        1.2，一般新建之后调用start()进入就绪，就绪获取CPU执行权进入运行，
            运行时失去CPU执行权(yield())进入就绪，执行完run()或者调用stop()或者异常没处理进入死亡，
            运行时进入阻塞：调用sleep(long million),join(),等待同步锁,wait(),一个过时的方法suspend()可能导致死锁
            阻塞进入就绪：sleep,join结束，获取同步锁，notify(),notifyAll(),一个过时的方法resume()与suspend对应
    2，线程的安全问题：
        2.1，出现的原因：当某个线程操作共享数据的过程中，尚未完成时，其他线程参与进来，操作数据
        2.2，如何解决：当一个线程在操作共享数据的时候，其他线程不能参与，直到该线程操作完成，即使出现了阻塞也不能改变
        2.3，同步机制解决线程安全问题：
            1，同步代码块：
                1.1synchronized(同步监视器){需要同步的代码}//操作共享数据的代码就是需要同步的代码
                1.2共享数据：多个线程共同操作的变量
                1.3同步监视器，俗称锁，任何一个类的对象都可以充当锁
                    要求多个线程必须共用同一把锁
                1.4使用同步代码块解决了线程安全问题，但是操作同步代码时只能有一个线程，其他线程等待
                    相当于是一个单线程，效率低
                1.5在实现runnable接口的方式中可以考虑使用this来充当锁，也不一定，继承Thread方法中可以考虑使用类.class来充当锁
                    总的来说，要保证锁的唯一性
                1.6synchornized(){}中包括的不能多也不能少

            2，同步方法：
                1，如果操作共享代码完整声明在一个方法中，可以将方法声明为同步方法
                2，同步方法仍然涉及到同步监视器，只是不需要显式声明
                3，非静态方法同步监视器是this，静态方法同步监视器是当前类.class





     */

    public static void main(String[] args) {
        //代码块
//        Window window = new Window();
//        Thread t1 = new Thread(window);
//        Thread t2 = new Thread(window);
//        Thread t3 = new Thread(window);
//        t1.start();
//        t2.start();
//        t3.start();

        //解决继承Thread安全问题
//
//        Thread t1 = new Window1();
//        Thread t2 = new Window1();
//        Thread t3 = new Window1();
//        t1.start();
//        t2.start();
//        t3.start();

        //同步方法
//        Window2 window = new Window2();
//        Thread t1 = new Thread(window);
//        Thread t2 = new Thread(window);
//        Thread t3 = new Thread(window);
//        t1.start();
//        t2.start();
//        t3.start();


        Thread t1 = new Window3();
        Thread t2 = new Window3();
        Thread t3 = new Window3();
        t1.start();
        t2.start();
        t3.start();
    }

}



class Window implements Runnable{

    private int ticket = 100;
    Object obj = new Object();

    @Override
    public void run() {
        while(true){
            //synchronized (this)//对象是唯一的即可
            synchronized(obj) {
                if (this.ticket > 0) {

                try {
                    Thread.sleep(100);
                    //Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                    System.out.println(Thread.currentThread().getName() + ":" + this.ticket);

                    this.ticket--;

                } else {
                    break;
                }
            }
        }
    }
}

class Window1 extends Thread{

    private static int ticket = 100;

    private static Object obj = new Object();

    @Override
    public void run() {
        while(true){
            //synchronized (Window1.class){//也可以，类只加载一次，有唯一性
            synchronized(obj) {
                if (this.ticket > 0) {

                    try {
                        Thread.sleep(100);
                        //Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":" + this.ticket);

                    this.ticket--;

                } else {
                    break;
                }
            }
        }
    }
}

class Window2 implements Runnable{

    private int ticket = 100;
    Object obj = new Object();

    @Override
    public void run() {
        while(true){
            //synchronized (this)//对象是唯一的即可
            show();
            if(this.ticket <= 0)
                break;

        }
    }

    public synchronized void show(){
        //synchronized (this)//相当于用this来当锁
        if (this.ticket > 0) {

            try {
                Thread.sleep(100);
                //Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ":" + this.ticket);

            this.ticket--;

        }
    }
}

class Window3 extends Thread{

    private static int ticket = 100;

    private static Object obj = new Object();

    @Override
    public void run() {
        while(true) {
            show();
            if (this.ticket <= 0)
                break;
        }
    }

    public static synchronized void show(){//锁是Window3.class
    //public synchronized void show(){//会出错，因为锁是this
                if (ticket > 0) {

                    try {
                        Thread.sleep(100);
                        //Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":" + ticket);

                    ticket--;

                }



    }
}