package lab2;

import java.util.Scanner;

public class F_YYJsMagicBeads1 {
    public static void main (String[] args) {
        var input = new Scanner(System.in);
        int testNum = input.nextInt();
        input.nextLine();

        for (int i=0; i<testNum; i++) {
            int n = input.nextInt();

            Pair[] a = new Pair[n];
            for (int j = 0; j < n; j++) {
                a[j] = new Pair(input.nextLong(),
                        input.nextLong());
            }

            mergeSort(a);

            // compute
            int res = 0;
            Pair pair = new Pair(0, 0);
            for (int j = 0; j < n; j++) {
                res += Pair.add(pair, a[j]);
            }

            System.out.println(res);

        }
    }

    private static class Pair implements Comparable{
        long x, y;

        Pair (long x, long y) {
            this.x = x;
            this.y = y;
        }

        // compute add and store the res into p1.
        private static long add(Pair p1, Pair p2) {
            long x = p1.x;
            long y = p2.y;
            long res;

            if (p1.y >= p2.x) {
                res = p2.x;
                y += p1.y - p2.x;
            } else {
                res = p1.y;
                x += p2.x - p1.y;
            }

            p1.x = x;
            p1.y = y;

            return res;
        }

        @Override
        public String toString() {
            return "("+ x + "," + y + ")";
        }

        @Override
        public int compareTo(Object obj) {
            if (!(obj instanceof Pair))
                throw new IllegalArgumentException();
            Pair p = (Pair) obj;
            if (x < y) {
                if (p.x < p.y) {
                    return compare1(this, p);
                    // return Long.compare(x, p.x);
                } else {
                    return -1;
                }
            } else if(x > y) {
                if (p.x > p.y) {
                    return compare2(this, p);
                    // return Long.compare(p.y, y);
                } else {
                    return 1;
                }
            } else {
                if (p.x < p.y) {
                    return 1;
                } else if (p.x > p.y) {
                    return -1;
                } else {
                    return Long.compare(x, p.x);
                }
            }
        }
        int compare1(Pair p1, Pair p2){
            int tmp = Long.compare(p1.x, p2.x);
            if (tmp != 0) { return tmp; }
            else { return -Long.compare(p1.y, p2.y); }
        }
        int compare2(Pair p1, Pair p2){
            int tmp = Long.compare(p1.y, p2.y);
            if (tmp != 0) { return tmp; }
            else { return Long.compare(p1.x, p2.x); }
        }
    }

    private static <T extends Comparable> void mergeSort(T[] ary) {
        var h = new Object() {
            void recurse(T[] ary, int lo, int hi) {
                if (lo == hi) { return; }
                int mi = (lo + hi) / 2;

                recurse(ary, lo, mi);
                recurse(ary, mi + 1, hi);

                Object[] res = new Object[hi - lo + 1];
                int i = lo;
                int j = mi + 1;
                int k = 0;

                while (i <= mi && j <= hi) {
                    int tmp = ary[i].compareTo(ary[j]);
                    if (tmp < 0) {
                        res[k++] = ary[i++];
                    } else{
                        res[k++] = ary[j++];
                    }
                }

                while (i <= mi) { res[k++] = ary[i++]; }
                while (j <= hi) { res[k++] = ary[j++]; }

                for (int i1 = 0; i1 < k; i1++) {
                    ary[lo + i1] = (T) res[i1];
                }
            }
        };

        h.recurse(ary, 0, ary.length - 1);
    }
}
