package lab5;

public class tt {
    public static void main(String[] args){


        System.out.println(7/2*(1+0.9));


        char[] cArr = new char[10];
        System.out.println(cArr[0] + "!");
        int j = 0;
        for (int i = 0;i < 10;i++){
            j += j++;
            System.out.println(j);
        }

        System.out.println((int)'A');
        System.out.println('A' + 'B');
//        int num = -1,ss = 0;

//        while (1 <= num || ss <= 3){
//            System.out.println(12345);
//            num++;
//            ss++;
//        }

        int[] nums = new int[]{1,2,0,4,5,7,9};

        for (int i = 0;i < 6 && nums[i] != 0;i++){
            System.out.println(nums[i]);
        }
    }

    private static int pow(int num,int index){
        int sum = 1;
        for (int i = 0 ;i < index;i++){
            sum *= num;
        }
        return sum;
    }
}
