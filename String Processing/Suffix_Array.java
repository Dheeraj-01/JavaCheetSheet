
		static char T[];                        // the input string, up to 100K characters
		static int n;                                             // the length of input string

	  static int[] RA, tempRA;             // rank array and temporary rank array
	  static Integer[] SA, tempSA;         // suffix array and temporary suffix array
	  static int[] c;                                         // for counting/radix sort
	  static void countingSort(int k) {
	    int i, sum, maxi = Math.max(300, n);   // up to 255 ASCII chars or length of n
	    for (i = 0; i < 100010; i++) c[i] = 0;                // clear frequency table
	    for (i = 0; i < n; i++)                    // count the frequency of each rank
	      c[i + k < n ? RA[i + k] : 0]++;
	    for (i = sum = 0; i < maxi; i++) {
	      int t = c[i]; c[i] = sum; sum += t;
	    }
	    for (i = 0; i < n; i++)               // shuffle the suffix array if necessary
	      tempSA[c[SA[i] + k < n ? RA[SA[i] + k] : 0]++] = SA[i];
	    for (i = 0; i < n; i++)                          // update the suffix array SA
	      SA[i] = tempSA[i];
	  }

	static void constructSA() {              // this version can go up to 100000 characters
	    int i, k, r;
	    for (i = 0; i < n; i++) RA[i] = T[i];                      // initial rankings
	    for (i = 0; i < n; i++) SA[i] = i;          // initial SA: {0, 1, 2, ..., n-1}
	    for (k = 1; k < n; k <<= 1) {            // repeat sorting process log n times
	      countingSort(k);       // actually radix sort: sort based on the second item
	      countingSort(0);               // then (stable) sort based on the first item
	      tempRA[SA[0]] = r = 0;                  // re-ranking; start from rank r = 0
	      for (i = 1; i < n; i++)                         // compare adjacent suffices
	        tempRA[SA[i]] =      // if same pair => same rank r; otherwise, increase r
	          (RA[SA[i]] == RA[SA[i-1]] && RA[SA[i]+k] == RA[SA[i-1]+k]) ? r : ++r;
	      for (i = 0; i < n; i++)                          // update the rank array RA
	        RA[i] = tempRA[i];
	  } }
	
	private static int[] suffixArray(String str) {/* str.length + 1 length of array because 
	adding Special Character $*/
		str+="$";
		int MAX_N = 100010;
	    c = new int[MAX_N];
	    RA = new int[MAX_N];
	    tempRA = new int[MAX_N];
	    SA = new Integer[MAX_N];
	    tempSA = new Integer[MAX_N];
	    T = str.toCharArray();
	    n = T.length;

	    constructSA();                                                   // O(n log n)
//	    System.out.printf("The Suffix Array of string T = '%s' is shown below (O(n log n) version):\n", new String(T));
//	    System.out.printf("i\tSA[i]\tSuffix\n");
//	    for (int i = 0; i < n; i++)
//	     System.out.printf("%2d\t%2d\t%s\n", i, SA[i], new String(T, SA[i], T.length - SA[i]));
	    int arr[] = new int[n];
	    for(int i=0; i<n; i++)arr[i] = SA[i];
	    return arr;
	}

	public static void process() throws IOException {
		/* Note : Update Max Length Otherwise get runtime error */
		String str = sc.next();
		int arr[] = suffixArray(str);/* str.length + 1 length of array because 
		adding Special Character $*/
		System.out.println(Arrays.toString(arr));
	}

	
