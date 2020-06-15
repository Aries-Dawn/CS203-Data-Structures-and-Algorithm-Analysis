package lab9;

import java.util.*;

public class C_Maze {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n,m;
        n = sc.nextInt();
        m = sc.nextInt();
        Node[] point = new Node[n + 1];
        ArrayList<Node>[] edge = new ArrayList[n + 1];
        ArrayList<Node>[] reverseEdge = new ArrayList[n + 1];
        for (int i = 0;i < n + 1;i++){
            point[i] = new Node(i);
            edge[i] = new ArrayList<>();
            reverseEdge[i] = new ArrayList<>();
        }
        for (int i = 1;i < m + 1;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            edge[u].add(point[v]);
            point[u].out += 1;
            point[v].in += 1;
            reverseEdge[v].add(point[u]);
        }
        Node begin = point[1];

//        for (int i = 1;i < point.length;i++){
//            if (point[i].in != 0)
//                begin = point[i];
//        }

        ArrayList<Node> LR = new ArrayList<>();
        DFS(LR,begin,reverseEdge,point);
        for (Node node : point) {
            node.color = 0;
        }

        ArrayList<Node> L = reverseArrayList(LR);

        secondDFS(L,L.get(0),edge,point);
        boolean allNotWhite = true;
        for (Node t : L){
            if (t.color == 0)
                allNotWhite = false;
        }
        if (allNotWhite)
            System.out.println("Bravo");
        else
            System.out.println("ovarB");

    }

    public static void secondDFS(ArrayList<Node> L,Node begin,ArrayList<Node>[] edge,Node[] point){
        Stack<Node> s = new Stack<>();
        s.push(begin);
        begin.color = 1;
        while (!s.isEmpty()){
            Node temp = s.peek();
            boolean pushed = false;
            for (Node t : edge[temp.value]){
                if (!pushed && t.color == 0){
                    s.push(t);
                    t.color = 1;
                    pushed = true;
                }
            }
            if (!pushed) {
                Node t = s.pop();
                t.color = 2;
            }

        }
    }

    public static void DFS(ArrayList<Node> L,Node begin,ArrayList<Node>[] edge,Node[] point){
        Stack<Node> s = new Stack<>();
        s.push(begin);
        begin.color = 1;
        while (!s.isEmpty()){
            Node temp = s.peek();
            boolean pushed = false;
            for (Node t : edge[temp.value]){
                if (!pushed && t.color == 0){
                    s.push(t);
                    t.color = 1;
                    pushed = true;
                }
            }
            if (!pushed) {
                Node t = s.pop();
                t.color = 2;
                L.add(t);
            }

        }

        boolean hasWhite = false;
        Node beginning = null;
        for (int i =1;i < point.length;i++){
            if (point[i].color == 0) {
                hasWhite = true;
                beginning = point[i];
            }
        }
        if (hasWhite)
            DFS(L,beginning,edge,point);
    }

    public static ArrayList<Node> reverseArrayList(ArrayList<Node> old){
        ArrayList<Node> newOne = new ArrayList<>();
        for (int i = old.size() - 1;i >= 0;i--){
            newOne.add(old.get(i));
        }
        return newOne;
    }


    static class Node {
        int value;
        long length = -1;
        int color;
        int in;
        int out;

        Node(int value) {
            this.value = value;
        }

    }

}
