package lab3;

import java.util.Scanner;

public class B_BlackEra {

    static class Node{
        int value;
        Node next;
        Node pre;

        private Node(int value){
            this.value = value;
        }


    }

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0;i < cases;i++){
            int memberNumber = sc.nextInt();
            int swapNumber = sc.nextInt();
            Node[] nums = new Node[memberNumber];
            Node head,tail;
            Node firstEmpty = new Node(-1);
            head = firstEmpty;
            tail = firstEmpty;
            for (int j = 0;j < memberNumber;j++){
                Node temp = new Node(sc.nextInt());
                temp.pre = tail;
                tail.next = temp;

                tail = temp;
                nums[temp.value] = temp;
            }
            Node empty = new Node(-1);
            empty.pre = tail;
            tail.next = empty;

            for (int j = 0;j < swapNumber;j++){
                int x1 = sc.nextInt();
                int y1 = sc.nextInt();
                int x2 = sc.nextInt();
                int y2 = sc.nextInt();
                Node thirdEmpty = new Node(-2);
                thirdEmpty.next = nums[y1].next;
                nums[y1].next.pre = thirdEmpty;
                nums[y1].next = thirdEmpty;
                thirdEmpty.pre = nums[y1];


                nums[x1].pre.next = nums[x2];
                Node temp = nums[y1].next;
                nums[y1].next = nums[y2].next;
                nums[y2].next.pre = nums[y1];
                nums[y2].next = temp;
                temp.pre = nums[y2];
                nums[x2].pre.next = nums[x1];
                temp = nums[x2].pre;
                nums[x2].pre = nums[x1].pre;
                nums[x1].pre = temp;


//                Node point = head;
//                while (point.next != null){
//                    if (point.value != -1 && point.value != -2){
//                        System.out.print(point.value);
//                    }
//                    if (point.next != null && point.next.value != -1 && point.next.value != -2 && point.value != -1){
//                        System.out.print(" ");
//                    }
//                    point = point.next;
//                }



            }
            Node point = head;
            while (point != null && point.next != null){
                if (point.value != -1 && point.value != -2){
                    System.out.print(point.value);
                }
                if (point.next != null && point.next.value != -1 && point.next.value != -2 && point.value != -1){
                    System.out.print(" ");
                }
                point = point.next;
            }
            System.out.println();

        }

    }
}
