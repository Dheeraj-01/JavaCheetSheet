

	private static ArrayList<Integer>[] adj;
	private static int timer;
	private static int[] Stime,Ttime,FT;
	private static void dfs(int node, int par) {
		Stime[node] = timer;
		FT[timer] = node;
		timer++;
		for(int child : adj[node]) {
			if(par == child)continue;
			dfs(child, node);
		}
		Ttime[node] = timer;
		FT[timer] = node;
		timer++;
		
	}
	public static void process() throws IOException {

		int n = sc.nextInt();
		adj = new ArrayList[n+1];
		for(int i = 0; i<=n; i++)adj[i] = new ArrayList<Integer>();
		for(int i= 0; i<n-1; i++) {
			int a = sc.nextInt(),b = sc.nextInt();
			adj[a].add(b);
			adj[b].add(a);
		}
		timer = 1;
		Stime = new int[n+1];
		Ttime = new int[n+1];
		FT = new int[(n+1)*2];
		dfs(1,-1);
		System.out.print("Stime : \n{ ");
		for(int i = 1; i<=n; i++)System.out.print(Stime[i]+", ");
		System.out.println("}");
		System.out.print("Ttime : \n{ ");
		for(int i = 1; i<=n; i++)System.out.print(Ttime[i]+", ");
		System.out.println("}");
		System.out.print("FT : \n{ ");
		for(int i = 1; i<=n*2; i++)System.out.print(FT[i]+", ");
		System.out.println("}");
	}

	
