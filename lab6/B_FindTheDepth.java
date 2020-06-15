package lab6;

import java.util.ArrayList;
import java.util.Scanner;

public class B_FindTheDepth {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases > 0){
            int n = sc.nextInt();
            boolean[] used = new boolean[n + 1];
            Node[] node = new Node[n + 1];
            for (int i = 0;i < node.length;i++){
                node[i] = new Node(i);
            }
            for (int i = 0;i < n - 1;i++){
                Node first = node[sc.nextInt()];
                Node second = node[sc.nextInt()];

                first.sons.add(second);
                second.sons.add(first);
            }


            Tree tree = new Tree();
            tree.root = node[1];
            sou(tree.root,used,0);
            for (int i = 1;i < node.length;i++){
                System.out.print(node[i].depth);
                if (i < node.length - 1){
                    System.out.print(" ");
                }
            }
            System.out.println();
            cases--;
        }

    }

    private static void sou(Node root,boolean[] used,int deep ){
        root.depth = deep;
        used[root.value] = true;
        for (int i = 0;i < root.sons.size();i++){
            if (!used[root.sons.get(i).value]){
                sou(root.sons.get(i),used,deep + 1);
            }
        }


    }

    static class Tree{
        Node root;
    }

    static class Node{
        int value;
        Node father;
        int depth;
        ArrayList<Node> sons = new ArrayList<>();

        Node(int value){
            this.value = value;
        }
    }




}
