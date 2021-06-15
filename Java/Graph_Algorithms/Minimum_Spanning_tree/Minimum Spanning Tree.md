# Minimum Spanning Tree

A spanning tree of a graph `G = (V,E)` is a subset of edges from `E` forming a
tree connecting all vertices of `V` . For edge-weighted graphs, we are particularly
interested in the minimum spanning tree—the spanning tree whose sum of edge
weights is as small as possible.

An __edge-weighted graph__ is a graph model where we associate weights or costs with each edge. Such graphs are natural models for many applications. Minimizing cost is naturally of interest in such situations.

Minimum spanning trees are the answer whenever we need to connect a set
of points (representing cities, homes, junctions, or other locations) by the smallest
amount of roadway, wire, or pipe. Any tree is the smallest possible connected graph
in terms of number of edges, while the minimum spanning tree is the smallest
connected graph in terms of edge weight. In geometric problems, the point set
`p1, . . . , pn` defines a complete graph, with edge `(vi,vj)` assigned a weight equal to
the distance from `pi` to `pj`.

A minimum spanning tree minimizes the total length over all possible spanning
trees. However, there can be more than one minimum spanning tree in a graph.
Indeed, all spanning trees of an unweighted (or equally weighted) graph G are
minimum spanning trees, since each contains exactly n − 1 equal-weight edges.
Such a spanning tree can be found using depth-first or breadth-first search. Finding
a minimum spanning tree is more difficult for general weighted graphs.

Minimum spanning tree is used in two diiferent algorithms:

- __Prim’s Algorithm__
- __Kruskal's Algorithm__

## Growing a minimum spanning tree

Assume that we have a connected, undirected graph `G = D (V, E)` with a weight
function `w:E -> R`, and we wish to find a minimum spanning tree for `G`. The
two algorithms we consider in this chapter use a greedy approach to the problem,
although they differ in how they apply this approach.
This greedy strategy is captured by the following generic method, which grows
the minimum spanning tree one edge at a time. The generic method manages a set
of edges A, maintaining the following loop invariant:
Prior to each iteration, `A` is a subset of some minimum spanning tree.
At each step, we determine an edge `(u, v)` that we can add to A without violating
this invariant, in the sense that `A U {(u, v)}` is also a subset of a minimum spanning tree. We call such an edge a __safe edge__ for A, since we can add it safely to A while
maintaining the invariant.

```
GENERIC-MST(G, w)
 A != phi
 while A does not form a spanning tree
       find an edge (u, v) that is safe for A
       A = A U {(u, v)}
 return A
```

We use the loop invariant as follows:

- Initialization: After line 1, the set A trivially satisfies the loop invariant.
- Maintenance: The loop in lines 2–4 maintains the invariant by adding only safe
edges.
- Termination: All edges added to A are in a minimum spanning tree, and so the
set A returned in line 5 must be a minimum spanning tree.

##  Code

Below written code is from [Geeksforgeeks](https://www.geeksforgeeks.org/minimum-product-spanning-tree/?ref=rp):


```java

// A Java program for getting minimum product 
// spanning tree The program is for adjacency matrix 
// representation of the graph 
import java.util.*; 

class GFG { 

	// Number of vertices in the graph 
	static int V = 5; 

	// A utility function to find the vertex with minimum 
	// key value, from the set of vertices not yet included 
	// in MST 
	static int minKey(int key[], boolean[] mstSet) 
	{ 
		// Initialize min value 
		int min = Integer.MAX_VALUE, min_index = 0; 

		for (int v = 0; v < V; v++) { 
			if (mstSet[v] == false && key[v] < min) { 
				min = key[v]; 
				min_index = v; 
			} 
		} 
		return min_index; 
	} 

	// A utility function to print the constructed MST 
	// stored in parent[] and print Minimum Obtaiable 
	// product 
	static void printMST(int parent[], int n, int graph[][]) 
	{ 
		System.out.printf("Edge Weight\n"); 
		int minProduct = 1; 
		for (int i = 1; i < V; i++) { 
			System.out.printf("%d - %d %d \n", 
							parent[i], i, graph[i][parent[i]]); 

			minProduct *= graph[i][parent[i]]; 
		} 
		System.out.printf("Minimum Obtainable product is %d\n", 
						minProduct); 
	} 

	// Function to construct and print MST for a graph 
	// represented using adjacency matrix representation 
	// inputGraph is sent for printing actual edges and 
	// logGraph is sent for actual MST operations 
	static void primMST(int inputGraph[][], double logGraph[][]) 
	{ 
		int[] parent = new int[V]; // Array to store constructed MST 
		int[] key = new int[V]; // Key values used to pick minimum 
		// weight edge in cut 
		boolean[] mstSet = new boolean[V]; // To represent set of vertices not 
		// yet included in MST 

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
			// Pick the minimum key vertex from the set of 
			// vertices not yet included in MST 
			int u = minKey(key, mstSet); 

			// Add the picked vertex to the MST Set 
			mstSet[u] = true; 

			// Update key value and parent index of the 
			// adjacent vertices of the picked vertex. 
			// Consider only those vertices which are not yet 
			// included in MST 
			for (int v = 0; v < V; v++) // logGraph[u][v] is non zero only for 
			// adjacent vertices of m mstSet[v] is false 
			// for vertices not yet included in MST 
			// Update the key only if logGraph[u][v] is 
			// smaller than key[v] 
			{ 
				if (logGraph[u][v] > 0
					&& mstSet[v] == false
					&& logGraph[u][v] < key[v]) { 

					parent[v] = u; 
					key[v] = (int)logGraph[u][v]; 
				} 
			} 
		} 

		// print the constructed MST 
		printMST(parent, V, inputGraph); 
	} 

	// Method to get minimum product spanning tree 
	static void minimumProductMST(int graph[][]) 
	{ 
		double[][] logGraph = new double[V][V]; 

		// Constructing logGraph from original graph 
		for (int i = 0; i < V; i++) { 
			for (int j = 0; j < V; j++) { 
				if (graph[i][j] > 0) { 
					logGraph[i][j] = Math.log(graph[i][j]); 
				} 
				else { 
					logGraph[i][j] = 0; 
				} 
			} 
		} 

		// Applyting standard Prim's MST algorithm on 
		// Log graph. 
		primMST(graph, logGraph); 
	} 

	// Driver code 
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
		int graph[][] = { 
			{ 0, 2, 0, 6, 0 }, 
			{ 2, 0, 3, 8, 5 }, 
			{ 0, 3, 0, 0, 7 }, 
			{ 6, 8, 0, 0, 9 }, 
			{ 0, 5, 7, 9, 0 }, 
		}; 

		// Print the solution 
		minimumProductMST(graph); 
	} 
} 

```

#
