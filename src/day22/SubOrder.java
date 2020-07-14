package day22;

public class SubOrder extends Order<Integer>{

    public SubOrder() {
    }

    public SubOrder(String orderName, int orderId, Integer orderT) {
        super(orderName, orderId, orderT);
    }
}
