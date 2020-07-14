package day18;

public class SingleToneTest {

}


class Bank{

    private static Bank bank = null;

    private Bank(){

    }

    public static Bank getInstance(){
        if(bank == null){
            //如果阻塞，在多个线程同时调用时可能new多个对象
            bank = new Bank();
        }
        return bank;
    }

    public static synchronized Bank getSecurityInstance(){
        //synchronized(Bank.class)//同效果
        if(bank == null){
            bank = new Bank();
        }
        return bank;
    }

    //上方法全部同步效率低，因为后续的每个对象都进入到了syn模块
    //将同步之前加一个判空，提升效率
    public static Bank getFastInstance(){
        if(bank == null){
            synchronized (Bank.class){
                if(bank == null){
                    bank = new Bank();
                }
            }

        }
        return bank;
    }


}