package lab8;

import java.util.ArrayList;
import java.util.Scanner;

public class I_TheElves {

//    static int size = 0;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases > 0){
//            size = 0;
            int n,m;
            n = sc.nextInt();
            m = sc.nextInt();
            int[] a = new int[n + 1];
            int[] b = new int[n + 1];
//            size = n + 1;
            Node[] point = new Node[n + 1];
            ArrayList<Node>[] cun = new ArrayList[n + 1];
            for(int i = 1;i < n + 1;i++){
                a[i] = sc.nextInt();
                b[i] = sc.nextInt();
                point[i] = new Node(i);
            }
            point[0] = new Node(-1);
            for (int i = 0;i < n + 1;i++){
                cun[i] = new ArrayList<>();
            }
            for (int i = 0;i < m;i++){
                int first = sc.nextInt();
                int second = sc.nextInt();
                point[first].out++;
                point[second].in++;
                cun[first].add(point[second]);
            }

            long sum = 0;
            count(cun,point,a,b);
            for (Node t : point){
                sum =  (long)((sum + t.sum) % (1e9 + 7));
            }
            System.out.println(sum);
            cases --;
        }
    }

    public  static void count(ArrayList<Node>[] cun,Node[] point,int[] a,int[]b){
        Queue s = new Queue();
        for (int i = 0;i < point.length;i++){
            if(point[i].in == 0 && i > 0){
                s.enQueue(point[i]);
            }
        }
        Topological(s,cun,a,b);
    }

    public static void Topological(Queue s,ArrayList<Node>[] cun,int[] a,int[]b){
        while (!s.isEmpty()){
            Node temp = s.deQueue();
            temp.color = 2;
            for (Node te : cun[temp.value]){
                if (te.color == 0){
                    te.in--;
                    te.sum += a[temp.value] + temp.sum;
                    if (te.in == 0){
                        te.color = 1;
                        s.enQueue(te);
                    }
                }
            }
            temp.sum *= b[temp.value];
        }
//        while (!s.isEmpty()){
//            Node temp = s.deQueue();
//            temp.color = 2;
//            for (Node te : cun[temp.value]){
//                if (te.color == 0){
//                    te.in--;
//                    te.length += temp.length;
//                    if (te == y)
//                        return;
//                    if (te.in == 0){
//                        te.color = 1;
//                        s.enQueue(te);
//                    }
//                }
//            }
//        }

    }

    static class Queue{
        NodeOfQueue head = new NodeOfQueue(new Node(-1));
        NodeOfQueue tail = head;
        int size = 0;
        Queue(){

        }

        private void clear(){
            head = new NodeOfQueue(new Node(-1));
            tail = head;
        }
        private void enQueue(Node value){
            NodeOfQueue t = new NodeOfQueue(value);
            tail.next = t;
            tail = t;
            size ++;
        }

        private boolean isEmpty(){
            return head.next == null;
        }

        private Node deQueue(){
            if (!isEmpty()){
                NodeOfQueue temp = head.next;
                head = head.next;
                size--;
                return temp.value;
            }
            else
                return null;

        }
    }

    static class NodeOfQueue{
        Node value;
        NodeOfQueue next;

        NodeOfQueue(Node value){
            this.value = value;
        }
    }

    static class NodeOfLinkedList{
        Node value;
        NodeOfLinkedList next;

        NodeOfLinkedList(Node value){
            this.value = value;
        }
    }


    static class Node{
        int value;
        int color;
        int in;
        int out;
        long sum;
//        int[] sons = new int[size];
        int length = 0;
        Node next;

        Node(int value){
            this.value = value;
        }

        void copyFrom(Node oldNode){
            this.next = oldNode.next;
            this.value = oldNode.value;
            this.in = oldNode.in;
            this.out = oldNode.out;
            this.color = oldNode.color;

        }

        void copyTo(Node newOne){
            newOne.value = this.value;
            newOne.color = this.color;
            newOne.in = this.in;
            newOne.out = this.out;
            newOne.next = this.next;

        }
    }

    static class LinkedList{
        int size = 0;
        NodeOfLinkedList head = new NodeOfLinkedList(null);
        NodeOfLinkedList tail = head;

        void add(Node temp){
            NodeOfLinkedList value = new NodeOfLinkedList(temp);
            tail.next = value;
            tail = value;
            size ++;
        }

        void add(int value){
            NodeOfLinkedList temp = new NodeOfLinkedList(new Node(-1));
            tail.next = temp;
            tail = temp;
            size++;
        }

        Node remove(){
            NodeOfLinkedList temp = head.next;
            head = head.next;
            size--;
            return temp.value;

        }

        Node getI(int key){
            NodeOfLinkedList temp = getHead();
            for (int i = 0;i < key && temp.next != null;i++){
                temp = temp.next;
            }
            return temp.value;
        }

        NodeOfLinkedList getHead(){
            return head.next;
        }
        boolean isEmpty(){
            return head.next == null;
        }


    }

    static class Node2{
        int index;
        int value;

        Node2(int index,int value){
            this.index = index;
            this.value = value;
        }
    }
}
