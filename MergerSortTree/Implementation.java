/*
5 4
5 4 3 8 4
1 4 4
[3, 4, 5]
[8]
1
1 5 3
[3, 4, 4, 5, 8]
0
1 5 4
[3, 4, 4, 5, 8]
1
1 5 4
[3, 4, 4, 5, 8]
1
*/
package SegmentTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MergeSortTree {

	private static final int MAX = 10001;
	private static int[] arr = new int[MAX];
	private static ArrayList<Integer>[] st;
	
	public static int upper(ArrayList<Integer> st2, int target) {
        if (st2 == null || st2.size() == 0) {
            return 0;
        }
        int l = 0;
        int r = st2.size() - 1;
        if (target <= st2.get(0)) {
            return -1;
        }
        if (target > st2.get(r)) {
            return r;
        }
        while (l  <  r - 1) {
            int m = l + (r - l ) / 2 ;
            if (st2.get(m) < target) {
                l = m;
            }else {
                r = m - 1;
            }
        }
        return st2.get(r) < target ? r : l;
    }

	private static void build(int si, int ss, int se) {

		if (ss == se) {
			st[si].add(arr[ss]);
			return;
		}
		int mid = (ss + se) / 2;
		build(si * 2, ss, mid);
		build(si * 2 + 1, mid + 1, se);

		int i = 0, j = 0;

		// merge the both child's

		while (i < st[2 * si].size() && j < st[2 * si + 1].size()) {

			if (st[2 * si].get(i) <= st[2 * si + 1].get(j)) {
				st[si].add(st[2 * si].get(i));
				i++;
			}

			else {
				st[si].add(st[2 * si + 1].get(j));
				j++;
			}
		}

		while (i < st[2 * si].size()) {
			st[si].add(st[2 * si].get(i));
			i++;
		}

		while (j < st[2 * si + 1].size()) {
			st[si].add(st[2 * si + 1].get(j));
			j++;
		}

	}

	private static int query(int si, int ss, int se, int qs, int qe, int k) {

		if (ss > qe || se < qs) {
			return 0;
		}

		if (ss >= qs && se <= qe) {
			System.out.println(st[si]);
			int val = upper(st[si], k);
			return val+1;
		}

		int mid = ss + (se - ss) / 2;

		int l = query(si * 2, ss, mid, qs, qe, k);
		int r = query(si * 2 + 1, mid + 1, se, qs, qe, k);

		return l + r;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(), q = sc.nextInt();

		for (int i = 1; i <= n; i++)
			arr[i] = sc.nextInt();
		st = new ArrayList[4 * MAX];
		for (int i = 0; i < 4 * MAX; i++)
			st[i] = new ArrayList<Integer>();
		build(1, 1, n);

		while (q-- != 0) {
			int l = sc.nextInt(), r = sc.nextInt(), k = sc.nextInt();
			// here k is all array element strictly smaller than k
			int ans = query(1, 1, n, l, r, k);
			System.out.println(ans);
		}
	}

}
