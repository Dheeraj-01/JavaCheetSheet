import java.util.*;



public class Solution{
/*
6 9
1 2 4
1 6 2 
2 3 5 
2 6 1
6 3 8
6 5 10 
3 4 6
3 5 3
5 4 5
output : 0 3 8 14 11 2 
*/
	
	static class Pair implements Comparable<Pair> {
		int x;
		int y;

		Pair(int u, int w) {
			this.x = u;
			this.y = w;
		}

		@Override
		public int compareTo(Pair o) {
			return Integer.compare(this.x, o.x);
		}

	}
	private static ArrayList<Pair>[] adj;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		adj = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			adj[i] = new ArrayList<Pair>();
		}
		while(m-- != 0)
		{
			int a = sc.nextInt();
			int b = sc.nextInt();
			int w = sc.nextInt();
			
			adj[a].add(new Pair(b, w));
			adj[b].add(new Pair(a, w));
		}
		
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		
		
		int dis[] = new int[n+1];
		for(int i=1; i<=n; i++) {
			dis[i] = Integer.MAX_VALUE;
		}
		
		pq.add(new Pair(0, 1));
		dis[1] = 0;
		
		while(!pq.isEmpty()) {
			int curr = pq.peek().y;
			int curr_d = pq.peek().x;
//			System.out.println("curr edge : "+curr);
//			System.out.println("curr weight : "+curr_d);
			pq.poll();
			
			for(Pair edge : adj[curr]) {
//				System.out.println(edge.y+" "+edge.x);
				if(curr_d + edge.y < dis[edge.x]) {
					dis[edge.x] = curr_d + edge.y;
					pq.add(new Pair(dis[edge.x], edge.x));
				}
			}
		}
		
		
		
		for(int i=1; i<=n; i++)
			System.out.print(dis[i]+" ");
		System.out.println();
		
	}
	
	
	
	
}






