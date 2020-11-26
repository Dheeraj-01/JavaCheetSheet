package MossAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Template {
	static int N = 30001;
	static int Block = 500;
	static int arr[] = new int[30001];
	static int ans[] = new int[200001];
	static int frq[] = new int[1000001];
	static int count = 0;
	
	
	static class Query implements Comparable<Query>{
		
		int l,r,index;
		public Query(int l,int r, int index) {
			this.l = l;
			this.r = r;
			this.index = index;
		}

		@Override
		public int compareTo(Query o) {
			int a = l/Block;
			int b = o.l/Block;
			if(a == b)return Integer.compare(r, o.r);
			return Integer.compare(l, o.l);
		}
		
	}
	private static void add(int pos) {
		frq[arr[pos]]++;
		
		if(frq[arr[pos]] == 1)
			count++;
		
	}

	private static void remove(int pos) {
		frq[arr[pos]]--;
		
		if(frq[arr[pos]] == 0)
			count--;	
		
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i=0; i<n; i++)arr[i] = sc.nextInt();
		int q = sc.nextInt();
		ArrayList<Query> lis = new ArrayList<Query>();
		for(int i=0; i<n; i++) {
			int l = sc.nextInt(),r = sc.nextInt();
			lis.add(new Query(l, r, i));
		}
		
		Collections.sort(lis);
//		for(Query e : lis)System.out.println(e.l+" "+e.r+" "+e.index);
		
		int ml = 0, mr = -1;
		for(int i=0; i<q; i++) {
			int l = lis.get(i).l;
			int r = lis.get(i).r;
			 
			// extended
			while(ml > l) {
				ml--;
				add(ml);
			}
			while(mr < r) {
				mr++;
				add(mr);
			}
			
			
			// reduces
			while(ml < l) {
				remove(ml);
				ml++;

			}
			while(mr > r) {
				remove(mr);
				mr--;
				
			}
			// answers
			ans[lis.get(i).index] = count;
			
		}
		
		for(int i=0; i<q; i++) {
			System.out.println(ans[i]);
		}

	}

	

}
