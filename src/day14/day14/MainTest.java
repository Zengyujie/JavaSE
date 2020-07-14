package day14;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 1,main作为程序的入口
		 * 2,main是一个普通的静态方法
		 * 3，main方可可以作为与控制台的交互方式，如Scanner，eclipe的configuration，和命令行的参数运行
		 * 
		 * 
		 */
		Main.main(new String[100]);
	}

}


class Main{
	
	public static void main(String[] args) {
		args = new String[100];
		for(String s: args) {
			System.out.println(s);
		}
	}
	
}
