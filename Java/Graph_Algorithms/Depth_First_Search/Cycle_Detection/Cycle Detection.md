# Cycle Detection

To detect a cycle in a directed graph (i.e to find a back edge), you can use depth-first search (with some introduction of local state to tell you if a back edge occurs):

- We will maintain 3 buckets of vertices: white, grey, & black buckets. (We can also colour vertices instead).
    - The white bucket will contain all of the unvisited vertices. At the start of our traversal, this means every vertex is in the white bucket.

    - Before visiting a vertex, we will move it from the white bucket into the grey bucket.
    - After fully visiting a vertex, it will get moved from the grey bucket into the black bucket.
    - We can skip over vertices already in the black bucket, if we happen to try and visit them again.
    - When visiting the children/descendants of a vertex, if we come to a descendant vertex that is already in the grey bucket - that means we have found a back edge/cycle.
    - This means the current vertex has a back edge to it's ancestor - as we only arrived at the current vertex via it's ancestor. So we have just determined that there is more than one path between the two (a cycle).
- To detect a cycle in an undirected graph, it is very similar to the approach for a directed graph. However, there are some key differences:
    - We no longer colour vertices/maintain buckets.
    - We have to make sure to account for the fact that edges are bidirectional - so an edge to an ancestor is allowed, if that ancestor is the parent vertex.
    - We only keep track of visited vertices (similar to the grey bucket).
    - When exploring/visiting all descendants of a vertex, if we come across a vertex that has already been visited then we have detected a cycle.

__Time complexity__ is `O(V + E)` for an adjacency list. __Space complexity__ is `O(V)`. For an adjacency matrix, the time & space complexity would be O(V^2).
Although using depth-first search is common for cycle detection, you can also detect cycles using topological sort too.

Below are implementations of cycle detection via depth-first search in both undirected & directed graphs. I have also implemented each for an adjacency list & an adjacency matrix graph representation. As C# does not have a graph class in it's standard library, I have written light weight graph implementations too (`Graph` is an adjacency list & `Graph2` is an adjacency matrix):

