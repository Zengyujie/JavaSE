package JUC_test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test1 {
    /*
    JUC（java.util.concurrent）
    1.1，进程/线程
    1.2，并发/并行
        1，并发：同一个时间，多个线程去抢同一个资源
        2，并行：同时进行
    juc有三个包
        java.util.concurrent
        java.util.concurrent.atomic
        java.util.concurrent.locks
    1.3 java线程的状态：NEW，BLOCK，RUNNABLE，WAITTING，TIMED_WAITTING,TERMINATED


     */

    /*
    题目：三个售票员 卖出30张票
    笔记：如何编写企业级多线程代码：
    固定模板：在高内聚低耦合的前提下：线程     操作     资源类
        1，一言不合先创建一个资源类




     */

    public static void main(String[] args) {//主线程，一切程序的入口
        Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= 40; i++){
                    ticket.sale();
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= 40; i++){
                    ticket.sale();
                }
            }
        }, "B").start();
        new Thread(()->{for(int i = 0; i <= 400; i++)ticket.sale();}, "C").start();

        //lambda表达式

    }

}

class Ticket{//资源类：实例变量+实例法昂发
    //要对资源执行什么操作，资源自己就要带上(eg:空调所有功能都实现了，只需要通过遥控器调用)
    //就是所谓的高内聚
    private int number = 300;
    Lock lock = new ReentrantLock();

    public Ticket() {
    }

    public Ticket(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void sale(){//枷锁的固定格式//模板代码可以加快捷键
        lock.lock();//加锁在try外
        try{
            if(number > 0){
                System.out.println(Thread.currentThread().getName() + "sale :" + (number--) + "rest:" + number);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();//finally中一定要解锁
        }
    }
}


