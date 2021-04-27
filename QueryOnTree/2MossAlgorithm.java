package MossAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Template {	

	static int N = 2_000_01,MaxValue = 2_000_001;
	static int Block = (int) sqrt(N);
	static int arr[] = new int[N];
	static int ans[] = new int[N];
	static int frq[] = new int[MaxValue];
	static int frqfrq[] = new int[MaxValue];
	
	
	static int MaxNum = 0,MaxCount = 0;
	
	
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
		frqfrq[frq[arr[pos]]]--;
		frq[arr[pos]]++;
		frqfrq[frq[arr[pos]]]++;
		MaxCount = max(MaxCount,frq[arr[pos]]);
	}

	private static void remove(int pos) {
		frqfrq[frq[arr[pos]]]--;
		if(frqfrq[frq[arr[pos]]] == 0)MaxCount--;
		frq[arr[pos]]--;
		frqfrq[frq[arr[pos]]]++;
		MaxCount = max(MaxCount,frq[arr[pos]]);
		
	}

	public static void process() throws IOException {

		int n = sc.nextInt();
		for(int i=0; i<n; i++)arr[i] = sc.nextInt();
		int q = sc.nextInt();
		ArrayList<Query> lis = new ArrayList<Query>();
		for(int i=0; i<q; i++) {
			int l = sc.nextInt(),r = sc.nextInt();
			lis.add(new Query(l, r, i));
		}
		
		Collections.sort(lis);
//		for(Query e : lis)System.out.println(e.l+" "+e.r+" "+e.index);
		frqfrq[0] = 1;
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
			ans[lis.get(i).index] = MaxCount;
			
		}
		
		for(int i=0; i<q; i++) {
			System.out.println(ans[i]);
		}


	}
	
	static long sqrt(long z) {
		long sqz = (long) Math.sqrt(z);
		while (sqz * 1L * sqz < z) {
			sqz++;
		}
		while (sqz * 1L * sqz > z) {
			sqz--;
		}
		return sqz;
	}
}
