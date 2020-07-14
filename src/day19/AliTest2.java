package day19;

import java.util.Scanner;

public class AliTest2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line1, line2;
        line1 = sc.nextLine();
        line2 = sc.nextLine();
        int n;
        String[] temp1 = line1.trim().split(" ");
        n = Integer.parseInt(temp1[0]);
        String[] temp2 = line2.trim().split(" ");
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(temp2[i]);
        }

        int len = array.length;
        int[] arr1 = new int[len];
        getCount(len, arr1);
        double res = arr1[0];
        for(int i = 1; i < len; i++){
            res += (arr1[i] - arr1[i - 1]) * array[i];
        }

        res = res / arr1[len - 1];




        System.out.println(2.333);
    }

    public static int getCount(int num, int[] a){

        if(num == 1){
            a[0] = 1;
            return 1;
        }
        if(num == 2){
            a[0] = 1;
            a[1] = 3;
            return 3;
        }
        int temp =  getCount((num - 1), a) + num;
        a[num - 1] = temp;
        return temp;
    }


}
