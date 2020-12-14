	
// Kasaraju Algorithm
	
	public class KasarajuAlgo {

		private static ArrayList<Integer>[] adj,trans;
		static ArrayList<Integer> order,res;
		private static int[] vis;

		private static void dfs1(int node) {
			vis[node] = 1;
			for(int child : trans[node]) {
				if(vis[child] == 0)
					dfs1(child);
			}
			res.add(node);
			
		}

		private static void dfs(int node) {
			vis[node] = 1;
			for(int child : adj[node]) {
				if(vis[child] == 0)
					dfs(child);
			}
			order.add(node);
			
			
		}

		public static void main(String[] args) throws IOException {

			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt();
			int m = sc.nextInt();
			adj = new ArrayList[n+1];
			trans = new ArrayList[n+1];
			order = new ArrayList<Integer>();
			res = new ArrayList<Integer>();
			vis = new int[n+1];
			for(int i=1; i<=n; i++) {
				adj[i] = new ArrayList<Integer>();
				trans[i] = new ArrayList<Integer>();
			}
			
			for(int i=1; i<=m; i++) {
				int a = sc.nextInt(),b = sc.nextInt();
				adj[a].add(b);
				trans[b].add(a);
			}
			
			for(int i=1; i<=n; i++) {
				if(vis[i] == 0) {
					dfs(i);
				}
			}
			Arrays.fill(vis, 0);
			for(int i=1; i<=n; i++) {
				if(vis[order.get(n-i)] == 0) {
					res.clear();
					dfs1(order.get(n-i));
					System.out.println("dfs(1) called from "+ order.get(n-i)
					+" and printing SCC");
					System.out.println(res);
				}
			}

		}
	}
	
	
	
	
