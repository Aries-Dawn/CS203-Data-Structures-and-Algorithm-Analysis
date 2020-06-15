package lab2;

import java.util.*;

public class C_Only3sum {

//    public static void main(String [] rags){
//        int count = 0;
//        while (count < 10000){
//            try {
//                CC();
//            }
//            catch (Exception e){
//                System.out.println(e.toString());
//            }
//            count += 1;
//        }
//    }
    public static void main(String [] rags){
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int n,m;
//        n = random.nextInt(10000);
//        m = random.nextInt(10000);
        n = sc.nextInt();
        m = sc.nextInt();
        int[] num = new int[n];
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0;i < n;i++){
            nums.add(sc.nextInt());
//            nums.add(random.nextInt(10000));
        }
        Collections.shuffle(nums);
        for (int i  = 0;i <  nums.size();i++){
            num[i] = nums.get(i);
        }
        quickSort(num,0,n - 1);
        int index = 0;
        for (int value : num) {
            if (value <= m - 2)
                index += 1;
            else
                break;
        }
        int[] newNum;
        newNum = Arrays.copyOfRange(num,0,index);

        long sum = 0;
        for (int i = 0;i < newNum.length - 2 && newNum[i] < m;i++){
            int first = newNum[i];
            int firstRemain = m - first;
            int low,high;
            low = i + 1;
            high = newNum.length - 1;
            while (low < high){
                long same = 1;
                if (newNum[low] + newNum[high] == firstRemain){
                    if (newNum[low] == newNum[high]){
                        int in = high - low + 1;
                        sum += (long) in * ((long)in - 1) / 2;
                        break;
                    }
                    else {
                        sum += 1;
                        while (low < high && newNum[high - 1] == newNum[high]){
                            high -= 1;
                            same += 1;
                            if (low < high)
                                sum += 1;
                        }
                        while (low < high && newNum[low + 1] == newNum[low]){
                            low += 1;
                            if (low < high)
                                sum += same;
                        }
                        high -= 1;
                        low += 1;
                    }
                }
                else
                if (newNum[low] + newNum[high] < firstRemain)
                    low += 1;
                else
                    high -= 1;
            }
        }
        System.out.println(sum);

    }


    private static long C(int n){
        //n为总共多少数字
        //m为选多少个数字
        return n * (n - 1) / 2;
    }

    private static long jieChen(int n,int m){
        long sum = 1;
        for (int i = 1;i <= n;i++){
            sum *= i;
        }
        return sum;
    }


     private static void quickSort(int[] num, int low, int high){
        if (low < high){
            int p = position(num,low,high);
            quickSort(num,low,p - 1);
            quickSort(num,p + 1,high);
        }
    }

     private static int position(int[] num, int low, int high){
        int positions = low;
        int positionNum = num[low];
        while (low < high){
            while (low < high && num[high] >= positionNum)
                high--;
            while (low < high && num[low] <= positionNum)
                low++;
            swap(num,low,high);
        }
        swap(num,positions,low);
        return low;
    }

    private static void swap(int[] num, int low, int high){
        int temp = num[low];
        num[low] = num[high];
        num[high] = temp;
    }
}
