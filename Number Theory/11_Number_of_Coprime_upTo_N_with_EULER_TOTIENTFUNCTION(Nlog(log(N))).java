  private static final int N = 100000;
	static int[] phi = new int[N+1];
	
	private static void init() {
		
		for(int i=1; i<=N; i++)phi[i] = i;
		for(int i=2; i*i<=N; i++) {
			if(phi[i] == i) {
				for(int j=i; j<=N; j+=i) {
					phi[j]/=i;
					phi[j]*=(i-1);
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		init();
		System.out.println(phi[230]);
	}
