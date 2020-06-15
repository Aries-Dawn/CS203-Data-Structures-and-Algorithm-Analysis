
package lab8;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class E {

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        PrintWriter out=new PrintWriter(System.out);




        int cases = sc.nextInt();
        while (cases > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            ArrayList<Soldier>[] goTo = new ArrayList[n + 1];
            ArrayList<Soldier>[] comeFrom = new ArrayList[n + 1];
            Soldier[] point = new Soldier[n + 1];
            ArrayList<Soldier> out0Point = new ArrayList<>();
            point[0] = new Soldier(-1);
            for (int i = 0; i < n + 1; i++) {
                goTo[i] = new ArrayList<>();
                comeFrom[i] = new ArrayList<>();
                point[i] = new Soldier(i);
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




        out.close();
    }


    public static void Topological(ArrayList<Soldier> out0Point, Queue s, ArrayList<Soldier>[] cun, Soldier[] point){
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
        Soldier next;

        Soldier (int value){
            this.value = value;
        }

        void copyFrom(Soldier oldSoldier ){
            this.next = oldSoldier .next;
            this.value = oldSoldier .value;
            this.in = oldSoldier .in;
            this.out = oldSoldier .out;
            this.color = oldSoldier .color;

        }

        void copyTo(Soldier newOne){
            newOne.value = this.value;
            newOne.color = this.color;
            newOne.in = this.in;
            newOne.out = this.out;
            newOne.next = this.next;

        }
    }


    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

}
