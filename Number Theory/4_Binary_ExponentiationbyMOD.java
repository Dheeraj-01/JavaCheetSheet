//	a^n % mod
	private static long mod = (long) (10e9+7);
	private static long power(long a, long n) {
		long res = 1;
		while(n > 0) {
			if(n%2 == 0) {
				a = (a%mod * a%mod)%mod;
				n/=2;
			}
			else {
				res = (res%mod * a%mod)%mod;
				n--;
			}
		}
		return res%mod;
	}
