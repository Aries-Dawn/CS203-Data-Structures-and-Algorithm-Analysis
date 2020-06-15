package lab9;
//快读模板1：更快，但没有next()用于读字符串

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class D {

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        PrintWriter out=new PrintWriter(System.out);

//        int size = sc.nextInt();
//        while (size > 0){

            int n,m;
            n = sc.nextInt();
            m = sc.nextInt();
//        Random rr = new Random();
//        n = rr.nextInt(50);
//        m = rr.nextInt(20);
//        System.out.println(n + " " + m);
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
//                    long coefficient = rr.nextInt(5000);
                    point[k] = new Node(k,coefficient);
//                    System.out.print(coefficient + " ");
                    C[i][j] = point[k];
                    k++;
                }
//                System.out.println();
            }
//        ArrayList<Node>[] edges = new ArrayList[m * n];
            for (int i = 0;i < C.length;i++){
                for (int j = 0;j < C[i].length;j++){
//                (i−1,j), (i+1,j), (i,j−1) or (i,j+1)
//                if (i - 1 < n && i - 1 >= 0 && j < m && j >= 0)
//                    edges.get(C[i][j].value).add(new Edge(C[i][j],C[i][j].coefficient * C[i - 1][j].coefficient,C[i - 1][j]));
                    if (i + 1 < n && i + 1 >= 0 && j < m && j >= 0){
                        edges.get(C[i][j].value).add(new Edge(C[i][j],(long)C[i][j].coefficient * (long)C[i + 1][j].coefficient,C[i + 1][j]));
                        edges.get(C[i + 1][j].value).add(new Edge(C[i + 1][j],C[i][j].coefficient * C[i + 1][j].coefficient,C[i][j]));

                    }
//                if (i < n && i >= 0 && j - 1 < m && j - 1 >= 0)
//                    edges.get(C[i][j].value).add(new Edge(C[i][j],C[i][j].coefficient * C[i][j - 1].coefficient,C[i][j - 1]));
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


        out.close();
    }



    static long MST(Node begin,ArrayList<ArrayList<Edge>> edges, Node[] point){
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
