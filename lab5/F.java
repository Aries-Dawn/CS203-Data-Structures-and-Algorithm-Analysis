package lab5;

import java.util.Scanner;

public class F {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        char[] mi = new char[26];
        for (int i = 0;i < mi.length;i++){
            mi[i] = sc.next().charAt(0);
        }
        String message = sc.next();
        int len;
        if (message.length() / 2 - (double)message.length() / 2 != 0){
            len = message.length() / 2 + 1;
        }
        else
            len = message.length() / 2;
        char[] messages = message.toCharArray();
        String hid_s = message.substring(0,len);
        char[] hid = hid_s.toCharArray();
        String mms = message.substring(len);
        char[] mm = mms.toCharArray();
        for (int i = 0;i < mm.length;i++){
            mm[i] = mi[mm[i] - 'a'];
        }
        System.out.println(KMP(mm,hid));



    }

    private static int KMP(char[] txt,char[] pattern){

        int m = txt.length;
        int n = pattern.length;
        int[] next = nextArray(pattern);
        int p = 0;
        int i = 0;
        while (i < m){
            if (txt[i] == pattern[p]){
                i++;
                p++;
            }
            else if (p != 0){
                p = next[p - 1];
            }
            else
                i++;
        }
        return i - p + n;



    }

    private static int[] nextArray(char[] pattern) {

        int[] next = new int[pattern.length];
        next[0] = 0;
        int j = 0;
        for (int i = 1; i < pattern.length; i++) {
            while (j > 0 && pattern[j] != pattern[i]) {
                j = next[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
