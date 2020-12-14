// Shortest Paths
	
	static int N = n;
	static int[] vis = new int[N];
    static int[] dis = new int[N];
	private static void init_paths(int node , int sum) {
		vis[node] = 1;
		dis[node] = sum;
		for(int child : adj[node]) {
			if(vis[child] == 0)
				init_paths(child,dis[node]+1);
		}
    }
	
	
