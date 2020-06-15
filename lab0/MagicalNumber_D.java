package lab0;

import java.util.Scanner;

public class MagicalNumber_D {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        String[] num = new String[cases];
        long[] count = new long[cases];
        for (int i = 0;i < cases;i++){
           num[i] = sc.next();
        }
        for (int i = 0;i < cases;i++){
            if (num[i].length() == 1){
                count[i] += (Integer.parseInt(num[i]) + 1);
            }
            else {
                int weiShu = 2;
                int num2 = 9;
                count[i] = 10;
                while (true){
                    if (weiShu % 2 != 0){
                        num2 *= 10;
                    }
                    if (weiShu == num[i].length()){
                        break;
                    }
                    count[i] += num2;
                    weiShu += 1;
                }
                weiShu = num[i].length();
                if (weiShu % 2 == 0){
                    String first = num[i].substring(0,weiShu / 2);
                    String second = num[i].substring(weiShu / 2);
                    int initial = weiShu / 2;
                    count[i] += counts(first,second,first,initial);
                }
                else {
                    String first = num[i].substring(0,weiShu / 2);
                    String second = num[i].substring(weiShu / 2 + 1);
                    String number = num[i].substring(0,weiShu / 2 + 1);
                    int initial = weiShu / 2 + 1;
                    count[i] += counts(first,second,number,initial);
                }

            }
        }
        for (int i = 0;i < cases;i++){
            System.out.println(count[i]);
        }
    }

    static boolean judge (String num){
        String num1 = inverted(num);
        return num.equals(num1);
    }

    private static long counts(String first, String second, String number, int initial){
        if (smaller(first,second)){
            return minus(initial,number);
        }
        else {
            long fi = Integer.parseInt(number);
            fi -= 1;
            number = String.valueOf(fi);
            return minus(initial,number);
        }
    }

    private static long minus(int initial, String first){
        int [] num = new int[initial];
        num[0] = 1;
        long num2;
        StringBuilder sb = new StringBuilder();
        for (int i : num){
            sb.append(i);
        }
        num2 = trans(sb.toString());
        long num3 = trans(first);
        if (num3 - num2 + 1 >= 0){
            return num3 - num2 + 1;
        }
        else
            return 0;
//        return num3 - num2 + 1;
    }
    private static String inverted (String num){
        StringBuilder sb = new StringBuilder();
        for (int i = num.length() - 1;i >= 0;i--){
            sb.append(num.charAt(i));
        }
        return sb.toString();
    }


    private static long trans (String s){
        long num = 0;
        int weiShu = s.length();
        for (int i = 0;i < s.length();i++){
            num += ((int)s.charAt(i) - (int)'0') * Math.pow(10,weiShu - 1);
            weiShu -= 1;
        }
        return num;
    }


    private static boolean smaller(String first, String second){
        first = inverted(first);
        long a,b;
        a = trans(first);
        b = trans(second);
        if (a <= b)
            return true;
        else
            return false;
    }

}
