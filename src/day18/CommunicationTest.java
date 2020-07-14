package day18;

public class CommunicationTest {

    /*
    线程间通信
    1，三个方法：
        1，wait():一旦执行，当前线程进入阻塞状态，并释放同步监视器
        2，notify():一旦执行，就会唤醒被wait的一个线程，如果有多个则会唤醒优先级高的
        3，notifyAll():一旦执行，唤醒所有wait的线程
    2，说明
        1，三个方法必须使用在同步代码块或同步方法中
        2，三个方法的调用者必须是同步代码块或者同步方法中的同步监视器，否则会出现IllegelMonitorStateException
        3，三个方法调用在Object中
        4，sleep和wait的异同：
            1，同：执行都可以让当前线程进入阻塞状态，都会抛异常
            2，异：
                1，声明位置不同，Thread中静态sleep，Object中wait
                2，调用范围不同，sleep可以在任何需要的场景调用，wait必须使用在同步代码块或者同步方法中
                3，是否释放同步监视器，若两个方法都使用在同步代码块或同步方法中，sleep不会释放锁，wait会释放锁



     */


    public static void main(String[] args) {
//        Number number = new Number();
//        Thread t1 = new Thread(number);
//        Thread t2 = new Thread(number);
//        t1.start();
//        t2.start();
        //number.test();

        //my implements
//        Clerk c = new Clerk();
//        Producer p = new Producer();
//        Customer1 cus = new Customer1();
//        p.setClerk(c);
//        cus.setClerk(c);
//
//        new Thread(p).start();
//        new Thread(cus).start();

        ClerkT c = new ClerkT();
        ProducerT p = new ProducerT(c);
        p.setName("producer1");
        ConsumerT co = new ConsumerT(c);
        co.setName("consumer1");
        p.start();
        co.start();

    }
}


class Number implements Runnable{
    private int number = 1;
    private Object obj = new Object();
    @Override
    public void run() {
        while(true){
            synchronized (this){
            //synchronized (obj){//同步代码块的同步监视器必须和调用notify，wait的对象一致，否则会报错

                this.notify();//唤醒另一个线程
                //notifyAll();//唤醒其他所有线程

                try {
                    Thread.sleep(100);//虽然阻塞，不会释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(number < 10){
                    System.out.println(Thread.currentThread().getName() + " : " + number);
                    number++;

                    try {
                        this.wait();//式线程进入阻塞状态
                        //执行wait()会释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else{
                    break;
                }

            }
        }
    }

    public void test(){
        try {
            this.wait();//必须声明在同步代码块中，否则会抛异常
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable{

    private Clerk clerk;

    public void setClerk(Clerk clerk){
        this.clerk = clerk;
    }


    public void produce(int num){
        if(this.clerk == null){
            System.out.println("clerk is null");
            return;
        }
        synchronized(clerk) {
            System.out.println("counter is: " + clerk.getCounter() + " producer produce " + num + " things");
            clerk.addThing(num);
        }
    }

    @Override
    public void run(){
        while(true){
            produce((int)(Math.random()*5 + 0));
            if(clerk.getCounter() > 30){
                break;
            }
        }
    }

}

class Clerk{

    private int thing;

    private int counter;

    public Clerk(){
        thing = 0;
        counter = 0;
    }

    public void sell(int number){
        counter++;
        if(number < 0){
            System.out.println("illegel number");
            return;
        }
        if(thing > number){
            System.out.println("sell " + number + " things rest is : " + (thing - number));
            thing -= number;
        }else{
            System.out.println("not enough, sell " + number + " things rest is : " + thing);
        }
    }

    public void addThing(int number){
        counter++;
        if(thing >= 20){
            System.out.println("enough things, don`t add more");
            return;
        }

        if(thing + number > 20){
            thing = 20;
            System.out.println("things full");
            return;
        }

        thing += number;
        System.out.println("adding success, rest is: " + thing);
    }

    public int getThing(){
        return thing;
    }

    public int getCounter(){
        return this.counter;
    }

}

class Customer1 implements Runnable{

    private Clerk clerk;

    public void setClerk(Clerk clerk){
        this.clerk = clerk;
    }


    public void buy(int num){
        if(this.clerk == null){
            System.out.println("clerk is null");
            return;
        }
        synchronized(clerk) {
            System.out.println("counter is: " + clerk.getCounter() + " customer buy " + num + " things");
            clerk.sell(num);
        }
    }

    @Override
    public void run(){
        while(true){
            buy((int)(Math.random()*5 + 0));
            if(clerk.getCounter() > 30){
                break;
            }
        }
    }

}


class ConsumerT extends Thread{

    private ClerkT clerk;

    public ConsumerT(ClerkT clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + " :start consuming");
        while(true){
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }

}

class ProducerT extends Thread{

    private ClerkT clerk;

    public ProducerT(ClerkT clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + " :start produce");
        while(true){
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();

        }
    }
}

class ClerkT{

    private int productCount = 0;

    public synchronized void produceProduct() {
        if(productCount < 20){
            productCount++;
            notify();
            System.out.println(Thread.currentThread().getName() + ":start produce " + productCount);
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void consumeProduct() {
        if(productCount > 0){
            productCount++;
            System.out.println(Thread.currentThread().getName() + ":start consume " + productCount);
            productCount--;
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}