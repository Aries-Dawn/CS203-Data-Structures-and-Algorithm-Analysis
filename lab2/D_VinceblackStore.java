package lab2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class D_VinceblackStore {
    public static void main(String [] rags){
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        long[] num = new long[size];
        long sum = 0;
        Random random = new Random();
        for (int i = 0;i < num.length;i++){
//            num[i] = random.nextlong(1000);
            num[i] = sc.nextLong();
        }
        sum += mergeSort(num,size);
//        for (long i:num) {
//            System.out.prlongln(i);
//        }
        System.out.println(sum);

    }

    static long mergeSort(long[] num,long n){
        long sum = 0;
        if (n > 1){
            long[] A,B;
            A = Arrays.copyOfRange(num,0, (int) (n / 2));
            B = Arrays.copyOfRange(num,(int)n / 2,(int)n);
            sum += mergeSort(A,n / 2);
            sum += mergeSort(B,n - n / 2);
            long[] nums = merge(A,(int)n / 2,B, (int) ((int)n - n / 2));
            int k = 0;
            for (int i = 1;i < nums.length;i++){
                num[k] = nums[i];
                k += 1;
            }
            sum += nums[0];

        }

        return sum;
    }

    static long[] merge(long[] A,int A_size,long[] B,int B_size){
        long sum = 0;
        long[] num = new long[A_size + B_size + 1];
        int i = 0,j = 0;
        for (int k = 1;k < num.length;k++){
            if (i < A.length && j < B.length && A[i] <= B[j]){
                num[k] = A[i];
                i += 1;
            }
            else if (i < A.length && j < B.length && A[i] > B[j]){
                num[k] = B[j];
                for (int flag1 = i;flag1 < A.length;flag1++){
                    sum += A[flag1] + B[j];
                }
                j += 1;
            }
            else if (i < A.length || j < B.length){
                if (i >= A.length){
                    if (j < B.length){
                        num[k] = B[j];
                        j += 1;
                    }
                }
                else if (j >= B.length){
                    num[k] = A[i];
                    i += 1;
                }

            }
        }
        num[0] = sum;
        return num;
    }
}
