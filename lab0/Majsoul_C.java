package lab0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Majsoul_C {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        String[][] majiang = new String[cases][13];
//        String[] wan = new String[9];
//        String[] tong = new String[9];
//        String[] tiao = new String[9];
//        String[] wu = new String [13];
        for (int k = 0;k < cases;k++){
            ArrayList<Integer> wan = new ArrayList<>();
            ArrayList<Integer> tong = new ArrayList<>();
            ArrayList<Integer> tiao = new ArrayList<>();
            ArrayList<Integer> wu = new ArrayList<>();
            String[] all = new String[13];
            for (int i = 0;i < 13;i++){
                all[i] = sc.next();
                if (all[i].length() == 2){
                    switch (all[i].charAt(0)){
                        case 'W':
                            wan.add((int)all[i].charAt(1) - (int)'0');
                            break;
                        case 'T':
                            tiao.add((int)all[i].charAt(1) - (int)'0');
                            break;
                        case 'Y':
                            tong.add((int)all[i].charAt(1) - (int)'0');
                            break;
                    }

                }
                //Wx>Tx>Yx>lab0.E 7>S 6 >W  5>N 4>B 3>F 2>Z 1
                else {
                    switch (all[i]){
                        case "lab0.E":
                            wu.add(1);
                            break;
                        case "S":
                            wu.add(2);
                            break;
                        case "W":
                            wu.add(3);
                            break;
                        case "N":
                            wu.add(4);
                            break;
                        case "B":
                            wu.add(5);
                            break;
                        case "F":
                            wu.add(6);
                            break;
                        case "Z":
                            wu.add(7);
                            break;

                    }
                }
            }
//            Collections.sort(wan);
            Collections.sort(wan);
            Collections.sort(tiao);
            Collections.sort(tong);
            Collections.sort(wu);
            int count = 0;
            for (int j = 0;j < wan.size();j++){
                majiang[k][count] = "W" + wan.get(j);
                count++;
            }
            for (int j = 0;j < tiao.size();j++){
                majiang[k][count] = "T" + tiao.get(j);
                count++;
            }
            for (int j = 0;j < tong.size();j++){
                majiang[k][count] = "Y" + tong.get(j);
                count++;
            }
            //Wx>Tx>Yx>lab0.E 7>S 6 >W  5>N 4>B 3>F 2>Z 1
            for (int j = 0;j < wu.size();j++){
                switch (wu.get(j)){
                    case 7:
                        majiang[k][count] = "Z";
                        count++;
                        break;
                    case 6:
                        majiang[k][count] = "F";
                        count++;
                        break;
                    case 5:
                        majiang[k][count] = "B";
                        count++;
                        break;
                    case 4:
                        majiang[k][count] = "N";
                        count++;
                        break;
                    //Wx>Tx>Yx>lab0.E 7>S 6 >W  5>N 4>B 3>F 2>Z 1
                    case 3:
                        majiang[k][count] = "W";
                        count++;
                        break;
                    case 2:
                        majiang[k][count] = "S";
                        count++;
                        break;
                    case 1:
                        majiang[k][count] = "lab0.E";
                        count++;
                        break;
                }
            }
        }
        for (int i = 0;i < cases;i++){
            System.out.print(majiang[i][0]);
            for (int j = 1;j < majiang[i].length;j++){
                System.out.print(" ");
                System.out.print(majiang[i][j]);
            }
            System.out.println();
        }
    }
}
