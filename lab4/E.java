package lab4;//快读模板2：较慢，但有next()

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class E {

    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("lab4.C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }







    static class Node{
        int value;
        int situation;
        Node pre;
        Node next;

        private Node(int value,int situation){
            this.value = value;
            this.situation = situation;
        }

    }


    static class Stack{
        Node head = new Node(-1,-1);
        Node tail = head;

        private void push(int value,int situation){
            Node temp = new Node(value,situation);
            tail.next = temp;
            temp.pre = tail;
            tail = temp;

        }

        private int pop (){
            int temp = tail.situation;
            tail = tail.pre;
            tail.next = null;
            return temp;
        }

        private int getPeakSituation(){
            return tail.situation;
        }
        private  Node getPeak(){return tail;}
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







    static class Task {

        public void solve(InputReader in, PrintWriter out) {


            int cases = in.nextInt();
            Stack stack = new Stack();
            for (int k = 1; k <= cases; k++) {
                int size = in.nextInt();
                int[] students = new int[size];
                int[][] partner = new int[size][2];

                for (int i = 0; i < size; i++) {
                    students[i] = in.nextInt();
                }

                for (int i = 0; i < size; i++) {
                    if (i == 0 || students[i] < stack.peak()) {
                        stack.push(students[i], i);
                    } else {
                        while (!stack.isEmpty() && students[i] > stack.peak()) {
                            int situation = stack.pop();
                            if (!stack.isEmpty()) {
                                partner[stack.getPeakSituation()][1] = situation + 1;

                            }
                        }
                        stack.push(students[i], i);
                    }
                }
                while (!stack.isEmpty()) {
                    int situation = stack.pop();
                    if (!stack.isEmpty()) {
                        partner[stack.getPeakSituation()][1] = situation + 1;
                    }
                }
                stack.clear();

                for (int i = size - 1; i >= 0; i--) {
                    if (i == size - 1 || students[i] < stack.peak()) {
                        stack.push(students[i], i);
                    } else {
                        while (!stack.isEmpty() && students[i] > stack.peak()) {
                            int situation = stack.pop();
                            if (!stack.isEmpty()) {
                                partner[stack.getPeakSituation()][0] = situation + 1;

                            }
                        }
                        stack.push(students[i], i);
                    }
                }
                while (!stack.isEmpty()) {
                    int situation = stack.pop();
                    if (!stack.isEmpty()) {
                        partner[stack.getPeakSituation()][0] = situation + 1;
                    }
                }
                stack.clear();

                out.println("Case " + k + ":");
                for (int[] temp : partner)
                    out.println(temp[0] + " " + temp[1]);

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