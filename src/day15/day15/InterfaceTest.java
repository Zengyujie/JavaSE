package day15;

public class InterfaceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 *  Inferface
		 *  1，为什么哟用接口
		 *  	1，为了解决不能多重继承的问题
		 *  	2，从几个类中抽取共同的行为特征，但是他们之间没有is-a的关系
		 *  	3，定义一组规则，继承是“是不是”的关系，接口是“能不能”的关系
		 *  	4,interface和class是并列结构
		 *  2，使用interface来定义接口
		 *  	1，在JDK7以前：
		 *  		1，只能定义全局常量和抽象方法
		 *  			全局常量：public static final
		 *  			抽象方法:public abstract
		 *  
		 *  	2，在JDK8以后：
		 *  			可以定义静态方法和默认方法
		 *  
		 *  3，接口中不能定义构造器，意味着接口不可以实例化
		 *  
		 *  4，在卡法中，接口都通过让类来实现的方式来使用implements,若实现类
		 *  	覆盖了接口的所有抽象方法，如果没有实现完所有的抽象方法，则只能声明为抽象类
		 * 	5,一个类可以实现多个接口
		 * 	6,类声明格式：class A extends B implements C,D{}
		 *  7,接口与接口之间可以多继承 extends AA,BB
		 *  
		 *  8,接口的使用可以体现多态性
		 *  9，接口实际上可以看成是一种规范
		 *  
		 *  interface和abstract 的区别
		 * 
		 */

	}

}

interface Flyable{
	
	public static final int MAX_SPEED = 7900;
	public static final int MIN_SPEED = 1;
	int MID_SPEED = 1000;//前面三个关键字会默认补全
	
	public abstract void fly();
	void stop();//前面的也可以省略
	
}

interface Attackable{
	
}

class Plane implements Flyable, Attackable{
	
	
	@Override
	public void fly() {
		// TODO Auto-generated method stub
		System.out.println("plane flies");
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("plane stops");
	}
}




abstract class Amphibious implements Flyable{
	
	
}

interface AA{
	void method1();
	
}

interface BB{
	void method2();
}

interface CC extends AA,BB{
	
}