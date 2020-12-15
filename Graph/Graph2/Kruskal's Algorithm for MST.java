// Kruskal's Algorithm for MST

public class KruskalsAlgorithm {

	private static int[] par;
	private static void merge(int a, int b) {
		par[a] = b;
	}
	private static int find(int val) {
		if(par[val] == -1)return val;
		return par[val] = find(par[val]);
	}
	static class Mygraph implements Comparable<Mygraph>{
		int a,b,val;
		public Mygraph(int a,int b,int val) {
			this.a = a;
			this.b = b;
			this.val = val;
		}
		@Override
		public int compareTo(Mygraph o) {
			return Integer.compare(val, o.val);
		}
		
	}

	public static void main(String[] args) throws IOException {

		FastScanner sc = new FastScanner();
		int n = sc.nextInt(),m = sc.nextInt();
		ArrayList<Mygraph> graph = new ArrayList<Mygraph>();
		par = new int[n+1];
		Arrays.fill(par, -1);
		for(int i=1; i<=m; i++) {
			int u = sc.nextInt(),v = sc.nextInt(),val = sc.nextInt();
			graph.add(new Mygraph(u, v, val));
		}
		Collections.sort(graph);
		int sum = 0;
		for(int i=0; i<m; i++) {
			int a = find(graph.get(i).a);
			int b = find(graph.get(i).b);
			if(a != b) {
				sum+=graph.get(i).val;
				merge(a,b);
			}
			else
				continue;
		}
		System.out.println(sum);
	}
	
}
