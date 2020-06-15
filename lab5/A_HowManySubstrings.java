package lab5;

import java.util.Scanner;

public class A_HowManySubstrings {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases > 0){
            String ss = sc.next();
            int size = ss.length();
            size = (size + 1) * size / 2;
            System.out.println(size);
            cases--;
        }
    }
}
