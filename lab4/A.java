package lab4;//快读模板2：较慢，但有next()

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class A {

    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("lab4.C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }



    private static class Node{
        int value;
        Node pre;
        Node next;

        private Node(int value){
            this.value = value;
        }

    }

    public static class Queue{
        Node head = new Node(-1);
        Node tail = head;
        int size = 0;

        private void enQueue(int value){
            Node adder = new Node(value);
            tail.next = adder;
            adder.pre = tail;
            tail = adder;
            size += 1;
        }

        private int deQueue(){
            int reNum =  head.next.value;
            head = head.next;
            return reNum;

        }

        private boolean isEmpty(){
            return head.next == null;
        }

        private int getFront(){
            return head.next.value;
        }

        private void clear(){
            head.next = null;
            head.pre = null;
            tail = head;
        }

    }



    static class Task {

        public void solve(InputReader in, PrintWriter out) {

            Queue queue = new Queue();
            int number = in.nextInt();
            while (number > 0){
                String operate = in.next();
                switch (operate.charAt(0)){
                    case 'E':
                        int adder = in.nextInt();
                        queue.enQueue(adder);
                        break;
                    case 'D':
                        if (!queue.isEmpty()){
                            queue.deQueue();
                        }
                        break;
                    case 'A':
                        if (!queue.isEmpty()){
                            out.println(queue.getFront());
                        }
                        break;

                }
                number--;
            }
            while (!queue.isEmpty()){
                out.print(queue.deQueue());
                if (!queue.isEmpty()){
                    out.print(" ");
                }
            }
        }

    }






    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        //         public boolean hasNext() {
//             try {
//                 return reader.ready();
//             } catch(IOException e) {
//                 throw new RuntimeException(e);
//             }
//         }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }
}