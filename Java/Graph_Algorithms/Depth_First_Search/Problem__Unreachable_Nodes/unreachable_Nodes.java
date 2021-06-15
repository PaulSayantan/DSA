package Graph_Algorithms.Depth_First_Search.Problem__Unreachable_Nodes;
/*
    UNREACHABLE NODES

    You have been given a graph consisting of N nodes and M edges. 
    The nodes in this graph are enumerated from 1 to N . 
    The graph can consist of self-loops as well as multiple edges. 
    This graph consists of a special node called the head node. 
    You need to consider this and the entry point of this graph. 
    You need to find and print the number of nodes that are unreachable from this head node.

    ----Input Format----

    The first line consists of a 2 integers N and M denoting the number of nodes and edges in this graph. 
    The next M lines consist of 2 integers a and b denoting an undirected edge between node a and b. 
    The next line consists of a single integer x denoting the index of the head node.

    ----Output Format----

    You need to print a single integer denoting the number of nodes that are unreachable from the given head node.


*/
/*
Author: Sayantan Paul
Regd. No: 1841012233
CSE-E 4th Sem 2020
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.HashMap;

public class unreachable_Nodes {

    static class Edge {
        int from, to;
    
        public Edge(int from, int to) {
          this.from = from;
          this.to = to;
        }
    }

    // Helper method to setup graph
    private static void addEdge(Map<Integer, List<Edge>> graph, int from, int to) {
        
        List<Edge> list = graph.get(from);
        if (list == null) {
        list = new LinkedList<Edge>();
        graph.put(from, list);
        }
        list.add(new Edge(from, to));
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
    
    public static void main(String[] args) {

        Map<Integer, List<Edge>> graph = new HashMap<>();
        int nodes, edges, x, y;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of nodes");
        nodes = sc.nextInt();
        System.out.println("Enter no. of edges");
        edges = sc.nextInt();

        for (int i = 0; i < edges; i++) {
            System.out.print("X: ");
            x = sc.nextInt();
            System.out.print("Y: ");
            y = sc.nextInt();
            addEdge(graph, x, y);
            System.out.println("-------");
        }
        System.out.print("Enter a rootNode: ");
        int rootNode = sc.nextInt();
        long nodeCount = dfs(rootNode, new boolean[nodes], graph);

        System.out.println(nodes - nodeCount);

    }

}

