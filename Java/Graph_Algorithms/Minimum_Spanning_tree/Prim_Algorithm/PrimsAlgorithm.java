package Graph_Algorithms.Minimum_Spanning_tree.Prim_Algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class PrimsAlgorithm {
    public final int n;
    public final List<List<Edge>> graph;
    private boolean solved;
    private boolean mstExists;
    private boolean[] visited;
    private PriorityQueue<Edge> pq;
    
    // Output
    public int minCostSum;
    public Edge[] mstEdges;


    public PrimsAlgorithm(List<List<Edge>> graph) {
        if (graph == null || graph.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.n = graph.size();
        this.graph = graph;
    }

    public void addEdges(int nodeIndex) {
        visited[nodeIndex] = true;
        List<Edge> edges = graph.get(nodeIndex);
        for (Edge e : edges) {
            if (!visited[e.to]) {
                pq.offer(e);
            }
        }
    }

    /*
    Compute the MST: Minimum Spanning Tree using the Prim's Algorithm
    and the MST cost
    */
    private void solve() {
        if (solved) return;
        solved = true;

        int m = n - 1, edgeCount = 0;
        pq = new PriorityQueue<>();
        visited = new boolean[n];
        mstEdges = new Edge[m];

        addEdges(0);

        // Loop while the MST is not complete
        while (!pq.isEmpty() && edgeCount != m) {
            Edge edge = pq.poll();
            int nodeIndex = edge.to;

            // Skip any edge pointing to an already visited node.
            if (visited[nodeIndex]) continue;

            mstEdges[edgeCount++] = edge;
            minCostSum += edge.cost;

            addEdges(nodeIndex);
        }

        // Check if MST spans in entire graph.
        mstExists = (edgeCount == m);
    }
    
    
    /*
    Returns the edges used for finding the MST
        returns null if no MST exists
    */
    public Edge[] getMST() {
        solve();
        return mstExists ? mstEdges : null;
    }

    public int getMSTCost() {
        solve();
        return mstExists ? minCostSum : null;
      }

    //  creates a temporary empty graph
    public static List<List<Edge>> createEmptyGraph(int n) {
        List<List<Edge>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        return g;
    }

    // to add a directed edge to the empty graph created
    public static void addDirectedEdge(List<List<Edge>> g, int from, int to, int cost) {
        g.get(from).add(new Edge(from, to, cost));
    }

    public static void main(String[] args) {
        int n = 8;
        List<List<Edge>> graph = createEmptyGraph(n);
        //  0
        addDirectedEdge(graph, 0, 1, 10);
        addDirectedEdge(graph, 0, 2, 1);
        addDirectedEdge(graph, 0, 3, 4);
        //  1
        addDirectedEdge(graph, 2, 1, 3);
        addDirectedEdge(graph, 2, 5, 8);
        addDirectedEdge(graph, 2, 3, 2);
        addDirectedEdge(graph, 2, 0, 1);
        //  2
        addDirectedEdge(graph, 3, 2, 2);
        addDirectedEdge(graph, 3, 5, 2);
        addDirectedEdge(graph, 3, 6, 7);
        addDirectedEdge(graph, 3, 0, 4);
        //  3
        addDirectedEdge(graph, 5, 2, 8);
        addDirectedEdge(graph, 5, 4, 1);
        addDirectedEdge(graph, 5, 7, 9);
        addDirectedEdge(graph, 5, 6, 6);
        addDirectedEdge(graph, 5, 3, 2);
        //  4
        addDirectedEdge(graph, 4, 1, 0);
        addDirectedEdge(graph, 4, 5, 1);
        addDirectedEdge(graph, 4, 7, 8);
        //  5
        addDirectedEdge(graph, 1, 0, 10);
        addDirectedEdge(graph, 1, 2, 3);
        addDirectedEdge(graph, 1, 4, 0);
        //  6
        addDirectedEdge(graph, 6, 3, 7);
        addDirectedEdge(graph, 6, 5, 6);
        addDirectedEdge(graph, 6, 7, 12);
        //  7
        addDirectedEdge(graph, 7, 4, 8);
        addDirectedEdge(graph, 7, 5, 9);
        addDirectedEdge(graph, 7, 6, 12);


        PrimsAlgorithm Prims = new PrimsAlgorithm(graph);
        int cost = Prims.getMSTCost();

        if (cost == 0) {
            System.out.println("No MST does not exists");
        } else {
            System.out.println("MST cost: " + cost);
            for (Edge e : Prims.getMST()) {
                System.out.println(String.format("from: %d, to: %d, cost: %d", e.from, e.to, e.cost));
            }
        }
    }
      
}

class Edge implements Comparable<Edge> {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }

    @Override
    public int compareTo(Edge other) {
      return cost - other.cost;
    }
}
