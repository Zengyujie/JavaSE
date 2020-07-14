package day14;

public class Singleton {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 单例模式：
		 * 1，采取一定的方法，使得在整个软件系统中，对，某个类只存在一个实例
		 * 2,如何创建：
		 * 		1，饿汉式：私有化构造器，内部创建对象，提供公共方法返回类对象，这些属性和对象都需要是static的
		 * 		2,懒汉式：私有化构造器，声明当前对象，不实例化，声明public，static的返回对象方法，判断属性是否为空，非空则创建
		 * 		3，区分饿汉式和懒汉式：
		 * 			饿汉式：坏处，对象加载时间过长，好处，线程安全的
		 * 			懒汉式：好处，延迟对象创建，坏处，线程不安全的
		 * 		4,单例模式应用场景
		 * 			1，网站计数器
		 * 			2，应用程序的日志应用
		 * 			3，数据库连接池
		 * 			4，项目中读取配置文件的类
		 * 			5，Application，android的
		 * 			6，Window的任务管理器，回收站
		 * 			7，。。。
		 * 	
		 * 
		 * 
		 */
	}

}

//饿汉式
class Single{
	private Single() {
		
	}
	
	private static Single instance = new Single();
	
	public static Single getInstance() {
		return instance;
	}
	
	
}

//懒汉式
class Single1{
	private Single1() {
		
	}
	
	private static Single1 instance = null;
	
	public static Single1 getInstance() {
		if(instance == null) {
			instance = new Single1();
		}
		return instance;
	}
	
}

//瞎写的
class Single2{
	private Single2() {
		
	}
	
	private static Single2 instance = null;
	
	private static Object lock = new Object();
	
	public static Single2 getInstance() {
		synchronized(lock) {
			if(instance == null) {
				instance = new Single2();
			}
		}
		return instance;
	}
}