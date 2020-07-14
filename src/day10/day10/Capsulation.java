package day10;

import static java.lang.Math.abs;

import static java.lang.Math.*;

public class Capsulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 属性的封装性的体现：
		 * 1，将成员写成private， 然后用public的get和set方法来获取和修改
		 * 2，不对外暴露的私有方法
		 * 3，单例模式，构造器的私有化
		 * 。。。
		 * 封装性的体现，需要权限修饰符来配合
		 * Java规定的四种权限：private，缺省(default会报错)，protected，public
		 * 
		 * 修饰符		类内部	同一个包	 不同包的子类	同一个工程
		 * private   Y
		 * 缺省                    Y		Y（同包的不同类文件也可以）
		 * protected Y		Y		Y
		 * public	 Y		Y		Y			Y
		 * 修饰符可以修饰类以及类的内部结构：属性，方法，构造器，内部类
		 * 			修饰类只能使用缺省或者public，另外两种是非法的
		 * 封装性总结：java提供了四种权限修饰符来修饰类及类的内部结构
		 * 		体现了类及内部结构在被调用时的可见性大小
		 * 在开发中，一般来说一个源文件一个类最好
		 * 
		 * Constructor:
		 * 1，作用：创建对象，属性初始化
		 * 2，定义，没有系统会默认无参数构造器，有参数则加形参列表，名字与函数同名
		 * 3，构造器是构造器，方法是方法
		 * 4，一个类中定义的多个构造器构成重载
		 * 5，一旦定义了构造器，系统不再默认提供空参构造器
		 * 
		 * 6，属性赋值的先后顺序：默认初始化-》显式初始化-》构造器赋值-》调用方法赋值
		 * 
		 * JavaBean:java语言写成的可重用组件：public，有一个无参public构造器
		 * 			有属性和对应的get，set方法
		 * 
		 * this关键字的使用：修饰属性，方法，构造器
		 * 1，this理解为当前对象
		 * 2，在方法中，我们可以使用this.field或者this.method来调用
		 * 		通常情况下选择省略this，若与形参同名则需要显式使用this
		 * 3，在类的构造器中可以使用this(参数)的方式构造本类中的其他构造器
		 * 		自己调自己会出错，多个构造器套娃也会出错
		 * 4，构造器中调用其他构造器必须放在首行，一个构造器只能调用一个其他构造器
		 * 5，this可用于类中的回调，单独一个this可作为当前对象的实参传递给形参
		 * 
		 * package关键字：
		 * 1，为了更好实现项目中类的管理
		 * 2，声明在源文件的首行，只要前面没内容就行
		 * 3，遵循标识符的命名规范
		 * 4，没。一次代表一层目录
		 * 5，同一个包下不可以命名同名接口和类，不同包可以
		 * 
		 * import关键字：
		 * 1，在源文件中导入指定的类，接口
		 * 2，导入多个则并列往下写
		 * 3，。*可以导入包下的所有类
		 * 4，java.lang下不用引用
		 * 5，当前包下的不用import
		 * 6，若要使用不同包的同名类，则只能import一个，其他的需要以全类名的方式显示
		 * 7，imoort xxx.*不管递归的包
		 * 8，import static：导入指定类或接口中的static方法
		 * 		import static java.lang.System.*
		 * 		就可以直接用out.println了
		 * 		import static要用到*或者指定到方法和结构，方法不用加()
		 * 
		 */
		Person p = new Person();
		System.out.println(p.getAge());
		p.setAge(18);
		System.out.println(p.getAge());
	}

}

class Person{
	
	private String name;
	private int age=100;
	
	
	{
		System.out.println(this.toString());
		this.name = "default";
		this.age = 0;
		System.out.println(this.toString());
	}
	
	
	public Person() {
		System.out.println(this.toString());
		this.name = "test";
		this.age = 1;
		System.out.println(this.toString());
		
	}
	
	public Person(String name) {
		this();
		this.setName(name);
	}
	
	public Person(int age) {
		this();
		this.setAge(age);
	}
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public Person(int age, String name) {
		this(name);
		this.age = age;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + this.age;
	}
	
}