```java
using System.Collections.Generic;

public class Program
{
    public class Graph
    {
        private Dictionary<int, List<int>> adjList;

        public Graph()
        {
            adjList = new Dictionary<int, List<int>>();
        }

        public Dictionary<int, List<int>> AdjList
        {
            get
            {
                return adjList;
            }
        }

        public void AddEdgeDirected(int source, int destination)
        {
            if (adjList.ContainsKey(source))
            {
                if (!adjList[source].Contains(destination))
                {
                    adjList[source].Add(destination);
                }
            }
            else
            {
                adjList.Add(source, new List<int> { destination });
            }

            if (!adjList.ContainsKey(destination))
            {
                adjList.Add(destination, new List<int>());
            }
        }

        // Don't actually do this (i.e. actually write 2 classes for directed/undirected):
        public void AddEdgeUndirected(int source, int destination)
        {
            if (adjList.ContainsKey(source))
            {
                if (!adjList[source].Contains(destination))
                {
                    adjList[source].Add(destination);
                }
            }
            else
            {
                adjList.Add(source, new List<int> { destination });
            }

            if (adjList.ContainsKey(destination))
            {
                if (!adjList[destination].Contains(source))
                {
                    adjList[destination].Add(source);
                }
            }
            else
            {
                adjList.Add(destination, new List<int> { source });
            }
        }
    }

    public static bool HasCycleDirected(Graph graph)
    {
        var white = new List<int>();
        var grey = new HashSet<int>();
        var black = new HashSet<int>();

        foreach (var vertex in graph.AdjList)
        {
            white.Add(vertex.Key);
        }

        while (white.Count > 0)
        {
            var vertex = white[white.GetEnumerator().Current];

            if (Dfs(vertex, graph.AdjList[vertex], white, grey, black, graph.AdjList))
            {
                return true;
            }
        }

        return false;
    }

    private static bool Dfs(
        int vertex,
        List<int> adjVertices,
        List<int> white,
        HashSet<int> grey,
        HashSet<int> black,
        Dictionary<int, List<int>> adjList)
    {
        white.Remove(vertex);
        grey.Add(vertex);

        foreach (var adjVertex in adjVertices)
        {
            if (black.Contains(adjVertex))
            {
                continue;
            }

            if (grey.Contains(adjVertex))
            {
                return true;
            }

            if (Dfs(adjVertex, adjList[adjVertex], white, grey, black, adjList))
            {
                return true;
            }
        }

        grey.Remove(vertex);
        black.Add(vertex);

        return false;
    }

    public static bool HasCycleUndirected(Graph graph)
    {
        var visited = new HashSet<int>();

        foreach (var vertex in graph.AdjList)
        {
            if (visited.Contains(vertex.Key))
            {
                continue;
            }

            if (Dfs(vertex.Key, int.MinValue, visited, graph.AdjList))
            {
                return true;
            }
        }

        return false;
    }

    private static bool Dfs(
        int vertex,
        int parent,
        HashSet<int> visited,
        Dictionary<int, List<int>> adjList)
    {
        visited.Add(vertex);

        foreach (var adjVertex in adjList[vertex])
        {
            if (adjVertex == parent)
            {
                continue;
            }

            if (visited.Contains(adjVertex))
            {
                return true;
            }

            if (Dfs(adjVertex, vertex, visited, adjList))
            {
                return true;
            }
        }

        return false;
    }

    public class Graph2
    {
        private bool[,] adjMatrix;

        public Graph2(int vertices)
        {
            adjMatrix = new bool[vertices, vertices];
        }

        public bool[,] AdjMatrix
        {
            get
            {
                return adjMatrix;
            }
        }

        public void AddEdgeDirected(int source, int destination)
        {
            adjMatrix[source, destination] = true;
        }

        public void AddEdgeUndirected(int source, int destination)
        {
            adjMatrix[source, destination] = true;
            adjMatrix[destination, source] = true;
        }
    }

    public static bool HasCycleDirected2(Graph2 graph)
    {
        var numVertices = graph.AdjMatrix.GetLength(0);
        // white = 0, grey = 1, black = 2
        // starts off all vertices are white
        var colours = new int[numVertices];

        for (int i = 0; i < numVertices; i++)
        {
            if (Dfs(i, colours, numVertices, graph.AdjMatrix))
            {
                return true;
            }
        }

        return false;
    }

    private static bool Dfs(int vertex, int[] colours, int numVertices, bool[,] adjMatrix)
    {
        // grey
        colours[vertex] = 1;

        for (int i = 0; i < numVertices; i++)
        {
            // i is not in graph (so not a vertex)
            if (!adjMatrix[vertex, i])
            {
                continue;
            }

            // black, so already visited
            if (colours[i] == 2)
            {
                continue;
            }

            // grey, so currently visiting already
            if (colours[i] == 1)
            {
                return true;
            }

            // i is a vertex in our graph, and is white (not visited/not being visited)
            if (Dfs(i, colours, numVertices, adjMatrix))
            {
                return true;
            }
        }

        // black
        colours[vertex] = 2;

        return false;
    }

    public static bool HasCycleUndirected2(Graph2 graph)
    {
        var visited = new HashSet<int>();
        var numVertices = graph.AdjMatrix.GetLength(0);

        for (int i = 0; i < numVertices; i++)
        {
            if (visited.Contains(i))
            {
                continue;
            }

            if (Dfs(i, int.MinValue, visited, numVertices, graph.AdjMatrix))
            {
                return true;
            }
        }

        return false;
    }

    private static bool Dfs(int vertex, int parent, HashSet<int> visited, int numVertices, bool[,] adjMatrix)
    {
        visited.Add(vertex);

        for (int i = 0; i < numVertices; i++)
        {
            // skip if i is parent (this is allowed in undirected graphs)
            if (i == parent)
            {
                continue;
            }

            // skip if not vertex in matrix
            if (!adjMatrix[vertex, i])
            {
                continue;
            }

            // cycle if already visited
            if (visited.Contains(i))
            {
                return true;
            }

            if (Dfs(i, vertex, visited, numVertices, adjMatrix))
            {
                return true;
            }
        }

        return false;
    }

    public static void Main()
    {
        // uncomment edge additions to introduce cycles

        var g1 = new Graph();
        // g1.AddEdgeDirected(0, 0);
        g1.AddEdgeDirected(1, 2);
        g1.AddEdgeDirected(1, 3);
        g1.AddEdgeDirected(2, 3);
        g1.AddEdgeDirected(4, 1);
        g1.AddEdgeDirected(4, 5);
        g1.AddEdgeDirected(5, 6);
        // g1.AddEdgeDirected(6, 4);

        var hasCycle = HasCycleDirected(g1);

        var g2 = new Graph();
        // g2.AddEdgeUndirected(0, 0);
        g2.AddEdgeUndirected(1, 2);
        g2.AddEdgeUndirected(1, 3);
        // g2.AddEdgeUndirected(2, 3);
        g2.AddEdgeUndirected(4, 1);
        g2.AddEdgeUndirected(4, 5);
        g2.AddEdgeUndirected(5, 6);
        // g2.AddEdgeUndirected(6, 4);

        var hasCycle2 = HasCycleUndirected(g2);

        // adj matrix:
        var g3 = new Graph2(10);
        // g3.AddEdgeDirected(0, 0);
        g3.AddEdgeDirected(1, 2);
        g3.AddEdgeDirected(1, 3);
        g3.AddEdgeDirected(2, 3);
        g3.AddEdgeDirected(4, 1);
        g3.AddEdgeDirected(4, 5);
        g3.AddEdgeDirected(5, 6);
        // g3.AddEdgeDirected(6, 4);

        var hasCycle3 = HasCycleDirected2(g3);

        var g4 = new Graph2(10);
        // g4.AddEdgeUndirected(0, 0);
        g4.AddEdgeUndirected(1, 2);
        g4.AddEdgeUndirected(1, 3);
        // g4.AddEdgeUndirected(2, 3);
        g4.AddEdgeUndirected(4, 1);
        g4.AddEdgeUndirected(4, 5);
        g4.AddEdgeUndirected(5, 6);
        // g4.AddEdgeUndirected(6, 4);

        var hasCycle4 = HasCycleUndirected2(g4);
    }
}
```

