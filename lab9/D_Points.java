package lab9;

import java.util.*;

public class D_Points {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n,m;
        n = sc.nextInt();
        m = sc.nextInt();
        Node[][] C = new Node[n][m];
        Node[] point = new Node[m * n + 1];
        ArrayList<ArrayList<Edge>> edges = new ArrayList<>();
        int k = 1;
        edges.add(new ArrayList<>());
        for (int i = 0;i < point.length;i++){
            edges.add(new ArrayList<>());
        }
        for (int i = 0;i < C.length;i++){
            for (int j = 0;j < C[i].length;j++){
                long coefficient = sc.nextLong();
                point[k] = new Node(k,coefficient);
                C[i][j] = point[k];
                k++;
            }
        }
        for (int i = 0;i < C.length;i++){
            for (int j = 0;j < C[i].length;j++){
                if (i + 1 < n && i + 1 >= 0 && j < m && j >= 0){
                    edges.get(C[i][j].value).add(new Edge(C[i][j],(long)C[i][j].coefficient * (long)C[i + 1][j].coefficient,C[i + 1][j]));
                    edges.get(C[i + 1][j].value).add(new Edge(C[i + 1][j],C[i][j].coefficient * C[i + 1][j].coefficient,C[i][j]));

                }
                if (i < n && i >= 0 && j + 1 < m && j + 1 >= 0) {
                    edges.get(C[i][j].value).add(new Edge(C[i][j], C[i][j].coefficient * C[i][j + 1].coefficient, C[i][j + 1]));

                    edges.get(C[i][j + 1].value).add(new Edge(C[i][j + 1],C[i][j].coefficient * C[i][j + 1].coefficient,C[i][j]));
                }

            }
        }
        Edge maxEdge = new Edge(null,-1,null);
        for (ArrayList<Edge> e : edges){
            for (Edge t : e){
                if (t.weight > maxEdge.weight){
                    maxEdge = t;
                }
            }
        }

        if (maxEdge.weight == -1)
            System.out.println(0);
        else {
            maxEdge.first.best = maxEdge;
            long sum = MST(maxEdge.first, edges, point);
            System.out.println(sum);
        }



////            size--;
//        }


    }



    static long MST(Node begin, ArrayList<ArrayList<Edge>> edges, Node[] point){
        long sum = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (int)(o2.best.weight - o1.best.weight);
            }
        });
        queue.offer(begin);

        while (!queue.isEmpty()){
            Node temp = queue.poll();
            temp.color = 2;
            sum += (long)temp.best.weight;
            temp.best.next.color = 2;
            for (Edge t : edges.get(temp.value)){
                if (t.next.best.weight <= t.weight) {
                    t.next.best = t;
                    if (t.next.color == 0) {
                        t.next.color = 1;
                        queue.offer(t.next);
                    }
                    else {
                        if (queue.remove(t.next)) {
                            queue.offer(t.next);
                        }
                    }
                }
            }
            for (Edge t : edges.get(temp.best.next.value)){
                if (t.next.best.weight <= t.weight) {
                    t.next.best = t;
                    if (t.next.color == 0) {
                        t.next.color = 1;
                        queue.offer(t.next);
                    }
                    else {
                        if (queue.remove(t.next)) {
                            queue.offer(t.next);
                        }
                    }
                }
            }
        }
        return sum;
    }




    static class Edge{
        long weight;
        Node first;
        Node next;

        Edge(Node first, long weight, Node next){
            this.first = first;
            this.weight = (long)weight;
            this.next = next;
        }
    }

    static class Node {
        int value;
        long length = -1;
        long coefficient;
        Node parent;
        Edge best = new Edge(null,(long)1,null);

        int color = 0;
        int in;
        int out;

        Node(int value,long coefficient) {
            this.value = value;
            this.coefficient = coefficient;

        }

    }




}
