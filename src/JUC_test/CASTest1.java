package JUC_test;

public class CASTest1 {

    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();
        for(int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectedValue = cas.getValue();
                    boolean b = cas.compareAndSet(expectedValue, 101);
                    System.out.println(b);
                }
            }).start();
        }
    }

}


class CompareAndSwap{
    private volatile int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue){
        int oldValue = value;
        if(oldValue == expectedValue){
            this.value = newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int newValue){
        return expectedValue == compareAndSwap(expectedValue,newValue);
    }

    public void setValue(int value) {
        this.value = value;
    }
}