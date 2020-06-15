package lab8;

/*
题目描述
One day, an elder told Pisces that there was a legendary sword at the end of the sky - the sword of Damocles. Pisces decided to get the sword at any cost. The area between Pisces and the sword can be described as a rectangular field of n∗m square meters, with Pisces currently at the top left corner and the sword at the bottom right corner. However, k monsters are living in this area, and to keep himself safe, Pisces must keep a distance longer than Si from the i-th monster (Euclidean Distance). Given the locations of these k monsters, Pisces wants to find whether he can get the legendary sword.

输入
The first line of input contains an integer T (1≤T≤10), which denotes the number of test cases.

For each of the test case, the first line contains three integers, N, M, and K (10≤N,M≤104,1≤K≤1000). Pisces is now at position (0,0), and the sword at position (N,M). Each of the next K lines describes one of the K monsters, it contains three integers, X,Y, and S, where (X,Y) represents the location and S represents the distance that must be kept. (0≤X≤N,0≤Y≤M,0<S≤104).

输出
For each test case, print “Yes” if Pisces can get the sword, and “No” otherwise.

样例输入
1
10 10 2
3 7 4
5 4 4
样例输出
No
 */

import java.util.Scanner;

public class B_TheSwordOfDamocles {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();

            int number = sc.nextInt();
            Monster[] edges = new Monster[number + 2];
            linkedList[] edge = new linkedList[number + 2];
            edges[0] = new Monster(10,0,-1);
            edge[0] = new linkedList();
            edges[number + 1] = new Monster(0,10,-1);
            edge[number + 1] = new linkedList();
            for (int i = 1;i < number + 1;i++){
                edge[i] = new linkedList();
                edges[i] = new Monster(sc.nextInt(),sc.nextInt(),sc.nextInt());
                edges[i].index = i;

            }
            for (int i = 1;i < number + 1;i++){
                for (int j = 1;j < number + 1;j++){
                    if (edges[i].x != edges[j].x || edges[i].y != edges[j].y){
                        if (dis(edges[i],edges[j])){
                            edge[i].add(edges[j]);
                            edge[j].add(edges[i]);
                        }
                    }
                }
                if (edges[i].distance + edges[i].x >= n - 1 || edges[i].y - edges[i].distance <= 0){
                    edge[0].add(edges[i]);
                    edge[i].add(edges[0]);
                }
                if (edges[i].x - edges[i].distance <= 0 || edges[i].distance + edges[i].y >= m - 1){
                    edge[number + 1].add(edges[i]);
                    edge[i].add(edges[number + 1]);
                }

            }
            BFS(edges[0],edge);
            if (edges[number + 1].colour == 0)
                System.out.println("Yes");
            else
                System.out.println("No");
            cases--;
        }

    }


    public static void BFS(Monster m,linkedList[] link){
        Queue queue = new Queue();
        m.colour = 1;
        queue.enQueue(m);
        while (!queue.isEmpty()){
            Monster monster = queue.deQueue();
            monster.colour = 2;
            while (!link[monster.index].isEmpty()){
                Monster t = link[monster.index].remove().monster;
                if (t.colour == 0){
                    t.colour = 1;
                    queue.enQueue(t);
                }
            }

        }


    }

    public static boolean dis(Monster m1,Monster m2){
        double l = (m1.x - m2.x) * (m1.x - m2.x) + (m1.y - m2.y) * (m1.y - m2.y);
        double length = Math.pow(l,0.5);
        int dis = m1.distance + m2.distance;
        return dis >= length;
    }

    static class Node{
        Monster monster;
        Node next;

        Node(Monster monster){
            this.monster = monster;
        }
        Node(){}
    }

    static class linkedList{
        Node head = new Node();
        Node tail = head;
        int size = 0;

        linkedList(){

        }

        private void add(Monster monster){
            Node t = new Node(monster);
            tail.next = t;
            tail = t;
            size++;
        }

        private Node remove(){
            Node temp = head.next;
            head = head.next;
            size--;
            return temp;
        }

        private boolean isEmpty(){
            return head.next == null ||head.next.monster == null || head.next.monster.distance == 0;
        }
    }

    static class Queue{
        Node head = new Node(null);
        Node tail = head;
        int size = 0;
        Queue(){

        }

        private void enQueue(Monster t){
            Node tt = new Node(t);
            tail.next = tt;
            tail = tt;
            size ++;
        }

        private boolean isEmpty(){
            return head.next == null;
        }

        private Monster deQueue(){
            if (!isEmpty()){
                Monster temp = head.next.monster;
                head = head.next;
                size--;
                return temp;
            }
            else
                return null;

        }
    }

    static class Monster{
        int distance;
        int x,y;
        int index;
        int colour = 0;
        //0 is white; 1 is yellow; 2 is red
        Monster next;
//        linkedList linkedList = new linkedList();

//        private void add(Monster monster){
//            linkedList.add(monster);
//        }
        Monster(){

        }

        Monster(int x,int y,int distance){
            this.distance = distance;
            this.x = x;
            this.y = y;

        }
    }
}
