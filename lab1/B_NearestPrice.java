package lab1;

import java.util.Scanner;

public class B_NearestPrice {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int days = sc.nextInt();
        int types = sc.nextInt();
        int[] daysMoney = new int[days];
        int [] typesPrice = new int[types];
        for (int i = 0;i < days;i++){
            daysMoney[i] = sc.nextInt();
        }
        for (int i = 0;i < types;i++){
            typesPrice[i] = sc.nextInt();
        }
        for (int i = 0;i < days;i++){
            int money = daysMoney[i];
            int low = 0;
            int high = typesPrice.length - 1;
            int middle = 0;
            if (money < typesPrice[low] || low > high){
                System.out.println(money);
                continue;
            }
            if (money > typesPrice[high]){
                money -= typesPrice[high];
                System.out.println(money);
                continue;
            }
            while (low <= high){
                middle = (low + high) / 2;
                if (money > typesPrice[middle]){
                    low = middle + 1;
                }
                else if (money < typesPrice[middle]){
                    high = middle - 1;
                }
                else
                    break;
            }
            if (money == typesPrice[middle]){
                money -= typesPrice[middle];
            }
            else if (money > typesPrice[middle]){
                money -= typesPrice[middle];
            }
            else
                money -= typesPrice[middle - 1];


            if (money == 0){
                System.out.println( "Meow");
            }
            else
                System.out.println(money);
        }

    }
}

