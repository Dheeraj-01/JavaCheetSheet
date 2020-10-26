

static int arr[] = new int[10000007];
	private static void factSieve() {
		
		int maxN = 10000000;
		for(int i=1; i<= maxN; i++)
			arr[i]=-1;
		
		for(int i = 2; i*i <= maxN ; i++) {
			if(arr[i] == -1) {
				for(int j=i; j<=maxN; j+=i)
					if(arr[j] == -1)
						arr[j]=i;
			}
		}
		
	}
  
  public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		factSieve();
		
		int n = sc.nextInt();
		while (n != -1) {
			if(arr[n] != -1)
				System.out.println(arr[n]);
			n /= arr[n];
		}
		

	}
