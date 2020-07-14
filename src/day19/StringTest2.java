package day19;

import org.junit.Test;

import java.util.Arrays;

public class StringTest2 {


    /*
    字符串指定部分反转
     */
    public static String myReverse(String str, int startIndex, int endIndex){
        StringBuffer sb = new StringBuffer(str.substring(startIndex, endIndex));
        sb = sb.reverse();
        StringBuffer sb2 = new StringBuffer(str);
        for(int i = startIndex, j = 0; i < endIndex; i++, j++ ){
            sb2.setCharAt(i, sb.charAt(j));
        }
        return sb2.toString();
    }

    public static String reverseByCharSet(String str, int startIndex, int endIndex){
        if(str != null){
            char[] arr = str.toCharArray();
            char temp;
            for(int i = startIndex, j = endIndex; i < endIndex; i++, j--){
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            return new String(arr);
        }
        return null;
    }

    public static String reverseByString(String str, int startIndex, int endIndex){
        if(str != null){
            String s1 = str.substring(0, startIndex);
            for(int i = endIndex-1; i >= startIndex; i--){
                s1 += str.charAt(i);//效率低，重复new String()
            }
            s1 += str.substring(endIndex);
            return s1;
        }
        return null;
    }

    public static String reverse(String str, int startIndex, int endIndex){
        if(str != null){
            StringBuilder sb = new StringBuilder(str.length());
            sb.append(str.substring(0, startIndex));
            for(int i = endIndex-1; i >= startIndex; i--){
                sb.append(str.charAt(i));
            }
            sb.append(str.substring(endIndex));
            return sb.toString();
        }
        return null;
    }


    /*
    获取一个字符串在另一个字符串中出现的次数
     */

    public static int getCount(String mainStr, String subStr){
        int mainLength = mainStr.length();
        int subLength = subStr.length();
        if(mainLength <= subLength){
            return 0;
        }
        int count = 0, tmp = 0;
        while(true){
            tmp = mainStr.indexOf(subStr, tmp);
            if(tmp == -1){
                break;
            }
            tmp += subLength;
            count++;
        }
        /*
        int index = 0;
        while((index = mainStr.indexOf(subStr, index)) != -1){
            count++;
            index + =subLength;
        }
         */
        return count;
    }

    public static void getMaxCommentString(String s1, String s2){
        int s1Len = s1.length();
        int s2Len = s2.length();
        int[][] matrix = new int[s1Len + 1][s2Len + 1];
        for(int i = 0; i < s1Len; i++){
            matrix[0][i] = 0;
        }
        for(int i = 1; i < s2Len; i++){
            matrix[i][0] = 0;
        }
        for(int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = matrix[i - 1][j] > matrix[i][j - 1] ? matrix[i - 1][j] : matrix[i][j - 1];
                }
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        int tagI = s1Len, tagJ = s2Len, count = 0, res[] = {0,0}, temp = 0;
        while(tagI > 0 && tagJ > 0){
            while(matrix[tagI][tagJ] == matrix[tagI][tagJ - 1] && (tagI > 0 && tagJ > 0)) {
                    tagJ--;
                System.out.println(tagJ);
            }
            if((tagI <= 0 && tagJ <= 0))
                break;
            if(matrix[tagI][tagJ] == 0||matrix[tagI - 1][tagJ - 1] == 0)
                break;
            if(matrix[tagI - 1][tagJ - 1] + 1 == matrix[tagI][tagJ]){
                temp++;
                tagI -= 1;
                tagJ -= 1;
            }else{
                while(matrix[tagI][tagJ] == matrix[tagI][tagJ - 1]) {
                    tagJ--;
                }
                while(matrix[tagI][tagJ] == matrix[tagI - 1][tagJ]) {
                    tagI--;
                }
                temp--;
                if(temp > count){
                    count = temp;
                    res[0] = tagI + 1;
                    res[1] = tagJ + 1;
                }
            }
        }
        System.out.println(count);
        System.out.println(Arrays.toString(res));
    }


    public static String getMaxsame(String str1, String str2){
        if(str1 == null || str2 == null){
            return null;
        }
        String maxStr = str1.length() > str2.length()? str1: str2;
        String minStr = str1.length() > str2.length()? str2: str1;
        int len = minStr.length();
        for(int i = 0; i < len; i++){

            for(int x = 0, y = len - i; y <=len ;x++, y++){
                String subStr = minStr.substring(x, y);
                if(maxStr.contains(subStr)){
                    return subStr;
                }
            }
        }
        return null;
    }




    public static void main(String[] args) {
        String s1 = "abcdefg";
        //System.out.println(reverse(s1, 2,5));
        String s2 = "abkkcadkabkebfkabkskab";
        //String target2 = "ab";
        //System.out.println(getCount(s2, target2));
        //getMaxCommentString("bccedxab","abccdex");
        System.out.println(getMaxsame(s1,s2));



    }


    @Test
    public void testString(){
        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);

        System.out.println(sb.length());//4

        System.out.println(sb);//"null"

        StringBuffer sb1 = new StringBuffer(str);//exception
        System.out.println(sb1);
    }

}
