package lab4;

import java.util.ArrayList;
import java.util.Scanner;

public class C_MagicSequences {

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> big = new ArrayList<>();
        int magic = sc.nextInt();
        Queue queue = new Queue();
        int input = 0,situation = 1,sum = 0;
        input = sc.nextInt();
        queue.enQueue(input,1);
        while (input != -1){
            if (!queue.isEmpty() && input < queue.getEndValue())
                queue.enQueue(input,situation);
            else {
                while (!queue.isEmpty() && input >= queue.getEndValue())
                    queue.move();
                queue.enQueue(input,situation);
            }
            if (situation <= queue.getSituation() + magic -1){
                if (situation >= 5 && sum == -1) {
                    big.add(queue.getFront());
                    sum = queue.getFront();
                }
                else if (situation >= magic) {
                    big.add(queue.getFront());
                    sum = sum ^ queue.getFront();
                }
            }
            else{
                queue.deQueue();
                big.add(queue.getFront());
                sum = sum ^ queue.getFront();
            }
            input = sc.nextInt();
            situation += 1;
        }
        System.out.println(sum);



    }




    private static class Node{
        int value;
        int situation;
        Node pre;
        Node next;

        private Node(int value,int situation){
            this.value = value;
            this.situation = situation;
        }

    }

    public static class Queue{
        Node head = new Node(-1,0);
        Node tail = head;
        int size = 0;

        private void enQueue(int value,int situation){
            Node adder = new Node(value,situation);
            tail.next = adder;
            adder.pre = tail;
            tail = adder;
            size += 1;
        }

        private void deQueue(){
            int reNum =  head.next.value;
            head = head.next;
//            return reNum;

        }

        private boolean isEmpty(){
            return head.next == null;
        }

        private int getFront(){
            return head.next.value;
        }

        private int getEndValue(){
            return tail.value;
        }

        private void move(){
            tail = tail.pre;
            tail.next = null;

        }

        private int getSituation(){
            return head.next.situation;
        }

        private void clear(){
            head.next = null;
            head.pre = null;
            tail = head;
        }

    }



}
