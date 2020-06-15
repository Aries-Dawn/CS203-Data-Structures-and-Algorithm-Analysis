package lab4;//快读模板2：较慢，但有next()

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class C {

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
        int situation;
        Node pre;
        Node next;

        private Node(int value,int situation){
            this.value = value;
            this.situation = situation;
        }

    }

    public static class Queue{
        Node head = new Node(-1,0);
        Node tail = head;
        int size = 0;

        private void enQueue(int value,int situation){
            Node adder = new Node(value,situation);
            tail.next = adder;
            adder.pre = tail;
            tail = adder;
            size += 1;
        }

        private void deQueue(){
            int reNum =  head.next.value;
            head = head.next;
//            return reNum;

        }

        private boolean isEmpty(){
            return head.next == null;
        }

        private int getFront(){
            return head.next.value;
        }

        private int getEndValue(){
            return tail.value;
        }

        private void move(){
            tail = tail.pre;
            tail.next = null;

        }

        private int getSituation(){
            return head.next.situation;
        }

        private void clear(){
            head.next = null;
            head.pre = null;
            tail = head;
        }

    }




    static class Task {

        public void solve(InputReader in, PrintWriter out) {


//            ArrayList<Integer> big = new ArrayList<>();
            int magic = in.nextInt();
            Queue queue = new Queue();
            int input = 0,situation = 1,sum = 0;
            input = in.nextInt();
            queue.enQueue(input,1);
            while (input != -1){
                if (!queue.isEmpty() && input < queue.getEndValue())
                    queue.enQueue(input,situation);
                else {
                    while (!queue.isEmpty() && input >= queue.getEndValue())
                        queue.move();
                    queue.enQueue(input,situation);
                }
                if (situation <= queue.getSituation() + magic -1){
                    if (situation >= 5 && sum == -1) {
//                        big.add(queue.getFront());
                        sum = queue.getFront();
                    }
                    else if (situation >= magic) {
//                        big.add(queue.getFront());
                        sum = sum ^ queue.getFront();
                    }
                }
                else{
                    queue.deQueue();
//                    big.add(queue.getFront());
                    sum = sum ^ queue.getFront();
                }
                input = in.nextInt();
                situation += 1;
            }
            out.println(sum);






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