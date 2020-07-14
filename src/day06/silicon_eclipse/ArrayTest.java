package silicon_eclipse;

public class ArrayTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 数组属于引用数据类型的，要使用new来创建
		 * 数组的元素可以时任何类型
		 * 数组长度一旦确定不能修改
		 */
		//静态初始化，数组初始化和赋值同时进行
		int[] ids;
		ids = new int[]{1,2,3,4};//大括号和[]中的值不能同时存在
		int isd[] = {1};//类型推断机制，一行才可以
		int[] ids1 = new int[]{1,2,3,4};
		//动态初始化，数组初始化和赋值分剋进行
		String[] names = new String[4];
		//数组一旦初始化完成，长度固定
		
		
		/*
		 * 二维数组
		 * 
		 * 
		 */
		//静态初始化
		int[][] a1 = new int[3][4];
		int[][] a11 = new int[][]{{1,2,3},{4,5,6},{1}};
		//动态初始化
		String[][] a2 = new String[3][2];
		String[][] a3 = new String[2][];//低维不能缺
		int[][][] a4 = new int[3][][];
		int[] a5[] = new int[4][];
		//System.out.println(a5[1][0]);//error
		a5[0] = new int[4];
		System.out.println(a5[0][0]);
		
		System.out.println(a1[0]);
		System.out.println(a1[0][0]);
	}

}
