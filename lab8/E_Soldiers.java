package lab8;

import java.util.ArrayList;
import java.util.Scanner;

public class E_Soldiers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while (cases > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            ArrayList<Soldier>[] goTo = new ArrayList[n + 1];
            ArrayList<Soldier>[] comeFrom = new ArrayList[n + 1];
            Soldier [] point = new Soldier [n + 1];
            ArrayList<Soldier> out0Point = new ArrayList<>();
            point[0] = new Soldier (-1);
            for (int i = 0; i < n + 1; i++) {
                goTo[i] = new ArrayList<>();
                comeFrom[i] = new ArrayList<>();
                point[i] = new Soldier (i);
            }
            for (int i = 0; i < m; i++) {
                int first = sc.nextInt();
                int second = sc.nextInt();
                point[first].out++;
                point[second].in++;
                goTo[first].add(point[second]);
                comeFrom[second].add(point[first]);
            }
            for (int i = 1;i < point.length;i++){
                if (point[i].out == 0)
                    out0Point.add(point[i]);
            }
            Queue s = new Queue();
            ArrayList<Integer> output = new ArrayList<>();
            Topological(out0Point,s,comeFrom,point);
            while (!s.isEmpty()){
                output.add(s.deQueue().value);
            }
            for (int i = output.size() - 1;i >= 0;i--){
                System.out.print(output.get(i));
                if (i > 0)
                    System.out.print(" ");
            }
            System.out.println();
            cases--;
        }
    }


    public static void Topological(ArrayList<Soldier> out0Point,Queue s,ArrayList<Soldier>[] cun,Soldier [] point){
        for (int i = 1;i < point.length;i++){
            int index = findMax(out0Point);
            Soldier temp = point[out0Point.get(index).value];
            s.enQueue(temp);
            out0Point.remove(index);
            temp.color = 1;
            for (Soldier te : cun[temp.value]){
                if (te.color == 0){
                    te.out--;
                }
                if (te.out == 0)
                    out0Point.add(te);
            }
        }
//        Soldier temp = point[index];
//        if (temp.out == 0)
//            s.enQueue(temp);
//        while (!s.isEmpty()){
//            Soldier t = s.deQueue();
//            t.color = 1;
//            for (Soldier te : cun[t.value]){
//                if (te.color == 0){
//                    te.out--;
//                    if (te.out == 0){
//                        te.color = 1;
//                        s.enQueue(te);
//                    }
//                }
//            }
//            index = findMax(point);
//            temp = point[index];
//        }

    }


    static class Queue{
        SoldierOfQueue head = new SoldierOfQueue(new Soldier(-1));
        SoldierOfQueue tail = head;
        int size = 0;
        Queue(){

        }

        private void clear(){
            head = new SoldierOfQueue(new Soldier(-1));
            tail = head;
        }
        private void enQueue(Soldier value){
            SoldierOfQueue t = new SoldierOfQueue(value);
            tail.next = t;
            tail = t;
            size ++;
        }

        private boolean isEmpty(){
            return head.next == null;
        }

        private Soldier deQueue(){
            if (!isEmpty()){
                SoldierOfQueue temp = head.next;
                head = head.next;
                size--;
                return temp.value;
            }
            else
                return null;

        }
    }


    static class SoldierOfQueue{
        Soldier value;
        SoldierOfQueue next;

        SoldierOfQueue(Soldier value){
            this.value = value;
        }
    }
    
    
    static int findMax(ArrayList<Soldier> point){
        int max = 0;
        int index = 0;
        for (int i = 0;i < point.size();i++){
            if (point.get(i).value > max){
                max = point.get(i).value;
                index = i;
            }
        }
        return index;
    }
    
    static class Soldier {
        int value;
        int color;
        int in;
        int out;
        Soldier  next;

        Soldier (int value){
            this.value = value;
        }

        void copyFrom(Soldier  oldSoldier ){
            this.next = oldSoldier .next;
            this.value = oldSoldier .value;
            this.in = oldSoldier .in;
            this.out = oldSoldier .out;
            this.color = oldSoldier .color;

        }

        void copyTo(Soldier  newOne){
            newOne.value = this.value;
            newOne.color = this.color;
            newOne.in = this.in;
            newOne.out = this.out;
            newOne.next = this.next;

        }
    }
}
