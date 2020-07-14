package day15;

public class AbstractTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 *抽象Abstract
		 * 1，abstract可以用来修饰的结构：类，方法
		 * 2,abstract修饰类
		 * 		1，不能实例化
		 * 		2,有构造器，便于子类对象实例化对象
		 * 		3，开发中，都会提供抽象类的子类，让子类对象实例化
		 * 		4，抽象类的引用可以指向子类对象
		 * 
		 * 
		 * 3，abstract修饰方法
		 * 		1，只有方法声明，没有方法体
		 * 		2，包含抽象方法的类一定是一个抽象类，反之抽象类中可以没有抽象方法
		 * 		3，若子类重写了父类中的所有抽象方法后，此子类方可实例化
		 * 			若没有重写，则子类也是抽象类，需要加上abstract
		 * 		4，普通方法中可以调用abstract方法
		 * 
		 * 4,abstract的注意点：
		 * 		1，不能修饰属性，构造器，代码块
		 * 		2，不能用来修饰private方法，static方法，final的方法和类
		 * 		3，
		 * 
		 * 5，抽象类的匿名子类
		 * 
		 * 6,模板方法设计模式：用到了抽象，将变化的部分写成抽象
		 * 
		 */
		//Person p1 = new Person();
		method(new Student());//非匿名的类的匿名对象
		
		Student worker = new Student();
		method1(worker);//非匿名类的非匿名对象
		
		Person p1 = new Student("zhangsan",10);
		
		Person p2 = new Person("est",12) {
			@Override
			public void breath() {
				// TODO Auto-generated method stub
				System.out.println("brea");
			}
			@Override
			public void eat() {
				// TODO Auto-generated method stub
				System.out.println("eatting");
			}
		};//创建一个匿名子类对象
		
		method1(p2);
		
		//匿名子类的匿名对象
		
		method1(new Person("p1",14) {
			@Override
			public void breath() {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void eat() {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public static void method(Student s) {
		
	}
	
	public static void method1(Person p) {
		p.breath();
	}

}


class Base{
	public void test() {
		
	}
}

abstract class Creature extends Base{
	public abstract void breath();
}

abstract class Person extends Creature{
	
	String name;
	int age;
	
	
	
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



	public abstract void eat();
	
	public void walk() {
		System.out.println("person walk");
	}
}

class Student extends Person{

	public Student(String name, int age) {
		super(name, age);
		// TODO Auto-generated constructor stub
	}
	
	public Student() {
		super("test",12);
	}
	
	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("student eat");
	}
	
	@Override
	public void breath() {
		// TODO Auto-generated method stub
		System.out.println("student breath");
	}
	
}


abstract class Person1{
	
	String name;
	int age;
	
	
	
	public Person1(String name, int age) {
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



	public void eat() {
		System.out.println("person eat");
	}
	
	public void walk() {
		System.out.println("person walk");
	}
}

class Student1 extends Person1{

	public Student1(String name, int age) {
		super(name, age);
		// TODO Auto-generated constructor stub
	}
	
	
}