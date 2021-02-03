
	//Time Complexity : O((V+E)log(E))
		
	private static long INF = 2000000000000000000L, M = 1000000007, MM = 998244353;
	private static int N = 200005;
	private static HashSet<Integer>[] adj;
	private static ArrayList<Integer>[] bridgeTree;
	private static boolean[] isBridge;
	private static int[] visited,disc,low,par,label;
	private static int cntr = 0;
	
	private static void init(int n) {
		adj = new HashSet[n+1];
		bridgeTree = new ArrayList[n+1];
		isBridge = new boolean[n+1];
		visited = new int[n+1];
		disc = new int[n+1];
		low = new int[n+1];
		par = new int[n+1];
		label = new int[n+1];
		for(int i=0; i<=n; i++) {
			adj[i] = new HashSet<Integer>();
			bridgeTree[i] = new ArrayList<Integer>();
		}
		
	}
	
	private static void dfs_bridge(int node, int prev) {// Bridge Finding
		visited[node] = 1;
	    par[node] = prev;
	    disc[node] = ++cntr;
	    low[node] = disc[node];
	    
	    for(int child : adj[node]){
	        if(child == prev)   continue;
	        if(visited[child] == 0){ // un-explore node, so down in DFS tree
	            dfs_bridge(child, node);
	            if(low[child] > disc[node])
	            	isBridge[child] = true;
	        }
	        low[node] = min(low[node], low[child]); // un-explored node OR back-edge
	    }
	}
	
	private static void dfs_label(int node) {// Label each bridge component
		visited[node] = 1;
	    label[node] = cntr;
	    for(int child : adj[node])
	        if(visited[child] == 0)
	            dfs_label(child);
		
	}
	public static void process() throws IOException {

		int n = sc.nextInt(),m = sc.nextInt();
		init(n);
		for(int i=0; i<m; i++) {
			int u = sc.nextInt(),v = sc.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		
		ArrayList<Pair> bridges = new ArrayList<Pair>();
		cntr = 0;
		dfs_bridge(1,0);
		for(int i = 1; i <= n; i++){
	        visited[i] = 0;
	        if(isBridge[i]){
	            bridges.add(new Pair(i, par[i]));
//	            System.out.println(i+" "+par[i]);//bridges
	            adj[i].remove(par[i]);
	            adj[par[i]].remove(i);
	        }
	    }
		
		cntr = 0;
	    for(int i = 1; i <= n; i++){
	        if(visited[i] == 0){
	            cntr++;
	            dfs_label(i);
	        }
	    }
	    
	    for(Pair e : bridges){ // Make bridge tree
	        int a = label[e.x], b = label[e.y];
//	        System.out.println(a+" "+b);
	        bridgeTree[a].add(b);
	        bridgeTree[b].add(a);
	    }
		
		
		
		
		
	}
	
	

	static class Pair{
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
