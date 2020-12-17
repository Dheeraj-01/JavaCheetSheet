


public class BellmanFordAlgo {

	private static ArrayList[] adj;
	private static int[] dis;

	public static void main(String[] args) throws IOException {

		FastScanner sc = new FastScanner();
		int n = sc.nextInt(),m = sc.nextInt();
		adj = new ArrayList[n+1];
		dis = new int[n+1];
		Edge[] edges = new Edge[m+1];
		for(int i=1; i<=m; i++) {
			edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}
		
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[1] = 0;
		for(int i=1; i<n; i++) {
			for(int j=1; j<=m; j++) {
				if(dis[edges[j].u] < Integer.MAX_VALUE) {
					dis[edges[j].v] = min(dis[edges[j].v],
							dis[edges[j].u]+edges[j].val);
				}
			}
		}
		System.out.println(Arrays.toString(dis));
		
		

	}
	static class Edge {
		int u,v,val;
		public Edge(int u,int v,int val) {
			this.u = u;
			this.v = v;
			this.val = val;
		}
	}

}
