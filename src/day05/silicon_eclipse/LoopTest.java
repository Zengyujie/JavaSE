package silicon_eclipse;

public class LoopTest {

	
	/*
	 * 嵌套循环最好不要超过三层
	 * 带标签的continue
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(maximumCommonDivisor(12, 20));
		System.out.println(minimumCommonMultiple(12, 20));
		printRhombus(6);
		printPrimeNumber(10000);
	}
	
	public static int maximumCommonDivisor(int num1, int num2) {
		int greater = (num1 > num2) ? num1 : num2;
		int smaller = (num1 > num2) ? num2 : num1;
		int temp = Integer.MAX_VALUE;
		while(temp > 0) {
			temp = greater % smaller;
			greater = smaller;
			smaller = temp;
		}
		return greater;
	}
	
	public static int minimumCommonMultiple(int num1, int num2) {
		int mcd = maximumCommonDivisor(num1, num2);
		return num1*(num2/mcd);
	}
	
	public static void printRhombus(int len) {
		StringBuffer sb = new StringBuffer();
		int temp = len / 2;
		boolean flag = len % 2 == 1? true : false;
		for(int i = 0; i < temp; i++) {
			if(flag) {
				sb.append(" ");
			}
			for(int j = 0; j < temp; j++) {
				if(j < temp - i - 1) {
					sb.append(" ");
				}else {
					sb.append("* ");
				}
			}
			sb.append("\r\n");
		}
		if(flag) {
			for(int i = 0; i < temp + 1; i++) {
				sb.append("* ");
			}
			sb.append("\r\n");
		}
		for(int i = 0; i < temp; i++) {
			if(flag) {
				sb.append(" ");
			}
			for(int j = 0; j < temp; j++) {
				if(j < i) {
					sb.append(" ");
				}else {
					sb.append("* ");
				}
			}
			sb.append("\r\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void printPrimeNumber(int range) {
		
		boolean flag = true;
		long start = System.currentTimeMillis();
		for(int i = 2; i <= range; i++) {
			flag = true;
			for(int j = 2; j <= Math.sqrt(i); j++) {
				if( i % j == 0) {
					flag = false;
					break;
				}
			}
			if(flag) {
				System.out.println(i);
			}
		}
		long end = System.currentTimeMillis();
		System.out.println((double)(end-start)/1000);
	}
	
public static void printPrimeNumber1(int range) {
		long start = System.currentTimeMillis();
		label:for(int i = 2; i <= range; i++) {
			for(int j = 2; j <= Math.sqrt(i); j++) {
				if( i % j == 0) {
					continue label;
				}
			}
				System.out.println(i);
		}
		long end = System.currentTimeMillis();
		System.out.println((double)(end-start)/1000);
	}
}
