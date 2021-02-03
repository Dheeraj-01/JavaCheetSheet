public static void process() throws IOException {

		int n = sc.nextInt();
		segtree tree = new segtree(n);
		long arr[] = sc.readArrayLong(n);
		for(int i=0; i<n; i++){
			tree.update(i+1, i+1, arr[i]);
		}
		System.out.println(tree.query(1, n));
		

	}
  // Start



	static class Node {
		long val;
		public Node(long val,int freq) {
			this.val = val;
		}
	}
	static class UpdatePart {
		long val;
		public UpdatePart(long val) {
			this.val = val;
		}
	}

	static class segtree {
		
		private void applyOperation(int si) {
			// TODO perform Operation 
			sagmentTree[si].val = (sagmentTree[si * 2].val +
					sagmentTree[si * 2 + 1].val); // perform operation i add it
			
			
		}
		
		void pushDown(int si, int ss, int se) {
			if (lazy[si].val != 0) {
				long dx = lazy[si].val*(se-ss+1);
				lazy[si].val = 0;
				sagmentTree[si].val += dx;

				if (ss != se) {
					lazy[si * 2].val += dx;
					lazy[si * 2 + 1].val += dx;
				}
			}
			
		}
		int n;
		Node[] sagmentTree;
		UpdatePart[] lazy;

		segtree(int n) {
			this.n=n;
			sagmentTree = new Node[4*n+1];
			lazy = new UpdatePart[4*n+1];
			for(int i=0; i<4*n+1; i++)sagmentTree[i] = new Node(0,0);
			for(int i=0; i<4*n+1; i++)lazy[i] = new UpdatePart(0);
			
			init(1,1,n);//init for root node and it is the entire range
		}

		void init(int si, int ss, int se) {
			if (ss == se) {
				sagmentTree[si].val = 0;
				return;
			}

			int mid = ss + (se - ss) / 2;
			init(si * 2, ss, mid);
			init(si * 2 + 1, mid + 1, se);

			applyOperation(si);
			
		}
		
		

		
		void update(int qs, int qe, long value) {
			update(1, 1, n, qs, qe, value);
		}
		
		
		void update(int si, int ss, int se, int qs, int qe, long value) {

			pushDown(si,ss,se);

			if (ss > qe || se < qs)
				return;

			if (ss >= qs && se <= qe) {
				long dx = value*(se-ss+1);
				sagmentTree[si].val += dx;
				if (ss != se) {
					lazy[si * 2].val += value;
					lazy[si * 2 + 1].val += value;
				}
				return;
			}

			int mid = ss + (se - ss) / 2;

			update(si * 2, ss, mid, qs, qe, value);
			update(si * 2 + 1, mid + 1, se, qs, qe, value);

			applyOperation(si);
		}
		
		
		
		long query(int qs, int qe) {
			return query(1, 1, n, qs, qe);
		}
		
		long query(int si, int ss, int se, int qs, int qe) {

			pushDown(si, ss, se);

			if (ss > qe || se < qs)
				return 0;

			if (ss >= qs && se <= qe)
				return sagmentTree[si].val;

			int mid = ss + (se - ss) / 2;

			long l = query(si * 2, ss, mid, qs, qe);
			long r = query(si * 2 + 1, mid + 1, se, qs, qe);
			
			return l+r;
		}


		

		
	}
