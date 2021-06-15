# Prim’s Algorithm

Prim’s minimum spanning tree algorithm starts from one vertex and grows the rest
of the tree one edge at a time until all vertices are included.

Greedy algorithms make the decision of what to do next by selecting the best local option from all available choices without regard to the global structure. Since we seek the tree of minimum weight, the natural greedy algorithm for minimum spanning tree repeatedly selects the smallest weight edge that will enlarge the number of vertices in the tree.

~~~
Prim-MST(G)
Select an arbitrary vertex s to start the tree from.
While (there are still nontree vertices)
Select the edge of minimum weight between a tree and nontree vertex
Add the selected edge and vertex to the tree Tprim.
~~~

## Implementation
Prim’s algorithm grows the minimum spanning tree in stages, starting from a given
vertex. At each iteration, we add one new vertex into the spanning tree. A greedy
algorithm suffices for correctness: we always add the lowest-weight edge linking a
vertex in the tree to a vertex on the outside. The simplest implementation of this
idea would assign each vertex a Boolean variable denoting whether it is already in
the tree (the array intree in the code below), and then searches all edges at each
iteration to find the minimum weight edge with exactly one intree vertex.
Our implementation is somewhat smarter. It keeps track of the cheapest edge
linking every nontree vertex in the tree. The cheapest such edge over all remaining
non-tree vertices gets added in each iteration. We must update the costs of getting
to the non-tree vertices after each insertion. However, since the most recently inserted vertex is the only change in the tree, all possible edge-weight updates must
come from its outgoing edges:

```c
prim(graph *g, int start)
{
    int i; /* counter */
    edgenode *p; /* temporary pointer */
    bool intree[MAXV+1]; /* is the vertex in the tree yet? */
    int distance[MAXV+1]; /* cost of adding to tree */
    int v; /* current vertex to process */
    int w; /* candidate next vertex */
    int weight; /* edge weight */
    int dist; /* best current distance from start */
    for (i=1; i<=g->nvertices; i++) {
        intree[i] = FALSE;
        distance[i] = MAXINT;
        parent[i] = -1;
    }
    distance[start] = 0;
    v = start;
    while (intree[v] == FALSE) {
        intree[v] = TRUE;
        p = g->edges[v];
        while (p != NULL) {
            w = p->y;
            weight = p->weight;
            if ((distance[w] > weight) && (intree[w] == FALSE)) {
                distance[w] = weight;
                parent[w] = v;
            }
            p = p->next;
        }
        v = 1;
        dist = MAXINT;
        for (i=1; i <= g -> nvertices; i++)
            if ((intree[i] == FALSE) && (dist > distance[i])) {
                dist = distance[i];
                v = i;
            }
    }
}
```

## Analysis

Prim’s algorithm is correct, but how efficient is it? This depends on which data
structures are used to implement it. In the pseudocode, Prim’s algorithm makes
n iterations sweeping through all m edges on each iteration—yielding an `O(mn)`
algorithm.

But our implementation avoids the need to test all `m` edges on each pass. It only considers the `≤ n` cheapest known edges represented in the parent array
and the `≤ n` edges out of new tree vertex `v` to update parent. By maintaining a Boolean flag along with each vertex to denote whether it is in the tree or not, we
test whether the current edge joins a tree with a non-tree vertex in constant time.

The result is an O(n<sup>2</sup>) implementation of Prim’s algorithm, and a good illustration of power of data structures to speed up algorithms. In fact, more sophisticated priority-queue data structures lead to an `O(m +nlgn)` implementation, by making
it faster to find the minimum cost edge to expand the tree at each iteration.

The minimum spanning tree itself or its cost can be reconstructed in two different ways. The simplest method would be to augment this procedure with statements that print the edges as they are found or totals the weight of all selected
edges. Alternately, the tree topology is encoded by the parent array, so it plus the
original graph describe everything about the minimum spanning tree.

## Code

Below given code is from [Geeksforgeeks](https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/):

```java
// A Java program for Prim's Minimum Spanning Tree (MST) algorithm. 
// The program is for adjacency matrix representation of the graph 

import java.util.*; 
import java.lang.*; 
import java.io.*; 

class MST { 
	// Number of vertices in the graph 
	private static final int V = 5; 

	// A utility function to find the vertex with minimum key 
	// value, from the set of vertices not yet included in MST 
	int minKey(int key[], Boolean mstSet[]) 
	{ 
		// Initialize min value 
		int min = Integer.MAX_VALUE, min_index = -1; 

		for (int v = 0; v < V; v++) 
			if (mstSet[v] == false && key[v] < min) { 
				min = key[v]; 
				min_index = v; 
			} 

		return min_index; 
	} 

	// A utility function to print the constructed MST stored in 
	// parent[] 
	void printMST(int parent[], int graph[][]) 
	{ 
		System.out.println("Edge \tWeight"); 
		for (int i = 1; i < V; i++) 
			System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]); 
	} 

	// Function to construct and print MST for a graph represented 
	// using adjacency matrix representation 
	void primMST(int graph[][]) 
	{ 
		// Array to store constructed MST 
		int parent[] = new int[V]; 

		// Key values used to pick minimum weight edge in cut 
		int key[] = new int[V]; 

		// To represent set of vertices included in MST 
		Boolean mstSet[] = new Boolean[V]; 

		// Initialize all keys as INFINITE 
		for (int i = 0; i < V; i++) { 
			key[i] = Integer.MAX_VALUE; 
			mstSet[i] = false; 
		} 

		// Always include first 1st vertex in MST. 
		key[0] = 0; // Make key 0 so that this vertex is 
		// picked as first vertex 
		parent[0] = -1; // First node is always root of MST 

		// The MST will have V vertices 
		for (int count = 0; count < V - 1; count++) { 
			// Pick thd minimum key vertex from the set of vertices 
			// not yet included in MST 
			int u = minKey(key, mstSet); 

			// Add the picked vertex to the MST Set 
			mstSet[u] = true; 

			// Update key value and parent index of the adjacent 
			// vertices of the picked vertex. Consider only those 
			// vertices which are not yet included in MST 
			for (int v = 0; v < V; v++) 

				// graph[u][v] is non zero only for adjacent vertices of m 
				// mstSet[v] is false for vertices not yet included in MST 
				// Update the key only if graph[u][v] is smaller than key[v] 
				if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) { 
					parent[v] = u; 
					key[v] = graph[u][v]; 
				} 
		} 

		// print the constructed MST 
		printMST(parent, graph); 
	} 

	public static void main(String[] args) 
	{ 
		/* Let us create the following graph 
		2 3 
		(0)--(1)--(2) 
		| / \ | 
		6| 8/ \5 |7 
		| /	 \ | 
		(3)-------(4) 
			9		 */
		MST t = new MST(); 
		int graph[][] = new int[][] { { 0, 2, 0, 6, 0 }, 
									{ 2, 0, 3, 8, 5 }, 
									{ 0, 3, 0, 0, 7 }, 
									{ 6, 8, 0, 0, 9 }, 
									{ 0, 5, 7, 9, 0 } }; 

		// Print the solution 
		t.primMST(graph); 
	} 
} 

```

