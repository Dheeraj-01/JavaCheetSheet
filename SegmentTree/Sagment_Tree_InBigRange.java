static class dynamicSegmentTree {
		treeNode root;
		treeNode lazy;
		long minN,maxN;
		dynamicSegmentTree(long min,long max){
			root=new treeNode(0);
			lazy=new treeNode(0);
			minN=min;
			maxN=max;
		}
		
		static class treeNode{
			long val;
			treeNode left,right;
			treeNode(long vals){
				val=vals;
			}
		}
		void update(long left,long right,long val) {
			update(root, minN, maxN, left, right,lazy,val);
		}
		void update(treeNode node,long curLeft,long curRight,long left,long right,treeNode curLazy,long val) {
			if(curLeft>right || curRight<left)return;
			
			if(curLeft>=left && curRight<=right) {
				node.val+=(curRight-curLeft+1)*val;
				curLazy.val+=val;
				return;
			}
			long mid=(curLeft+curRight)>>1;
			checkChildren(node);
			checkChildren(curLazy);
			propagate(node, curLeft, mid, curRight,curLazy);
			update(node.left, curLeft, mid, left, right,curLazy.left,val);
			update(node.right, mid+1, curRight, left, right,curLazy.right,val);
			node.val=node.left.val+node.right.val;
		}
		void checkChildren(treeNode node) {
			if(node.left==null) {
				node.left=new treeNode(0);
			}
			if(node.right==null) {
				node.right=new treeNode(0);
			}
		}
		void propagate(treeNode node, long l, long mid, long r,treeNode curLazy){
			if(curLazy==null)return;
			curLazy.left.val+=curLazy.val;
			curLazy.right.val+=curLazy.val;
			
			node.left.val+=(mid-l+1)*curLazy.val;
			node.right.val+=(r-mid)*curLazy.val;
			curLazy.val = 0;//the value that won't affect the required operation
		}
		
		
		long query(long left,long right) {
			return query(root, minN, maxN, left, right,lazy);
		}
		long query(treeNode node,long curLeft,long curRight,long left,long right,treeNode curLazy) {
			if(node==null || curLeft>right || curRight<left)return 0;//the value that won't affect the required operation
			
			if(curLeft>=left && curRight<=right) {
				return node.val;
			}
			long mid=(curLeft+curRight)>>1;
			checkChildren(node);
			checkChildren(curLazy);
			propagate(node, curLeft, mid, curRight,curLazy);
			long q1=query(node.left, curLeft, mid, left, right,curLazy.left);
			long q2=query(node.right, mid+1, curRight, left, right,curLazy.right);
			
			return q1+q2;
		}
	}

	public static void process() throws IOException {
		dynamicSegmentTree st=new dynamicSegmentTree(1, 100000000);
		st.update(1, 5, 1);
		println(st.query(1, 4));

	}
