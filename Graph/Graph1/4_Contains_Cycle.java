	
// Contains Cycle or not ???
	
	
	private static boolean Graph_Contains_Cycle(int node, int par) {
		vis[node] = 1;
		for(int child : adj[node]) {
			if(vis[child] == 0) {
				if(Graph_Contains_Cycle(child, node) == true)
					return true;
			}
			else {
				if(child != par)
					return true;
			}
		}
		return false;
	}
	
	
	
