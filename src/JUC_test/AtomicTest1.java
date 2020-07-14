package JUC_test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest1 {
    /*
    原子性操作：
    1，i++的操作原子性问题：
        int i = 10; i++;
        原理是：int temp = i; i = i + 1; i = temp;
    2，java.util.concurrent.atomic包提供了原子性的操作
        1，封装的值用volatile修饰，保证内存可见性
        2，CAS(Compare-and-swap)算法保证数据原子性
            1，是硬件对于并发操作共享数据的支持
            2，包含了三个操作数：
                内存值：V
                预估值：A
                更新值：B
                当且仅当V == A时，V=B，否则不做任何操作
                可以理解为V先读一次，然后AB是一个原子操作，相当于再读一次
            3，CAS算法一旦失败了就会理解重复请求，不会阻塞，因此效率比阻塞高，但是自己编写的部分增多

    3，
     */


    public static void main(String[] args) {
        AtomicInt ai = new AtomicInt();
        for(int i = 0; i < 10; i++){
            new Thread(ai).start();
        }
    }
}


class AtomicInt implements Runnable{

    private int serialNumber = 0;

    private AtomicInteger num = new AtomicInteger(0);

    @Override
    public void run() {
        try{
            Thread.sleep(200);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber());
    }

    public int getSerialNumber() {
        return num.getAndIncrement();
        //return serialNumber++;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}