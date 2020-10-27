//	a^n % mod
	private static int power(long a, long n,int mod) {
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
		return (int) (res%mod);
	}