Below written codes is python implementation

```py

# Prim's Algorithm in Python


INF = 9999999
# number of vertices in graph
V = 5
# create a 2d array of size 5x5
# for adjacency matrix to represent graph
G = [[0, 9, 75, 0, 0],
     [9, 0, 95, 19, 42],
     [75, 95, 0, 51, 66],
     [0, 19, 51, 0, 31],
     [0, 42, 66, 31, 0]]
# create a array to track selected vertex
# selected will become true otherwise false
selected = [0, 0, 0, 0, 0]
# set number of edge to 0
no_edge = 0
# the number of egde in minimum spanning tree will be
# always less than(V - 1), where V is number of vertices in
# graph
# choose 0th vertex and make it true
selected[0] = True
# print for edge and weight
print("Edge : Weight\n")
while (no_edge < V - 1):
    # For every vertex in the set S, find the all adjacent vertices
    #, calculate the distance from the vertex selected at step 1.
    # if the vertex is already in the set S, discard it otherwise
    # choose another vertex nearest to selected vertex  at step 1.
    minimum = INF
    x = 0
    y = 0
    for i in range(V):
        if selected[i]:
            for j in range(V):
                if ((not selected[j]) and G[i][j]):  
                    # not in selected and there is an edge
                    if minimum > G[i][j]:
                        minimum = G[i][j]
                        x = i
                        y = j
    print(str(x) + "-" + str(y) + ":" + str(G[x][y]))
    selected[y] = True
    no_edge += 1

```

```py
# A Python program for Prim's Minimum Spanning Tree (MST) algorithm. 
# The program is for adjacency matrix representation of the graph 
  
import sys # Library for INT_MAX 
  
class Graph(): 
  
    def __init__(self, vertices): 
        self.V = vertices 
        self.graph = [[0 for column in range(vertices)]  
                    for row in range(vertices)] 
  
    # A utility function to print the constructed MST stored in parent[] 
    def printMST(self, parent): 
        print "Edge \tWeight"
        for i in range(1, self.V): 
            print parent[i], "-", i, "\t", self.graph[i][ parent[i] ] 
  
    # A utility function to find the vertex with  
    # minimum distance value, from the set of vertices  
    # not yet included in shortest path tree 
    def minKey(self, key, mstSet): 
  
        # Initilaize min value 
        min = sys.maxint 
  
        for v in range(self.V): 
            if key[v] < min and mstSet[v] == False: 
                min = key[v] 
                min_index = v 
  
        return min_index 
  
    # Function to construct and print MST for a graph  
    # represented using adjacency matrix representation 
    def primMST(self): 
  
        # Key values used to pick minimum weight edge in cut 
        key = [sys.maxint] * self.V 
        parent = [None] * self.V # Array to store constructed MST 
        # Make key 0 so that this vertex is picked as first vertex 
        key[0] = 0 
        mstSet = [False] * self.V 
  
        parent[0] = -1 # First node is always the root of 
  
        for cout in range(self.V): 
  
            # Pick the minimum distance vertex from  
            # the set of vertices not yet processed.  
            # u is always equal to src in first iteration 
            u = self.minKey(key, mstSet) 
  
            # Put the minimum distance vertex in  
            # the shortest path tree 
            mstSet[u] = True
  
            # Update dist value of the adjacent vertices  
            # of the picked vertex only if the current  
            # distance is greater than new distance and 
            # the vertex in not in the shotest path tree 
            for v in range(self.V): 
  
                # graph[u][v] is non zero only for adjacent vertices of m 
                # mstSet[v] is false for vertices not yet included in MST 
                # Update the key only if graph[u][v] is smaller than key[v] 
                if self.graph[u][v] > 0 and mstSet[v] == False and key[v] > self.graph[u][v]: 
                        key[v] = self.graph[u][v] 
                        parent[v] = u 
  
        self.printMST(parent) 
  
g = Graph(5) 
g.graph = [ [0, 2, 0, 6, 0], 
            [2, 0, 3, 8, 5], 
            [0, 3, 0, 0, 7], 
            [6, 8, 0, 0, 9], 
            [0, 5, 7, 9, 0]] 
  
g.primMST();

```

#
