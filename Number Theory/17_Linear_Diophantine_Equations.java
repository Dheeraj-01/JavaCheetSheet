
private static int x, y;
	
	static int[] gcd(int p, int q) {
	      if (q == 0)
	         return new int[] { p, 1, 0 };

	      int[] vals = gcd(q, p % q);
	      int d = vals[0];
	      int a = vals[2];
	      int b = vals[1] - (p / q) * vals[2];
	      return new int[] { d, a, b };
	   }
	
	private static boolean find_any_Solution(int a, int b, int c) {
		int[] g = gcd(Math.abs(a), Math.abs(b));
		int gcd = g[0];
		int x0 = g[1],y0 = g[2];
		if(c%gcd != 0)
			return false;
		x = x0*c/gcd;
		y = y0*c/gcd;
		if(a<0)x = -x;
		if(b<0)y = -y;
		return true;
	}
	

	public static void main(String[] args) throws IOException {

		FastScanner sc = new FastScanner();
		int t = sc.nextInt();
		first: while (t-- != 0) {

			int a = sc.nextInt(),b = sc.nextInt(),c = sc.nextInt();
			// ax + by = c
			if(find_any_Solution(a,b,c) == false) {
				System.out.println("No Solution Exists");
			}
			else {
				System.out.println("x= "+x+"\ny = "+y);
			}

		}

	}
