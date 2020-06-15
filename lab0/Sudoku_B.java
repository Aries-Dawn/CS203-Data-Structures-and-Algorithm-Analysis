package lab0;

import java.util.Scanner;

public class Sudoku_B {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[][] shuDu = new int[9][12];
        String [][] ss = new String[9][12];
        //Read Shu Ru

        for (int i = 0;i < ss.length;i++){
            for (int j = 0;j < ss[i].length;j++){
                ss[i][j] = sc.next();
            }
        }
        for (int i = 0;i < 9;i++){
            for (int j = 0;j < ss[i].length;j++){
                int num = (int)ss[i][j].charAt(0) - (int)'0';
                if (num == 72)
                    shuDu[i][j] = 0;
                else
                    shuDu[i][j] = num;
            }
        }

        boolean key = true;
        boolean is_Print = true;
        while (key){
            switch (finish(shuDu)){
                case 0:
                    key = false;
                    break;
                case 1:
                    key = true;
                    break;
                case 2:
                    key = false;
                    is_Print = false;
                    break;

            }
        }

        if (!is_Print){
            return;
        }

        //An Ge Shi Shu Chu
        for (int i = 0;i < shuDu.length;i++){
            System.out.print((char) (shuDu[i][0] + (int)'0'));
            for (int j = 1;j < shuDu[i].length;j++){
                System.out.print(" ");
                System.out.print((char) (shuDu[i][j] + (int)'0'));

            }
            System.out.println();
            if ((i + 1) % 3 == 0 && i != shuDu.length - 1){
                System.out.println();
            }
        }


    }


    private static int finish (int [][] num){
        boolean cont = true;
        int [] column = new int[9];
        int [] row = new int[9];

        //column
        int columnNum = -1;
        for (int i = 0;i < 12;i++){
            int [] nums = new int[10];
            int set = 0;
            if ((i + 1) % 4 == 0){
                continue;
            }
            columnNum += 1;
            for (int j = 0;j < num.length;j++){
                if (num[j][i] == 0){
                    column[columnNum] += 1;
                    set = j;
                }
            }
            if (column[columnNum] == 1){
                for (int j = 0;j < num.length;j++){
                    nums[num[j][i]] += 1;
                }
                for (int k = 1;k < nums.length;k++){
                    if (nums[k] == 0){
                        num[set][i] = k;
                        cont = false;
                    }
                }

            }
        }

        //row
        for (int i = 0;i < num.length;i++){
            int [] nums = new int[10];
            int set = 0;
            for (int j = 0;j < 12;j++){
                if (num[i][j] == 0){
                    row[i] += 1;
                    set = j;
                }
            }
            if (row[i] == 1){
                for (int j = 0;j < 12;j++){
                    if ((j + 1) % 4 == 0){
                        continue;
                    }
                    nums[num[i][j]] += 1;
                }

                for (int k = 1;k < nums.length;k++){
                    if (nums[k] == 0){
                        num[i][set] = k;
                        cont = false;
                    }
                }

            }
        }

        //block

        int begin_x = 0,begin_y = 0;
        int [] block = new int[9];
        int  setx = 0,sety = 0,count = 0;
        for (int i = 0; i < 3;i++){
            //every row_block
            for (int j = 0;j < 3;j++){
                //every block
                int[] nums = new int[10];
                for (int k = begin_y;k < begin_y + 3;k++){
                    for (int h = begin_x;h < begin_x + 3;h++){
                        if (num[k][h] == 0) {
                            block[count] += 1;
                            setx = h;
                            sety = k;
                        }
                        nums[num[k][h]] += 1;
                    }
                }
                if (block[count] == 1){
                    for (int temp = 1;temp < nums.length;temp++){
                        if (nums[temp] == 0){
                            num[sety][setx] = temp;
                            cont = false;
                        }
                    }
                }
                count += 1;
                begin_x = (begin_x + 4) % 12;
            }
            begin_y += 3;
        }

        begin_x = 0;begin_y = 0;count = 0;
        for (int i = 0; i < 3;i++){
            //every row_block
            for (int j = 0;j < 3;j++){
                //every block
                for (int k = begin_y;k < begin_y + 3;k++){
                    for (int h = begin_x;h < begin_x + 3;h++){
                        if (num[k][h] == 0) {
                            block[count] += 1;
                        }
                    }
                }
                count += 1;
                begin_x = (begin_x + 4) % 12;
            }
            begin_y += 3;
        }


//        if (check(column) == 0){
//            if (check(row) == 0){
//                return 0;
//            }
//            else if (cont){
//                System.out.println("The lab0.test data is incorrect!");
//                return 2;
//            }
//            else
//                return 1;
//        }
//        else if (cont){
//            System.out.println("The lab0.test data is incorrect!");
//            return 2;
//        }
//        else
//            return 1;

        if (check(column) == 0 && check(row) == 0 && check(block) == 0)
            return 0;
        else if (cont){
            System.out.println("The lab0.test data is incorrect!");
            return 2;
        }
        else
            return 1;
    }

    private static int check (int [] num){
        int count = 0;
        for (int i = 0;i < num.length;i++){
            if (num[i] !=0){
                count += 1;
            }
        }
        return count;
    }

}
