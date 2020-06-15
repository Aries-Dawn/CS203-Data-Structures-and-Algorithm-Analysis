package lab2;

import java.util.Arrays;
import java.util.Scanner;

public class F_YYJMagicBeads {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0;i < cases;i++){
            int size = sc.nextInt();
            int[][] beads = new int[size][3];
            for (int[] temp :beads){
                temp[0] = sc.nextInt();
                temp[1] = sc.nextInt();
                temp[2] = temp[0] - temp[1];
            }
            mergeSort(beads,size,2);

            int left = 0,right = 0;
            for (int j = 0;j < size;j++){
                if (beads[j][2] == 0){
                    left = j;
                    break;
                }
            }
            for (int j = size - 1;j >= 0;j--){
                if (beads[j][2] == 0){
                    left = j;
                    break;
                }
            }
            int[][] leftBeads,rightBeads;
            if (left > 0){
                leftBeads = Arrays.copyOfRange(beads,0,left);
                mergeSort(leftBeads,leftBeads.length,0);
                for (int j = 0;j < leftBeads.length;j++){
                    beads[j] = leftBeads[j].clone();
                }
            }

            if (right > 0){
                rightBeads = Arrays.copyOfRange(beads,right + 1,size);
                mergeSort(rightBeads,rightBeads.length,1);
                int index = size - 1;
                for (int j = 0;j < rightBeads.length;j++){
                    beads[index] = rightBeads[j].clone();
                    index -= 1;
                }
            }
            int sum = 0;

            for (int j = 0;j < size - 1;j++){
                sum += Math.min(beads[j][1],beads[j + 1][0]);
                if (beads[j][1] > beads[j + 1][0])
                    beads[j + 1][1] += beads[j][1] - beads[j + 1][0];
                else
                    beads[j][0] += beads[j + 1][0] - beads[j][1];
            }

            System.out.println(sum);

        }


    }

    private static void mergeSort(int[][] data, int n, int key){

        if (n > 1){
            int[][] A,B;
            A = Arrays.copyOfRange(data,0, (int) (n / 2));
            B = Arrays.copyOfRange(data,(int)n / 2,(int)n);
            mergeSort(A,n / 2,key);
            mergeSort(B,n - n / 2,key);
            int[][] nums = merge(A,(int)n / 2,B, (int) ((int)n - n / 2),key);
            int k = 0;
            for (int[] num : nums) {
                data[k] = num.clone();
                k += 1;
            }

        }

    }

    private static int[][] merge(int[][] A,int A_size, int[][] B, int B_size, int key){
        int[][] num = new int[A_size + B_size][4];
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
