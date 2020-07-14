package day15;

public class InnerClassTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 内部类：
		 * 	1，java中允许将一个类A声明在另一个类B中
		 * 	2，内部类分类：成员内部类(静态，非静态)，局部内部类（方法，代码块，构造器内）
		 * 	3，成员内部类：
		 * 		1,一方面，作为外部类的成员：
		 * 			1，调用外部类的结构
		 * 			2，可以用static修饰
		 * 			3，可以被四种不同的权限修饰
		 * 			
		 * 		2，另一方面，作为类：
		 * 			1，可以定义属性，方法，构造器等
		 * 			2,可以被final修饰，不适用final可以被继承
		 * 			3，可以被abstract修饰
		 * 
		 * 	4，关注如下3个问题：
		 * 		1，如何实例化成员内部对象
		 * 		2，如何在成员内部类中调用外部类的结构
		 * 		3，开发中局部内部类的使用
		 * 		4，在局部内部类的方法中，局部内部类调用方法中定义的变量，要求变量为final的
		 * 		5,对4的变量JDK7,及以前的版本必须显式声明final，8之后可以省略，但是为什么我的jdk不行？？
		 */
		
		//创建Dog（静态内部类）
		P.Dog dog = new P.Dog();
		dog.show();
		//创建非静态内部类
		//P.Bird bird = new P.Bird();error
		P p = new P();
		P.Bird bird = p.new Bird();
		bird.sing();
		bird.display("test");
		System.out.println("=-----------------=");
	}
	
	//返回实现接口的对象
	public Comparable getComparable() {
		class MyComparable implements Comparable{
			@Override
			public int compareTo(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
		}
		return new MyComparable();
	}
	//或者
	public Comparable getComparable1() {
		return new Comparable(){
			@Override
			public int compareTo(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}
	
	
	public void method3() {
		final int num = 10;
		
		class EE{
			public void show() {
				//num = 10
				System.out.println(num);
			}
		}
		
	}

}

class P{
	
	String name= "person";
	int age;
	
	public void eat() {
		System.out.println("people eat");
	}
	
	//成员内部类
	static class Dog{
		
		String name = "dog";
		int age;
		
		public void show() {
			System.out.println("inner dog");
		}
		
	}
	
	class Bird{
		
		String name = "bird";
		
		public Bird() {}
		
		public void sing() {
			System.out.println("sing");
			P.this.eat();//调用外部类的非静态属性方法
		}
		
		public void display(String name) {
			System.out.println(name);
			System.out.println(this.name);//内部类属性
			System.out.println(P.this.name);//外部类属性
			System.out.println(age);//无重名
		}
		
	}
	
	public void method() {
		class AA{
			
		}
	}
	
	{
		class BB{
			
		}
	}
	
	public P() {
		class CC{
			
		}
	}
	
	
}