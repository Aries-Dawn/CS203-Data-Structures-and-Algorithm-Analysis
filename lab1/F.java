package lab1;

import java.util.Scanner;

public class F {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0;i < cases;i++){
            long num = sc.nextLong();
            long count = 0;
            while (num > 0){
                count += num / 5;
                num /= 5;
            }
            System.out.println(count);
        }
    }
}
