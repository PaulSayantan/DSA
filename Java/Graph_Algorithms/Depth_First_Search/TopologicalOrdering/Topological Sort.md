# Topological Sort

>   In computer science, a topological sort or topological ordering of a directed graph is a linear ordering of its vertices such that for every directed edge `uv` from vertex `u` to vertex `v`, u comes before `v` in the ordering.

>   Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge `uv`, vertex `u` comes before `v` in the ordering. Topological Sorting for a graph is not possible if the graph is not a __DAG__.

## Topological Sorting vs Depth First Traversal (DFS):

In DFS, we print a vertex and then recursively call DFS for its adjacent vertices. In topological sorting, we need to print a vertex before its adjacent vertices.

## Algorithm to find Topological Sorting:
I recommend to first see implementation of DFS [here](https://github.com/belikesayantan/Algorithm-Design/blob/master/algorithm/src/Graph_Algorithms/Depth_First_Search/df_Search.java). We can modify DFS to find Topological Sorting of a graph. 

In DFS, we start from a vertex, we first print it and then recursively call DFS for its adjacent vertices. In topological sorting, we use a temporary stack. We don’t print the vertex immediately, we first recursively call topological sorting for all its adjacent vertices, then push it to a stack. Finally, print contents of stack. Note that a vertex is pushed to stack only when all of its adjacent vertices (and their adjacent vertices and so on) are already in stack.

![topsort Pseudocode](https://user-images.githubusercontent.com/53504602/83350913-0fc3d600-a35d-11ea-91c2-7a8e41223ad8.png)
![topsort psudocode 2](https://user-images.githubusercontent.com/53504602/83351095-c7a5b300-a35e-11ea-91cc-1db38e872124.png)

![topsort optimization](https://user-images.githubusercontent.com/53504602/83351245-e0fb2f00-a35f-11ea-9257-5c3a092d4883.png)
![topsort optimization 2](https://user-images.githubusercontent.com/53504602/83351306-6bdc2980-a360-11ea-8d73-dcb3e1b6f787.png)

---

My previous written topological sort code (easy-implementation):
```java
package Graph_Algorithms.Depth_First_Search.TopologicalOrdering;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class topologicalSort {
    public static void main(String[] args) {
        TopologicalOrdering order = new TopologicalOrdering();

        List<Vertex> graph = new ArrayList<>();

        graph.add(new Vertex("Fourier Series"));            //  0
        graph.add(new Vertex("Calculus"));                  //  1
        graph.add(new Vertex("Differential Equations"));    //  2
        graph.add(new Vertex("Linear Algebra"));            //  3
        graph.add(new Vertex("Stochastic Processes"));      //  4

        graph.get(1).connect(graph.get(0));
        graph.get(3).connect(graph.get(1));
        graph.get(2).connect(graph.get(1));
        graph.get(4).connect(graph.get(2));
        graph.get(4).connect(graph.get(1));

        for (int i = graph.size() - 1; i >= 0; i--) {
            if (!graph.get(i).isVisited()) {
                order.sort(graph.get(i));
            }
        }

        Stack<Vertex> stack = order.getStack();

        for (int i = 0; i < graph.size(); i++) {
            Vertex v = stack.pop();
            System.out.print(v.toString()+"-->");
        }
    }
}

class Vertex {
    private String data;
    private boolean visited;
    private List<Vertex> neighbours;

    public Vertex(String data) {
        this.data = data;
        this.neighbours = new ArrayList<>();
    }

    public void connect(Vertex e) {
        this.neighbours.add(e);
    }

    public String getData() {
        return data;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Vertex> getNeighbours() {
        return neighbours;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setNeighbours(List<Vertex> neighbours) {
        this.neighbours = neighbours;
    }
    @Override
    public String toString() {
        return this.data;
    }
    
}

class TopologicalOrdering {
    private Stack<Vertex> stack;

    public TopologicalOrdering() {
        this.stack = new Stack<>();
    }

    public void sort(Vertex v) {
        
        v.setVisited(true);

        for(Vertex u : v.getNeighbours()) {
            if(!u.isVisited()) {
                sort(u);
            }
        }

        stack.push(v);
    }

    public Stack<Vertex> getStack() {
        return stack;
    }
}
```
---

Below written is the code written in [GeeksforGeeks](https://www.geeksforgeeks.org/topological-sorting/):

```java
// A Java program to print topological sorting of a DAG  
import java.io.*;  
import java.util.*;  
    
// This class represents a directed graph using adjacency  
// list representation  
class Graph  
{  
    private int V;   // No. of vertices  
  
    // Adjacency List as ArrayList of ArrayList's 
    private ArrayList<ArrayList<Integer>> adj;  
    
    //Constructor  
    Graph(int v)  
    {  
        V = v;  
        adj = new ArrayList<ArrayList<Integer>>(v);  
        for (int i=0; i<v; ++i)  
            adj.add(new ArrayList<Integer>());  
    }  
    
    // Function to add an edge into the graph  
    void addEdge(int v,int w) { adj.get(v).add(w); }  
    
    // A recursive function used by topologicalSort  
    void topologicalSortUtil(int v, boolean visited[],  
                             Stack<Integer> stack)  
    {  
        // Mark the current node as visited.  
        visited[v] = true;  
        Integer i;  
    
        // Recur for all the vertices adjacent to this  
        // vertex  
        Iterator<Integer> it = adj.get(v).iterator();  
        while (it.hasNext())  
        {  
            i = it.next();  
            if (!visited[i])  
                topologicalSortUtil(i, visited, stack);  
        }  
    
        // Push current vertex to stack which stores result  
        stack.push(new Integer(v));  
    }  
    
    // The function to do Topological Sort. It uses  
    // recursive topologicalSortUtil()  
    void topologicalSort()  
    {  
        Stack<Integer> stack = new Stack<Integer>();  
    
        // Mark all the vertices as not visited  
        boolean visited[] = new boolean[V];  
        for (int i = 0; i < V; i++)  
            visited[i] = false;  
    
        // Call the recursive helper function to store  
        // Topological Sort starting from all vertices  
        // one by one  
        for (int i = 0; i < V; i++)  
            if (visited[i] == false)  
                topologicalSortUtil(i, visited, stack);  
    
        // Print contents of stack  
        while (stack.empty()==false)  
            System.out.print(stack.pop() + " ");  
    }  
    
    // Driver method  
    public static void main(String args[])  
    {  
        // Create a graph given in the above diagram  
        Graph g = new Graph(6);  
        g.addEdge(5, 2);  
        g.addEdge(5, 0);  
        g.addEdge(4, 0);  
        g.addEdge(4, 1);  
        g.addEdge(2, 3);  
        g.addEdge(3, 1);  
    
        System.out.println("Following is a Topological " +  
                           "sort of the given graph");  
        g.topologicalSort();  
    }  
}
```

