package day13;

import org.junit.Test;

public class JUnitTest {

	/*
	 * 步骤
	 * 1，选中当前工程：biuld path，add lib- JUnit 
	 * 2,创建Java类，进行单元测试
	 * 3，此时类必须是public的，且要有无参构造器
	 * 4，在此类中声明单元测试方法
	 * 5,要求方法是public，没有返回值和形参
	 * 6，单元测试方法上需要声明一个注解@Test
	 * 7,在单元测试类中导入org.junit.Test
	 * 8,在方法体内写测试代码
	 * 9，左键双击方法名，右键run as Junit Test
	 * 10,结果没有异常就是绿条，出现异常就是红条
	 * 11，选中哪个方法就测试哪个方法
	 */
	
	int num = 10;
	
	@Test
	public void testEquals() {
		String s1 = "MM";
		String s2 = "MM";
		System.out.println(s1.equals(s2));
		System.out.println(num);//不用new方法就能访问属性
	}

}
