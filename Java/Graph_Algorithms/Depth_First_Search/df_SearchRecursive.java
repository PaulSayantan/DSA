package Graph_Algorithms.Depth_First_Search;
/*
Write a java program to implement a simple Depth First Search Traversal
*/
/*
Author: Sayantan Paul
Regd. No: 1841012233
CSE-E 4th Sem 2020
*/
import java.util.*;

public class df_SearchRecursive {

  static class Edge {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  // Perform a depth first search on the graph counting
  // the number of nodes traversed starting at some position
  static long dfs(int at, boolean[] visited, Map<Integer, List<Edge>> graph) {

    // We have already visited this node
    if (visited[at]) return 0L;

    // Visit this node
    visited[at] = true;
    long count = 1;

    // Visit all edges adjacent to where we're at
    List<Edge> edges = graph.get(at);
    if (edges != null) {
      for (Edge edge : edges) {
        count += dfs(edge.to, visited, graph);
      }
    }

    return count;
  }

  // Example usage of DFS
  public static void main(String[] args) {

    // Create a fully connected graph
    //           (0)
    //           / \
    //        5 /   \ 4
    //         /     \
    // 10     <   -2  >
    //   +->(2)<------(1)      (4)
    //   +--- \       /
    //         \     /
    //        1 \   / 6
    //           > <
    //           (3)
    int numNodes = 5;
    Map<Integer, List<Edge>> graph = new HashMap<>();
    addDirectedEdge(graph, 0, 1, 4);
    addDirectedEdge(graph, 0, 2, 5);
    addDirectedEdge(graph, 1, 2, -2);
    addDirectedEdge(graph, 1, 3, 6);
    addDirectedEdge(graph, 2, 3, 1);
    addDirectedEdge(graph, 2, 2, 10); // Self loop

    long nodeCount = dfs(0, new boolean[numNodes], graph);
    System.out.println("DFS node count starting at node 0: " + nodeCount);
    if (nodeCount != 4) System.err.println("Error with DFS");

    nodeCount = dfs(4, new boolean[numNodes], graph);
    System.out.println("DFS node count starting at node 4: " + nodeCount);
    if (nodeCount != 1) System.err.println("Error with DFS");
  }

  // Helper method to setup graph
  private static void addDirectedEdge(Map<Integer, List<Edge>> graph, int from, int to, int cost) {
    List<Edge> list = graph.get(from);
    if (list == null) {
      list = new ArrayList<Edge>();
      graph.put(from, list);
    }
    list.add(new Edge(from, to, cost));
  }
}





/*
*
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class df_Search {
    public static void main(String[] args) {
        DFS dfs = new DFS();

        Vertex v1 = new Vertex("Adam");
        Vertex v2 = new Vertex("Eva");
        Vertex v3 = new Vertex("Luke");
        Vertex v4 = new Vertex("Jeffrey");
        Vertex v5 = new Vertex("Jonathan");


        v1.addNeighborVertex(v2);

        v1.addNeighborVertex(v4);
        v2.addNeighborVertex(v3);

        v4.addNeighborVertex(v5);

        List<Vertex> vertexList = new LinkedList<>();
        vertexList.add(v1);
        vertexList.add(v2);
        vertexList.add(v3);
        vertexList.add(v4);
        vertexList.add(v5);

        dfs.dfs(vertexList);
    }
}

class Vertex {

    private String data;
    private boolean visited;
    private List<Vertex> neighbourList;

    public Vertex(String data) {
        this.data = data;
        this.neighbourList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "data='" + data + '\'' +
                '}';
    }

    public List<Vertex> getNeighbourList() {
        return neighbourList;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }   

    public void addNeighborVertex(Vertex vertex) {
        this.neighbourList.add(vertex);
    }
}

class DFS {

    private Stack<Vertex> stack;

    public DFS() {
        this.stack = new Stack<>();
    }

    public void dfs(List<Vertex> vertexList) {
        for (Vertex v : vertexList) {

            if (!v.isVisited()) {
                v.setVisited(true);
                System.out.println("Depth First Search Traversal in Iterative Style");
                dfs_iterative(v);       //iterative implementation runs the traversal as right->root->left
                //System.out.println("Depth First Search Traversal in Recursive Style");
                //dfs_recursive(v);       //recursive implementation runs the traversal as left->root->right
            }
        }
    }

    private void dfs_recursive(Vertex root) {
        System.out.println(root.toString() + " ");

        for (Vertex v : root.getNeighbourList()) {
            if (!v.isVisited()) {
                v.setVisited(true);
                dfs_recursive(v);
            }
        }
    }

    private void dfs_iterative(Vertex root) {

        
        this.stack.add(root);
        root.setVisited(true);

        while (!stack.isEmpty()) {
            Vertex ver = this.stack.pop();
            System.out.println(ver.toString() + " ");

            for (Vertex v : ver.getNeighbourList()) {
                if (!v.isVisited()) {
                    v.setVisited(true);
                    this.stack.push(v);
                }
            }
        }


    }
}



*
*/