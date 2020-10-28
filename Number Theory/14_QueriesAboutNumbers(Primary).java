
static HashMap<Long, Integer> map = new HashMap<Long, Integer>();
	private static int total = 1;
	 
	 private static void primefact(long n) {

			for (long i = 2; i * i <= n; i++) {
	    
				if (n % i == 0) {
					int count = 0;
					while (n % i == 0) {
						count++;
						n /= i;
					}
					map.put(i, count);
					total *=(count+1);
//					System.out.println(i + "^" + count);
				}
			}
			if (n > 1) {
				map.put(n, 1);
				total *=(1+1);
			}
	      

		}
	 
	
	 
	 public static void main(String[] args) {
		
		 Scanner sc = new Scanner(System.in);
		 long n = sc.nextLong();
		 int q = sc.nextInt();
		 primefact(n);
		 while(q-- != 0) {
			 int t = sc.nextInt();
			 long k = sc.nextLong();
			 if(t == 1) {
				 	long res = 1;
					for(long p : map.keySet())
					{
						long cnt = 0;
						while(k % p == 0) {
							cnt++;
							k /= p;
						}
						
						res *= (Math.min(cnt , map.get(p)) + 1);
					}
					
					System.out.println(res);
			 }
			 else if(t == 2) {
				 	long res = 1;
					for(long p : map.keySet())
					{
						long cnt = 0;
						while(k % p == 0) {
							cnt++;
							k /= p;
						}
						if(cnt > map.get(p))
						{
							res = 0;
							break;
						}
						res *= (map.get(p) - cnt + 1);
					}
					if(k > 1) res = 0;
					System.out.println(res);
			 
			 }
			 else {
				 
				 	long res = 1;
					for(long p : map.keySet())
					{
						long cnt = 0;
						while(k % p == 0) {
							cnt++;
							k /= p;
						}
						if(cnt > map.get(p))
						{
							res = 0;
							break;
						}
						res *= (map.get(p) - cnt + 1);
					}
					if(k > 1) res = 0;
					System.out.println(total - res);
				 
			 }
			 

		 }
	}
	 
	 
