
	static int lp[];
	static ArrayList<Integer> prime;
	private static void Linear_seive(int N) {
		lp = new int[N+1];
		prime = new ArrayList<Integer>();
		for(int i=2; i<=N; i++) {
			if(lp[i] == 0) {
				lp[i] = i;
				prime.add(i);
			}
			
			int size = prime.size();
			for(int j=0; j<size && prime.get(j) <= lp[i] && i*prime.get(j) <= N; j++)
				lp[i*prime.get(j)] = prime.get(j);
		}
		
	}
	
	
	
	

	public static void process() throws IOException {
		Linear_seive(1_000_000);
		System.out.println(prime);
		

	}

	
