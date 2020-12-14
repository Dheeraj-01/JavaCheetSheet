
	
// BFS Implementation
	
	// In Main {  BFS(1);  }
	static int N = 0;
	static int visited[] = new int[N];
	static int distance[] = new int[N];
	 
	
	private static void BFS(int node) {
		
		visited[node] = 1;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(node);
		distance[node] = 0;
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			for(int child : arr[curr]) {
				if(visited[child] == 0) {
					q.offer(child);
					distance[child] = distance[curr] + 1;
					visited[child] = 1;
				}
			}
		}
		
	}
	
	
	
