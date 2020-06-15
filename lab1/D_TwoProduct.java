package lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class D_TwoProduct {
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        int size = sc.nextInt();
        int target = sc.nextInt();
        long count = 0;
        long numOf0 = 0;
        ArrayList<Long> num = new ArrayList<>();
        long[] nums = new long[size];
        for (int i = 0;i < size;i++){
            nums[i] = sc.nextLong();
            if (i == 0 && nums[i] != 0)
                num.add(nums[i]);
            if (i > 0 && nums[i - 1] != nums[i] && nums[i] != 0){
                num.add(nums[i]);
            }
            if (nums[i] == 0)
                numOf0 += 1;
        }

        if (size !=1){
            if (target != 0){
                int i = 0;
                int begin = 1;
                int index;
                long key;
                while (i <= size - 1){
                    index = -1;
                    if (nums[i] != 0 && (double)(target / (int)nums[i]) == ((double)target / nums[i])){
                        key = target / nums[i];
                        index = Arrays.binarySearch(nums,begin,size,key);
                    }
                    if (index > 0){
                        count += 1;
                    }
                    while (true){
                        if (i >= size - 2){
                            break;
                        }
                        if (nums[i] == nums[i + 1]){
                            i += 1;
                        }
                        else
                            break;
                    }
                    i += 1;
                    if (i >= size - 2){
                        break;
                    }
                    begin = i + 1;
                }
            }
            else{
                if (numOf0 > 1)
                    count = num.size() + 1;
                else if (numOf0 > 0)
                    count = num.size();
            }
        }

        System.out.println(count);

    }
}