Below written code is from [GeeksforGeeks](https://www.geeksforgeeks.org/detect-cycle-undirected-graph/) :

```java
// A Java Program to detect cycle in an undirected graph 
import java.io.*; 
import java.util.*; 

// This class represents a directed graph using adjacency list 
// representation 
class Graph 
{ 
	private int V; // No. of vertices 
	private LinkedList<Integer> adj[]; // Adjacency List Represntation 

	// Constructor 
	Graph(int v) { 
		V = v; 
		adj = new LinkedList[v]; 
		for(int i=0; i<v; ++i) 
			adj[i] = new LinkedList(); 
	} 

	// Function to add an edge into the graph 
	void addEdge(int v,int w) { 
		adj[v].add(w); 
		adj[w].add(v); 
	} 

	// A recursive function that uses visited[] and parent to detect 
	// cycle in subgraph reachable from vertex v. 
	Boolean isCyclicUtil(int v, Boolean visited[], int parent) 
	{ 
		// Mark the current node as visited 
		visited[v] = true; 
		Integer i; 

		// Recur for all the vertices adjacent to this vertex 
		Iterator<Integer> it = adj[v].iterator(); 
		while (it.hasNext()) 
		{ 
			i = it.next(); 

			// If an adjacent is not visited, then recur for that 
			// adjacent 
			if (!visited[i]) 
			{ 
				if (isCyclicUtil(i, visited, v)) 
					return true; 
			} 

			// If an adjacent is visited and not parent of current 
			// vertex, then there is a cycle. 
			else if (i != parent) 
				return true; 
		} 
		return false; 
	} 

	// Returns true if the graph contains a cycle, else false. 
	Boolean isCyclic() 
	{ 
		// Mark all the vertices as not visited and not part of 
		// recursion stack 
		Boolean visited[] = new Boolean[V]; 
		for (int i = 0; i < V; i++) 
			visited[i] = false; 

		// Call the recursive helper function to detect cycle in 
		// different DFS trees 
		for (int u = 0; u < V; u++) 
			if (!visited[u]) // Don't recur for u if already visited 
				if (isCyclicUtil(u, visited, -1)) 
					return true; 

		return false; 
	} 


	// Driver method to test above methods 
	public static void main(String args[]) 
	{ 
		// Create a graph given in the above diagram 
		Graph g1 = new Graph(5); 
		g1.addEdge(1, 0); 
		g1.addEdge(0, 2); 
		g1.addEdge(2, 1); 
		g1.addEdge(0, 3); 
		g1.addEdge(3, 4); 
		if (g1.isCyclic()) 
			System.out.println("Graph contains cycle"); 
		else
			System.out.println("Graph doesn't contains cycle"); 

		Graph g2 = new Graph(3); 
		g2.addEdge(0, 1); 
		g2.addEdge(1, 2); 
		if (g2.isCyclic()) 
			System.out.println("Graph contains cycle"); 
		else
			System.out.println("Graph doesn't contains cycle"); 
	} 
} 
// This code is contributed by Aakash Hasija 
```