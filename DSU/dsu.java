

static class WeightedQU 
	{
	    private int[] id;
	    private int[] iz;

	    public WeightedQU(int N)
	    {
	        id = new int[N];
	        iz = new int[N];
	        for(int i = 0; i < id.length; i++)
	        {
	            iz[i] = i;
	            id[i] = i;
	        }
	    }

	    public int root(int i)
	    {
	        while(i != id[i])
	        {
	            id[i] = id[id[i]];   // this line represents "path compression"
	            i = id[i];
	        }
	        return i;
	    }

	    public boolean connected(int p, int q)
	    {
	        return root(p) == root(q);
	    }

	    public void union(int p, int q)   // here iz[] is used to "weighting"
	    {
	        int i = root(p);
	        int j = root(q);
	        if(iz[i] < iz[j])
	        {
	            id[i] = j;
	            iz[j] += iz[i];
	        }
	        else
	        {
	            id[j] = i;
	            iz[i] += iz[j];
	        }
	    }
	}
  
  
  public static void main(String[] args) {
		WeightedQU dsu = new WeightedQU(n);
	}
