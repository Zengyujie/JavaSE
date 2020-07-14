package silicon_eclipse;

import java.io.PrintStream;

public class MethodTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 匿名对象，创建对象没有显式赋值给变量
		 * 匿名对象只能调用一次
		 */
		//System.out.println(new Phone().getPrice());
		//PhoneMall p = new PhoneMall();
		//p.printPhone(new Phone());
		/*
		 * 
		 * 方法重载
		 * 在同一个类中
		 * 方法名相同，参数类型或个数不同，顺序不同也算重载
		 * 判断是否重载跟权限修饰符、返回值类型、形参变量名、方法体都没有关系
		 * 
		 * 重载中的自动类型提升：若调用时的参数类型可提升为定义中的类型，且定义里没
		 * 有刚好匹配的方法则会触发自动类型提升。
		 * 
		 * 
		 */
		
		/*
		 * 
		 * 可变参数
		 * 声明：参数类型 ... 变量名
		 * 传入参数可以是任意个，但必须是指定类型
		 * 可变形参只能声明在末尾且只能有一个
		 * 可变个数形参与方法名相同，参数不同的方法构成重载
		 * 可变参数与同类型数组参数不能共存
		 * 当可变参数可以包括其他精确参数时，则调用更为精确的参数，若非得调用则需要传入数组
		 * 多用在SQL中
		 */
		Phone p = new Phone();
		p.show();
		p.show(" ");
		p.show("1","2");
		p.show(new String[] {":"});
		
		/*
		 * 
		 * 变量赋值
		 * 基本数据类型赋值，赋值就是将变量实际存储的数据赋值
		 * 引用类型，赋值时变量保存的数据的地址
		 * 
		 * 形参：方法声明时
		 * 实参：方法调用时
		 * 
		 * 基本数据类型作为形参是是值传递，不影响原来的数据本身
		 * 引用类型作为参数，实参赋值给形参是实参存储数据的地址值，也是值传递
		 * java中只有值传递
		 * 
		 */
		int m = 10;
		int n = m;
		System.out.println(" " + m + n);
		n = 20;
		System.out.println(" " + m + n);
		
		p.setPrice(10.0);
		Phone p1 = p;
		System.out.println(" " +p.getPrice()+p1.getPrice());
		p1.setPrice(20.0);
		System.out.println(" " +p.getPrice()+p1.getPrice());
		System.out.println(p.toString().equals(p1.toString()));
		System.setOut(new PrintStream(System.out) {
			@Override
			public void println(String x) {
				if(x.contentEquals("10")) {
					x = "2000";
				}
				super.println(x);
			}
		});
		System.out.println("10");
	}

}

class Phone{
	
	private double price;
	
	public double getPrice() {
		return this.price;
	}
	
	public int getPrice(int i) {
		return -1;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void show(String ... args) {
		System.out.println("multi string");
		for(String s: args) {
			System.out.println(s);
		}
	}
	//public void show(String[] args) {
	//	duplicated
	//}
	
	public void show(String s) {
		System.out.println("single string");
	}
	
	public void show(int i, String ...strings) {
		
	}
	
}

class PhoneMall{
	public void printPhone(Phone p) {
		System.out.println(p.getPrice());
	}
}

class PhoneFactory{
	public void show(Phone p) {
		System.out.println(p.getPrice());
	}
}