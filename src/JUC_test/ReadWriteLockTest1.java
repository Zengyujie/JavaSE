package JUC_test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest1 {
    /*
    读写锁 ReadWriteLock接口
    1，多个线程对同一个资源类访问，读没有问题，为满足并发量，读写可以同时进行
        读-读能共存，读-写不能共存，写-写不能共存
     */

    public static void main(String[] args) {

        MyCache mc = new MyCache();

        for(int i = 0; i < 5; i++){
            final int in = i;
            new Thread(()->{
                mc.put("write" + String.valueOf(in), in);
            },String.valueOf(in)).start();
        }

        for(int i = 0; i < 5; i++){
            final int in = i;
            new Thread(()->{
                mc.get("write" + String.valueOf(in));
            },String.valueOf(in)).start();
        }

    }
}

class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    public void put(String key, Object value){

        rwl.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "start write data");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "finish write data");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            rwl.writeLock().unlock();
        }


    }

    public void get(String key){
        rwl.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "start read data");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "finish read data");

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            rwl.readLock().unlock();
        }


    }
}