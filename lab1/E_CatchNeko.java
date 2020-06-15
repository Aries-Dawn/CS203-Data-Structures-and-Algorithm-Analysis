package lab1;

import java.util.Scanner;

public class E_CatchNeko {
    public static void main(String[] args ){
        Scanner sc = new Scanner(System.in);
        long[] eve = new long[2];
        //0---x;1---y
        long[] neko = new long[2];
        //0---x;1---y
        eve[0] = sc.nextLong();
        eve[1] = sc.nextLong();
        neko[0] = sc.nextLong();
        neko[1] = sc.nextLong();
        int sequence = sc.nextInt();
        String direction = sc.next();
        long[] sequenceMoves = allMove(direction);
        long all_Move,x_Move1 = neko[0] - eve[0],y_Move1 = neko[1] - eve[1];
        long low = 0,high = 1000000000000000L,middle = (low + high) / 2;
        long steps,remainSteps;
        if (x_Move1 < 0)
            x_Move1 *= -1;
        if (y_Move1 < 0)
            y_Move1 *= -1;
        if ( x_Move1 + y_Move1 <= high){
            while (low <= high){
                x_Move1 = neko[0] - eve[0];
                y_Move1 = neko[1] - eve[1];
                long[] moveStep = new long[2];
                middle = (low + high) / 2;
                steps = middle / sequence;
                remainSteps = middle % sequence;
                moveStep[0] += sequenceMoves[0] * steps;
                moveStep[1] += sequenceMoves[1] * steps;
                for (int i = 0;i < remainSteps;i++){
                    move(moveStep,direction.charAt(i));
                }
                x_Move1 += moveStep[0];
                y_Move1 += moveStep[1];
                if (x_Move1 < 0)
                    x_Move1 *= -1;
                if (y_Move1 < 0)
                    y_Move1 *= -1;
                all_Move = x_Move1 + y_Move1;
                if (all_Move > middle)
                    low = middle + 1;
                else if (all_Move < middle)
                    high = middle - 1;
                else
                    break;
            }
            System.out.println(middle);
        }
        else
            System.out.println(-1);

    }

    private static long[] allMove(String direction){
        long[] x_y = new long[2];
        for (int i = 0;i < direction.length();i++){
            switch (direction.charAt(i)){
                //U,D,L,R
                case 'U':
                    x_y[1] += 1;
                    break;
                case 'D':
                    x_y[1] -= 1;
                    break;
                case 'L':
                    x_y[0] -= 1;
                    break;
                case 'R':
                    x_y[0] += 1;
                    break;
            }
        }
        return x_y;
    }

    private static void move(long[] x_y, char direction){
        switch (direction){
            //U,D,L,R
            case 'U':
                x_y[1] += 1;
                break;
            case 'D':
                x_y[1] -= 1;
                break;
            case 'L':
                x_y[0] -= 1;
                break;
            case 'R':
                x_y[0] += 1;
                break;
        }
    }

}
