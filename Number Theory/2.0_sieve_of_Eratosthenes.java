
public static int[] generatePrimes(int n) {
	    boolean[] prime = new boolean[n + 1];
	    Arrays.fill(prime, 2, n + 1, true);
	    for (int i = 2; i * i <= n; i++)
	      if (prime[i])
	        for (int j = i * i; j <= n; j += i)
	          prime[j] = false;
	    int[] primes = new int[n + 1];
	    int cnt = 0;
	    for (int i = 0; i < prime.length; i++)
	      if (prime[i])
	        primes[cnt++] = i;

	    return Arrays.copyOf(primes, cnt);
	  }


    public static void main(String[] args) throws IOException {
		int arr[] = generatePrimes(89000000);
		System.out.println(arr.length);
	}
