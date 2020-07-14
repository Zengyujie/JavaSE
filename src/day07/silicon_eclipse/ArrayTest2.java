package silicon_eclipse;

import java.util.HashSet;
import java.util.Arrays;

public class ArrayTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 
		 * 
		 */
		//lottery(30, 6);
		//clip(5);
		//stringReverse("hello".toCharArray());
		int[] test = {23,45,33,22,13,6,3,8,54,443};
		//binarySearch(test, 0);
		//bubbleSort(test);
		//quickSort(test, 0, test.length - 1);
		//printArray(test);
		
		//Arrays类使用
		int[] arr1 = new int[] {1,2,3};
		int[] arr2 = {1,3,2};
		System.out.println(Arrays.equals(arr1, arr2));
		System.out.println(Arrays.toString(arr1));
		Arrays.fill(arr1, 11);//全部替换成11
		System.out.println(Arrays.toString(arr1));
		Arrays.sort(test);
		System.out.println(Arrays.toString(test));
		System.out.println(Arrays.binarySearch(arr1, 10));
		arrayException();
	}
	
	public static void lottery(int range, int len) {
		HashSet<Integer> set = new HashSet<Integer>();
		while(set.size() < len) {
			set.add((int)(0 + Math.random()*31 ));
		}
		for(int i: set) {
			System.out.println(i);
		}
	}
	
	public static void clip(int range) {
		int[][] clip = new int[range][range];
		int temp_len = range / 2;
		int counter = 1, j = 0;
		for(int i = 0; i < temp_len; i++) {
			for(j = 0; j < range - i * 2; j++) {
				clip[i][j + i] = counter++;
			}
			for(j = 1; j < range - i * 2; j++) {
				clip[j + i][range - i - 1] = counter++;
			}
			for(j = 1; j < range - i * 2; j++) {
				clip[range - 1 - i][range - 1 - i - j] = counter++;
			}
			for(j = 1; j < range - i * 2 - 1; j++) {
				clip[range - 1 - i - j][i] = counter++;
			}
		}
		if(range % 2 == 1) {
			clip[temp_len][temp_len] = counter;
		}
		for(int i = 0; i < range; i++) {
			for(j = 0; j < range; j++) {
			    System.out.print(clip[i][j]);
			    System.out.print(" ");
			}
			System.out.println();
		}
	}

	public static void stringReverse(char[] str) {
		char temp = ' ';
		for(int i = 0, j = str.length - 1; i < str.length/2; i++, j--) {
			temp = str[i];
			str[i] = str[j];
			str[j] = temp;
		}
		System.out.println(new String(str));
	}
	
	public static void printArray(int[] array) {
		for(int t : array) {
			System.out.print(t);
			System.out.println(" ");
		}
		System.out.println();
	}
	
	public static void print2DArray(int[][] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j]);
				System.out.println(" ");
			}
			System.out.println();
		}
	}
	
	public static void binarySearch(int[] array, int target) {
		int start = 0, end = array.length - 1, mid;
		while(start <= end) {
			mid = (start + end) / 2;
			if(array[mid] == target) {
				System.out.println(mid + 1);
				return;
			}if(array[mid] < target) {
				start = mid + 1;
			}else {
				end = mid - 1;
			}
		}
		System.out.println("no");
	}
	
	public static void bubbleSort(int[] array) {
		int temp = 0;
		for(int i = 0; i < array.length - 1; i++) {
			for(int j = 0; j < array.length - 1 - i; j++) {
				if(array[j] > array[j + 1]) {
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		printArray(array);
	}
	public static void quickSort(int[] array, int start, int end) {
		int mid = pillar(array, start, end);
		if(start < mid - 1) {
			quickSort(array, start, mid - 1);
		}
		if(end > mid + 1) {
			quickSort(array, mid + 1, end);
		}
	}
	
	public static int pillar(int[] array, int start, int end) {
		int temp = 0, i = start, j = end;
		while(i < j) {
			while(array[i] <= array[start] && i < j)
				i++;
			while(array[j] > array[start] && i < j)
				j--;
			if(i < j) {
				temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		if(array[i] > array[start])
			i--;
		temp = array[i];
		array[i] = array[start];
		array[start] = temp;
		return i;
	}
	
	public static void arrayException() {
		try {
			int[] a = {1,2,3,4};
			System.out.println(a[5]);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("out of bound");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("finally");
		}
		
		try {
			int[] b = null;
			System.out.println(b[3]);
		}catch(NullPointerException e) {
			System.out.println("null pointer");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
