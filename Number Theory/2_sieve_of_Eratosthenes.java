

public static boolean[] generatePrimes(int n) {
	boolean[] prime = new boolean[n+1];
        Arrays.fill(prime, true);
        for(int i = 2; i < n+1; ++i)
            if(prime[i]) {
                if(1l * i * i < n+1)
                    for(int j = i * i; j < n+1; j += i) {
                        prime[j] = false;
                    }
            }
        return prime;
	}
    
    
    public static void main(String[] args) throws IOException {
		boolean[] prime = generatePrimes(n);
		System.out.println(prime.length);
	}
    
    
