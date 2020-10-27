
// Calculate sum of GCD up to N
	
	// Time Complexity is nlog(n) + Q*sqrt(n) = Q*sqrt(n)
	
	private static final int MaxN = 100000;
	static int[] phi = new int[MaxN+1];
	
	private static void init() {
		
		for(int i=1; i<=MaxN; i++)phi[i] = i;
		for(int i=2; i*i<=MaxN; i++) {
			if(phi[i] == i) {
				for(int j=i; j<=MaxN; j+=i) {
					phi[j]/=i;
					phi[j]*=(i-1);
				}
			}
		}
		
	}
	
	private static int getCount(int d, int n) {
		return phi[n/d];
	}
	private static int getGcdSumUpToN(int n) {
		int res = 0;
		for(int i=1; i*i<=n; i++) {
			if(n%i == 0) {
				int d1 = i;
				int d2 = n/i;
				 
				res+=(d1*getCount(d1,n));
				if(d1 != d2)
					res+=(d2*getCount(d2,n));
			}
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		init();
		Scanner sc = new Scanner(System.in);
		int q = sc.nextInt();
		while(q-- != 0) {
			int n = sc.nextInt();
			int getGCDsum = getGcdSumUpToN(n);
			System.out.println(getGCDsum);
		}
	}

