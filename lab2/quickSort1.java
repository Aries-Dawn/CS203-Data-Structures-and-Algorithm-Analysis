package lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class quickSort1 {
     public static void main(String [] args){
         Scanner sc = new Scanner(System.in);
         int size = sc.nextInt();
         ArrayList<Long> nums = new ArrayList<>();
         for (int i = 0;i < size;i++){
             nums.add(sc.nextLong());
         }
         Collections.shuffle(nums);
         long[] num = new long[size];
         for (int i = 0;i < size;i++){
             num[i] = nums.get(i);
         }
         quickSort(num,0, size - 1);
         for (long i:num)
             System.out.println(i);
     }

     private static void quickSort(long[] num,int low,int high){
         if (low < high){
             int p = position(num,low,high);
             quickSort(num,low,p - 1);
             quickSort(num,p + 1,high);
         }

     }

     private static int position(long[] num,int low,int high){
         long positions = num[low];
         int po = low;
         while (low < high){
             while (low < high && num[high] >= positions){
                 high--;
             }
             while (low < high && num[low] <= positions){
                 low++;
             }
             swap(num,low,high);
         }
         swap(num,po,high);

         return low;
     }


     private static void swap (long[] num,int first,int second){
         long temp = num[first];
         num[first] = num[second];
         num[second] = temp;
     }
}
