package MossAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Template {	

	

	static int N = 3_000_08,MaxValue = 3_00_008;
	static int Block = (int) sqrt(N);
	static int arr[] = new int[N];
	static int ans[] = new int[N];
	static int fre[] = new int[MaxValue];
	static int freOfFre[] = new int[MaxValue];
	
	
	static int currMax = 0;
	
	
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
		int preF = fre[arr[pos]];
		fre[arr[pos]]++;
		int currF = fre[arr[pos]];
		
		freOfFre[preF]--;
		freOfFre[currF]++;
		
		if(currF > currMax)
		{
			currMax = currF;
		}
	}
 
	private static void remove(int pos) {
		int preF = fre[arr[pos]];
		fre[arr[pos]]--;
		int currF = fre[arr[pos]];
		
		freOfFre[preF]--;
		freOfFre[currF]++;
		
		if(currF < currMax)
		{
			while(freOfFre[currMax] == 0)
			currMax--;
		}
		
	}

	public static void process() throws IOException {

		int n = sc.nextInt(),q = sc.nextInt();
		for(int i=0; i<n; i++)arr[i] = sc.nextInt();
		ArrayList<Query> lis = new ArrayList<Query>();
		for(int i=0; i<q; i++) {
			int l = sc.nextInt(),r = sc.nextInt();
			lis.add(new Query(l-1, r-1, i));
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
			int total = lis.get(i).r - lis.get(i).l + 1;
			int rem = total - currMax;
			int half = (total+1)/2;
			
			if(currMax <= half)
			ans[lis.get(i).index] = 1;
			else
			{
				ans[lis.get(i).index] = total - 2*rem;
			}
			
			
		}
		
		for(int i=0; i<q; i++) {
			println(ans[i]);
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
