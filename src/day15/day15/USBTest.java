package day15;

public class USBTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 接口的使用
		 * 定义了一种规范
		 * 面向接口编程
		 * 代理模式
		 * 
		 * 
		 */
		
		Computer com = new Computer();
		Flash f = new Flash();
		com.tranforData(f);//接口非匿名实现类的非匿名对象
		com.tranforData(new Flash());//接口非匿名实现类的匿名对象
		
		USB u = new USB() {//匿名接口的非匿名实现类
			@Override
			public void start() {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void stop() {
				// TODO Auto-generated method stub
				
			}
		};
		com.tranforData(u);
		
		com.tranforData(new USB() {//匿名实现类的匿名对象
			@Override
			public void start() {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void stop() {
				// TODO Auto-generated method stub
				
			}
		});
	}

}

interface USB{
	
	void start();
	
	void stop();
	
}

class Flash implements USB{
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
}

class Printer implements USB, Comparable{
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}


class Computer{
	public void tranforData(USB usb) {
		usb.start();
		
		System.out.println("work");
		
		usb.stop();
		
	}
}