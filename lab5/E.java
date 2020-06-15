package lab5;

import java.util.Arrays;
import java.util.Scanner;


//本代码仅用于查看IDEA的将重复代码块转化成方法
public class E {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String first = sc.next();
        String second = sc.next();
        long low = 1,high = Math.min(first.length(),second.length());
        long middle ;
        long max = 0;
        while (low <= high){
            middle = (low + high) / 2;
            if (rabin(first,second,middle)) {
                low = middle + 1;
                if (middle > max)
                    max = middle;
            }
            else
                high = middle - 1;
        }
        System.out.println(max);

    }


    private static boolean rabin(String first, String second, long sub){
        //first
        long[] firstRabin = new long[first.length() - (int)sub + 1];
        long[] secondRabin = new long[second.length() - (int)sub + 1];
        long[] firstRabin2 = new long[first.length() - (int)sub + 1];
        long[] secondRabin2 = new long[second.length() - (int)sub + 1];
        int zhishu = 7;
        change(first, sub, firstRabin, zhishu);
        change(first, sub, secondRabin, zhishu);

        change(first, sub, firstRabin2, zhishu);
//        change(first, sub, firstRabin,7);
//        change(second, sub, secondRabin,7);
//        change(first, sub, firstRabin2,3);
//        change(second, sub, secondRabin2,3);

        Arrays.sort(secondRabin);
        Arrays.sort(secondRabin2);
        boolean point = false;
        for (long i : firstRabin){
            if (binarySearch(secondRabin, i) >= 0) {
                point = true;
                break;
            }
        }
        if (point){
            point = false;
            for (long i : firstRabin2){
                if (binarySearch(secondRabin2, i) >= 0) {
                    point = true;
                    break;
                }
            }
        }
        return point;
    }

    private static void change(String first, long sub, long[] firstRabin, int zhishu) {
        long index = zhishu;
        long index2 = pow(zhishu,sub - 1);
        for (int i = 0;i < firstRabin.length;i++){
            if (i == 0){
//                for (int j = i;j < i + sub;j++){
//                    Rabin[i] += first.charAt(j) * pow(zhishu,index);
//                    index -= 1;
//                }
                firstRabin[i] = first.charAt(i + (int)sub - 1);
                for (int j = i + (int)sub - 2;j >= 0;j--){
                    firstRabin[i] += first.charAt(j) * index;
                    index *= zhishu;
                }



            }
            else{
                firstRabin[i] = (firstRabin[i - 1] - first.charAt(i - 1) * index2) * zhishu + first.charAt(i + (int)sub - 1);
            }
        }
    }


//    private static void quickSort(int[] secondRabin,int low,int high){
//        while (low < high){
//            int p = position(secondRabin,low,high);
//            quickSort(secondRabin,low,p - 1);
//            quickSort(secondRabin,p + 1,high);
//        }
//
//
//    }
//
//    private static int position(int[] Array,int low,int high){
//        int pivot = Array[low];
//        int positions = low;
//        for (int i = low;i < high;i++){
//            if (Array[i] < pivot){
//
//            }
//        }
//
//
//        return 1;
//    }
//



    private static int binarySearch(long[] num , long key){
        int low = 0;
        int high = num.length - 1;
        int mid;
        while (low <= high){
            mid = (low + high) / 2;
            if (num[mid] == key){
                return mid;
            }
            else if (num[mid] < key)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }


//    private static void change(String first, long sub, long[] Rabin,long zhishu) {
//        long index = zhishu;
//        long index2 = pow(zhishu,sub - 1);
//        for (int i = 0;i < Rabin.length;i++){
//            if (i == 0){
////                for (int j = i;j < i + sub;j++){
////                    Rabin[i] += first.charAt(j) * pow(zhishu,index);
////                    index -= 1;
////                }
//                Rabin[i] = first.charAt(i + (int)sub - 1);
//                for (int j = i + (int)sub - 2;j >= 0;j--){
//                    Rabin[i] += first.charAt(j) * index;
//                    index *= zhishu;
//                }
//
//
//
//            }
//            else{
//                Rabin[i] = (Rabin[i - 1] - first.charAt(i - 1) * index2) * zhishu + first.charAt(i + (int)sub - 1);
//            }
//        }
//    }




    private static long pow(long num,long index){
        long sum = 1;
        for (int i = 0 ;i < index;i++){
            sum *= num;
        }
        return sum;
    }
}
