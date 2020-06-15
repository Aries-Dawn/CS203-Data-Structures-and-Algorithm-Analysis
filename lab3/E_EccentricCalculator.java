package lab3;

import java.util.Scanner;

public class E_EccentricCalculator {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0;i < cases;i++){
            long size,num,fast,max;
            size = sc.nextLong();
            size = (long) Math.pow(10L,size);
            num = sc.nextLong();
            max = num;
            fast = num;
            while (true){
                num *= num;
                while (num >= size){
                    num = num / 10;
                }
                if (num > max)
                    max = num;
                fast *= fast;
                while (fast >= size){
                    fast = fast / 10;
                }
                if (fast > max)
                    max = fast;
                fast *= fast;
                while (fast >= size){
                    fast = fast / 10;
                }
                if (fast > max)
                    max = fast;

                if (fast == num){
                    break;
                }

            }
            System.out.println(max);
        }
    }
}
