package SegmentTree;

import java.util.Scanner;

public class LazyPropagationTemplate {

	static int maxN = 100001;
	static int arr[] = new int[maxN];
	static int st[] = new int[maxN * 4];
	static int lazy[] = new int[maxN * 4];

	private static void build(int si, int ss, int se) {

		if (ss == se) {
			st[si] = arr[ss];
			return;
		}

		int mid = ss + (se - ss) / 2;
		build(si * 2, ss, mid);
		build(si * 2 + 1, mid + 1, se);

		st[si] = st[si * 2] + st[si * 2 + 1]; // perform operation i add it

	}

	private static int query(int si, int ss, int se, int qs, int qe) {

		if (lazy[si] != 0) {
			int dx = lazy[si];
			lazy[si] = 0;
			st[si] += dx * (se - ss + 1);

			if (ss != se) {
				lazy[si * 2] += dx;
				lazy[si * 2 + 1] += dx;
			}
		}

		if (ss > qe || se < qs)
			return Integer.MAX_VALUE;

		if (ss >= qs && se <= qe)
			return st[si];

		int mid = ss + (se - ss) / 2;

		int l = query(si * 2, ss, mid, qs, qe);
		int r = query(si * 2 + 1, mid + 1, se, qs, qe);
		return l + r;
	}

	private static void update(int si, int ss, int se, int qs, int qe, int value) {

		if (lazy[si] != 0) {
			int dx = lazy[si];
			lazy[si] = 0;
			st[si] += dx * (se - ss + 1);

			if (ss != se) {
				lazy[si * 2] += dx;
				lazy[si * 2 + 1] += dx;
			}
		}

		if (ss > qe || se < qs)
			return;

		if (ss >= qs && se <= qe) {
			int dx = (se - ss + 1) * value;
			st[si] += dx;
			if (ss != se) {
				lazy[si * 2] += value;
				lazy[si * 2 + 1] += value;
			}
			return;
		}

		int mid = ss + (se - ss) / 2;

		update(si * 2, ss, mid, qs, qe, value);
		update(si * 2 + 1, mid + 1, se, qs, qe, value);

		st[si] = st[si * 2] + st[si * 2 + 1];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 1; i <= n; i++)
			arr[i] = sc.nextInt();
		build(1, 1, n);

		int q = sc.nextInt();
		while (q-- != 0) {
			int code = sc.nextInt();
			if (code == 1) {
				int l = sc.nextInt();
				int r = sc.nextInt();
				int ans = query(1, 1, n, l, r);
				System.out.println(ans);
			} else {
				int l = sc.nextInt();
				int r = sc.nextInt();
				int value = sc.nextInt();

				update(1, 1, n, l, r, value);
			}
		}
	}

}
