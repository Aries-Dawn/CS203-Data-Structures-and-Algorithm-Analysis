package lab8;

import java.util.ArrayList;
import java.util.Scanner;

public class D_DefensiveTower {
    
    static int countOfWhite = 0;
    static int countOfBlack = 0;
    static ArrayList<Node> White = new ArrayList<>();
    static ArrayList<Node> Black = new ArrayList<>();

    
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases > 0){

            int n = sc.nextInt();
            int m = sc.nextInt();
            LinkedList[] cun = new LinkedList[n + 1];
            Node[] point = new Node[n + 1];
            for (int i = 0;i < n + 1;i++){
                cun[i] = new LinkedList();
                point[i] = new Node(i);
            }
            for (int i = 0;i < m;i++){
                int first = sc.nextInt();
                int second = sc.nextInt();
                cun[first].add(point[second]);
                cun[second].add(point[first]);
            }

            int max = 0;
            int index = 0;
            for (int i = 1;i < cun.length;i++){
                LinkedList l = cun[i];
                if (l.size > max){
                    max = l.size;
                    index = i;
                }
            }
            countOfBlack = 0;
            countOfWhite = 0;
            White = new ArrayList<>();
            Black = new ArrayList<>();
            BFS(point[index],cun);
//            BFS(point[random.nextInt(point.length - 1) + 1],cun);
//            while (Math.min(countOfWhite,countOfBlack) > n / 2){
//                countOfBlack = 0;
//                countOfWhite = 0;
//                White = new ArrayList<>();
//                Black = new ArrayList<>();
//                BFS(point[random.nextInt(point.length - 1) + 1],cun);
//            }
            if (countOfWhite <= countOfBlack){
                System.out.println(countOfWhite);
                for (int i = 0;i < White.size();i++){
                    Node t = White.get(i);
                    System.out.print(t.value);
                    if (i != White.size() - 1){
                        System.out.print(" ");
                    }

                }
            }
            else {
                System.out.println(countOfBlack);
                for (int i = 0;i < Black.size();i++){
                    Node t = Black.get(i);
                    System.out.print(t.value);
                    if (i != Black.size() - 1){
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
            cases--;
        }

    }
    
    public static void BFS(Node root,LinkedList[] cun){
        int color = 1;
        LinkedList[] cun2 = cun.clone();
        Queue s = new Queue();
        root.color = 1;
        root.color2 = color;
        countOfWhite += 1;
        White.add(root);
        s.enQueue(root);
        while (!s.isEmpty()){
            Node temp = s.deQueue();
            color = temp.color2 % 2 + 1;
            temp.color = 2;
            while (!cun2[temp.value].isEmpty()){
                Node tt = cun2[temp.value].remove();
                if (tt.color == 0){
                    tt.color = 1;
                    if(tt.color2==0) {
                        tt.color2 = color;
                        if (color == 1) {
                            countOfWhite += 1;
                            White.add(tt);
                        } else {
                            countOfBlack += 1;
                            Black.add(tt);
                        }
                        s.enQueue(tt);
                    }
                }
            }
        }
    }

    public static void DFS(LinkedList[] cun,Node[] point,int begin){
        int count = 1;
        LinkedList[] cun2 = cun.clone();
        Stack s = new Stack();
        point[begin].color = 1;
        point[begin].begin = count;
        s.push(point[begin]);
        while (!s.isEmpty()){
            Node temp = s.getPeak();
            if (cun2[temp.value].isEmpty()){
                temp.color = 2;
                count++;
                temp.end = count;
                s.pop();
            }
            else {
                cun2[temp.value].getHead().value.color = 1;
                count++;
                s.push(cun2[temp.value].remove());
            }
            
        }
    }
    
//    static class TreeNode{
//        int value;
//        Node son;
//        int begin;
//        int end;
//        
//    }


    static class Queue{
        Node head = new Node(-1);
        Node tail = head;
        int size = 0;
        Queue(){

        }

//        private void enQueue(int t){
//            City tt = new City(t);
//            tail.next = tt;
//            tail = tt;
//            size ++;
//        }

        private void enQueue(Node t){
            tail.next = t;
            tail = t;
            size ++;
        }

        private boolean isEmpty(){
            return head.next == null;
        }

        private Node deQueue(){
            if (!isEmpty()){
                Node temp = head.next;
                head = head.next;
                size--;
                return temp;
            }
            else
                return null;

        }
    }


    static class Stack{
        Node head = new Node(-1);
        Node tail = head;

        void push(Node value){
            tail.next = value;
            value.pre = tail;
            tail = value;
        }

        void push(int value){
            Node temp = new Node(value);
            tail.next = temp;
            temp.pre = tail;
            tail = temp;
        }
        Node getPeak(){
            return tail;
        }

        boolean isEmpty(){
            return head.next == null;
        }

        void pop(){
            tail = tail.pre;

        }
    }

    static class NodeOfLinkedList{
        Node value;
        NodeOfLinkedList next;

        NodeOfLinkedList(Node value){
            this.value = value;
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
        NodeOfLinkedList getHead(){
            return head.next;
        }
        boolean isEmpty(){
            return head.next == null;
        }


    }

    static class Node{
        int color2;
        int color;
        int value;
        int begin;
        int end;
        Node next;
        Node pre;

        Node(int value){
            this.value = value;
        }
    }
}
