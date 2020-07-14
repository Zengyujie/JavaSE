package day22;

public class SubOrder1<T> extends Order<T> {

    public SubOrder1() {
    }

    public SubOrder1(String orderName, int orderId, T orderT) {
        super(orderName, orderId, orderT);
    }
}


