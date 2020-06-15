package lab3;

import java.util.Scanner;

public class C_CovertedVinuxInput {

    static class Node{
        int value;
        Node pre;
        Node next;

        private Node(int value){
            this.value = value;
        }
    }

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases != 0){
            int size = sc.nextInt();
            String num = sc.next();
            Node head = new Node(-1);
            Node tail = new Node(-1);
            head.next = tail;
            tail.pre = head;
            Node point = head;
            Node point2 = point.next;

            int flags = 0;
            int standard = flags;
            for (int i = 0;i < size;i++){
                Node temp = new Node((int)num.charAt(i) - '0');


//                temp.next = tail;
//                temp.pre = head;
//                tail.pre = temp;
//                head .next = temp;



                switch (temp.value){
                    case 66:
                        //replace the current character
                        assert point2 != null;
                        if (point2.next == null){
                            Node empty = new Node(-1);
                            point2.next = empty;
                            empty.pre = point2;
                        }
                        point2 = point2.next;
                        flags += 1;


//                        temp.next = point.next;
//                        point.next.pre = temp;
//                        temp.pre = point;
//                        point.next = temp;
//                        point = point.next;
                        break;
                    case 25:
                        //move the character pointer to the head of the line
                        point = head;
                        point2 = point.next;
                        break;
                    case 24:
                        //left shift to -1
                        if (point.value != -1 && point.pre != null){
                            point = point.pre;
                            point2 = point.next;
                        }
                        break;
                    case 28:
                        //right shift to -1
                        if (point2 != null && point2.value != -1 && point2.next != null){
                            point = point.next;
                            point2 = point.next;
                        }
                        break;
                    case 72:
                        //delete the current character
                        if (point2 != null && point2.value != -1 && point2.next != null){
                            point.next = point2.next;
                            point2.next.pre = point;
                            point2 = point.next;
                        }
                        break;
                    default:
                        temp.next = point2;
                        assert (point2 != null): temp.value;
                        point2.pre = temp;
                        temp.pre = point;
                        point.next = temp;
                        if (flags > standard){
                            flags -= 1;
                            point2 = point2.pre;
                        }
                        else {
                            point = point.next;
                            point2 = point.next;
                        }
                        break;
                }

            }
            Node printPoint = head;
            while (printPoint.next != null){
                if (printPoint.value != -1){
                    System.out.print((char) (printPoint.value + (int)'0'));
                }
                printPoint = printPoint.next;
            }
            System.out.println();
            cases--;
        }



    }


}
