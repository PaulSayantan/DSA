package Graph_Algorithms.Shortest_Path.Single_Source_SP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class singleSrcSP {
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

        // Finds all the shortest paths starting at node 0
        Integer[] dists = TopologicalSort.dagShortestPath(graph, 0, N);

        // Find the shortest path from 0 to 4 which is 8.0
        System.out.println(dists[4]);

        // Find the shortest path from 0 to 6 which
        // is null since 6 is not reachable!
        System.out.println(dists[6]);
    }
}


class Edge {
    int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
}

class TopologicalSort {

    // Depth-First Search Algorithm
    private static int dfs(
      int i, int at, boolean[] visited, int[] ordering, 
            Map<Integer, List<Graph_Algorithms.Shortest_Path.Single_Source_SP.Edge>> graph) {

        visited[at] = true;

        List<Graph_Algorithms.Shortest_Path.Single_Source_SP.Edge> edges = graph.get(at);

        if (edges != null)
            for (Graph_Algorithms.Shortest_Path.Single_Source_SP.Edge edge : edges)
                if (!visited[edge.to])
                    i = dfs(i, edge.to, visited, ordering, graph);

    ordering[i] = at;
    return i - 1;
  }

    public static int[] topologicalSort(Map<Integer, List<Graph_Algorithms.Shortest_Path.Single_Source_SP.Edge>> graph,
            int numNodes) {

        int[] ordering = new int[numNodes];
        boolean[] visited = new boolean[numNodes];

        int i = numNodes - 1;
        for (int at = 0; at < numNodes; at++)
            if (!visited[at])
                i = dfs(i, at, visited, ordering, graph);

        return ordering;
    }


  // A useful application of the topological sort is to find the shortest path
  // between two nodes in a Directed Acyclic Graph (DAG). Given an adjacency list
  // this method finds the shortest path to all nodes starting at 'start'
  //
  // NOTE: 'numNodes' is not necessarily the number of nodes currently present
  // in the adjacency list since you can have singleton nodes with no edges which
  // wouldn't be present in the adjacency list but are still part of the graph!
  //
  public static Integer[] dagShortestPath(
          Map<Integer, List<Graph_Algorithms.Shortest_Path.Single_Source_SP.Edge>> graph, int start, int numNodes) {

      int[] topsort = topologicalSort(graph, numNodes);
      Integer[] dist = new Integer[numNodes];
      dist[start] = 0;

      for (int i = 0; i < numNodes; i++) {

          int nodeIndex = topsort[i];
          if (dist[nodeIndex] != null) {
              List<Graph_Algorithms.Shortest_Path.Single_Source_SP.Edge> adjacentEdges = graph.get(nodeIndex);
        if (adjacentEdges != null) {
          for (Graph_Algorithms.Shortest_Path.Single_Source_SP.Edge edge : adjacentEdges) {

            int newDist = dist[nodeIndex] + edge.weight;
            if (dist[edge.to] == null) dist[edge.to] = newDist;
            else dist[edge.to] = Math.min(dist[edge.to], newDist);
          }
        }
      }
    }

    return dist;
  }
}
