package day19;

public class Goods implements Comparable{

    private String name;
    private double price;

    public Goods(){

    }

    public Goods(String name) {
        this.name = name;
    }

    public Goods(double price) {
        this.price = price;
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }


    @Override
    public int compareTo(Object o) {
        if(o instanceof Goods){
            Goods goods = (Goods)o;
            if(this.price > goods.price){
                return 1;
            }else if(this.price < goods.price){
                return -1;
            }else{
                return this.name.compareTo(goods.name);
            }
        }
        throw new RuntimeException("error input");
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
