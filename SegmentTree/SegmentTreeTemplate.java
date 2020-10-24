package SegmentTree;

import java.util.Scanner;

public class Template {

	static int MAX = 100001;
	static int arr[] = new int[MAX];
	static int st[] = new int[4 * MAX];

	// Build A Segment Tree
	private static void build(int si, int ss, int se) {

		if (ss == se) {
			st[si] = arr[ss];// base Condition
			return;
		}

		int mid = ss + (se - ss) / 2;
		build(si * 2, ss, mid);
		build(si * 2 + 1, mid + 1, se);

		st[si] = Math.min(st[si * 2], st[si * 2 + 1]);// According to you
	}

	// Answer the Query
	private static int query(int si, int ss, int se, int qs, int qe) {

		if (ss > qe || se < qs)
			return Integer.MAX_VALUE;// your base Condition

		if (ss >= qs && se <= qe)
			return st[si];

		int mid = ss + (se - ss) / 2;

		int l = query(si * 2, ss, mid, qs, qe);
		int r = query(si * 2 + 1, mid + 1, se, qs, qe);
		return Math.min(l, r);
	}

	// Update the Segment Tree it takes time (NLog(N))
	private static void update(int si, int ss, int se, int qi) {

		if (ss == se) {
			st[si] = arr[ss];
			return;
		}
		int mid = ss + (se - ss) / 2;

		if (qi <= mid) {
			update(si * 2, ss, mid, qi);
		} else {
			update(si * 2 + 1, mid + 1, se, qi);
		}

		st[si] = Math.min(st[si * 2], st[si * 2 + 1]);

	}

	public static void main(String[] args) {
		// Your Inputs
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		for (int i = 1; i <= n; i++)
			arr[i] = sc.nextInt();

		build(1, 1, n); // build Segment tree According to you

		int q = sc.nextInt();
		while (q-- != 0) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int ans = query(1, 1, n, a + 1, b + 1);
			System.out.println(ans);
		}
	}
}
