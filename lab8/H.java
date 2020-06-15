//快读模板1：更快，但没有next()用于读字符串
package lab8;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class H {

    public static void main(String[] args) throws IOException {
        Reader sc=new Reader();
        PrintWriter out=new PrintWriter(System.out);


        int cases = sc.nextInt();
        while (cases > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            ArrayList<Node>[] cun = new ArrayList[n + 1];
            Node[] point = new Node[n + 1];
            point[0] = new Node(-1);
            for (int i = 0;i < n + 1;i++){
                cun[i] = new ArrayList<>();
                point[i] = new Node(i);
            }
            for (int i = 0;i < m;i++){
                int first = sc.nextInt();
                int second = sc.nextInt();
                point[first].out++;
                point[second].in++;
                cun[first].add(point[second]);
            }
            boolean yes = false;
            Queue s = new Queue();
//            Queue re = new Queue();
            for (Node nn : point){
                if (nn.in == 0)
                    s.enQueue(nn);
            }
            Topological(s,cun);
            Node[] newPoint = new Node[n + 1];
            for (int i = 0;i < point.length;i++){
                newPoint[i] = new Node(0);
                point[i].copyTo(newPoint[i]);
            }
            for (int i = 1;i < point.length;i++){
//                if (hasNot0(point)){
////                    while (!re.isEmpty()){
////                        Node temp = re.deQueue();
////                        temp.in++;
////                    }
//                }
//                else{
//                    yes = true;
//                    break;
//                }
                s = new Queue();
//                re = new Queue();
                boolean key = false;
                if (point[i].in == 1){
                    point[i].in--;
                    key = true;
                    s.enQueue(point[i]);
                }
                Topological(s,cun);
                if (hasNot0(point)){
                    if (key)
                        point[i].in++;
                    for (int j = 0;j < point.length;j++){
                        point[j].copyFrom(newPoint[j]);
                    }
//                    while (!re.isEmpty()){
//                        Node temp = re.deQueue();
//                        temp.in++;
//                    }
//                    for (int j = 0;j < point.length;j++){
//                        point[j].color = 0;
//                    }
                }
                else{
                    yes = true;
                    break;
                }
            }
            if (yes)
                System.out.println("Yes");
            else
                System.out.println("No");
            cases--;
        }


        out.close();
    }


    public static boolean hasNot0(Node[] point){
        for (Node n : point){
            if (n.in > 0){
                return true;
            }
        }
        return false;
    }

    public static void Topological(Queue s, ArrayList<Node>[] cun){

        while (!s.isEmpty()){
            Node temp = s.deQueue();
            temp.color = 2;
            for (Node te : cun[temp.value]){
                if (te.color == 0){
                    te.in--;
//                    re.enQueue(te);
                    if (te.in == 0){
                        te.color = 1;
                        s.enQueue(te);
                    }
                }
            }
        }

    }

    static class Queue{
        NodeOfQueue head = new NodeOfQueue(new Node(-1));
        NodeOfQueue tail = head;
        int size = 0;
        Queue(){

        }

        private void clear(){
            head = new NodeOfQueue(new Node(-1));
            tail = head;
        }
        private void enQueue(Node value){
            NodeOfQueue t = new NodeOfQueue(value);
            tail.next = t;
            tail = t;
            size ++;
        }

        private boolean isEmpty(){
            return head.next == null;
        }

        private Node deQueue(){
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
        Node value;
        NodeOfQueue next;

        NodeOfQueue(Node value){
            this.value = value;
        }
    }

    static class NodeOfLinkedList{
        Node value;
        NodeOfLinkedList next;

        NodeOfLinkedList(Node value){
            this.value = value;
        }
    }

    static class LinkedList{
        int size = 0;
        NodeOfLinkedList head = new NodeOfLinkedList(null);
        NodeOfLinkedList tail = head;

        void add(Node temp){
            NodeOfLinkedList value = new NodeOfLinkedList(temp);
            tail.next = value;
            tail = value;
            size ++;
        }

        void add(int value){
            NodeOfLinkedList temp = new NodeOfLinkedList(new Node(-1));
            tail.next = temp;
            tail = temp;
            size++;
        }

        Node remove(){
            NodeOfLinkedList temp = head.next;
            head = head.next;
            size--;
            return temp.value;

        }

        Node getI(int key){
            NodeOfLinkedList temp = getHead();
            for (int i = 0;i < key && temp.next != null;i++){
                temp = temp.next;
            }
            return temp.value;
        }

        NodeOfLinkedList getHead(){
            return head.next;
        }
        boolean isEmpty(){
            return head.next == null;
        }


    }

    static class Node{
        int value;
        int color;
        int in;
        int out;
        Node next;

        Node(int value){
            this.value = value;
        }

        void copyFrom(Node oldNode){
            this.next = oldNode.next;
            this.value = oldNode.value;
            this.in = oldNode.in;
            this.out = oldNode.out;
            this.color = oldNode.color;

        }

        void copyTo(Node newOne){
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
