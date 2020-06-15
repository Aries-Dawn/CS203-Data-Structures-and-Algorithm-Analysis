package lab4;

import java.util.Scanner;

public class D_ExcitingSpider {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        Stack stack = new Stack();
        while (cases > 0){
            int target = 1;
            int size = sc.nextInt();
            int count = 0;
            boolean[] contains = new boolean[size + 1];
            StringBuilder sb = new StringBuilder();
            flags:
            for (int i = 0;i < size;i++){
                int temp = sc.nextInt();

                stack.push(temp);
                contains[temp] = true;
                while (true){
                    while (!stack.isEmpty() && (stack.peak() == target || stack.peak() < target)){
                        int pop = stack.pop();
                        contains[pop] = false;
                        sb.append(pop);
//                        System.out.print(pop);
                        if (count != size - 1 ){
//                            System.out.print(" ");
                            sb.append(" ");
                        }
                        count++;
                        target += 1;
                    }
                    if (!stack.isEmpty() && !contains[target]){
                        continue flags;
                    }
                    while (!stack.isEmpty() && contains[target]){
                        target += 1;
                        break;
                    }
                    if (stack.isEmpty())
                        break;
                }
            }


            System.out.println(sb.toString());
            cases--;
            stack.clear();
        }





    }


    static class Node{
        int value;
        Node pre;
        Node next;

        private Node(int value){
            this.value = value;
        }

    }


    static class Stack{
        Node head = new Node(-1);
        Node tail = head;

        private void push(int value){
            Node temp = new Node(value);
            tail.next = temp;
            temp.pre = tail;
            tail = temp;

        }

        private int pop (){
            int temp = tail.value;
            tail = tail.pre;
            tail.next = null;
            return temp;
        }

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
