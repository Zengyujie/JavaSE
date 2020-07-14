package day14;

public class StaticTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 
		 * 栈：局部变量
		 * 堆：new出来的结构，对象，数组
		  *  方法区：类的加载信息，静态域，常量池
		 * 
		 * 
		 * 
		 * 
		 * static关键字
		 * 1，可以用来修饰：属性，方法，代码块，内部类
		 * 2，不能修饰构造器
		 * 3，使用static修饰属性
		 * 		1，属性按照是否使用static修饰分类静态属性和非静态属性，每个对象都独立地拥有一套类中的
		 * 		非静态属性，当修改其中一个的非静态属性值，不会导致其他对象中属性的修改。
		 * 		2，对于static变量，多个对象共享一个静态变量，当用过某一个对象修改静态变量时，其他对象的也会修改
		 * 		3，static变量也能称为类变量
		 * 		4，静态变量随着类的加载而加载
		 * 		5，static变量的加载早于对象的创建
		 * 		6，static变量可以通过Class.变量来调用
		 * 		7，类只加载一次，static变量只存在一份,存储在方法区的静态域中
		 * 4，使用static修饰方法
		 * 		1，随着类的加载而加载，可以通过类.静态方法的调用而调用
		 * 		2,非静态方法中可以调用静态和非静态属性和方法
		 * 5，在静态的结构中，不能使用this，super关键字，不能使用非静态的属性和方法，非静态方法可以使用this调静态，但是会有warning
		 * 6,关于静态属性和静态方法的使用都从声明周期的角度来理解
		 * 7，在开发中如何确定一个属性是否声明为static
		 * 		1，属性可以被多个对象共享的，不会随着对象不同而不同
		 * 		2，操作静态属性的方法通常设置为static
		 * 		3，工具类中的方法习惯上声明为静态，因为没有必要造对象，例如Array，Math，Collection
		 * 
		 * 
		 */
		Person c1 = new Person("yao",40);
		System.out.println(c1);
		Person c2 = new Person("ma",30);
		System.out.println(c2);
		c1.nation = "China";
		System.out.println(c1);
		System.out.println(c2);
		Person.nation = "USA";
		System.out.println(c1);
		System.out.println(c2);
		c1.show();
		Person.show();
		
	}

}

class Person{
	public static String nation;
	private String name;
	private int age;
	public Person() {
		super();
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "nation=" +nation + "]";
	}
	
	public static void show() {
		System.out.println("static person");
	}
	
	
}