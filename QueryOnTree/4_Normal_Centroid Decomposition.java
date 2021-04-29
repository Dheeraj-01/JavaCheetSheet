
	static ArrayList<Integer> a[];
	static int sub[], nn, ans[];
 
	static void dfs1(int s, int p) {
		sub[s] = 1;
		nn++;
		for(int x : a[s]) {
			if(x != p) {
				dfs1(x, s);
				sub[s] += sub[x];
			}
		}
	}
 
	static int dfs2(int s, int p) {
		for(int x : a[s]) {
			if(x != p && sub[x] > nn / 2) {
				return dfs2(x, s);
			}
		}
		return s;
	}
 
	static void decompose(int s, int p) {
		nn = 0;
		dfs1(s, s);
		int centroid = dfs2(s, s);
		if(p != -1) {
			ans[centroid] = ans[p] + 1;
		}
		for(int x : a[centroid]) {
			a[x].remove(a[x].indexOf(centroid));
			decompose(x, centroid);
		}
		a[centroid].clear();
	}
 
	public static void process() throws IOException {
 
		int n = sc.nextInt();
		a = new ArrayList[n+1];
		for(int i = 0; i <= n; i++)	a[i] = new ArrayList<>();
		for(int i = 1; i < n; i++) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			a[u].add(v);
			a[v].add(u);
		}
		sub = new int[n];
		ans = new int[n];
		decompose(0, -1);
		for(int x : ans)	out.print((char)(x + 'A') + " ");
		out.println();
		
 
	}
 
