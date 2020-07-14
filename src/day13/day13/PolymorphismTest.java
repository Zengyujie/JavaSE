package day13;

public class PolymorphismTest {

	public static void main(String[] args) {
		
		 // TODO Auto-generated method stub
		 /*
		   * 多态性补充：
		 *	1,有了多态之后，父类引用承接new子类的时候内存中实际上是加载了子类的特有属性和方法，但是由于变量声明为父类类型
		 *		在编译时只能调用父类的属性和方法，子类特有的属性和方法不能调用
		 *	2,向下转型：使用强制类型转换将父类转为子类才能调用子类方法
		 *	3,强制转换有风险，会抛出class cast exception
		 *	4，instanceof 判断对象a是否为类A的示例
		 *	5，为了避免ClassCastException异常，在向下转型是进行instanceof判断，返回true则向下转型
		 *	6,参数中的数组和可变参数也可以构成重载，但是输入的差别注意
		 */
		Person p = new Person();
		Person p1 = new Man();
		p1.eat();
		//p1.work(); //error
		Man m = (Man)p1;
		m.work();
		
		p.add(0, new int[]{1,2});
		m.add(0,new int[]{1,2});
		p.add(0,1,2);
		p1.add(0,1,2);//子类可以算重载，但是子类直接用父类的输入形式会报错
		m.add(0,1,2);
		System.out.println();
//		Man m2 = (Man)p;
//		try{
//			m2.work();
//		}catch(Exception e) {
//			System.out.println("exception");
//		}
		Man m1 = null;
		if(p instanceof Man) {
			m1 = (Man)p;
			m1.work();
		}else {
			System.out.println("is a person");
		}
		
		//编译过，运行不过
		Person p3 = new Student();
		Man m3 = (Man)p3;
		
		Person p5 = new Person();
		Man m5 = (Man)p5;
		
		//编译过，运行过
		Object obj = new Man();
		Person p4 = (Person)obj;
		
		//编译不过
		//Man m6 = new Student();
		
		//骗过编译器,向低又向高
		
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
	
	public void add(int a, int... arr) {
		System.out.println("person add");
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
	
	public void add(int a, int[] arr) {
		System.out.println("man add");
	}
	
	public void add(int a, int b, int c) {//不是重载，是新的方法
		System.out.println("sub method");
	}
}

