package day16;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import org.junit.Test;

public class ExceptionTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 异常
		 * 	1，异常中，除将程序执行中发生的不正常的情况，不包括语法错误，逻辑错误等
		 * 	2，异常包括Error（虚拟机无法解决的严重问题，内部错误，资源耗尽等StackOverFlowError和OOM等）一般不处理
		 * 		Exception因编程或偶然发生的外在因素，可以使用针对性代码处理：空指针，不存在的文件，网络中断，数组越界等
		 *  3，异常的顶级父类，Throwable，两个子类，Error，Exception
		 *  	Throwable
		 *  		|---Error：一般不编写针对性代码处理
		 *  		|---Exception:可以进行异常处理
		 *  			|---编译时异常(checked)
		 *  				|---IOException
		 *  					|---FileNotFoundException
		 *  				|---ClassNotFoundException
		 *  			|---运行时异常(unchecked)
		 *  				|---NullPointerException
		 *  				|---ArrayIndexOutOfBoundsException
		 *  				|---ClassCastException
		 *  				|---NumberFormatException
		 *  				|---InputMismatchException
		 *  				|---ArithmeticException
		 *  面试题，常见的异常有那些？举例说明
		 *  4，异常的处理方式：
		 *  	抓抛模型
		 *  		过程一：抛：程序在正常执行的过程中，一旦出现异常，就会在异常代码处生成一个异常的对象
		 *  			    并将此对象抛出，一旦抛出后，后续代码不再执行
		 *  		过程二：抓：
		 *  			1，try-catch-finally
		 *  				处理编译时异常，使编译时不再报错，但运行时可能报错
		 *  			2，throws+异常类型
		 *  				写在方法的声明处，指出可能发生的异常类型，之明可能抛出的异常对象，一旦出现异常，产生满足
		 *  				要求的异常时就能抛出，后续代码不再执行
		 *	5,catch中的异常如果没有子父类关系，顺序无所谓，若有继承关系，父类要在后面
		 *  6,处理方式，getMessage和printstacktrace
		 *  7，finally：可选的，是一定会被执行的，即使try，catch中有return语句，也会执行
		 *  8，数据库连接，输入输出流，socket等资源，JVM是不能自动回收的，需要手动释放资源，
		 *  	可以声明在finally中
		 *  9，子类重写父类方法抛出的异常只能是父类异常或者子类，如果父类方法没有抛异常，子类不能抛异常
		 *  10，开发中如何选择try-catch-finally，还是throws
		 *  	1，如果父类中被重写的方法没有throws异常，子类重写的方法不能使用throws
		 *  		意味着子类必须使用try-catch-finally
		 *  	2，执行的方法A中，先后又调用了另外的几个方法，这几个方法是递进关系
		 *  		建议使用throws，而执行方法A可以用try-catch
		 *  11，手动抛出异常：
		 *  	异常对象的产生：系统自动生成异常，手动生成一个异常对象
		 *  12,自定义异常类：
		 *  	1，继承现有异常类：RunTimeException， Exception
		 *  	2,提供一个常量serialVersionUID，相当于对类的唯一标识
		 *  	3，提供重载的构造器
		 *  
		 *  
		 *  
		 */
		//main(args);栈溢出
		//Integer in[] = new Integer[1024*1024*1024]//堆溢出
		Student s = new Student();
		s.regist(1000);
	}
	
	//运行时异常
	@Test
	public void test1() {
		//NullPointerException
		int[] arr = null;
		System.out.println(arr[3]);
	}
	
	@Test
	public void test2() {
		//ArrayIndexOutOfBoundsException
		int[] arr = new int[3];
		System.out.println(arr[3]);
	}
	
	@Test
	public void test3() {
		//ClassCastException
		Object o = new String();
		Date d = (Date)o;
	}
	
	@Test
	public void test4() {
		//NumberFormatException
		String str = "123";
		str = "avc";
		int num = Integer.parseInt(str);
	}
	@Test
	public void test5() {
		//InputMismatchException
		Scanner scan = new Scanner(System.in);
		int s = scan.nextInt();
		System.out.println(s);
	}
	
	@Test
	public void test6() {
		//ArithmeticException
		int a = 10;
		int b = 0;
		System.out.println(a/b);
	}
	
	//编译时异常
	@Test
	public void test17() {
		//NullPointerException
		File f = new File("test.txt");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
			int data;
			data = fis.read();
			while(data != -1) {
				System.out.println((char)data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(fis != null)	
					fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void test8() {
		String str = "123";
		str = "abc";
		try {
			int num = Integer.parseInt(str);
			System.out.println("1");
		}//catch(Exception e) {
			//System.out.println(e.getMessage());
			//e.printStackTrace();
		//}
		catch(NumberFormatException e) {
			System.out.println("number error");
		}catch(NullPointerException e) {
			System.out.println("null");
		}
		System.out.println("2");
	}
	
	@Test
	public void test9() {
		try {
			int a = 10, b = 0;
			System.out.println(a/b);
		}catch(ArithmeticException e) {
			System.out.println(e.getMessage());
			
			try {
				int[] arr = new int[10];
				System.out.println(arr[10]);
			}catch(ArrayIndexOutOfBoundsException ae) {
				System.out.println(ae.getMessage());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("finfh");
		}
		
	}
	
	@Test
	public void test10() {
		method();
	}
	
	public int method() {
		try {
			int arr[] = new int[10];
			System.out.println(arr[10]);
			return 1;
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			return 2;//要有返回值
		}finally {
			System.out.println("finally");//先执行在return
			//return 3;//所以会返回3
		}
	}
	
	
	public void method4() {
		try {
			method3();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void method3() throws FileNotFoundException, IOException{
		method2();
	}
	
	public void method2() throws FileNotFoundException, IOException{
		File f = new File("test.txt");
		FileInputStream fis = null;
		
			fis = new FileInputStream(f);
			int data;
			data = fis.read();
			while(data != -1) {
				System.out.println((char)data);
	
		
			if(fis != null)	
					fis.close();
			
	}
	}
	
}


class SuperClass{
	public void method() throws IOException{
		
	}
}

class SubClass extends SuperClass{
	public void method() throws FileNotFoundException{
		
	}
}


class Student{
	private int id;
	public void regist(int id) {
		if(id > 0)
		{	
			this.id = id;
		}else {
			//System.out.println("illegel");
			throw new RuntimeException("eeee");//运行时异常，如果写Exception要马上处理，因为包括了编译时异常
		}
	}
	
	public void regist1(int id) throws Exception{
		if(id > 0)
		{	
			this.id = id;
		}else {
			//System.out.println("illegel");
			//throw new Exception("eeee");//或者try-catch直接处理
			throw new MyException("no negative number");
		}
	}
}

class MyException extends RuntimeException{
	static final long serialVersionUID = -7034897190745766939L;
	
	public MyException(){
		
	}
	
	public MyException(String str) {
		super(str);
	}
}