package lab6;

import java.util.ArrayList;
import java.util.Scanner;

public class F_KPeopleTravelOnATree {

    private static int max_depth = 0;
    public static int max_node = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases > 0){
            max_depth = 0;
            max_node = 1;
            int n = sc.nextInt();
            int num_friends = sc.nextInt();
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
            for (int i = 0;i < num_friends;i++){
                node[sc.nextInt()].hasPeople = true;
            }
            Tree tree = new Tree();
            tree.root = node[1];
            sou(tree.root,used,0);
            tree.root = node[max_node];
            max_depth = 0;
            used = new boolean[n + 1];
            sou(tree.root,used,0);
            System.out.println(max_depth / 2 + max_depth % 2);

            cases--;
        }


    }


    private static void sou(Node root,boolean[] used,int deep ){
        root.depth = deep;
        if (root.hasPeople && deep > max_depth){
            max_depth = deep;
            max_node = root.value;
        }
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
        boolean hasPeople;
        int depth;
        ArrayList<Node> sons = new ArrayList<>();

        Node(int value){
            this.value = value;
        }
    }


}
