public class HelloWorld{
	public static void main(String args[]){
		System.out.println("test");
	}

	/**
	@author dell
	@version v20.2.15
	the first java procedure
	*/
	protected void test(){
		System.out.println("test");
	}
}
/*
单行和多行注释的作用：
	1，对程序解释说明，增强可读性，方便便自己和别人
	2，调试所写代码

注释不参与编译，字节码文件中不包含注释

文档注释：java特有，可以被欸javadoc生成一套以html形式的文档

javadoc -d 名字 -author -version 文件名.java

多行注释不可以嵌套使用

*/