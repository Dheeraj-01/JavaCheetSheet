

static int N = 200005;
	static int parent[] = new int[N];
	static long size[] = new long[N];
	static int findParent(int i)
	//function to find the connected component that the ith node belongs to
	{
	    if(parent[parent[i]]!=parent[i])
	        parent[i]=findParent(parent[i]);
	    return parent[i];
	}
	static void unionNodes(int a,int b)
	//to merge the connected components of nodes a and b
	{
	    int parent_a=findParent(a),parent_b=findParent(b);
	    if(parent_a==parent_b)
	        return;
	    if(size[parent_a]>=size[parent_b])//the larger connected component eats up the smaller one
	    {
	         size[parent_a]+=size[parent_b];
	         parent[parent_b]=parent_a;
	    }
	    else
	    {
	         size[parent_b]+=size[parent_a];
	         parent[parent_a]=parent_b;
	    }
	    return;
	}
  
  
  
  
  ///////////////////////main/////////////////
  for(int i=1;i<N;i++)
	    {
	        parent[i]=i;
	        size[i]=1;
	    }
