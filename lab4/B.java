package lab4;//快读模板2：较慢，但有next()

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class B {

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
        char value;
        Node pre;
        Node next;

        private Node(char value){
            this.value = value;
        }

    }

    public static class Queue{
        Node head = new Node('0');
        Node tail = head;
        int size = 0;

        private void enQueue(char value){
            Node adder = new Node(value);
            tail.next = adder;
            adder.pre = tail;
            tail = adder;
            size += 1;
        }

        private char deQueue(){
            char reNum =  tail.value;
            tail = tail.pre;
            tail.next = null;
            return reNum;

        }

        private boolean isEmpty(){
            return head.next == null;
        }

        private char getFront(){
            return tail.value;
        }

        private void clear(){
            head.next = null;
            head.pre = null;
            head.value = '0';
            tail = head;
        }

    }



    static class Task {

        public void solve(InputReader in, PrintWriter out) {

            int cases = in.nextInt();
            Queue queue = new Queue();
            flags:
            while (cases > 0){
                int length = in.nextInt();
                String brackets = in.next();
                for (int i = 0;i < brackets.length();i++){
                    if (brackets.charAt(i) == '(' || brackets.charAt(i) == '[' || brackets.charAt(i) == '{')
                        queue.enQueue(brackets.charAt(i));
                    else{
                        switch (brackets.charAt(i)){
                            case ')':
                                if (!queue.isEmpty() && queue.getFront() == '(')
                                    queue.deQueue();
                                else{
                                    System.out.printf("NO\n");
                                    cases--;
                                    queue.clear();
                                    continue flags;
                                }
                                break;
                            case ']':
                                if (!queue.isEmpty() && queue.getFront() == '[')
                                    queue.deQueue();
                                else {
                                    System.out.printf("NO\n");
                                    cases--;
                                    queue.clear();
                                    continue flags;
                                }
                                break;
                            case '}':
                                if (!queue.isEmpty() && queue.getFront() == '{')
                                    queue.deQueue();
                                else{
                                    System.out.printf("NO\n");
                                    cases--;
                                    queue.clear();
                                    continue flags;
                                }
                                break;
                        }
                    }
                }
                if (queue.isEmpty())
                    System.out.printf("YES\n");
                else
                    System.out.printf("NO\n");
                queue.clear();
                cases--;
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