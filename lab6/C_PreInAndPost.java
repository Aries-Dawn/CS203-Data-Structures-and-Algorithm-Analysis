package lab6;

import java.util.Scanner;

public class C_PreInAndPost {

    private static int point = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases > 0){
            point = 0;
            int size = sc.nextInt();
            int[] pre = new int[size];
            int[] in = new int[size];
            for (int i = 0;i < size;i++){
                pre[i] = sc.nextInt();
            }
            for (int i = 0;i < size;i++){
                in[i] = sc.nextInt();
            }
            Tree tree = new Tree();
            Node root;


            root = build(in,pre);

            tree.root = root;
            if (root != null){
                pos(root);
            }
            System.out.println();

            cases--;
        }



    }


    private static void pos (Node root){
        if (root.left != null){
            pos(root.left);
        }
        if (root.right != null){
            pos(root.right);
        }
        System.out.print(root.value);
        System.out.print(" ");

    }


    private static Node build(int[] in,int[] pre){
        if (point < pre.length){
            int nodes = pre[point];
            int index = search(in,nodes);
            Node root = null;
            if (index >= 0){
                root = new Node(nodes);
                int[] left = new int[index];
                System.arraycopy(in, 0, left, 0, left.length);
                point++;
                root.left = build(left,pre);



                int[] right = new int[in.length - index];
                System.arraycopy(in,index + 1,right,0,right.length - 1);
                point++;
                root.right = build(right,pre);



            }
            else
                point--;
            return root;
        }
        return null;
    }



    private static int search(int[] in,int key){
        for (int i = 0;i < in.length;i++){
            if (in[i] == key){
                return i;
            }
        }
        return -1;
    }


    static class Tree{
        Node root;
    }

    static class Node{
        int value;
        Node left;
        Node right;

        private Node(int value){
            this.value = value;
        }
    }



}

