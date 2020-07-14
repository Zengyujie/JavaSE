package silicon_eclipse;
import java.util.Scanner;

public class ProcessControl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 控制流程：顺序，分支，循环
		 * if-else不加大括号后面的作用域只有一个语句
		 * if-else一般不要超过三层嵌套，否则重新设计
		 * 
		 * switch(表达式){
		 * case 常量1:
		 * 	语句;
		 *	break;
		 * case 常量2：
		 * 	语句;
		 * 	break;
		 * default:
		 * 	语句;
		 * 	break;
		 * }
		 * case 后不加break的话，之后case中的语句会顺序执行
		 * switch中的表达式只能是：byte，short，char，int，enum，String六种类型
		 * case之后只能声明常量
		 * default的位置无所谓，都是先执行case，就算是default中没有break，也会顺序往下执行case中语句，直到break
		 *case可合并、
		 *switch-case可以用if-else来代替，反之不成立
		 *两者均可用时，switch-case效率稍高一些
		 */
		Scanner scan = new Scanner(System.in);
		int i = scan.nextInt();
		System.out.println(i);
		
		int ishanser = 34;
		switch(ishanser) {
		case 3:
		case 4:
		case 5:
			break;
		default:
			break;
		}
		
	}

}
