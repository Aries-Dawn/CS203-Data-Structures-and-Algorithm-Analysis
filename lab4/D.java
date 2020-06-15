package lab4;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class D {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);


        int cases = in.nextInt();
        Stack stack = new Stack();
        while (cases > 0){
            int target = 1;
            int size = in.nextInt();
            int count = 0;
            boolean[] contains = new boolean[size + 1];
            StringBuilder sb = new StringBuilder();
            flags:
            for (int i = 0;i < size;i++){
                int temp = in.nextInt();

                stack.push(temp);
                contains[temp] = true;
                while (true){
                    while (!stack.isEmpty() && (stack.peak() == target || stack.peak() < target)){
                        int pop = stack.pop();
                        contains[pop] = false;
                        sb.append(pop);
//                        System.out.print(pop);
                        if (count != size - 1 ){
//                            System.out.print(" ");
                            sb.append(" ");
                        }
                        count++;
                        target += 1;
                    }
                    if (!stack.isEmpty() && !contains[target]){
                        continue flags;
                    }
                    while (!stack.isEmpty() && contains[target]){
                        target += 1;
                        break;
                    }
                    if (stack.isEmpty())
                        break;
                }
            }


            out.println(sb.toString());
            cases--;
            stack.clear();
        }



        out.close();
    }







    static class Node{
        int value;
        Node pre;
        Node next;

        private Node(int value){
            this.value = value;
        }

    }


    static class Stack{
        Node head = new Node(-1);
        Node tail = head;

        private void push(int value){
            Node temp = new Node(value);
            tail.next = temp;
            temp.pre = tail;
            tail = temp;

        }

        private int pop (){
            int temp = tail.value;
            tail = tail.pre;
            tail.next = null;
            return temp;
        }

        private int peak(){
            return tail.value;
        }

        private boolean isEmpty(){
            return head.next == null;
        }

        private void clear(){
            head.value = -1;
            head.next = null;
            head.pre = null;
            tail = head;
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
