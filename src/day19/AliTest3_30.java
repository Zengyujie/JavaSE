package day19;

import java.util.Arrays;
import java.util.Scanner;

public class AliTest3_30 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line1, line2;
        line1 = sc.nextLine();
        line2 = sc.nextLine();
        int n, m, k;
        String[] temp1 = line1.trim().split(" ");
        n = Integer.parseInt(temp1[0]);
        m = Integer.parseInt(temp1[1]);
        k = Integer.parseInt(temp1[2]);
        String[] temp2 = line2.trim().split(" ");
        int[] chickenFac = new int[n];

        long sell = 0, sum = 0;
        for(int i = 0; i < n; i++){
            chickenFac[i] = Integer.parseInt(temp2[i]);
            sum += chickenFac[i];
        }
        Arrays.sort(chickenFac);
        for(int i = 0; i < m; i++){

            chickenFac[n - 1] = chickenFac[n - 1] / 2;
            sell += chickenFac[n - 1];
        }
        long res = sum + k * m * n - sell;
        System.out.println(res);
    }



}
