
// LCA Of a graph
    public static int log2(int N) 
    { 
        // calculate log2 N indirectly 
        // using log() method 
        int result = (int)(Math.log(N) / Math.log(2)); 
  
        return result; 
    } 
    
    //static ArrayList<Integer> arr[];
    static int N = 0;
    static int n = 1001;
    
    static int maxN = log2(n);
    static int level[] = new int[N];
    static int LCA[][] = new int[N][maxN+1];

	private static ArrayList<Integer>[] arr;
  
private static void init(int n) {
		
	dfsForLCA(1,0,-1);
	
	for(int i=1; i<=maxN; i++) {
		for(int j=1; j<=n; j++) {
			
			if(LCA[j][i-1] != -1) {
				
				int par = LCA[j][i-1];
				LCA[j][i] = LCA[par][i-1];
			}
			
		}
	}
		
}

private static void dfsForLCA(int node, int lvl, int par) {
	
	level[node] = lvl;
	LCA[node][0] = par;
	
	for(int child : arr[node]) {
		if(child != par) {
			dfsForLCA(child, lvl+1, node);
		}
	}
	
	
}


private static int getLCA(int x, int y) {
	int a = -1;
	int b = -1;
	if(level[x] > level[y]) {
		b = x;
		a = y;
	}
	else {
		a = x;
		b = y;
	}
	
	
	int d = level[b] - level[a];
	
	while(d > 0) {
		int i = log2(d);
		b = LCA[b][i];
		d -= (1<<i);
		
	}
	
	if(a == b)return a;
	
	for(int i=maxN; i>=0; i--) {
	
		if(LCA[a][i] != -1 && (LCA[a][i] != LCA[b][i])) {
			a = LCA[a][i];
			b = LCA[b][i];
		}
	}
		
	return LCA[a][0];
}



// get Distance Between Two Node

static int getDistance(int a, int b) {
	int lca = getLCA(a, b);
	return level[a] + level[b] - 2*level[lca];
}





// main Body
public static void main(String[] args) {
	  Scanner sc = new Scanner(System.in);
      int n = sc.nextInt();
      
      
      for(int i=1; i<=n; i++) {
      	for(int j=0; j<=maxN; j++)
      		LCA[i][j] = -1;
      }
      

      arr = new ArrayList[n+1];
      for(int i = 0;i <= n;i++){
          arr[i] = new ArrayList<>();
      }

      for(int i=1; i<n; i++) {
      	int a = sc.nextInt(),b = sc.nextInt();
      	arr[a].add(b);
      	arr[b].add(a);
      }
      
      init(n);
      
//      int val = getLCA(8,7);
      int dis = getDistance(8, 7);
//      System.out.println(val);
      
      System.out.println(dis);
	 
		
}

