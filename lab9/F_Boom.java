package lab9;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class F_Boom {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        while (size > 0){
            int num = sc.nextInt();
            Node[] point = new Node[num + 1];
            ArrayList<ArrayList<Node>> edges = new ArrayList<>();
            ArrayList<ArrayList<Node>> reverseEdge = new ArrayList<>();
            edges.add(new ArrayList<>());
            reverseEdge.add(new ArrayList<>());
            int[] inWhichSet = new int[num + 1];
            point[0] = new Node(0);
            for (int i = 1;i < point.length;i++){
                int x,y,r,t;
                x = sc.nextInt();
                y = sc.nextInt();
                r = sc.nextInt();
                t = sc.nextInt();
                Node temp = new Node(x,y,r,t,i);
                point[i] = temp;
                edges.add(new ArrayList<>());
                reverseEdge.add(new ArrayList<>());
            }

            for (int i = 1;i < point.length;i++){
                for (int j = i + 1;j < point.length;j++){
                    int x1 = point[i].x;
                    int y1 = point[i].y;
                    int r1 = point[i].r;
                    int x2 = point[j].x;
                    int y2 = point[j].y;
                    int r2 = point[j].r;
                    double dis = Math.sqrt(Math.pow(x1 - x2,2) + Math.pow(y1 - y2,2));
                    if (dis <= r1){
                        edges.get(point[i].value).add(point[j]);
                        point[i].out += 1;
                        point[j].in += 1;
                        reverseEdge.get(point[j].value).add(point[i]);
                    }
                    if (dis <= r2){
                        edges.get(point[j].value).add(point[i]);
                        point[j].out += 1;
                        point[i].in += 1;
                        reverseEdge.get(point[i].value).add(point[j]);
                    }
                }
            }


            Node begin = point[1];

            ArrayList<Node> LR = new ArrayList<>();
            DFS(LR,begin,reverseEdge,point);
            for (Node node : point) {
                node.color = 0;
            }

            ArrayList<Node> L = reverseArrayList(LR);
            secondDFS(1,inWhichSet,L,L.get(0),edges,reverseEdge,point);
            long sum = 0;
            boolean[] isUsed = new boolean[point.length];
            for (int i = 1;i < point.length;i++){
                if (point[i].in == 0 && !isUsed[point[i].value]){
                    sum += point[i].t;
                    isUsed[point[i].value] = true;
                }
            }
            System.out.println(sum);
            size--;
        }
        
    }

    public static void secondDFS(int setIndex,
                                 int[] inWhichSet,
                                 ArrayList<Node> L,
                                 Node begin,
                                 ArrayList<ArrayList<Node>> edge,
                                 ArrayList<ArrayList<Node>> reverseEdge,
                                 Node[] point){
        Stack<Node> s = new Stack<>();
        ArrayList<Node> SSC = new ArrayList<>();
        Node min = null;
        s.push(begin);
        begin.color = 1;
        while (!s.isEmpty()){
            Node temp = s.peek();
            boolean pushed = false;
            for (Node t : edge.get(temp.value)){
                if (!pushed && t.color == 0){
                    s.push(t);
                    t.color = 1;
                    pushed = true;
                }
            }
            if (!pushed) {
                Node t = s.pop();
                t.color = 2;
                SSC.add(t);
                inWhichSet[t.value] = setIndex;
                if (min == null)
                    min = t;
                else if (min.t > t.t)
                    min = t;
            }

        }
        int setIn = 0;
        for (Node node : SSC) {
            for (Node t : reverseEdge.get(node.value)){
                if (inWhichSet[t.value] != setIndex){
                    setIn += 1;
                }
            }
            point[node.value] = min;
        }
        min.in = setIn;
        boolean hasWhite = false;
        Node beginning = null;
        for (int i =1;i < L.size();i++){
            if (L.get(i).color == 0) {
                hasWhite = true;
                beginning = L.get(i);
                break;
            }
        }
        if (hasWhite)
            secondDFS(setIndex + 1,inWhichSet,L,beginning,edge,reverseEdge,point);

    }

    public static void DFS(ArrayList<Node> L, Node begin, ArrayList<ArrayList<Node>> edge, Node[] point){
        Stack<Node> s = new Stack<>();
        s.push(begin);
        begin.color = 1;
        while (!s.isEmpty()){
            Node temp = s.peek();
            boolean pushed = false;
            for (Node t : edge.get(temp.value)){
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
        int x;
        int y;
        int r;
        int t;
        int color;
        long length = -1;
        int in;
        int out;

        Node(int x,int y,int r,int t,int value){
            this.x = x;
            this.y = y;
            this.r = r;
            this.t = t;
            this.value = value;
        }


        Node(int value) {
            this.value = value;
        }

    }

    
    
}
