package day19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class ACutil {



    // 求排列数 A(n,m) n>m
    public static int A(int n, int m) {
        int result = 1;
        // 循环m次,如A(6,2)需要循环2次，6*5
        for (int i = m; i > 0; i--) {
            result *= n;
            n--;// 下一次减一
        }
        return result;
    }



    public static int C(int n, int m){
        // 应用组合数的互补率简化计算量
        m = m > (n - m) ? (n - m) : m;
        // 分子的排列数
        int son = A(n, m);
        // 分母的排列数
        int mother = A(m, m);
        return son / mother ;
    }

    public static void main(String args[]){
        BufferedReader sb = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String[] tempSet;
        HashSet<Integer> set = new HashSet<Integer>();
        try{
            while(!(line = sb.readLine()).equals("")){

                tempSet = line.trim().split(" ");
                set.add(Integer.parseInt(tempSet[0]));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        for(Iterator<Integer> it = set.iterator(); it.hasNext();){
            System.out.print(it.next());
        }

        Scanner sc = new Scanner(System.in);

        while((line = sc.nextLine()) != null){
            if(line.equals(""))
                break;
            System.out.println(line);
        }





    }

    /*

     protected int[] getNext(char[] p) {
            // 已知next[j] = k,利用递归的思想求出next[j+1]的值
            // 如果已知next[j] = k,如何求出next[j+1]呢?具体算法如下:
            // 1. 如果p[j] = p[k], 则next[j+1] = next[k] + 1;
            // 2. 如果p[j] != p[k], 则令k=next[k],如果此时p[j]==p[k],则next[j+1]=k+1,
            // 如果不相等,则继续递归前缀索引,令 k=next[k],继续判断,直至k=-1(即k=next[0])或者p[j]=p[k]为止
            int pLen = p.length;
            int[] next = new int[pLen];
            int k = -1;
            int j = 0;
            next[0] = -1; // next数组中next[0]为-1
            while (j < pLen - 1) {
                if (k == -1 || p[j] == p[k]) {
                    k++;
                    j++;
                    next[j] = k;
                } else {
                    k = next[k];
                }
            }
            return next;
        }

    @Override
        public int indexOf(String source, String pattern) {
            int i = 0, j = 0;
            char[] src = source.toCharArray();
            char[] ptn = pattern.toCharArray();
            int sLen = src.length;
            int pLen = ptn.length;
            int[] next = getNext(ptn);
            while (i < sLen && j < pLen) {
                // 如果j = -1,或者当前字符匹配成功(src[i] = ptn[j]),都让i++,j++
                if (j == -1 || src[i] == ptn[j]) {
                    i++;
                    j++;
                } else {
                    // 如果j!=-1且当前字符匹配失败,则令i不变,j=next[j],即让pattern模式串右移j-next[j]个单位
                    j = next[j];
                }
            }
            if (j == pLen)
                return i - j;
            return -1;
        }


        stack
        push( num) //入栈
pop() //栈顶元素出栈
empty() //判定栈是否为空
peek() //获取栈顶元素
search(num) //判端元素num是否在栈中，如果在返回1，不在返回-1。

    queue

    add(E e) : 将元素e插入到队列末尾，如果插入成功，则返回true；如果插入失败（即队列已满），则会抛出异常；
remove() ：移除队首元素，若移除成功，则返回true；如果移除失败（队列为空），则会抛出异常；
offer(E e) ：将元素e插入到队列末尾，如果插入成功，则返回true；如果插入失败（即队列已满），则返回false；
poll() ：移除并获取队首元素，若成功，则返回队首元素；否则返回null；
peek() ：获取队首元素，若成功，则返回队首元素；否则返回null


     */



}



