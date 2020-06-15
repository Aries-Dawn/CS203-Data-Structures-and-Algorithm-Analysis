package lab9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class B_Construction {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n,m;
        n = sc.nextInt();
        m = sc.nextInt();
        Node[] point = new Node[n + 1];
        ArrayList<ArrayList<Edge>> edges = new ArrayList<>(new ArrayList<>());

        for (int i = 0;i < point.length;i++){
            point[i] = new Node(i);
            edges.add(new ArrayList<>());
        }
        Edge minEdge = new Edge(null,-1,null);
        for (int i = 0;i < m;i++){
            int first = sc.nextInt();
            int second = sc.nextInt();
            int weight = sc.nextInt();
            Edge ed = new Edge(point[first],weight,point[second]);
            edges.get(first).add(ed);
            Edge ed1 = new Edge(point[second],weight,point[first]);
            edges.get(second).add(ed1);
            if (minEdge.weight == -1)
                minEdge = ed;
            else if (minEdge.weight > weight)
                minEdge = ed;
        }
        Node root = minEdge.first;
        minEdge.first.best = new Edge(minEdge.first,0,minEdge.next);
        long sum = MST(minEdge,root,edges,point);
        System.out.println(sum);


    }

    static long MST(Edge minEdge,Node root,ArrayList<ArrayList<Edge>> edges,Node[] point){
        long sum = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1,Node o2) {
                return o1.best.weight - o2.best.weight;
            }
        });
        for (int i = 1;i < point.length;i++){
            queue.offer(point[i]);
        }

        while (!queue.isEmpty()){
            Node temp = queue.poll();
            temp.color = 2;
            sum += (long)temp.best.weight;
            for (Edge t : edges.get(temp.value)){
                if (t.next.best.weight > t.weight) {
                    t.next.best = t;
                }
            }
            queue.clear();
            for (int i = 1;i < point.length;i++){
                if (i != temp.value &&  point[i].color == 0){
                    queue.offer(point[i]);
                }
            }
        }
        return sum;
    }


    static class Edge{
        int weight;
        Node first;
        Node next;

        Edge(Node first, long weight, Node next){
            this.first = first;
            this.weight = (int)weight;
            this.next = next;
        }
    }

    static class Node{
        int value;
        int color = 0;
        ArrayList<Node> sons = new ArrayList<>();
        Edge best = new Edge(null,(long)1e7,null);

        Node(int value){
            this.value = value;
        }

    }
}


/*
问题 B: Construction
时间限制: 1 Sec  内存限制: 128 MB
提交: 325  解决: 131
[提交][状态][讨论版]
题目描述
Yuki is a wise girl and she rules a country named Blossom.

Blossom is a big country with n cities, and there are m roads determined to be constructed.
Due to many reasons during construction, different roads might have different costs to be built.
The roads are bidirectional, that is a road from u to v can be passed from v to u.

Since Yuki wants to make the construction more economical,
she decides to choose some of the roads to construct and wants to spend the least money.
However, to make the traffic in Blossom convenient,
all the cities in Blossom are needed to be connected after construction.
Your task is to determine the minimum cost of construction.

输入
The first line contains two integers: n and m (1≤n≤1 000, 1≤m≤10 000
) — the number of cities and roads to be constructed in Blossom.

Each of the next m lines contains three space-separated integers:
u, v and w (1≤u, v≤n, 1≤w≤106), meaning that there is a bidirectional road
from city u to city v considered to be built. And the cost of this edge is w.
Cities are numbered from 1 to n.

It is guaranteed that there is at least one plan to connect all cities.

输出
Print one line with an integer — the minimum cost of the construction.

样例输入
4 4
1 2 2
2 3 2
3 4 2
4 1 2
样例输出
6
*/