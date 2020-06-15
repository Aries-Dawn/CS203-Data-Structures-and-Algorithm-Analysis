package lab8;

import java.util.*;

public class F_Palace {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases > 0){
            int size = sc.nextInt();
            Material[] materials = new Material[size + 1];
            ArrayList<Material>[] edge = new ArrayList[size + 1];
            ArrayList<Material> top = new ArrayList<>();
            for (int i = 1;i < size + 1;i++){
                int a = sc.nextInt();
                int b = sc.nextInt();
                int c = sc.nextInt();
                materials[i] = new Material(a,b,c,i);
                edge[i] = new ArrayList<>();
            }
            for (int i = 1;i < size + 1;i++){
                for (int j = i;j < size+ 1;j++){
                    if (materials[i].bigger(materials[j])) {
                        edge[i].add(materials[j]);
                        materials[i].out++;
                        materials[j].in++;
                    }
                    else if (materials[i].smaller(materials[j])){
                        edge[j].add(materials[i]);
                        materials[j].out++;
                        materials[i].in++;
                    }
                }
            }
            for (int i = 1;i < size + 1;i++){
                if (materials[i].in == 0){
                    top.add(materials[i]);
                }
            }
            long Max = 0;
            for (Material m : top){
                Queue s = new Queue();
                m.color = 1;
                s.enQueue(m);
                Topological(s,edge);
            }
            for (Material material : materials){
                if (material != null && material.high > Max)
                    Max = material.high;
            }
            System.out.println(Max);
            cases--;
        }

    }


    public static void Topological(Queue s,ArrayList<Material>[] cun){
        while (!s.isEmpty()){
            Material temp = s.deQueue();
            temp.color = 2;
            for (Material te : cun[temp.index]){
                if (te.color == 0){
                    te.in--;
                    if (temp.high + te.c > te.high)
                        te.high = temp.high + te.c;
                    if (te.in == 0){
                        te.color = 1;
                        s.enQueue(te);
                    }
                }
            }
        }

    }

    static class Queue{
        NodeOfQueue head = new NodeOfQueue(new Material());
        NodeOfQueue tail = head;
        int size = 0;
        Queue(){

        }

        private void clear(){
            head = new NodeOfQueue(new Material());
            tail = head;
        }
        private void enQueue(Material value){
            NodeOfQueue t = new NodeOfQueue(value);
            tail.next = t;
            tail = t;
            size ++;
        }

        private boolean isEmpty(){
            return head.next == null;
        }

        private Material deQueue(){
            if (!isEmpty()){
                NodeOfQueue temp = head.next;
                head = head.next;
                size--;
                return temp.value;
            }
            else
                return null;

        }
    }


    static class NodeOfQueue{
        Material value;
        NodeOfQueue next;

        NodeOfQueue(Material value){
            this.value = value;
        }
    }
    
    static class Material{
        int color;
        int a;
        int b;
        int c;
        int in;
        int out;
        long high;
        int index;

        public boolean bigger(Material temp){
            return (a < temp.a && b < temp.b) || (a < temp.b && b < temp.a);
        }

        public boolean smaller(Material temp){
            return (a > temp.a && b > temp.b) || (a > temp.b && b > temp.a);
        }
        Material(){}
        Material(int a,int b,int c,int index){
            this.a = a;
            this.b = b;
            this.c = c;
            high = (long)this.c;
            this.index = index;
        }
    }
}

//
//    问题 F: Palace
//        时间限制: 1 Sec  内存限制: 128 MB
//        提交: 430  解决: 90
//        [提交][状态][讨论版]
//        题目描述
//        To celebrate the victory of the war, Pisces has decided to build a splendid palace.
//        The craftsmen has brought back n kinds of cube materials from the dwarf kingdom.
//        The length, width, and height of each material are a, b and c respectively.
//        To make the palace magnificent, the craftsmen have to stack these materials together.
//        Material i can be stacked on material j if and only if ai<aj⋂bi<bj, or ai<bj⋂bi<aj.
//        Pisces wants to know how high these materials can stack at most.
//        输入
//        The first line contains an integer T (1≤T≤10), which denotes the number of test cases.
//
//        For each of the test cases, the first line contains an integer n (1≤n≤2∗103), which represents the number of materials. Each of the next n lines contains 3 integers a, b and c (1≤a,b,c≤1000), which represents the size of a material.
//
//        输出
//        For each test case, print the maximum height.
//
//        样例输入
//        1
//        3
//        2 3 5
//        4 3 4
//        3 3 3
//        样例输出
//        9
//        提示
