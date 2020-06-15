package lab5;

import java.util.Scanner;

public class C_Repeat {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        int sum = 0;
        while (cases > 0){
            String pattern = sc.next();
            String txt = sc.next();
            int length;
            length = pattern.length();
            double third = (double)length / 3;
            int thirds = length / 3;
            if (third - thirds > 0){
                thirds++;
            }
            pattern = pattern.substring(0,thirds);
            if (KMP(txt,pattern)){
                sum++;
            }
            cases--;
        }
        System.out.println(sum);

    }



    private static boolean KMP(String txt,String pattern){


        int m = txt.length();
        int n = pattern.length();
        int[] next = nextArray(pattern);
        int p = 0;
        int i = 0;
        while (i < m ){
            if (txt.charAt(i) == pattern.charAt(p)){
                i++;
                p++;
            }
            else if (p != 0){
                p = next[p - 1];
            }
            else
                i++;
            if (p == n) {
                return true;
            }
        }
        return false;




    }

    private static int[] nextArray(String pattern){

        int[] next = new int[pattern.length()];
        next[0] = 0;
        int j = 0;
        for(int i = 1; i < pattern.length(); i++){
            while(j > 0 && pattern.charAt(j) != pattern.charAt(i)){
                j = next[j - 1];
            }
            if(pattern.charAt(i) == pattern.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
//        for (int j = 1;j < pattern.length() - 1;j++){
//            while (k > 0 && pattern.charAt(k + 1) != pattern.charAt(j)){
//                k = next[k];
//            }
//            if (pattern.charAt(k + 1) == pattern.charAt(j))
//                k++;
//            next[j] = k;
//        }
//
//        return next;

    }
}

//        int q = 0;
//        for (int i = 0;i < m;i++){
//            while (q > 0 && pattern.charAt(q) != txt.charAt(i)){
//               q = next[q];
//            }
//            if ( pattern.charAt(q) == txt.charAt(i))
//                q++;
//            if (q == n){
//                return true;
//            }
//        }
//        return false;