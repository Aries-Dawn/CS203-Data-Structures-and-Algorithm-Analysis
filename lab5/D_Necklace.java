package lab5;

import java.util.Scanner;

public class D_Necklace {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases > 0){
            String necklace = sc.next();
            int[] next = nextArray(necklace);
            int size = necklace.length();
            int finalNum = next[size - 1];
            int loop = size - finalNum;
            int need;
            if (loop != size){
                need = loop - size % loop;
                need = need % loop;
            }
            else
                need = loop;
            System.out.println(need);


            cases--;
        }


    }


    private static int[] nextArray(String pattern){
        int [] next = new int[pattern.length()];
        next[0] = 0;
        int k = 0;
        for (int i = 1;i < pattern.length();i++){
            while (k > 0 && pattern.charAt(i) != pattern.charAt(k)){
                k = next[k - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(k))
                k++;
            next[i] = k;
        }
        return next;
    }
}
