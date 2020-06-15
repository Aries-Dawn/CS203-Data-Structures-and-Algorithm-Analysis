package lab6;

import java.util.ArrayList;
import java.util.Scanner;

public class E_CutATree {
    private static int v = 0;
    private static int R_Max = 0;
    private static int B_Max = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases > 0){
            v = 0;
            R_Max = 0;
            B_Max = 0;
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
            for (int i = 1;i < node.length;i++){
                node[i].value = sc.nextInt();
                if (node[i].value == 1)
                    R_Max += 1;
                if (node[i].value == 2)
                    B_Max += 1;
            }
            Tree tree = new Tree();
            tree.root = node[1];
            sou(tree.root,used);



            System.out.println(v);
            cases--;
        }
    }





    private static void sou(Node root,boolean[] used){
        used[root.number] = true;

        if(root.value==1){
            root.R++;
        }
        if(root.value==2){
            root.B++;
        }


        for (int i = 0;i < root.sons.size();i++){

            if (!used[root.sons.get(i).number]){
                sou(root.sons.get(i),used);
                root.R += root.sons.get(i).R;
                root.B += root.sons.get(i).B;

            }
        }
        if ((root.R == R_Max && root.B == 0) || (root.R == 0 && root.B == B_Max)){
            v++;
        }


    }

    static class Tree{
        Node root;
    }



    static class Node{
        int number;
        int value;
        int R = 0;
        int B = 0;
        ArrayList<Node> sons = new ArrayList<>();

        Node(int number){
            this.number = number;
        }
    }



}
