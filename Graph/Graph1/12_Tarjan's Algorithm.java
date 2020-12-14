
	
	
// Tarjan's Algorithm for scc
	
	
	public class Tarjan_Algo{
		
		private static ArrayList<Integer>[] adj;
		private static int n, timer = 1 , SCC = 0;
		private static int[] vis,in,low,onStack;
		static Stack<Integer> st = new Stack<Integer>();
		
		private static void dfs(int node) {

			vis[node] = 1;
			in[node] = low[node] = timer++;
			onStack[node] = 1;
			st.push(node);
		 
			for(int u : adj[node])
			{
				if((vis[u] == 1) && (onStack[u] == 1))
				{
					low[node] = Math.min(low[node] , in[u]);
				}
				else
				if(vis[u] == 0)
				{
					dfs(u);
		 
					if(onStack[u] == 1)
					low[node] = Math.min(low[node] , low[u]);
				}
			}
		 
			if(in[node] == low[node])
			{
				SCC++;
				System.out.println("SCC #"+SCC);
		 
				int u;
		 
				while(true)
				{
					u = st.peek();
					st.pop();
					onStack[u] = 0;
					System.out.print(u+" ");
		 
					if(u == node) break;
				}
				System.out.println();
			}
			
		}

		public static void main(String[] args) {
			
			Scanner sc = new Scanner(System.in);
			n = sc.nextInt();
			adj = new ArrayList[n+1];
			for(int i=0; i<=n; i++)adj[i] = new ArrayList<Integer>();
			vis = new int[n+1];
			in = new int[n+1];
			low = new int[n+1];
			onStack = new int[n+1];
			int m = sc.nextInt();
			for(int i=0; i<m; i++) {
				int a = sc.nextInt(),b = sc.nextInt();
				adj[a].add(b);
			}
			for(int i=1; i<=n; i++) {
				if(vis[i] == 0)dfs(i);
			}
			
			
		}
		
		
		
		
	}





	
