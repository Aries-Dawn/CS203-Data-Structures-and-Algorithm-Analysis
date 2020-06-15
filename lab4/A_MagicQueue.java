package lab4;

import java.util.Scanner;

public class A_MagicQueue {

    private static class Node{
        int value;
        Node pre;
        Node next;

        private Node(int value){
            this.value = value;
        }

    }

    public static class Queue{
        Node head = new Node(-1);
        Node tail = head;
        int size = 0;

        private void enQueue(int value){
            Node adder = new Node(value);
            tail.next = adder;
            adder.pre = tail;
            tail = adder;
            size += 1;
        }

        private int deQueue(){
            int reNum =  head.next.value;
            head = head.next;
            return reNum;

        }

        private boolean isEmpty(){
            return head.next == null;
        }

        private int getFront(){
            return head.next.value;
        }

        private void clear(){
            head.next = null;
            head.pre = null;
            tail = head;
        }

    }

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        Queue queue = new Queue();
        int number = sc.nextInt();
        while (number > 0){
            String operate = sc.next();
            switch (operate.charAt(0)){
                case 'E':
                    int adder = sc.nextInt();
                    queue.enQueue(adder);
                    break;
                case 'D':
                    if (!queue.isEmpty()){
                        queue.deQueue();
                    }
                    break;
                case 'A':
                    if (!queue.isEmpty()){
                        System.out.println(queue.getFront());
                    }
                    break;

            }
            number--;
        }
        while (!queue.isEmpty()){
            System.out.print(queue.deQueue());
            if (!queue.isEmpty()){
                System.out.print(" ");
            }
        }
    }

}
