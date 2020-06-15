package lab0;

import java.util.Scanner;

public class RubikCube {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        char[] color = new char[cases];
        for (int i = 0;i < cases;i++){
            String[] first = new String[3];
            for (int j = 0;j < 3;j++){
                first[j] = sc.next();
            }
            String[] second = new String[3];
            for (int j = 0;j < 3;j++){
                second[j] = sc.next();
            }
            String[] third = new String[3];
            for (int j = 0;j < 3;j++){
                third[j] = sc.next();
            }
            color[i] = second[1].charAt(0);
        }
        for (int i = 0;i < cases;i++){
            System.out.println(color[i]);
        }
    }
}
