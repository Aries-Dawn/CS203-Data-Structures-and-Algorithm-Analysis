package lab1;

import java.util.Scanner;

public class test {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0;i < cases;i++){
            int num = sc.nextInt();
            System.out.println(C_FactorialMagic.jieCheng(num));
        }
    }
}
