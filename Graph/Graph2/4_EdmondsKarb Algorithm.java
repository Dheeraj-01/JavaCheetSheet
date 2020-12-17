
// EdmondsKarb Algorithm

	public class EdmondsKarbAlgo {
	
		private static int[][] matrix;//Residual Capacity
		private static ArrayList<Integer>[] adj;
		private static int[] vis;
		private static int n;
	
		public static void main(String[] args) throws IOException {
	
			FastScanner sc = new FastScanner();
			 n = sc.nextInt();
			int m = sc.nextInt();
			matrix = new int[n+1][n+1];
			adj = new ArrayList[n+1];
			vis = new int[n+1];
			for(int i=1; i<=n; i++)adj[i] = new ArrayList<Integer>();
			for(int i=1; i<=m; i++) {
				int a = sc.nextInt(),b = sc.nextInt(),capacity = sc.nextInt();
				matrix[a][b] = capacity;
				adj[a].add(b);
			}
	//		Queue<Pair> q = new LinkedList<>();
			int maxFlow = MaxFlow(1,n);
			System.out.println("Max Flow to 1 to "+n+" is "+maxFlow);
			
		}
		private static int MaxFlow(int src, int t) {
			int flow = 0;
			int[] parent = new int[n+1];
			int newflow = 0;
			while(true) {
				newflow = bfs(src, t, parent);
				if(newflow == 0)
					break;
				flow += newflow;
				int curr = t;
				while(curr != src) {
					int prev = parent[curr];
					matrix[prev][curr] -= newflow;
					matrix[curr][prev] += newflow;
					curr = prev;
				}
			}
			return flow;
			
		}
		private static int bfs(int src, int t, int[] parent) {
			Arrays.fill(parent, -1);
			parent[src] = -2;
			Queue<Pair> q = new LinkedList<>();
			q.add(new Pair(src, Integer.MAX_VALUE));
			
			while(!q.isEmpty()) {
				int curr = q.peek().a;
				int flow = q.peek().b;
				q.poll();
				for(int child : adj[curr]) {
					if(parent[child] == -1 && matrix[curr][child] != 0) {
						parent[child] = curr;
						int newflow = Math.min(flow, matrix[curr][child]);
						if(child == t)
							return newflow;
						q.add(new Pair(child, newflow));
					}
				}
			}
			
			return 0;
		}
		static class Pair {
			
			int a,b;
			public Pair(int a,int b) {
				this.a = a;
				this.b = b;
			}
			
		}
	}
