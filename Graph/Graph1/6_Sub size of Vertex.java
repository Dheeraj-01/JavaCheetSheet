	
//Sub size of Vertex
	
	
	// dfs(root) In Main
	
	static int N = 50;
	static int visited[] = new int[N];
	static int subtree[] = new int[N];
	 
	private static int dfs(int node) {
		visited[node]=1;
		int cursize = 1;
		for (int  child : adj[node]) {
			if(visited[child] == 0) {
				cursize += dfs(child);
			}
		}
		subtree[node] = cursize;
		return cursize;
	}
	
	
	
	
