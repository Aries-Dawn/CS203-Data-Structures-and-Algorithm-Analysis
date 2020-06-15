package lab0;

import java.util.Scanner;

public class F_SummerCamp {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cases;
        cases = sc.nextInt();
        int[] count = new int[cases];
        for (int i = 0;i < cases;i++){
            int number = sc.nextInt();
            if (number % 2 == 0 ){
                for (int j = 2;j < Math.sqrt(2 * number);j++){
                    double n = (double) (2 * number + j * j - j) / (2 * j);
                    int nn = (2 * number + j * j - j) / (2 * j);
                    if (n == nn){
                        count[i] = j;
                        break;
                    }
                }
            }
            else if (number > 1)
                count[i] = 2;
        }
        for (int i:count){
            if (i < 2){
                System.out.println("impossible");
            }
            else
                System.out.println(i);
        }
    }
}
