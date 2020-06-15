package lab4;

import java.util.Scanner;

public class C5 {
    public static void main(String args[]){
        new C5().run();
    }
    private void run(){
        Scanner sc=new Scanner(System.in);
        int length=sc.nextInt();
        int input;
        int count=1;
        int result=0;
        Stack target=new Stack();
        while (true){
            input=sc.nextInt();
            if(input==-1) {
                break;
            }
            while(target.getTop().value<input&&target.getTop().index!=-1){
                target.pop();
            }
            target.push(input,count);
            if(target.getFirst().index<=count-length){
                target.removeFirst();
            }
            if(count>=length) {
                result ^= target.getFirst().value;
            }

            count++;
        }
        System.out.println(result);
    }
    class Stack {
        private Node first;
        private Node top;
        private Stack(){
            this.first=new Node(-1,-1,null);
            top=first;
        }
        private void push(int in,int index){
            Node node=new Node(index,in,top);
            top.next=node;
            top=node;
        }
        private Node getTop(){
            return top;
        }
        private void pop(){
            if(top!=first) {
                top = top.former;
                top.next.former = null;
                top.next = null;
            }
        }
        private Node getFirst(){
            return first.next;
        }
        private void removeFirst(){
            first.next=first.next.next;
            first.next.former=first;
        }
    }
    private class Node{
        int index;
        int value;
        Node next;
        Node former;
        private Node(int index,int value,Node former){
            this.index=index;
            this.value=value;
            this.former=former;
        }
    }
}
