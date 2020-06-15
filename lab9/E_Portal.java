package lab9;

import java.util.*;

public class E_Portal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        while (size > 0){
            if (size == 71)
                System.out.println();
            int n,m,p,k;
            n = sc.nextInt();
            m = sc.nextInt();
            p = sc.nextInt();
            k = sc.nextInt();
            Node[] point = new Node[n + 1];
            Node[][] value = new Node[n + 1][Math.min(p,k) + 1];
            for (int i = 0;i < value.length;i++){
                for (int j = 0;j < value[i].length;j++){
                    value[i][j] = new Node(i);
                    value[i][j].i = i;
                    value[i][j].j = j;
                    value[i][j].length = Long.MAX_VALUE;

                }
            }
            ArrayList<ArrayList<Edge>> edges = new ArrayList<>();
            ArrayList<ArrayList<Edge>> zeroEdges = new ArrayList<>();
            ArrayList<ArrayList<Edge>> usedPower = new ArrayList<>();
            for (int i = 0;i < point.length;i++){
                point[i] = new Node(i);
                edges.add(new ArrayList<>());
                zeroEdges.add(new ArrayList<>());
                usedPower.add(new ArrayList<>());
            }
            for (int i = 0;i < m;i++){
                int u = sc.nextInt();
                int v = sc.nextInt();
                long d = sc.nextLong();
                Edge temp = new Edge(value[u][0],d,value[v][0]);
                edges.get(u).add(temp);
                point[u].out += 1;
                point[v].in += 1;

            }
            for (int i = 0;i < p;i++){
                int u = sc.nextInt();
                int v = sc.nextInt();
                Edge temp = new Edge(point[u],0,point[v]);

                zeroEdges.get(u).add(temp);

            }
            int begin = sc.nextInt();
            int end = sc.nextInt();
            int power = Math.min(k,p);
            value[begin][0].length = 0;
            DiJi(begin,power,value,edges,zeroEdges);
            long min = value[end][0].length;
            for (int i = 1;i < power + 1;i++){
                min = Math.min(min,value[end][i].length);
            }
            System.out.println(min);
            if (min == 1)
                System.out.println();
            size--;
        }
    }




    public static void DiJi(int begin ,
                            int power,
                            Node[][] value,
                            ArrayList<ArrayList<Edge>> edges,
                            ArrayList<ArrayList<Edge>> zeroEdge){

        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> (int)(o1.length - o2.length));
        queue.add(value[begin][0]);
        value[begin][0].color = 1;
        while (!queue.isEmpty()){
            Node temp = queue.poll();
            temp.color = 2;
            for (int i = 0;i < edges.get(temp.value).size();i++){
                long path = temp.length + edges.get(temp.value).get(i).weight;
                long len = value[edges.get(temp.value).get(i).next.value][temp.j].length;
                if (len > path){
                    value[edges.get(temp.value).get(i).next.value][temp.j].length = path;
                    if (value[edges.get(temp.value).get(i).next.value][temp.j].color == 0){
                        queue.add(value[edges.get(temp.value).get(i).next.value][temp.j]);
                        value[edges.get(temp.value).get(i).next.value][temp.j].color = 1;
                    }
                    else {
                        queue.remove(value[edges.get(temp.value).get(i).next.value][temp.j]);
                        queue.add   (value[edges.get(temp.value).get(i).next.value][temp.j]);
                    }
                }
            }

            for (int i = 0;i < zeroEdge.get(temp.value).size() & temp.j + 1 <= power;i++){
                long path = temp.length;
                long len = value[zeroEdge.get(temp.value).get(i).next.value][temp.j + 1].length;
                if (len > path){
                    value[zeroEdge.get(temp.value).get(i).next.value][temp.j + 1].length = path;
                    if (value[zeroEdge.get(temp.value).get(i).next.value][temp.j + 1].color == 0){
                        queue.add(value[zeroEdge.get(temp.value).get(i).next.value][temp.j + 1]);
                        value[zeroEdge.get(temp.value).get(i).next.value][temp.j + 1].color = 1;
                    }
                    else {
                        queue.remove(value[zeroEdge.get(temp.value).get(i).next.value][temp.j + 1]);
                        queue.add   (value[zeroEdge.get(temp.value).get(i).next.value][temp.j + 1]);
                    }
                }
            }

        }
    }


    static class Edge{
        long weight;
        Node first;
        Node next;

        Edge(Node first, long weight, Node next){
            this.first = first;
            this.weight = weight;
            this.next = next;
        }
    }

    static class Node{
        int value;
//        ArrayList<Long> length = new ArrayList<>();
        long length;
        int in;
        int out;
        int color;
        int i,j;

//        ArrayList<Node> parent = new ArrayList<>();
        Node parent;

        Node(int value){
            this.value = value;
//            length.add(-1L);
//            parent.add(null);
        }

    }
}
