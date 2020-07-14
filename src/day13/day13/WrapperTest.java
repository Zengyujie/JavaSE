package day13;

import org.junit.Test;

public class WrapperTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 1, 八种基本数据类型的包装类：
		 * 	byte->Byte
		 * 	short->Short
		 * 	int->Integer
		 * 	long->Long
		 * 	float->Float
		 * 	double->Double
		 * 	以上六种的父类Number
		 * 	boolean->Boolean
		 * 	char->Character
		 * 2,基本类型，包装类，和String的相互转换
		 * 3,包装类是类，拥有类的特征
		 * 4，包装类不可以运算，必须转换成基本数据类型 
		 * 5，自动装箱和开箱
		 * 6,基本数据类型转换成String类型，调用string的valueOf方法
		 * 7,String转换为基本类型和包装类，需要用到对应包装类的方法
		 */
	}
	
	@Test
	public void Test1() {
		int num1 = 10;
		Integer in1 = new Integer(num1);
		Integer in2 = new Integer("123");//需要是纯粹的数
		System.out.println(in1);
		Float f1 = new Float("12.3f");
		Float f2 = new Float(12.3f);
		Boolean b1 = new Boolean("false");//可忽略大小写
		Boolean b2 = new Boolean(false);
		System.out.println(new Order().b1);//注意，已经是一个类了，不再是基本数据类型了
		
	}
	
	@Test
	public void Test2() {
		Integer in1 = new Integer(12);
		int i1 = in1.intValue();
		Float f1 = new Float(12.3f);
		float f2 = f1.floatValue();
		int i2 = new Integer(1);//自动开箱
		Integer in2 = 3;//自动装箱
		
	}
	
	@Test
	public void Test3() {
		int i1 = 10;
		String s1 = i1 + "";
		String s2 = String.valueOf(i1);
		Double d1 = 12.0;
		s2 = String.valueOf(d1);
		double d2 = Double.valueOf(s2);
		System.out.println(d2);
		d2 = Double.parseDouble(s1);
		System.out.println(d2);
		//可能会有NumberFormatException。注意字符赋值基本类型
		
		
	}
	
	@Test
	public void Test4() {
		//三元运算符需要两个统一成一个类型，和true，false没关系
		Object o1 = true ? new Integer(1):new Double(2.0);
		System.out.println(o1);
		Object o2;
		if(true) {//不用考虑以上
			o2 = new Integer(1);
		}else {
			o2 = new Double(2.0);
		}
		System.out.println(o2);
	}
	
	@Test
	public void Test5() {
		Integer i = new Integer(1);
		Integer j = new Integer(1);
		System.out.println(i == j);//false
		
		i = 1;//相当于从IntegerCache中直接获取
		j = 1;
		System.out.println(i == j);//true
		
		i = 128;//相当于new了对象
		j = 128;
		System.out.println(i == j);//false
		
		//Integer中定义了一个静态内部类叫IntegerCache
		//其中存了一个Integer数组，其中存了-128-127的数
		//给Integer赋值这范围内的数时直接使用，不再new了
		//因为这些数比较常见，装箱的时候就能直接返回，以提升性能
		Object o = new Integer(1);
		int i1 = (int)o;//这样拆是可以的
	}

}

class Order{
	Boolean b1;
}
