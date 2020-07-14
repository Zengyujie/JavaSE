package day12;

public class InheritTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 继承
		 * 1,好处，减少了代码冗余，提高复用性
		 * 2，便于功能扩展
		 * 3，为之后类的多态性使用提供了前提
		 * 
		 * 4，继承的格式 class A extends B{}
		 * 		A：子类，subclass
		 * 		B：父类/基类 superclass
		 * 5，体现：继承之后可以调用父类中声明的结构：属性和方法
		 * 6，父类中的private的属性和方法，子类继承后也获取到了，只是由于封装型
		 * 		性的影响，子类不能直接调用，可以通过父类提供的get和set或其他方法来调用
		 * 7，子类继承之后可以声明自己的属性和方法，实现功能扩展
		 * 8，一个类可以被多个子类继承，一个类只能有一个父类
		 * 9，直接父类，间接父类
		 * 10，如果没有显式声明父类，则此类继承java.lang.Object
		 * 
		 * Override/Overwrite重写
		 * 1，子类继承父类之后可以对父类中同名的方法重写
		 * 2，区分方法的重写与重载
		 * 3，重写的规定：
		 *		约定俗称：子类中叫重写方法，父类中叫被重写方法
		 *		子类重写的方法名和形参列表与父类相同
		 *		子类的权限修饰符不小于父类的，例如父类是protected，子类只能是protected或者public
		 *		特殊情况：子类不能重写父类中的private方法，如果子类中定义了同名，同参数方法，则视为是两个不相关的方法
		 *		返回值类型：父类是void，子类只能是void
		 *				父类是A类型，子类可以是A类或者A的子类
		 *				父类是基本数据类型，子类必须是相同的基本数据类型
		 *		子类重写方法抛出的异常类型不大于父类重写方法抛出的异常类型，类似返回值类型
		 *		子类和父类中同名同参数的方法要么都是非static的，要么都是static的，非static的方法就不叫重写了
		 *		static方法不能被重写，同名static，就是另一个函数了，不算重写了
		 *4，super关键字：
		 *		1，在子类的方法或构造器中使用super.field或者super.method来显式调用父类的属性和方法，不重复则可省略super
		 *		2，当父类子类中定义里同名属性和方法时，子类想调用父类的方法和属性必须显式调用super
		 *		3，当子类重写了父类中的方法以后，我们想在子类中调用父类的方法，可使用super调用父类被重写的方法
		 *		4，super调用构造器：
		 *			可以在子类的构造器中显式使用super(参数)构造方式调用父类中的构造器
		 *			super()的使用必须声明在构造器的首行
		 *			在类的构造器中针对this(),super()只能二选一,不能同时出现
		 *			在子类构造器中没有显式调用父类构造器或者this(参数)则会显式调用super()
		 *			在类的多个构造器中，至少有一个类的构造器中使用了super(参数0~n)，调用父类中的构造器
		 *5，创建子类对象的全过程：
		 *		1，从结果上来看，子类继承父类就获取了父类中声明的属性或方法，创建子类的对象，堆空间中有父类中声明的属性
		 *		2，从过程上看，子类构造器创建子类对象时，一定会直接或间接地调用父类的构造器，父类的父类等，直到Object的，父类结构都会被加载到内存中，堆
		 *      3，虽然创建子类对象时调用了父类的构造器，但是自始至终只创建了一个对象
		 *  
		 *6，多态性
		 *		1，对象的多态性，父类的引用指向子类的对象
		 *		2,当调用字符类同名参数的方法时，实际执行的是重写父类的方法--虚拟方法调用
		 *		3,父类引用不能调用子类独有的方法
		 *		4，多态的使用，有了多态性后，在编译期间只能调用父类声明的方法，在执行期实际执行子类重写的方法
		 *		5，编译看左边，运行看右边
		 *		6，多态性的使用前提：有类的继承关系，有方法的重写
		 *		7,对象的多态性只适用于方法，不适用于属性
		 *		8，虚拟方法调用，父类根据赋值的子类对象动态调用属于子类的方法，该方法在编译期是无法确定的，是运行时行为
		 *		9，重载是早绑定，或者静态绑定，多态是晚绑定或者动态绑定
		 *
		 */
		
		Student s = new Student();
		s.eat();
		s.study();
		System.out.println(s.getCode());
		Person p = s;
		p.eat();
		Person p2 = (Person)s;
		p2.eat();
		//debugTest();
		
		Person p3 = new Person();
		p3.eat();
		Man man = new Man();
		man.eat();
		man.work();
		
		
		Person p4 = new Man();
		Person p5 = new Student();
		p4.eat();
		//p4.work(); //error
		func(new Dog());
		func(new Cat());
	}
	
	public static void func(Animal a) {
		a.eat();
		a.shout();
	}
	
	
	/*
	 * 如何调试：
	 * 1，用System.out.println()
	 * 2，Eclipse Debug调试工具
	 * 		1。1设置断点：在行的位置双击
	 * 
	 * 
	 */
	public static void debugTest(){
		int i = 10;
		int j = 20;
		System.out.println("" + i + " " + j);
		int max = getMax(i, j);
		System.out.println(max);
	}
	
	public static int getMax(int i, int j) {
		int max = 0;
		if(i < j)
		{ 
			max = j;
		}else {
			max = i;
		}
		return max;
	}

}

class Person{
	
	protected String name;
	protected int age;
	private int code;
	protected String test="person";
	
	
	public Person() {
		
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
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public void eat() {
		System.out.println("person eat");
	}
	
}


class Student extends Person{
	protected String major;
	
	protected String test="student";//子类定义
	
	public Student() {
		super();
	}
	

	public Student(String major) {
		super();
		this.major = major;
	}



	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("student eat");
		super.eat();
		System.out.println(super.test + this.test);
	}
	
	public void study() {
		System.out.println("student study");
	}
	
}

class Man extends Person{
	
	boolean isHandsome;
	
	public Man() {
		
	}
	
	public Man(boolean b) {
		this.isHandsome = b;
	}

	public boolean isHandsome() {
		return isHandsome;
	}

	public void setHandsome(boolean isHandsome) {
		this.isHandsome = isHandsome;
	}
	
	@Override
	public void eat() {
		System.out.println("man eat");
	}
	
	public void work() {
		System.out.println("man is working");
	}
}


class A{
	public A(int a) {
		System.out.println(a);
	}
	
}

class B extends A{
	public B() {
		super(1);//不用显式调用super(int i)就出错，因为没有空参的构造器
	}
}

class Animal{
	
	protected int id = 1;
	
	public void eat() {
		System.out.println("eat");
		System.out.println(id);
	}
	
	public void shout() {
		System.out.println("shout");
	}
	
}

class Dog extends Animal{
	
	protected int id = 2;
	
	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("dog eat");
		System.out.println(id);
	}
	
	@Override
	public void shout() {
		// TODO Auto-generated method stub
		System.out.println("dog shout");
	}
	
}

class Cat extends Animal{
	
	protected int id = 3;
	
	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("cat eat");
		System.out.println(id);
	}
	
	@Override
	public void shout() {
		// TODO Auto-generated method stub
		System.out.println("cat shout");
	}
	
}
