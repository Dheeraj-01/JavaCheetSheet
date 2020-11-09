
public static void main(String[] args) throws IOException {

		FastScanner sc = new FastScanner();
		int n = sc.nextInt();
		int arr[] = sc.readArray(n);
		BIT bit=new BIT(n);
		for(int i=0; i<n; i++) {
			bit.update(i, arr[i]);
		}
		int q = sc.nextInt();
		while(q-- != 0) {
			int l = sc.nextInt()-1,r = sc.nextInt()-1;
			System.out.println(bit.read(r) - bit.read(l-1));
		}
		

	}
	
	static class BIT {
		int n;
		int[] tree;
		
		public BIT(int n) {
			this.n = n;
			tree = new int[n + 2];
		}
		
		int read(int i) {
			i++;
			int sum = 0;
			while (i > 0) {
				sum += tree[i];
				i -= i & -i;
			}
			return sum;
		}
		
		void update(int i, int val) {
			i++;
			while (i <= n) {
				tree[i] += val;
				i += i & -i;
			}
		}

		// if the BIT is a freq array, returns the
		// index of the kth item, or n if there are fewer
		// than k items.
		int getKth(int k) {
			int e=Integer.highestOneBit(n), o=0;
			for (; e!=0; e>>=1) {
				if (e+o<=n && tree[e+o]<=k) {
					k-=tree[e+o];
					o+=e;
				}
			}
			return o;
		}
		
	}
