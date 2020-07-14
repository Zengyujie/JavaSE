package day17;

public class WindowTest {


    public static void main(String[] args) {
        Window window = new Window();
        Thread t1 = new Thread(window);
        Thread t2 = new Thread(window);
        Thread t3 = new Thread(window);
        t1.start();
        t2.start();
        t3.start();

    }


}

class Window implements Runnable{

    private int ticket = 100;

    @Override
    public void run() {
        while(true){
            if(this.ticket > 0){
                System.out.println(Thread.currentThread().getName()+":"+this.ticket);

                this.ticket--;

            }else{
                break;
            }
        }
    }
}
