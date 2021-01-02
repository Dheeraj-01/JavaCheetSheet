  static long mod = 1000000007;
	static long getHash(String key)
	{
		long value = 0;
		long p = 31;
		long p_power = 1;
	 
		for(char ch : key.toCharArray())
		{
			value = (value + (ch - 'a' + 1)*p_power) % mod;
			p_power = (p_power * p) % mod;
		}
	 
		return value;
	}
  
  ///////  main ////////
  
  
    System.out.print("enter key : ");
		String key = sc.next();
	
		System.out.println("KEY : "+key);
		System.out.println("HashCode : "+getHash(key));
