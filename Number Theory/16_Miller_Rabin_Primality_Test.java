

private static boolean check_Composite(long n, int a, long d, int s) {
		
		long x = power(a, d, n);
		
		if(x == 1 || x == n-1)return false;
		
		for(int i=1; i<s; i++) {
			x = multMod(x, x, n);
			if(x == n-1)
				return false;
		}
		return true;
	}

	public static boolean Miller_Rabin_isPrime(long n)
    {
		if(n<2)return false;
		int r = 0;
		long d = n-1;
		while((d&1) == 0) {
			d>>=1;
			r++;
		}
		int arr[] = {2,3,5,7,11,13,17,19,23,29,31,37}; // For 64 bit system
		for(int a  : arr) {
			if(n == a)return true;
			
			if(check_Composite(n,a,d,r))
				return false;
		}
		
		
		return true;
    }

	private static long power(long a, long n, long mod) {
		long res = 1;
		while (n > 0) {
			if ((n & 1) > 0) {
				res = multMod(res, a, mod);
			}
			n >>= 1;
			a = multMod(a, a, mod);
		}
		return (res % mod);
	}

	private static long multMod(long a, long b, long c) {
		long x = 0, y = a % c;
		while (b > 0) {
			if (b % 2 == 1) {
				x = (x + y) % c;
			}
			y = (y * 2L) % c;
			b /= 2;
		}

		return x % c;
	}

	public static void main(String[] args) throws IOException {

		FastScanner sc = new FastScanner();
		int t = sc.nextInt();
		first: while (t-- != 0) {

			long n = sc.nextLong();
			if (Miller_Rabin_isPrime(n)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}

		}

	}
