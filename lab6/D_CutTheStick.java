package lab6;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class D_CutTheStick {
    private static int sum;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases > 0){
            sum = 0;
            Queue<Integer> num = new PriorityQueue<>();
            int nums = sc.nextInt();
            for (int i = 0;i < nums;i++){
                num.add(sc.nextInt());
            }
            addAll(num);
            System.out.println(sum);

            cases--;
        }

    }

    private static void addAll(Queue<Integer> num){
        int first,second,sums;
        if (!num.isEmpty()){
            first = num.poll();
            if (!num.isEmpty()){
                second = num.poll();
                sums = first + second;
                sum += sums;
                num.add(sums);
                addAll(num);
            }
        }
    }

//
//
//    static class Node{
//        int value;
//        Node next;
//        Node pre;
//
//        public Node(int value){
//            this.value = value;
//        }
//
//    }
}
