package lab1;

import java.util.Scanner;

public class C_FactorialMagic {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n,m;
        n = sc.nextLong();
        m = sc.nextLong();
        if (n > 4){
            System.out.println(0);
        }
        else {
            if (n == 1)
                System.out.println(1 % m);
            else if(n == 2){
                n = jieCheng(jieCheng(jieCheng(n)));
                n = n % m;
                System.out.println(n);
            }
            else if (n == 0)
                System.out.println(1 % m);
            else if (n == 3){
                long big = jieCheng(jieCheng(3));
                long num = 1;
                for (long i = 1;i <= big;i++){
                    num *= i % m;
                    num = num % m;
                }
                System.out.println(num);
            }
            else
                System.out.println(0);
        }
    }

    static long jieCheng(long num){
        long k = num;
        num = 1;
        for (int i = 1;i <= k;i++){
            num *= i;
        }
        return num;
    }
}
