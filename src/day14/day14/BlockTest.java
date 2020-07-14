package day14;

public class BlockTest{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 代码块：
		 * 1，代码块的作用式初始化类，对象
		 * 2，只能用static来修饰
		 * 3,分类：静态代码块和非静态代码块
		 * 4，静态代码块
		 * 		1,内部可以有输出语句
		 * 		2,随着类的加载而执行,而且只执行一次
		 * 		3，初始化类的信息
		 * 		4，静态代码库块的执行要优先于非静态代码块
		 * 		5，只能调用静态结构
		 * 
		 * 5，非静态代码块
		 * 		1，内部可以有输出语句
		 * 		2,随着对象的创建而执行，没创建一个对象就执行一次非静态代码块
		 * 		3，作用：可以在创建对象时对对象的属性，等初始化
		 * 		4，可以调用静态和非静态结构
		 * 
		 * 6，属性赋值的位置：默认初始化，显式初始化，构造器初始化，有了对象后用对象.属性或方法赋值
		 * 		代码块中赋值
		 * 7，可以定义多个代码块，按照声明的先后顺序执行
		 * 
		 * 8，执行顺序：由父及子，静态代码块先行，按顺序执行，然后由父及子，父亲先按顺序执行代码块，
		 * 		然后执行父类构造方法，然后子类按顺序执行代码块，然后执行子类构造方法
		 * 9，属性赋值顺序：默认初始化--显式初始化/代码块中赋值（谁写在前面，谁就先赋值）--构造器初始化--有了对象后调用
		 * 
		 * 
		 *final
		 * 1,final 可以修饰类，方法，变量
		 * 2,final类不能被继承，例如String，System，StringBuffer
		 * 3，final修饰方法，方法不可以被重写
		 * 4，final用来修饰变量，此时的变量就是常量
		 * 5，final修饰属性，可以考虑赋值的位置有：显式初始化，或者声明之后在代码块中赋值，
		 * 		或者声明之后再构造器中赋值，如果声明和赋值分开，则每个构造器中都要为其赋值
		 * 		总之在构造阶段或类加载时必须给final变量赋值
		 * 6,final修饰形参，一旦调用赋值之后，该形参不能被改变
		 * 7，final修饰局部变量，变量不能被调用
		 * 8，static final修饰属性：全局常量
		 * 9，static final修饰方法：可通过类来调用，不能重写
		 * 
		 */
		
		D d = new D();
		
		String desc = People.desc;
		System.out.println("-------");
		People p1 = new People();
		People p2 = new People();
		People.info();
	}


}


final class FinalA{
	
}



class People{
	
	
	//代码块
	{
		age=10;//顺序先后
		System.out.println("normal block");
	}
	
	String name;
	int age = 1;//顺序先后
	static String desc="people";
	
	final int ID;
	
	
	
	//静态代码块
	static {
		System.out.println("static block");
		desc = "new person";
	}
	
	
	public People() {
		ID = 1;
		System.out.println(age);
	}

	public People(String name, int age) {
		super();
		ID = 1;
		this.name = name;
		this.age = age;
	}

	public People(int n) {
		ID = n;
		System.out.println(age);
	}


	public void eat() {
		final int NUM = 10;
		System.out.println("eat");
	}
	
	public void eat(final int NUM) {
		final int NUM1 = 10;
		System.out.println("eat");
	}
	
	public static void info() {
		System.out.println("static info");
	}
	
}


class A{
	
	
	static{
		if(true) {
			System.out.println("output");
		}
		System.out.println("A static block 1");
		
	}
	
	
	{
		System.out.println("A normal block 1");
		
	}
	
	public A() {
		System.out.println("A constructor");
	}
	
	{
		System.out.println("A normal block 2");
		
	}
	
	static{
		System.out.println("A static block 2");
		
	}
	
	
	
}

class B extends A{
	
	
	static{
		System.out.println("B static block 1");
		
	}
	
	
	{
		System.out.println("B normal block 1");
		
	}
	
	public B() {
		System.out.println("B constructor");
	}
	
	{
		System.out.println("B normal block 2");
		
	}
	
	static{
		System.out.println("B static block 2");
		
	}
	
	
}


class D extends B{
	
	
	static{
		System.out.println("D static block 1");
		
	}
	
	
	{
		System.out.println("D normal block 1");
		
	}
	
	public D() {
		System.out.println("D constructor");
	}
	
	{
		System.out.println("D normal block 2");
		
	}
	
	static{
		System.out.println("D static block 2");
		
	}
	
	
}