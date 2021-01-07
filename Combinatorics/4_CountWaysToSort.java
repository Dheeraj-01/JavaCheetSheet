

static long mod = (int) (1000000007);
static long countWaysToSort(ArrayList<Pair> arr) {
		ArrayList<Pair> a = new ArrayList<Pair>(arr);
		Collections.sort(a);
		long ways = 1;
		long prevEqual=1;
		for (int i=1; i<a.size(); i++) {
			if (a.get(i).x == a.get(i-1).x && a.get(i).y == a.get(i-1).y)
				prevEqual = (prevEqual+1)%mod;
			else
				prevEqual=1;
			ways=(ways*(prevEqual)%mod)%mod;
			if (a.get(i).x<a.get(i-1).x || a.get(i).y<a.get(i-1).y)
				ways=(0);
		}
		return ways%mod;
	}
  
  
  
  static class Pair implements Comparable<Pair> {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair o) {
			if(x == o.x)return Integer.compare(y, o.y);
			return Integer.compare(this.x, o.x);
		}
	}
