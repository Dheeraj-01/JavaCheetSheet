static long mod = 1_000_000_007;
	static class Matrix {
        static long[][] mult(long[][] a, long[][] b, long MOD) {
            if(a[0].length != b.length) return null;
            long[][] res = new long[a.length][b[0].length];
            for(int i = 0; i < res.length; i++) {
                for(int j = 0; j < res[0].length; j++) {
                    for(int k = 0; k < b.length; k++) {
                        res[i][j] = add(res[i][j], mult(a[i][k], b[k][j], MOD), MOD);
                    }
                }
            }
            return res;
        }
 
        static long[] mult(long[][] a, long[] b, long MOD) {
            if(a[0].length != b.length) return null;
            long[] res = new long[a.length];
            for(int i = 0; i < res.length; i++) {
                for(int j = 0; j < b.length; j++) {
                    res[i] = add(res[i], mult(a[i][j], b[j], MOD), MOD);
                }
            }
            return res;
        }
 
        static long[][] pow(long[][] a, long b, long MOD) {
            if(b == 1) return a;
            long[][] total = pow(a, b >> 1, MOD);
            total = mult(total, total, MOD);
            if((b & 1) == 1) total = mult(total, a, MOD);
            return total;
        }
 
        static long add(long a, long b, long MOD) {
            return (a%MOD+b%MOD)%MOD;
        }
 
        static long mult(long a, long b, long MOD) {
            return ((a%MOD)*(b%MOD))%MOD;
        }
    }    
  
  
 
	public static void process() throws IOException {
 
		int n = sc.nextInt();
		long arr[][] = {
				{0,1,1,1},
				{1,0,1,1},
				{1,1,0,1},
				{1,1,1,0},
		};
		
		long T[][] = {
				{1},
				{0},
				{0},
				{0},
			};
		long ans[][] = Matrix.pow(arr, n, mod);
		ans = Matrix.mult(ans, T, mod);
		System.out.println(ans[0][0]);
		
	}
