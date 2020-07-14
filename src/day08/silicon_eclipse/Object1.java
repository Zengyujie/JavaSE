package silicon_eclipse;

public class Object1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 类及成员：属性，方法，构造器，代码块，内部类
		 * 三大特征：封装，继承，多态，（四个：抽象）
		 * 关键字：this,super,static,final,abstract, interface, package,import,et al.
		 *属性=成员变量=field=域，字段
		 *方法=成员方法=method=函数
		 *创建类的对象=类的实例化=实例化类
		 *
		 *局部变量：声明在方法内，方法形参，代码块内，构造器内部的变量
		 *成员变量权限修饰符：private，public，缺省，protected
		 *局部变量没有默认初始化值，意味着在调用局部变量之前一定要显式赋值
		 *成员变量有默认初始化值
		 *形参是调的时候赋值
		 *
		 *内存中加载的位置：
		 *	属性：堆 （非static，static在方法区）
		 *	局部变量：栈
		 *
		 *
		 */
		Test tt = new Test();
		System.out.println(tt.getNum());
		setNum(tt);
		System.out.println(tt.getNum());
	}
	
	public static void setNum(Test t) {
		t.setNum(t.getNum() + 1);
	}
	

}

class Test{
	
	private int num;
	
	{
		num = 1;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
}
