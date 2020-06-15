package lab6;

import java.util.Scanner;

public class A_K_aryTree{



    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases > 0){
            long n = sc.nextLong();
            long k = sc.nextLong();
            long h = getH(k,n);
            long a = ((long)Math.pow(k,h) - 1) / (k - 1);
            long leaf = ((long)Math.pow(k,h) - (n - a)) / k + (n - a);
            System.out.println(leaf);
            cases--;
        }

    }


    private static long getH(long k, long n){
        if (k == 1){
            return n - 1;
        }
        else{
            int h = 0;
            long num = (long) Math.pow(k,h);
            while (num < n){
                h++;
                num += (long) Math.pow(k,h);
            }
            return h;
        }

    }

}
