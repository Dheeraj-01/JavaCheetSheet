package Combinatorics;

import java.util.*;

public class Finding_Power_of_Factorial_Divisor {

	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	private static void primefact(int n) {

		for (int i = 2; i * i <= n; i++) {
    
			if (n % i == 0) {
				int count = 0;
				while (n % i == 0) {
					count++;
					n /= i;
				}
				map.put(i, count);
			}
		}
		if (n > 1)
			map.put(n, 1);
      

	}


	private static int Total_Divisors_nFactorial_For_Composite(int n, int k) {
		primefact(k);
		int min = Integer.MAX_VALUE;
		for(int prime : map.keySet()) {
			int a = Total_Divisors_k_Prime_nFactorial(n, prime);
			min = Math.min(min,a/map.get(prime));
		}
		return min;
	}
	
	private static int Total_Divisors_k_Prime_nFactorial(int n,int k) {
		int res = 0;
		while(n > 0) {
			n/=k;
			res+=n;
		}
		return res;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(),k = sc.nextInt();
		int val = Total_Divisors_k_Prime_nFactorial(n,k);// if k is prime
		int s = Total_Divisors_nFactorial_For_Composite(n,k);// if k is not prime
		System.out.println(s);
	}

	

	

}
