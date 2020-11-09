
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.StringTokenizer;




 class A {

	public static void main(String[] args) throws IOException {

		FastScanner sc = new FastScanner();
		int t = sc.nextInt();
		PrintWriter out = new PrintWriter(System.out);
		while(t-- != 0) {
//			String str = sc.nextLine();
			int n = sc.nextInt();
			int arr[] = sc.readArray(n);
			
			BIT bit=new BIT(n*2);
			long inv=0;
			for (int i=n-1; i>=0; i--) {
				inv+=bit.read(arr[i]-1);
				bit.update(arr[i]-1, 1);
//				print(bit);
			}
			out.println(inv);
		}
		out.close();
		
	}
	
	static class BIT {
		int n;
		int[] tree;
		
		public BIT(int n) {
			this.n = n;
			tree = new int[n + 2];
		}
		
		int read(int i) {
			i++;
			int sum = 0;
			while (i > 0) {
				sum += tree[i];
				i -= i & -i;
			}
			System.out.println(Arrays.toString(tree));
			return sum;
		}
		
		void update(int i, int val) {
			i++;
			while (i <= n) {
				tree[i] += val;
				i += i & -i;
			}
		}

		// if the BIT is a freq array, returns the
		// index of the kth item, or n if there are fewer
		// than k items.
		int getKth(int k) {
			int e=Integer.highestOneBit(n), o=0;
			for (; e!=0; e>>=1) {
				if (e+o<=n && tree[e+o]<=k) {
					k-=tree[e+o];
					o+=e;
				}
			}
			return o;
		}
		
	}
}
