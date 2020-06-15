package lab5;

import java.util.Scanner;

public class B_MatchingProblem {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int cases = sc.nextInt();
        while (cases > 0){
            int s = sc.nextInt();
            int t = sc.nextInt();
            String first = sc.next();
            String second = sc.next();
            int key = 1;

            if (s - 1 <= t){
                int point = 0;
                int flag = 0;
                while (point < s){
                    if (first.charAt(point) == '*'){
                        flag = 1;
                        break;
                    }
                    if (first.charAt(point) != second.charAt(point)){
                        key = 0;
                        break;
                    }
                    point++;
                }
                if (s < t && flag != 1){
                    key = 0;
                }
                if (flag == 1){
                    point = s - 1;
                    int point2 = t - 1;
                    while (point >= 0){
                        if (first.charAt(point) == '*'){
                            break;
                        }
                        if (first.charAt(point) != second.charAt(point2)) {
                            key = 0;
                            break;
                        }
                        point--;
                        point2--;
                    }
                }
            }
            else
                key = 0;
            if (key == 0)
                System.out.println("NO");
            else
                System.out.println("YES");
            cases--;
        }

    }
}
