package lab8;

import java.util.Scanner;

public class C_ValentineDay {

    //    static class City{
//        int value;
//        City next;
//
//        City(int value){
//            this.value = value;
//        }
//        City(){
//
//        }
//    }
    static class City {
        int value;
        int colour = 0;
        int spendTime = 0;
        //0 is white; 1 is yellow; 2 is red
        City next;

        City(int value) {
            this.value = value;
        }

        City() {

        }
    }

    static class Node {
        City city;
        //0 is white; 1 is yellow; 2 is red
        Node next;

        Node(City city) {
            this.city = city;
        }

        Node() {

        }
    }

    static class LinkedList {
        Node head = new Node();
        Node tail = head;

        private void add(Node t) {
            tail.next = t;
            tail = t;
        }

        private boolean isEmpty() {
            return head.next == null;
        }

        private Node remove() {
            Node t = head.next;
            head = head.next;
            return t;
        }

        private Node getPeak() {
            return head.next;
        }


    }

    private static void BFS(City m, LinkedList[] link, int n) {
        Queue queue = new Queue();
        m.colour = 1;
        Node mm = new Node(m);
        queue.enQueue(mm);
        while (!queue.isEmpty()) {
            City city = queue.deQueue().city;
            city.colour = 2;
            if (city.value == 0) {
                City t = city.next;
                if (t.value == n) {
                    t.colour = 1;
                    t.spendTime = city.spendTime + 1;
                    return;
                }
                if (t.colour == 0) {
                    t.colour = 1;
                    t.spendTime = city.spendTime + 1;
                    Node tt = new Node(t);
                    queue.enQueue(tt);

                }
            } else {
                while (!link[city.value].isEmpty()) {
                    City t = link[city.value].remove().city;
                    if (t.value == n) {
                        t.colour = 1;
                        t.spendTime = city.spendTime + 1;
                        return;
                    }
                    if (t.colour == 0) {
                        t.colour = 1;
                        t.spendTime = city.spendTime + 1;
                        Node tt = new Node(t);
                        queue.enQueue(tt);

                    }

                }
            }

        }

    }

    static class Queue {
        Node head = new Node(null);
        Node tail = head;
        int size = 0;

        Queue() {

        }

//        private void enQueue(int t){
//            City tt = new City(t);
//            tail.next = tt;
//            tail = tt;
//            size ++;
//        }

        private void enQueue(Node t) {
            tail.next = t;
            tail = t;
            size++;
        }

        private boolean isEmpty() {
            return head.next == null;
        }

        private Node deQueue() {
            if (!isEmpty()) {
                Node temp = head.next;
                head = head.next;
                size--;
                return temp;
            } else
                return null;

        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
//        ArrayList<LinkedList> cityRoad = new ArrayList<>();
        int n = sc.nextInt();
        int m = sc.nextInt();
        City[] cities = new City[n + 1];
        LinkedList[] cityRoad = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            cityRoad[i] = new LinkedList();
            cities[i] = new City(i);
        }
        for (int i = 0; i < m; i++) {
            int first = sc.nextInt();
            int second = sc.nextInt();
            int time = sc.nextInt();
            if (time == 1) {
                Node t = new Node(cities[second]);
                cityRoad[first].add(t);
            } else {
                City temp = new City(0);
                Node t = new Node(temp);
                Node t1 = new Node(cities[second]);
                cityRoad[first].add(t);
                t.city.next = t1.city;
//                cityRoad[first].add(t1);
            }
        }

        BFS(cities[1], cityRoad, n);

        if (cities[n].colour != 0) {
            System.out.println(cities[n].spendTime);
        } else
            System.out.println(-1);


    }

}

/*
题目描述
Today is Valentine’s day, and Pisces is going to date with the beautiful princess in the neighboring kingdom.
There are n cities numbered from 1 to n on the mainland, with Pisces in city 1 and the princess in city n.
There are m unidirectional roads among these n cities.
Usually, it takes Pisces 1 unit of time from one city to another,
but due to the probable existence of thorns, rivers or even robbers, some of the roads will double the time.
Pisces wants to know the minimum time that he can meet the princess.

输入
The first line contains 2 integers n (2≤n≤2∗105) and m (1≤m≤4∗105).

In each of the next m lines, there are 3 integers u, v (1≤u,v≤n) and w (1≤w≤2),
which means there is a road from u to v, and it takes w unit(s) of time for Pisces to go through.

输出
Print the minimum time in one line. Or, if he cannot reach the destination, print "-1" (without quotes).

样例输入
4 5
1 2 1
2 4 1
2 3 2
3 4 1
1 3 1
样例输出
2

 */
