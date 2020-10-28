

// Queries About Numbers https://www.codechef.com/problems/QNUMBER

/*
Chef loves number theory very much. Now it is time to solve a new kind of problem.

There is given a natural number N. Chef has to answer Q queries of the form T K.

Here T is the type of query and K is the natural number. 
If T=1, Chef must find the number of natural numbers which is divisor of both N and K. 
If T=2, Chef must find the number of natural numbers which is divisor of N and is divisible by K. 
If T=3, Chef must find the number of natural numbers which is divisor of N and is not divisible by K. 

Chef can solve all these queries, but you will be hungry for night if this happens, because Chef will not have free time to cook a meal. 
Therefore you compromise with him and decided that everyone must do his/her own job. You must program and Chef must cook.

*/


	static HashMap<Long, Integer> map = new HashMap<Long, Integer>();
	private static long total = 1;
	private static long n;


	static long getDivisorDivisible_byK(long k) {
        if (n % k != 0) return 0;
        k = n / k;
        long res = 1;
        for (Map.Entry<Long, Integer> i: map.entrySet()) {
            long key = i.getKey();
            int val = i.getValue();
            int cnt = 0;
            while (k % key == 0) {
                k /= key; cnt++;
            }
            res *= (cnt + 1);
        }
        return res;
    }


    static long getCommonDivisor(long k) {
        long commonDivisor = 1;
        for (Map.Entry<Long, Integer> i: map.entrySet()) {
            long key = i.getKey();
            if (k % key == 0) {
                int cnt = 0;
                while (k % key == 0) {
                    k /= key; cnt++;
                }
                commonDivisor *= (Math.min(i.getValue(), cnt)+1);
            }
        }
        return commonDivisor;
    }


	 
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
		
		 FastScanner sc = new FastScanner();
		  n = sc.nextLong();
		 StringBuilder builder = new StringBuilder();
		 int q = sc.nextInt();
		 PrintWriter out = new PrintWriter(System.out);
		 primefact(n);
		 while(q-- != 0) {
			 int t = sc.nextInt();
			 long k = sc.nextLong();
			 if(t == 1) {
				 	long res = getCommonDivisor(k);
					 builder.append(res+"\n");
					
			 }
			 else if(t == 2) {
				 long res = getDivisorDivisible_byK(k);
				 builder.append(res+"\n");
			 
			 }
			 else {
				 
				 long res = total - getDivisorDivisible_byK(k);
				 builder.append(res+"\n");
			 }
			 

		 }
		 out.print(builder);
		 out.flush();
	}
