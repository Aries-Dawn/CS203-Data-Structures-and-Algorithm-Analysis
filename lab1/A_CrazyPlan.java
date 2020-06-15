package lab1;

import java.util.Scanner;

public class A_CrazyPlan {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0;i < cases;i++){
            long num =sc.nextLong();
            System.out.println(calculate(num));
        }
    }

    private static long calculate(long n){
        long second = (1 + n) * n / 2;
        long first =  n * (n + 1) * (2 * n + 1) / 6;
        return (first + second) / 2;
    }
}
