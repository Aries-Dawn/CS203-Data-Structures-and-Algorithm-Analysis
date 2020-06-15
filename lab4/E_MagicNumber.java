package lab4;

import java.util.Scanner;

public class E_MagicNumber {

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        Stack stack = new Stack();
        for (int k = 1;k <= cases;k++){
            int size = sc.nextInt();
            int[] students = new int[size];
            int[][] partner = new int[size][2];

            for (int i = 0;i < size;i++){
                students[i] = sc.nextInt();
            }

            for (int i = 0;i < size;i++){
                if (i == 0 || students[i] < stack.peak()){
                    stack.push(students[i],i);
                }
                else {
                    while (!stack.isEmpty() && students[i] > stack.peak()){
                        int situation = stack.pop();
                        if (!stack.isEmpty()){
                            partner[stack.getPeakSituation()][1] = situation + 1;

                        }
                    }
                    stack.push(students[i],i);
                }
            }
            while (!stack.isEmpty()){
                int situation = stack.pop();
                if (!stack.isEmpty()){
                    partner[stack.getPeakSituation()][1] = situation + 1;
                }
            }
            stack.clear();

            for (int i = size - 1;i >= 0;i--){
                if (i == size - 1 || students[i] < stack.peak()){
                    stack.push(students[i],i);
                }
                else {
                    while (!stack.isEmpty() && students[i] > stack.peak()){
                        int situation = stack.pop();
                        if (!stack.isEmpty()){
                            partner[stack.getPeakSituation()][0] = situation + 1;

                        }
                    }
                    stack.push(students[i],i);
                }
            }
            while (!stack.isEmpty()){
                int situation = stack.pop();
                if (!stack.isEmpty()){
                    partner[stack.getPeakSituation()][0] = situation + 1;
                }
            }
            stack.clear();

//            int size = sc.nextInt();
//            int[] students = new int[size];
//            int[][] partner = new int[size][2];
//            for (int i = 0;i < size;i++){
//                students[i] = sc.nextInt();
//                if (i == 0 || students[i] < stack.peak()){
//                    stack.push(students[i],i);
//                }
//                else {
//                    partner[stack.getPeakSituation()][1] = i;
//                    while (students[i] >= stack.peak()){
//                        if (stack.isEmpty()){
//                            partner[0][1] = 0;
//                        }
//                        else {
//                            int value = stack.pop();
//                            if (!stack.isEmpty()) {
//                                partner[stack.getPeakSituation()][1] = value;
//                            }
//                        }
//                    }
//                    stack.push(students[i],i);
//                }
//            }
//            partner[stack.getPeakSituation()][1] = 0;
//            while (!stack.isEmpty()){
//                int value = stack.pop();
//                if (!stack.isEmpty()){
//                    partner[stack.getPeakSituation()][1] = value;
//                }
//            }
//            stack.clear();
//            for (int i = size - 1;i >= 0;i--){
//                if (i == size - 1 || students[i] < stack.peak()){
//                    stack.push(students[i],i);
//                }
//                else {
//                    partner[stack.getPeakSituation()][0] = i;
//                    while (students[i] >= stack.peak()){
//                        if (stack.isEmpty()){
//                            partner[size - 1][0] = 0;
//                        }
//                        else {
//                            int value = stack.pop();
//                            if (!stack.isEmpty()) {
//                                partner[stack.getPeakSituation()][0] = value;
//                            }
//                        }
//                    }
//                    stack.push(students[i],i);
//                }
//            }
//            partner[stack.getPeakSituation()][0] = 0;
//            while (!stack.isEmpty()){
//                int value = stack.pop();
//                if (!stack.isEmpty()){
//                    partner[stack.getPeakSituation()][0] = value;
//                }
//            }
            System.out.println("Case " + k + ":");
            for (int[] temp : partner)
                System.out.println(temp[0] + " " + temp[1]);

        }







    }


    static class Node{
        int value;
        int situation;
        Node pre;
        Node next;

        private Node(int value,int situation){
            this.value = value;
            this.situation = situation;
        }

    }


    static class Stack{
        Node head = new Node(-1,-1);
        Node tail = head;

        private void push(int value,int situation){
            Node temp = new Node(value,situation);
            tail.next = temp;
            temp.pre = tail;
            tail = temp;

        }

        private int pop (){
            int temp = tail.situation;
            tail = tail.pre;
            tail.next = null;
            return temp;
        }

        private int getPeakSituation(){
            return tail.situation;
        }
        private  Node getPeak(){return tail;}
        private int peak(){
            return tail.value;
        }

        private boolean isEmpty(){
            return head.next == null;
        }

        private void clear(){
            head.value = -1;
            head.next = null;
            head.pre = null;
            tail = head;
        }



    }



}
