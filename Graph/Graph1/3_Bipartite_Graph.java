// Bipartite
//	static int[] vis = new int[100001];
//	private static int[] col = new int[2];
    
	private static boolean Bipartite(int node, int c) {
		vis[node] = 1;
		col [node]= c;
		
		for(int child : arr[node]) {
			if(vis[child] == 0) {
				if(Bipartite(child, c ^ 1) == false)
					return false;
			}
			else {
				if(col[node] == col[child])
					return false;
			}
		}
		return true;
	}
	
	
