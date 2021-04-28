
	private static int N = 1_00_005,LOGN = 20,nn;
	// Original Tree
	private static ArrayList<Integer>[] g;
	private static int[] sub = new int[N];
	private static int[] U = new int[N];
	private static int[] V = new int[N];
	private static int[] W = new int[N];
	private static int[] deleted = new int[N];
	
	// Centroid Tree
	private static int[] par = new int[N];
	private static int[] level = new int[N];
	private static int[][] dist = new int[LOGN][N];
	
	// dist[LOGN][N] : dist[lvl][x] :  Distance between C and x in the original tree, when node C becomes a centroid at level "lvl". 
	// G[u] --> [v1, v2, v3] ... Not doing this.
	// G[u] --> [e1, e1, e3 ..] 
	private static int adj(int x, int e) {return U[e] ^ V[e] ^ x;}
	private static void dfs1(int u, int p) {
		sub[u] = 1;
		nn++;
		for(int e : g[u]) {
			int v = adj(u, e);
			if(v != p && deleted[e] == 0) {
				dfs1(v, u);
				sub[u]+=sub[v];
			}
		}
		
	}
	
	private static int find_centroid(int u, int p) {
		for(int e : g[u]) {
			if(deleted[e] == 1)continue;
			int v = adj(u, e);
			if (v != p && sub[v] > nn / 2) return find_centroid(v, u);
		}
		return u;
	}
	
	private static void add_edge_centroid_tree(int parent, int child) {
		par[child] = parent;
		level[child] = level[parent] + 1;
	}
	private static void dfs2(int u, int p, int lvl) {
		for(int e : g[u]) {
			int v = adj(u, e);
			if(deleted[e] == 1 || v == p)continue;
			dist[lvl][v] = dist[lvl][u] + W[e];
			dfs2(v, u, lvl);
		}
	}
	
	// unordered_map<int, int> dist[N]; -- inefficient.
	// all the nn nodes which lie in the component of "centroid"
	// dist[centroid][x] = <value>
	// int dist[LOGN][N]; (centroid,x) --> one to one mapping --> (level[centroid], x);
	private static void decompose(int root, int par) {
	    nn = 0;
	    // Compute subtree sizes and no. of nodes (nn) in this tree.
	    dfs1(root, root);
	    // Find the centroid of the tree and make it the new root.
	    int centroid = find_centroid(root, root);
	    // Construct the Centroid Tree
	    if (par == -1) par = root;
	    add_edge_centroid_tree(par, centroid);
	    // Process the O(N) paths from centroid to all leaves in this component.
	    dfs2(centroid, centroid, level[centroid]);
	    // Delete the adjacent edges and recursively decompose the adjacent subtrees.
	    for (int e : g[centroid]) {
	        if (deleted[e] == 1) continue;
	        deleted[e] = 1;
	        int w = adj(centroid, e);
	        decompose(w, centroid);
	    }
		
	}

	
	private static int compute_distance(int x, int y) {
		// We need to compute the LCA(x, y) in the centroid tree. 
		// O(logN).
		int lca_level = 0;
		int i = x,j = y;
		while(i != j) {
			if(level[i] < level[j])j = par[j];
			else i = par[i];
		}
		lca_level = level[i];
		return dist[lca_level][x] + dist[lca_level][y];
		
	}
	public static void process() throws IOException {

		int n = sc.nextInt();
		g = new ArrayList[n+1];
		for(int i =0; i<=n; i++)g[i] = new ArrayList<Integer>();
		for(int i = 0; i<n-1; i++) {
			int a = sc.nextInt(),b = sc.nextInt(),w = sc.nextInt();// here one base indexing
			U[i] = a;
			V[i] = b;
			W[i] = w;
			g[U[i]].add(i);
			g[V[i]].add(i);
		}
		decompose(1,-1);// par default = -1;
		int m = sc.nextInt();
		while (m-- != 0) {
		    int x = sc.nextInt(),y = sc.nextInt();// 1 base
		    int ans = compute_distance(x, y);
		    println(ans);
		  }
	}

	
