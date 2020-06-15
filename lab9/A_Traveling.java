package lab9;

import java.util.*;

public class A_Traveling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, m;
        n = sc.nextInt();
        m = sc.nextInt();
        Node[] point = new Node[n + 1];
        ArrayList<ArrayList<edge>> edges = new ArrayList<>(new ArrayList<>());
        for (int i = 0; i < point.length; i++) {
            point[i] = new Node(i);
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int first = sc.nextInt();
            int second = sc.nextInt();
            int weight = sc.nextInt();
            edge ed = new edge(point[first], weight, point[second]);
            edges.get(first).add(ed);
            edge ed1 = new edge(point[second], weight, point[first]);
            edges.get(second).add(ed1);
        }
        int begin, end;
        begin = sc.nextInt();
        end = sc.nextInt();
        DiJi(begin, point, edges);
        System.out.println(point[end].length);

    }

    public static void DiJi(int begin, Node[] point, ArrayList<ArrayList<edge>> edges) {
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> (int) (o1.length - o2.length));
        point[begin].length = 0;
        queue.add(point[begin]);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            for (int i = 0; i < edges.get(temp.value).size(); i++) {
                long path = temp.length + (long) edges.get(temp.value).get(i).weight;
                long len = (long) point[edges.get(temp.value).get(i).next.value].length;
                if (len == -1 || len > path) {
                    point[edges.get(temp.value).get(i).next.value].length = path;
                    point[edges.get(temp.value).get(i).next.value].parent = temp;
                    queue.add(point[edges.get(temp.value).get(i).next.value]);
                }
            }

        }
    }

    static class edge {
        long weight;
        Node first;
        Node next;

        edge(Node first, int weight, Node next) {
            this.first = first;
            this.weight = weight;
            this.next = next;
        }
    }

    static class Node {
        int value;
        long length = -1;
        int in;
        int out;
        Node parent;

        Node(int value) {
            this.value = value;
        }

    }

}

/*问题 A: Traveling
时间限制: 1 Sec  内存限制: 128 MB
提交: 763  解决: 156
[提交][状态][讨论版]
题目描述
Yuki is a playful girl and she enjoys traveling.

One day, she is planning to play in the Disneyland.
The resort is so large that she cannot find the shortest path between two sights immediately,
so she wants to ask for your help.

Specifically, there are n sights and m roads in the Disneyland.
Each road, with a certain distance, connects two sights.
The sights are numbered from 1 to n and the roads are all bidirectional,
that is the road from sight u to sight v can be passed from sight v to u.
You are asked to find the shortest distance between sight S and sight T.

输入
The first line contains two integers:
n and m (1≤n≤1 000, 1≤m≤5 000) — the number of sights and roads in Disneyland.

Each of the next m lines contains three space-separated integers:
u, v and w (1≤u, v≤n, 1≤w≤105), meaning that there is a bidirectional
road from sight u to sight v with distance w.

The last line contains two integers: S and T (1≤S, T≤n) — the origin and destination.

输出
Print the result — the shortest distance between sight S and sight T.

If there are no paths from sight S to sight T, print −1 instead.

样例输入
3 3
1 2 5
2 3 5
3 1 2
1 3
样例输出
2

 */


