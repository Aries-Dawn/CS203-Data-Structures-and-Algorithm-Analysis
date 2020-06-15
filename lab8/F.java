//快读模板1：更快，但没有next()用于读字符串
package lab8;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class F {

    public static void main(String[] args) throws IOException {
        Reader sc=new Reader();
        PrintWriter out=new PrintWriter(System.out);




        int cases = sc.nextInt();
        while (cases > 0){
            int size = sc.nextInt();
            Material[] materials = new Material[size + 1];
            ArrayList<Material>[] edge = new ArrayList[size + 1];
            PriorityQueue<Material>[] pq = new PriorityQueue[size + 1];
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





        out.close();
    }



    public static long Topological(Queue s, ArrayList<Material>[] cun){
        long max = 0;
        while (!s.isEmpty()){
            Material temp = s.deQueue();
            temp.color = 2;
            max += (long) temp.c;
            for (Material te : cun[temp.index]){
                if (te.color == 0){
                    te.in--;
                    if (te.in == 0){
                        te.color = 1;
                        s.enQueue(te);
                        te.high += temp.high;
                    }
                }
            }
        }
        return max;

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
            return a < temp.a && b < temp.b;
        }

        public boolean smaller(Material temp){
            return a > temp.a && b > temp.b;
        }
        Material(){}
        Material(int a,int b,int c,int index){
            this.a = Math.min(a,b);
            this.b = Math.max(a,b);
            this.c = c;
            high = (long) this.c;
            this.index = index;
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
