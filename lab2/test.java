package lab2;

import java.util.Random;

public class test {
    static C_Only3sum c_only3sum = new C_Only3sum();
    public static void main(String [] rags){
        C_Only3sum c_only3sum = new C_Only3sum();
        Random random = new Random();
        for (int i = 0;i < 10;i++) {
            int n = random.nextInt(100000);
            System.out.println(n);
        }
//        Scanner sc = new Scanner(System.in);
//        int n,m;
//        n = sc.nextInt();
//        m = sc.nextInt();
//        int[] num = new int[n];
//        ArrayList<Integer> nums = new ArrayList<>();
//        for (int i = 0;i < n;i++){
//            nums.add(sc.nextInt());
//        }
//        Collections.shuffle(nums);
//        for (int i  = 0;i <  nums.size();i++){
//            num[i] = nums.get(i);
//        }
//        lab2.C_Only3sum.quickSort(num,0,n - 1);
//        for (int i : num){
//            System.out.println(i);
//        }
    }
}
