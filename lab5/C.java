////快读模板2：较慢，但有next()
//
//import java.io.*;
//import java.math.*;
//import java.util.*;
//
//public class C {
//
//    public static void main(String[] args) {
//        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
//        OutputStream outputStream = System.out;
//        InputReader in = new InputReader(inputStream);
//        PrintWriter out = new PrintWriter(outputStream);
//        Task solver = new Task();
//        solver.solve(in, out);
//        out.close();
//    }
//
//    static class Task {
//
//        public void solve(InputReader in, PrintWriter out) {
//
//
//
//
//
//            int cases = in.nextInt();
//            int sum = 0;
//            while (cases > 0){
//                String pattern = in.next();
//                String txt = in.next();
//                int length = pattern.length();
//                double third = (double)length / 3;
//                int thirds = length / 3;
//                if (third != thirds){
//                    thirds++;
//                }
//                pattern = pattern.substring(0,thirds);
//                if (KMP(txt,pattern)){
//                    sum++;
//                }
////        System.out.println(KMP(txt,pattern));
//
//                cases--;
//            }
//            System.out.println(sum);
//
//
//
//
//
//
//
//
//
//
//
//        }
//
//    }
//
//
//
//
//
//
//
//
//    private static boolean KMP(String txt,String pattern){
//
//        int m = txt.length();
//        int n = pattern.length();
//        int[] next = nextArray(pattern);
//        int q = 0;
//        for (int i = 0;i < m;i++){
//            while (q > 0 && pattern.charAt(q) != txt.charAt(i)){
//                q = next[q];
//            }
//            if ( pattern.charAt(q) == txt.charAt(i))
//                q++;
//            if (q == n){
//                return true;
//            }
//        }
//        return false;
//
//    }
//
//    private static int[] nextArray(String pattern){
//
//        int[] next = new int[pattern.length()];
//        next[0] = 0;
//        int j = 0;
//        for(int i = 1; i < pattern.length(); i++){
//            while(j > 0 && pattern.charAt(j) != pattern.charAt(i)){
//                j = next[j - 1];
//            }
//            if(pattern.charAt(i) == pattern.charAt(j)){
//                j++;
//            }
//            next[i] = j;
//        }
//        return next;
////        for (int j = 1;j < pattern.length() - 1;j++){
////            while (k > 0 && pattern.charAt(k + 1) != pattern.charAt(j)){
////                k = next[k];
////            }
////            if (pattern.charAt(k + 1) == pattern.charAt(j))
////                k++;
////            next[j] = k;
////        }
////
////        return next;
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    static class InputReader {
//        public BufferedReader reader;
//        public StringTokenizer tokenizer;
//
//        public InputReader(InputStream stream) {
//            reader = new BufferedReader(new InputStreamReader(stream), 32768);
//            tokenizer = null;
//        }
//
//        public String next() {
//            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
//                try {
//                    tokenizer = new StringTokenizer(reader.readLine());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            return tokenizer.nextToken();
//        }
//
//        public int nextInt() {
//            return Integer.parseInt(next());
//        }
//
//        public long nextLong() {
//            return Long.parseLong(next());
//        }
//
//        public double nextDouble() {
//            return Double.parseDouble(next());
//        }
//
//        public char[] nextCharArray() {
//            return next().toCharArray();
//        }
//
//        //         public boolean hasNext() {
////             try {
////                 return reader.ready();
////             } catch(IOException e) {
////                 throw new RuntimeException(e);
////             }
////         }
//        public boolean hasNext() {
//            try {
//                String string = reader.readLine();
//                if (string == null) {
//                    return false;
//                }
//                tokenizer = new StringTokenizer(string);
//                return tokenizer.hasMoreTokens();
//            } catch (IOException e) {
//                return false;
//            }
//        }
//
//        public BigInteger nextBigInteger() {
//            return new BigInteger(next());
//        }
//
//        public BigDecimal nextBigDecinal() {
//            return new BigDecimal(next());
//        }
//    }
//}