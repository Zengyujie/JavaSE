package JUC_test;

public class VolatileTest1 {
    /*
    1，volatile关键字：
        多个线程操作共享数据时，保证内存中数据可见的
        原理是jvm底层优化会对指令重排序
        是一个轻量级的同步策略
    2，与synchronized不同：
        1，更为轻量级
        2，不具有互斥性
        3，不能保证变量的原子性



     */

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        while(true){
            synchronized (td) {
                //用同步锁之后内存会更新，因此相当于可见
                //但是会对其他线程阻塞，效率低
                if (td.isFlag()) {
                    System.out.println("end");
                    break;
                }
            }
        }
    }

}

class ThreadDemo implements Runnable{

    //private volatile boolean flag = false;
    private boolean flag = false;
    /*
    内存可见性问题
    主存，main线程，执行线程中的变量一般是互相不可见的
    即多个线程操作共享数据时彼此不可见
     */


    @Override
    public void run() {
        try{
            Thread.sleep(200);
        }catch(Exception e){
            System.out.println("something error");
        }
        flag = true;
        System.out.println("flag=" + flag);
    }

    public boolean isFlag(){
        return flag;
    }
}
