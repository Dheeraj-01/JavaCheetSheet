// Connecting cc
private static int Connecting_Components() {
		int cc = 0;
        for(int i=1; i<=n; i++) {
        	if(vis[i] == 0) {
        		dfs(i);
        		cc++;
        	}
        }
		
		return cc;
	}

	private static void dfs(int node) {
		vis[node] = 1;
		for(int child : adj[node]) {
			if(vis[child] == 0)
				dfs(child);
		}
    }
	
	
