package day15;

public class NewItems8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 
		 * JDK8中得静态方法和默认方法
		 * 	1,接口中定义得静态方法只能通过接口来调用，例如Collection/Collections，Path/Paths等
		 * 		可以充当工具类
		 * 	2，通过实现类得对象可以调用default方法,如果实现类重写了默认方法，调用时调用重写得方法
		 * 		重写时去掉default
		 * 	3,如果子类继承得父类和实现得接口中声明了同名同参数得方法，子类在没有重写得情况下，调用父类得方法
		 * 		-》类优先原则
		 * 	4,如果实现类实现了多个接口，多个接口中有同名同参数的额方法，那么在此类型情况下，会报错
		 * 		-》接口冲突。此时需要在实现类中重写此方法
		 *	5,如何在子类中调用父类，接口中被重写的方法，接口.super.method
		 *	6，一般来说，接口中的default方法不推荐重写
		 *
		 *
		 */
		SubClass s = new SubClass();
		//s.method1();error
		CompareA.method1();
		s.method2();
		s.method3();
	}

}


interface CompareA{
	
	//静态方法啊
	public static void method1() {
		System.out.println("compareA:Peking");
	}
	//默认方法
	public default void method2() {
		System.out.println("CompareA:default");
	}
	
	default void method3() {//public可省略
		System.out.println("CompareA:moit public default");
	}
}


interface CompareB{
	
	
	default void method3() {
		System.out.println("CompareB:moit public default");
	}
}

class SubClass implements CompareA{
	
	@Override
	public void method2() {
		// TODO Auto-generated method stub
		CompareA.super.method2();
		System.out.println("subclass default");
	}
	
	
}

class SubSubclass extends SubClass implements CompareA{
	@Override
	public void method2() {
		// TODO Auto-generated method stub
		System.out.println("subsubclass default");
	}
}


class SubSubclas implements CompareA,CompareB{
	@Override
	public void method3() {//重写方法
		// TODO Auto-generated method stub
		System.out.println("subsubclass default");
	}

}


class SubSubcla extends SubClass implements CompareA,CompareB{
	//这种情况也同样报错，必须重写两个接口中定义得同名同参数方法
	@Override
	public void method2() {
		// TODO Auto-generated method stub
		System.out.println("subsubclass default");
	}

	@Override
	public void method3() {
		// TODO Auto-generated method stub
		super.method3();
	}
	
	public void myMethod() {
		method3();//自己的
		super.method3();//父类的
		//调用接口的方法
		CompareA.super.method3();
		CompareB.super.method3();
	}
}