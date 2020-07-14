package day17;

public class ThreadMethodTest {
    /*
    1,start():启动当前线程，调用run()
    2,run():需要重写的方法，将创建线程要执行的内容写在里面
    3,currentThread():静态方法，返回当前代码执行的线程
    4,getName():获取当前线程的名字
    5,setName():设置当前线程的名字
    6,yield():释放当前CPU的执行权（有可能在下一刻该线程又拿到了执行权）
    7,join():在线程A中调用线程B的join方法，则线程A进入到阻塞状态，直到线程B
            执行完毕之后再执行A
    8,stop():Deprecated的方法，强制结束一个线程，已经过时
    9,sleep(long millitime)：让当前线程阻塞一定的时间
    10,isAlive():判断当前线程是否存活
    11,线程的优先级：
        MAX_PRIORITY:10 MIN_PRIORITY:1 NORM_PRIOIRTY:5
        getPrioirty() 和 setPriority(int p)
        优先级越高表示从概率上来将更容易被CPU执行到，并不是一定会先执行


     */
    public static void main(String[] args) {
        //给主线程命名
        Thread.currentThread().setName("my main");
        System.out.println(Thread.currentThread().getName());

        MyThread2 t2 = new MyThread2("mytest");
        t2.setName("test");
        t2.start();

        for(int j = 0; j < 100; j++) {
            if (j %2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + j);
            }
            System.out.println(t2.isAlive());
            System.out.println(Thread.currentThread().getPriority());
            t2.setPriority(Thread.MAX_PRIORITY);
            if( j == 90){
                try{
                    t2.join();
                }catch(InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println(t2.isAlive());

    }
}

class MyThread2 extends Thread{

    MyThread2(String name){
        super(name);
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            if(i % 2 == 0){
                //System.out.println(i);
                System.out.println(Thread.currentThread().getName()+":"+i);
                System.out.println(Thread.currentThread().getPriority());
                try{
                    sleep(100);
                }catch(InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }

            if(i  %5 == 0){
                //yield();
            }
        }
    }
}