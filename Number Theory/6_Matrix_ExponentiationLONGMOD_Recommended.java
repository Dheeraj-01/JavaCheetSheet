

	
	static int N;
	static long Mod = 1000000007;
	static long[][] arr; // Matrix N X N
	static long[][] I;// Identity Matrix
  
  
	private static void power(long[][] A, int dim, int n) {
		
		for(int i=1; i<=dim; i++) {
			for(int j=1;j<=dim; j++) {
				if(i==j)
					I[i][j]=1;
				else
					I[i][j]=0;
			}
		}
		
//		for(int i=1; i<=n; i++)
//			multiply(I,A,dim);
		
		while(n != 0) {
			if(n%2 == 1) {
				multiply(I, A, dim);
				n--;
			}
			else {
				multiply(A, A, dim);
				n/=2;
			}
		}
		
		for(int i=1; i<=dim; i++)
			for(int j = 1; j<=dim; j++)
				A[i][j]=I[i][j]%Mod;
		
	}

	private static void multiply(long[][] A, long[][] B, int dim) {
		
		long res[][] = new long[dim+1][dim+1];
		for(int i=1; i<=dim; i++) {
			for(int j=1;j<=dim; j++) {
				
				res[i][j]=0;
				for(int k=1; k<=dim; k++)
					res[i][j] += (A[i][k]%Mod * B[k][j]%Mod)%Mod;
				
			}
		}
		
		// copy in  A Matrix
		for(int i=1; i<=dim; i++)
			for(int j = 1; j<=dim; j++)
				A[i][j]=res[i][j]%Mod;
		
		
		
	}


	private static void printMatrix(long[][] A, int dim) {
		
		for(int i =1; i<=dim; i++) {
			for(int j=1; j<=dim; j++)
				System.out.print(A[i][j]+" ");
			System.out.println();
		}
	}
  
  

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
	
		int t = sc.nextInt();
		while(t-- != 0) {
			int dim = sc.nextInt();
			N = dim+2;
			arr = new long[N][N];
			I = new long[N][N];
			int n = sc.nextInt();
			for(int i=1; i<=dim; i++)
				for(int j = 1; j<=dim; j++)
					arr[i][j]=sc.nextLong();
			power(arr,dim,n);
			printMatrix(arr,dim);
		}

	}
