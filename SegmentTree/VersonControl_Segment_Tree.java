
	
	// Declaring maximum number
	static Integer MAXN = 100_009;

	// Making Node for tree
	static class node
	{
		
		// Stores sum of the elements in node
		long val;

		// Reference to left and right children
		node left, right;

		// Required constructors..
		node() {}

		// Node constructor for l,r,v
		node(node l, node r, long v)
		{
			left = l;
			right = r;
			val = v;
		}
	}

	// Input array
	static long[] arr = new long[MAXN];

	// Root pointers for all versions
	static node version[] = new node[MAXN];

	// Constructs Version-0
	// Time Complexity : O(nlogn)
	static void build(node n, int low, int high)
	{
		if (low == high)
		{
			n.val = arr[low];
			return;
		}
		
		int mid = (low + high) / 2;
		n.left = new node(null, null, 0);
		n.right = new node(null, null, 0);
		build(n.left, low, mid);
		build(n.right, mid + 1, high);
		n.val = n.left.val + n.right.val;
	}

	/* Upgrades to new Version
	* @param prev : points to node of previous version
	* @param cur : points to node of current version
	* Time Complexity : O(logn)
	* Space Complexity : O(logn) */
	static void upgrade(node prev, node cur, int low,
						int high, int idx, long value)
	{
		if (idx > high || idx < low || low > high)
			return;

		if (low == high)
		{
			
			// Modification in new version
			cur.val = value;
			return;
		}
		int mid = (low + high) / 2;
		
		if (idx <= mid)
		{
			
			// Link to right child of previous version
			cur.right = prev.right;

			// Create new node in current version
			cur.left = new node(null, null, 0);

			upgrade(prev.left, cur.left, low,
					mid, idx, value);
		}
		else
		{
			
			// Link to left child of previous version
			cur.left = prev.left;

			// Create new node for current version
			cur.right = new node(null, null, 0);

			upgrade(prev.right, cur.right, mid + 1,
					high, idx, value);
		}

		// Calculating data for current version
		// by combining previous version and current
		// modification
		cur.val = cur.left.val + cur.right.val;
	}

	static long query(node n, int low, int high,
							int l, int r)
	{
		if (l > high || r < low || low > high)
			return 0;
		if (l <= low && high <= r)
			return n.val;
			
		int mid = (low + high) / 2;
		long p1 = query(n.left, low, mid, l, r);
		long p2 = query(n.right, mid + 1, high, l, r);
		return p1 + p2;
	}

	public static void process() throws IOException {

		
		int n = sc.nextInt(),m = sc.nextInt();
		arr = sc.readArrayLong(n);
		

		// Creating Version-0
		node root = new node(null, null, 0);
		build(root, 0, n - 1);

		// Storing root node for version-0
		version[0] = root;
		int prev = 0,curr = 1;
		
		while(m-- != 0) {
			int a = sc.nextInt();
			if(a == 1) {
				int i = sc.nextInt();
				long val = sc.nextLong();
				version[curr] = new node(null, null, 0);
				upgrade(version[prev], version[curr], 0, n - 1, i, val);
				prev++;
				curr++;
			}
			else {
				int l = sc.nextInt(),r = sc.nextInt()-1;
				println(query(version[prev], 0, n - 1, l, r));
			}
			
		}
	

	}
