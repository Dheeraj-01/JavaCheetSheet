import java.util.*;
import java.io.*;

public class _152 {
    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            String s = sc.next();
            int [] nextOne = new int[n];
            int [] prevZero = new int[n];
            int cur = -1;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) - '0' == 0) {
                    cur = i;
                }
                prevZero[i] = cur;
            }
            cur = -1;
            for (int i = n - 1; i >= 0; i--) {
                if (s.charAt(i) - '0' == 1) {
                    cur = i;
                }
                nextOne[i] = cur;
            }
            Set<Pair> set = new HashSet<>();
            int def = 0;
            for (int i = 0; i < m; i++) {
                int l = sc.nextInt() - 1;
                int r = sc.nextInt() - 1;
                if (nextOne[l] == -1 || prevZero[r] == -1) def = 1;
                else if (nextOne[l] != -1 && nextOne[l] > r) def = 1;
                else if (prevZero[r] != -1 && prevZero[r] < l) def = 1;
                else if (prevZero[r] < nextOne[l]) def = 1;
                else set.add(new Pair(nextOne[l], prevZero[r]));
            }
            out.println(set.size() + def);
        }
        out.close();
    }

    static class Pair {
        int left; int right;
        Pair(int left, int right) {
            this.left = left; this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return left == pair.left && right == pair.right;
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, right);
        }
    }




    static void sort(int[] a) {
        ArrayList<Integer> q = new ArrayList<>();
        for (int i : a) q.add(i);
        Collections.sort(q);
        for (int i = 0; i < a.length; i++) a[i] = q.get(i);
    }

    static void sort(long[] a) {
        ArrayList<Long> q = new ArrayList<>();
        for (long i : a) q.add(i);
        Collections.sort(q);
        for (int i = 0; i < a.length; i++) a[i] = q.get(i);
    }


    //-----------MyScanner class for faster input----------
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }


    }

}
