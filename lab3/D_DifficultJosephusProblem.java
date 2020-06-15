package lab3;
import java.util.Scanner;

public class D_DifficultJosephusProblem {
    public static class Node{
        int note;
        int situations;
        Node pre;
        Node next;
        private Node(int note,int situations){
            this.note = note;
            this.situations = situations;
        }
    }

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases > 0){
            int size = sc.nextInt();
            int note = sc.nextInt();
            Node head = new Node(-1,0);
            Node tail = new Node(-1,0);
            head.next = tail;
            tail.pre = head;
            Node point = head;
            //creat linkedList
            for (int i = 0;i < size;i++){
                Node temp = new Node(sc.nextInt(),i + 1);
                temp.next = tail;
                tail.pre = temp;
                point.next = temp;
                temp.pre = point;
                point = point.next;
            }

            while (size != 1){
                Node temp = head.next;
                note %= size;
                if (note > 0){
                    for (int i = 1;i < note;i++){
                        temp = temp.next;
                    }
                    note = note - 1 + temp.note;
                    temp.next.pre = temp.pre;
                    temp.pre.next = temp.next;
                }
                else {
                    note = size - 1 + tail.pre.note;
                    tail.pre = tail.pre.pre;
                    tail.pre.next = tail;

                }
                size -= 1;
            }
            System.out.println(head.next.situations);


            cases--;
        }
    }
}
