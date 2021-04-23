

static class segmentTree {
		int[]arr,sTree,lazy;
		int N;
		public segmentTree(int[]in) {//in array is 1-based array
			arr=in;
			int n=in.length;
			N=1;
			while(N<n) {
				N<<=1;
			}
			sTree=new int[N<<1];
			lazy=new int[N<<1];
			build(1,1,N);
		}
		private int appyOperation(int val1, int val2) {
			return val1 + val2;
		}
		private int Answer_Not_Affect() {
			return 0;
		}
		private void Update_When_EveryIsInside(int node, int curRight, int curLeft, int val) {
			sTree[node] += (curRight-curLeft+1)*val;// logic for Add	
			lazy[node] += val;
		}
		
		void propagate(int node, int b, int mid, int e){
			lazy[node<<1] += lazy[node];
			lazy[node<<1|1] += lazy[node];
			sTree[node<<1] += (mid-b+1)*lazy[node];		
			sTree[node<<1|1] += (e-mid)*lazy[node];		
			lazy[node] = 0;//the value that won't affect the required operation
		}
		
		
		
		
		
		
		void build(int node,int left,int right) {
			if(left==right) {
				if(left>=arr.length) {
					sTree[node]=Answer_Not_Affect();//the value that won't affect the required operation
					
				}
				else {
					sTree[node]=arr[left];
				}
				return;
			}
			int mid=(left+right)>>1;
			int leftChild=node<<1,rightChild=node<<1|1;
			build(leftChild, left, mid);
			build(rightChild, mid+1, right);
			sTree[node]=appyOperation(sTree[leftChild],sTree[rightChild]);
		}
		
		
		int query(int left,int right) {
			return query(1, 1, N, left, right);
		}
		int query(int node,int curLeft,int curRight,int left,int right) {
			if(curLeft>right || curRight<left)return Answer_Not_Affect();//the value that won't affect the required operation
			
			if(curLeft>=left && curRight<=right) {
				return sTree[node];
			}
			int mid=(curLeft+curRight)>>1;
			int leftChild=node<<1,rightChild=node<<1|1;
			propagate(node, curLeft, mid, curRight);
			int q1=query(leftChild, curLeft, mid, left, right);
			int q2=query(rightChild, mid+1, curRight, left, right);
			
			return appyOperation(q1, q2);
		}
		int query_point(int i){
			return query_point(1,1,N,i);
		}
		
		int query_point(int node,int curLeft,int curRight,int idx){
			
			if(curLeft==curRight) {
				return sTree[node];
			}
			int mid=(curLeft+curRight)>>1;
			int leftChild=node<<1,rightChild=node<<1|1;
			propagate(node, curLeft, mid, curRight);
			if(idx<=mid) {
				return query_point(leftChild, curLeft, mid,idx);
			}
			return query_point(rightChild, mid+1, curRight,idx);
					
		}
		
		void update_range(int i, int j, int val){
			update_range(1,1,N,i,j,val);
		}
		
		void update_range(int node,int curLeft,int curRight,int left,int right,int val) {
			if(curLeft>right || curRight<left)return;
			
			if(curLeft>=left && curRight<=right) {
				Update_When_EveryIsInside(node,curRight,curLeft,val);
				return;
			}
			int mid=(curLeft+curRight)>>1;
			int leftChild=node<<1,rightChild=node<<1|1;
			propagate(node, curLeft, mid, curRight);
			update_range(leftChild, curLeft, mid, left, right,val);
			update_range(rightChild, mid+1, curRight, left, right,val);
			sTree[node]=appyOperation(sTree[leftChild], sTree[rightChild]);
		}
		
		
		void update_Point(int i, int val){
			update_Point(1,1,N,i,val);
		}
		
		void update_Point(int node,int curLeft,int curRight,int idx,int val) {
			if(curLeft==curRight) {
				sTree[node] += val;			
				lazy[node] += val;
				return;
			}
			int mid=(curLeft+curRight)>>1;
			int leftChild=node<<1,rightChild=node<<1|1;
			propagate(node, curLeft, mid, curRight);
			if(idx<=mid)
				update_Point(leftChild, curLeft, mid, idx, val);
			else
				update_Point(rightChild, mid+1, curRight, idx ,val);
			sTree[node]=appyOperation(sTree[leftChild], sTree[rightChild]);
		}
		
		
	}
