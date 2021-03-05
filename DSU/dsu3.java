
	static int N = 200005;
	static int parent[] = new int[N];
	static int get(int a){
		if(a==parent[a]) return a;
		else return (get(parent[a]));
	}

public static void process() throws IOException {
	
		for(int i=1;i<N;i++)
	    {
	        parent[i]=i;
	    }
	
}
