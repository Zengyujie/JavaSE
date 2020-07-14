package day18;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest1 {

    /*
    1，synchronized和lock的异同：
        1，相同：两者都可以解决线程安全问题
        2，不同：syn执行完同步代码块之后自动释放
                lock需要手动启动同步lock()，手动结束同步unlock()
                lock机制是jdk5.0新增，所以建议先使用lock，然后是代码块，最后是同步方法
        3，解决线程安全的方法有几种：三种，代码块，方法，锁

     */

    public static void main(String[] args) {

        Window5 w5 = new Window5();
        Thread t1 = new Thread(w5);
        Thread t2 = new Thread(w5);
        Thread t3 = new Thread(w5);

        t1.start();
        t2.start();
        t3.start();

        //System.out.println(w5.test(2));
        System.out.println("**********");

        Account account = new Account();
        Customer c1 = new Customer(account);
        Customer c2 = new Customer(account);

        c1.start();
        c2.start();

    }
}

class Window5 implements Runnable{

    private int ticket = 100;

    //1，实例化lock
    private ReentrantLock lock = new ReentrantLock(true);//true表示公平锁，先进先出，false表示普通的


    @Override
    public void run() {
        while(true){
            try{
                lock.lock();//锁定方法
                if(ticket > 0){
                    System.out.println(Thread.currentThread().getName()+":"+ticket);
                    ticket--;
                }else{
                    break;
                }
            }finally{
                lock.unlock();//解锁方法
            }
        }
    }

    public Object test(int arg){
        throw new NullPointerException();//抛出运行时异常之后之后的代码不再执行了
        //因此不用写返回值，如果是编译时异常就需要手动处理了，不然会报错
    }

}


class Customer extends Thread{

    private Account account;

    public Customer(Account account){
        this.account = account;
    }

    public void withdraw(int money){
        synchronized(account){
            if(account.getBalance() < money){
                System.out.println("not sufficient funds");
            }else{
                account.setBalance(account.getBalance()-money);
            }
        }
    }

    @Override
    public void run() {
        int temp = 0;
        while(account.getBalance() > 0){
            temp = (int)(0 + Math.random()*10);
            temp = temp > account.getBalance()?account.getBalance():temp;
            withdraw(temp);
            System.out.println(Thread.currentThread().getName() + " get :" + temp + " rest: " + account.getBalance() + " order: " + account.getCounter());

        }
    }
}

class Account{
    private int balance;
    private int counter;

    public Account(){
        balance = 100;
        counter = 0;
    }

    public void setBalance(int money){
        balance = money;
        counter++;
    }

    public int getBalance(){
        return balance;
    }

    public int getCounter(){
        return counter;
    }
}