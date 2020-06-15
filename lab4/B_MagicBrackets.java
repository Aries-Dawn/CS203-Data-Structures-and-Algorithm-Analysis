package lab4;

import java.util.Scanner;

public class B_MagicBrackets {

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        Queue queue = new Queue();
        flags:
        while (cases > 0){
            int length = sc.nextInt();
            String brackets = sc.next();
            for (int i = 0;i < brackets.length();i++){
                if (brackets.charAt(i) == '(' || brackets.charAt(i) == '[' || brackets.charAt(i) == '{')
                    queue.enQueue(brackets.charAt(i));
                else{
                    switch (brackets.charAt(i)){
                        case ')':
                            if (!queue.isEmpty() && queue.getFront() == '(')
                                queue.deQueue();
                            else{
                                System.out.println("NO");
                                cases--;
                                queue.clear();
                                continue flags;
                            }
                            break;
                        case ']':
                            if (!queue.isEmpty() && queue.getFront() == '[')
                                queue.deQueue();
                            else {
                                System.out.println("NO");
                                cases--;
                                queue.clear();
                                continue flags;
                            }
                            break;
                        case '}':
                            if (!queue.isEmpty() && queue.getFront() == '{')
                                queue.deQueue();
                            else{
                                System.out.println("NO");
                                cases--;
                                queue.clear();
                                continue flags;
                            }
                            break;
                    }
                }
            }
            if (queue.isEmpty())
                System.out.println("YES");
            else
                System.out.println("NO");
            queue.clear();
            cases--;
        }



    }



    private static class Node{
        char value;
        Node pre;
        Node next;

        private Node(char value){
            this.value = value;
        }

    }

    public static class Queue{
        Node head = new Node('0');
        Node tail = head;
        int size = 0;

        private void enQueue(char value){
            Node adder = new Node(value);
            tail.next = adder;
            adder.pre = tail;
            tail = adder;
            size += 1;
        }

        private char deQueue(){
            char reNum =  tail.value;
            tail = tail.pre;
            tail.next = null;
            return reNum;

        }

        private boolean isEmpty(){
            return head.next == null;
        }

        private char getFront(){
            return tail.value;
        }

        private void clear(){
            head.next = null;
            head.pre = null;
            head.value = '0';
            tail = head;
        }

    }



}
