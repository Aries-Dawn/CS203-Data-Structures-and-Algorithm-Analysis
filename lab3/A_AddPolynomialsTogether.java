package lab3;

import java.util.Scanner;

public class A_AddPolynomialsTogether {
    static class Node {
            int coefficients;
            int exponents ;
            Node next;
            Node pre;

        public Node(int coefficients, int exponents) {
            this.coefficients = coefficients;
            this.exponents = exponents;
        }

    }

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0;i < cases;i++){
            int first = sc.nextInt();
            Node head1 = null;
            Node head2 = null;
            Node head3 = null;
            Node tail1 = null;
            Node tail2 = null;
            Node tail3 = null;
            Node a1 = new Node(sc.nextInt(),sc.nextInt());
            head1 = a1;
            tail1 = a1;
            for (int j = 1;j < first;j++){
                Node b1 = new Node(sc.nextInt(),sc.nextInt());
                tail1.next = b1;
                tail1 = b1;
            }
            int second = sc.nextInt();
            Node a2 = new Node(sc.nextInt(),sc.nextInt());
            head2 = a2;
            tail2 = a2;
            for (int j = 1;j < second;j++){
                Node b2 = new Node(sc.nextInt(),sc.nextInt());
                tail2.next = b2;
                tail2 = b2;
            }
            int size = sc.nextInt();
            int[] nums = new int[size];
            for (int j = 0;j < nums.length;j++){
                nums[j] = sc.nextInt();
            }
            Node A = head1;
            Node B = head2;
            if (A.exponents < B.exponents){
                head3 = A;
                tail3 = A;
                A = A.next;
            }
            else if (A.exponents > B.exponents){
                head3 = B;
                tail3 = B;
                B = B.next;
            }
            else {
                head3 = A;
                tail3 = A;
                head3.coefficients += B.coefficients;
                A = A.next;
                B = B.next;
            }
            while (A != null && B != null){
                if (A.exponents < B.exponents){
                    tail3.next = A;
                    tail3 = A;
                    A = A.next;
                }
                else if (A.exponents > B.exponents){
                    tail3.next = B;
                    tail3 = B;
                    B = B.next;
                }
                else{
                    tail3.next = A;
                    tail3 = A;
                    tail3.coefficients += B.coefficients;
                    A = A.next;
                    B = B.next;
                }
            }
            while (A != null && B == null){
                tail3.next = A;
                tail3 = A;
                A = A.next;
            }
            while (A == null && B != null){
                tail3.next = B;
                tail3 = B;
                B = B.next;
            }
            for (int j : nums){
                int count = 0;
                while (head3 != null){
                    if (head3.exponents == j){
                        count = head3.coefficients;
                        break;
                    }
                    else if (head3.exponents > j){
                        break;
                    }
                    head3 = head3.next;
                }
                System.out.println(count);
            }
        }


    }
}
