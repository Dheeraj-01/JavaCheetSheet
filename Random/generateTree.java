
	static void printTreeEdges(int prufer[], int m, PrintWriter out)
    {
        int vertices = m + 2;
        int vertex_set[] = new int[vertices];
 
        // Initialize the array of vertices
        for (int i = 0; i < vertices; i++)
            vertex_set[i] = 0;
 
        // Number of occurrences of vertex in code
        for (int i = 0; i < vertices - 2; i++)
            vertex_set[prufer[i] - 1] += 1;
 
        int j = 0;
 
        // Find the smallest label not present in
        // prufer[].
        for (int i = 0; i < vertices - 2; i++) {
            for (j = 0; j < vertices; j++) {
 
                // If j+1 is not present in prufer set
                if (vertex_set[j] == 0) {
 
                    // Remove from Prufer set and print
                    // pair.
                    vertex_set[j] = -1;
                    int limit = ThreadLocalRandom.current().nextInt(1,Max);
                    long tax = ThreadLocalRandom.current().nextLong(1,Max_Tax);
                   out.println((j + 1) + " "
                                     + prufer[i] + " "+limit+" "+tax);
 
                    vertex_set[prufer[i] - 1]--;
 
                    break;
                }
            }
        }
 
        j = 0;
 
        // For the last element
        int limit = ThreadLocalRandom.current().nextInt(1,Max);
        long tax = ThreadLocalRandom.current().nextLong(1,Max_Tax);
        for (int i = 0; i < vertices; i++) {
            if (vertex_set[i] == 0 && j == 0) {
 
            	out.print((i + 1) +" ");
                j++;
            }
            else if (vertex_set[i] == 0 && j == 1)
            	out.println((i + 1)+" "+limit+" "+tax);
        }
    }
 
    // Function to Generate Random Tree
    static void generateRandomTree(int n, PrintWriter out)
    {
 
        Random rand = new Random();
        int length = n - 2;
        int[] arr = new int[length];
 
        // Loop to Generate Random Array
        for (int i = 0; i < length; i++) {
            arr[i] = rand.nextInt(length + 1) + 1;
        }
        printTreeEdges(arr, length,out);
    }
