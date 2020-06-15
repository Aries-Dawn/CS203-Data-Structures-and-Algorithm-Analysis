package lab2;

import java.util.Arrays;
import java.util.Scanner;

public class E_ExcellentPower {
//    public static void main(String [] args){
//        while (true)
//            cc();
//    }


    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int n,p,q;

        n = sc.nextInt();
        p = sc.nextInt();
        q = sc.nextInt();
        long multiply = 1;
        long sum = 0;
        for (int i = 0;i < p;i++){
            multiply *= 2L;
        }
        long[][] data = new long[n][4];
        for (int i = 0;i < n;i++){
            data[i][0] = sc.nextLong();
            data[i][1] = sc.nextLong();
//            data[i][2] = multiply * data[i][0] -  data[i][1];
            data[i][3] = data[i][0] -  data[i][1];
        }

        if (q != 0){
            mergeSort(data,n,3);
            int size = q;
            long loss = 0;
            for (int i = 0;i < size ;i++){
                if (n - 1 - i >= 0 && data[n - 1 - i][3] > 0){
                    q--;
                    data[n - 1 - i][1] = data[n - 1 - i][0];
                    if (q == 0){
                        loss = data[n - 1 - i][3];
                    }
                }
                else
                    break;
            }
            for (int i = n - 1;i >= n - (size - q);i--){
                data[i][2] = multiply * data[i][0] -  data[i][1];
            }
            for (int i = n - 1 - (size - q);i >= 0;i--){
                data[i][2] = multiply * data[i][0] -  data[i][1] - loss;
            }

            int begin = 0;
            int index = begin;
            long max = data[begin][2];
            while (begin < data.length){
                if (data[begin][2] > max){
                    max = data[begin][2];
                    index = begin;
                }
                begin += 1;
            }
            if (max > 0)
                data[index][1] = data[index][2] + data[index][1];

//
//
//            if (data[n - 1][2] > 0){
//                if (n - 1 == 0 || data[n - 1][2] > data[n - 2][2]){
//                    data[n - 1][0] = data[n - 1][2] + data[n - 1][1];
//                    data[n - 1][1] = data[n - 1][0];
//                    data[n - 1][3] = 0;
//
//                }
//                else{
////                    int begin = n - 1;
////                    int index = begin;
////                    long min = data[begin][3];
////                    while (begin > 0 && data[begin][2] == data[begin - 1][2]){
////                        if (data[begin - 1][3] < min){
////                            min = data[begin - 1][3];
////                            index = begin - 1;
////                        }
////                        begin -= 1;
////                    }
////                    data[index][0] = data[index][2] + data[index][1];
////                    data[index][1] = data[index][0];
////                    data[index][3] = 0;
//                }
//            }
//            mergeSort(data,n,3);
//            for (int i = 0;i < q - 1;i++){
//                if (n - 1 - i >= 0 && data[n - 1 - i][3] > 0){
//                    data[n - 1 - i][1] = data[n - 1 - i][0];
//                }
//                else
//                    break;
//            }
        }
        for (long[] datum : data) {
            sum += datum[1];
        }
        System.out.println(sum);

    }


    private static void mergeSort(long[][] data, long n, int key){

        if (n > 1){
            long[][] A,B;
            A = Arrays.copyOfRange(data,0, (int) (n / 2));
            B = Arrays.copyOfRange(data,(int)n / 2,(int)n);
            mergeSort(A,n / 2,key);
            mergeSort(B,n - n / 2,key);
            long[][] nums = merge(A,(int)n / 2,B, (int) ((int)n - n / 2),key);
            int k = 0;
            for (long[] num : nums) {
                data[k] = num.clone();
                k += 1;
            }

        }

    }

    private static long[][] merge(long[][] A,int A_size, long[][] B, int B_size, int key){
        long[][] num = new long[A_size + B_size][4];
        int i = 0,j = 0;
        for (int k = 0;k < num.length;k++){
            if (i < A.length && j < B.length && A[i][key] <= B[j][key]){
                num[k] = A[i].clone();
                i += 1;
            }
            else if (i < A.length && j < B.length && A[i][key] > B[j][key]){
                num[k] = B[j].clone();
                j += 1;
            }
            else if (i < A.length || j < B.length){
                if (i >= A.length){
                    num[k] = B[j].clone();
                    j += 1;
                }
                else if (j >= B.length){
                    num[k] = A[i].clone();
                    i += 1;
                }

            }
        }
        return num;
    }
}
