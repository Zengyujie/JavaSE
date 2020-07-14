package day22;

import java.util.Arrays;
import java.util.List;

public class Order<T> {
    //声明后类的内容接口可以使用类的泛型

    String orderName;
    int orderId;
    T orderT;

    public Order() {
        //T[] arr = new T[10];//编译不过
        T[] arr = (T[]) new Object[10];
    }

    public Order(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }


    public <E> List<E> copyList(E[] arr){
        return Arrays.asList(arr);
    }




}
