package Graph_Algorithms.Depth_First_Search.TopologicalOrdering;

import java.util.*;

public class topologicalSort {

  // Helper Edge class to describe edges in the graph
  static class Edge {
    int from, to, weight;

    public Edge(int f, int t, int w) {
      from = f;
      to = t;
      weight = w;
    }
  }

  // Helper method that performs a depth first search on the graph to give
  // us the topological ordering we want. Instead of maintaining a stack
  // of the nodes we see we simply place them inside the ordering array
  // in reverse order for simplicity.
  private static int dfs(
      int i, int at, boolean[] visited, int[] ordering, Map<Integer, List<Edge>> graph) {

    visited[at] = true;

    List<Edge> edges = graph.get(at);

    if (edges != null)
      for (Edge edge : edges) if (!visited[edge.to]) i = dfs(i, edge.to, visited, ordering, graph);

    ordering[i] = at;
    return i - 1;
  }

  // Finds a topological ordering of the nodes in a Directed Acyclic Graph (DAG)
    // The input to this function is an adjacency list for a graph and the number
    // of nodes in the graph.
    //
    // NOTE: 'numNodes' is not necessarily the number of nodes currently present
    // in the adjacency list since you can have singleton nodes with no edges which
    // wouldn't be present in the adjacency list but are still part of the graph!
    //
    public static int[] TopologicalSort(Map<Integer, List<Edge>> graph, int numNodes) {

      int[] ordering = new int[numNodes];
      boolean[] visited = new boolean[numNodes];

      int i = numNodes - 1;
      for (int at = 0; at < numNodes; at++)
      if (!visited[at]) i = dfs(i, at, visited, ordering, graph);

      return ordering;
  }

  // Example usage of topological sort
  public static void main(String[] args) {

    // Graph setup
    final int N = 7;
    Map<Integer, List<Edge>> graph = new HashMap<>();
    for (int i = 0; i < N; i++) graph.put(i, new ArrayList<>());
    graph.get(0).add(new Edge(0, 1, 3));
    graph.get(0).add(new Edge(0, 2, 2));
    graph.get(0).add(new Edge(0, 5, 3));
    graph.get(1).add(new Edge(1, 3, 1));
    graph.get(1).add(new Edge(1, 2, 6));
    graph.get(2).add(new Edge(2, 3, 1));
    graph.get(2).add(new Edge(2, 4, 10));
    graph.get(3).add(new Edge(3, 4, 5));
    graph.get(5).add(new Edge(5, 4, 7));

    int[] ordering = TopologicalSort(graph, N);

    // // Prints: [6, 0, 5, 1, 2, 3, 4]
    System.out.println(java.util.Arrays.toString(ordering));
  }
}