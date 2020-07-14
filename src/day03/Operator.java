public class Operator{

	public static void main(String args[]){
		int a1 = 10;
		int b1 = ++a1;
		//a1==b1==11;
		int a2 = 10;
		int b2 = a2++;
		//a2==10, b2==10
		//--符号同理
		//在后的++就算再==中也会后运算
		//自增符号不会改变变量类型
		//a2++++与(a2++)++都是错的

		short s1 = 2;
		//s1 = s1 + 2//error
		s1 += 2;//ok
		//运算符号不改变变量的数据类型
		int i = 1;
		i *= 0.1;//结果为0，成浮点之后被截断了
		int n = 10;
		n += (n++)+(++n);//32:=n+(n++)+(++n)=10+10+12
		boolean bb1 = false;
		boolean bb2 = true;
		System.out.println(b1=b2);//true,先赋值，在运算

		//逻辑运算符，只能针对布尔类型
		//&与，&&短路与
		//运算结果相同，当左边是true时，二者都会执行符号右侧的运算
		//当符号左边时false时，&&不再执行右侧的运算
		

		System.out.println(n);
		if(bb1 & (n++ > 0)) {
			System.out.println(n);
		}else {
			System.out.println("result");
		}
		
		if(bb1 && (n++ > 0)) {
			System.out.println(n);
		}else {
			System.out.println("result");
		}
		
		//|或，||短路或
		//运算结果相同，当左边为false时，二者都执行右侧
		//若左边为true，|继续执行，||不再执行右侧
		
		//开发中推荐使用短路&&与||

		//！非

		// ^ 异或
		
		boolean x = true, y = false;
		short z = 42;
		if(y == true)
		if((z++ == 42) && (y = true)) z++;
		if((x = false) || (++z == 45)) z++;
		System.out.println(z);

		/*位运算符
		 * 都是整形数据
		 * 高效计算2*8：2<<3 或者 8<<1
		 * 左移补0，右移根据正负补
		 * 无符号右移都用0补,多用于hash操作中
		 */
		System.out.println(3<<3);//左移，乘以2的三次方
		System.out.println(24>>3);//右移，除以2的三次方
		System.out.println(-55>>>1);
		
		/*需要两端是整数
		 * &按位与，0010&0110=0010
		 * |按位或
		 * ^按位异或
		 * ~按位取反
		 * */
		
		/*
		 * 如果程序既可以用三元运算符又可以用if-else优先选择三元运算符，简介，效率高
		 * */
		
		
	}

/*
%:结果的符号和被模数的符号相同：-12%5=-2，12%-5=2，-12%-5=-2


*/

}