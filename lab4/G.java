package lab4;

import java.io.*;
import java.math.*;
import java.util.*;

public class G {

    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);

        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {
        static char[] arr;

        public void solve(InputReader in, PrintWriter out) {
            int times = in.nextInt();
            int[] level = new int[200];
            level[38] = 5;// &
            level[40] = 9;// (
            level[41] = 9;// )
            level[42] = 3;// *
            level[43] = 4;// +
            level[45] = 4;// -
            level[94] = 6;// ^
            level[95] = 2;// _ -> negative
            level[124] = 7;// |
            level[126] = 2;// ~
            for (int t = 0; t < times; t++) {
                String s = in.next();
                arr = s.toCharArray();
                char[] oStack = new char[arr.length];
                int oTop = -1;
                String[] r = new String[arr.length];
                int rTop = -1;
                int i = 0;
                while (i < arr.length) {
                    if (0 <= arr[i] - '0' && arr[i] - '0' <= 9) {
                        r[++rTop] = "" + (arr[i] - '0');
                    } else {
                        switch (arr[i]) {
                            case '(':
                                oStack[++oTop] = '(';
                                break;
                            case ')':
                                while (oStack[oTop] != '(') {
                                    r[++rTop] = "" + oStack[oTop--];
                                }
                                oTop--;
                                break;
                            case '+':
                                if (i - 1 < 0 || ((arr[i - 1] - '0' < 0 || arr[i - 1] - '0' > 9) && arr[i - 1] != ')'))
                                    break;
                                else {
                                    while (oTop >= 0 && level[oStack[oTop]] <= level[arr[i]]) {
                                        r[++rTop] = "" + oStack[oTop--];
                                    }
                                    oStack[++oTop] = '+';
                                }
                                break;
                            case '-':
                                if (i - 1 < 0 || ((arr[i - 1] - '0' < 0 || arr[i - 1] - '0' > 9) && arr[i - 1] != ')'))
                                    oStack[++oTop] = '_';
                                else {
                                    while (oTop >= 0 && level[oStack[oTop]] <= level[arr[i]]) {
                                        r[++rTop] = "" + oStack[oTop--];
                                    }
                                    oStack[++oTop] = '-';
                                }
                                break;
                            default:
                                if (arr[i] != '~')
                                    while (oTop >= 0 && level[oStack[oTop]] <= level[arr[i]]) {
                                        r[++rTop] = "" + oStack[oTop--];
                                    }
                                oStack[++oTop] = arr[i];
                        }
                    }
                    i++;
                }
                while (oTop >= 0) {
                    r[++rTop] = oStack[oTop--] + "";
                }
//				for(int j=0;j<=rTop;j++) {
//					out.print(r[j]);
//				}
                //out.println();
                int re = calculate(r, rTop);
                out.println(re);
            }
        }

        int calculate(String[] s, int sTop) {
            int[] cStack = new int[s.length];
            int cTop = -1;
            int i = 0;
            while (i <= sTop) {
                switch (s[i].charAt(s[i].length() - 1)) {
                    case '|':
                        cStack[cTop - 1] = cStack[cTop] | cStack[cTop - 1];
                        cTop--;
                        break;
                    case '^':
                        cStack[cTop - 1] = cStack[cTop] ^ cStack[cTop - 1];
                        cTop--;
                        break;
                    case '&':
                        cStack[cTop - 1] = cStack[cTop] & cStack[cTop - 1];
                        cTop--;
                        break;
                    case '_':
                        cStack[cTop] = -cStack[cTop];
                        break;
                    case '~':
                        cStack[cTop] = ~cStack[cTop];
                        break;
                    case '+':
                        cStack[cTop - 1] = cStack[cTop] + cStack[cTop - 1];
                        cTop--;
                        break;
                    case '-':
                        cStack[cTop - 1] = cStack[cTop - 1] - cStack[cTop];
                        cTop--;
                        break;
                    case '*':
                        cStack[cTop - 1] = cStack[cTop] * cStack[cTop - 1];
                        cTop--;
                        break;
                    default:
                        cStack[++cTop] = Integer.parseInt(s[i]);
                }
                i++;
            }
            if(cTop!=0) throw new RuntimeException();
            return cStack[0];
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