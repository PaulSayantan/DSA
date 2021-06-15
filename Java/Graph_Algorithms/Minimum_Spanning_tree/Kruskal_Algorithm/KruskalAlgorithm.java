package Graph_Algorithms.Minimum_Spanning_tree.Kruskal_Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
An implementation of Kruskal's MST algorithm using an edge list Time Complexity: O(ElogE)
*/

public class KruskalAlgorithm {

    public static void main(String[] args) {
        int numNodes = 10;
        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 5));
        edges.add(new Edge(1, 2, 4));
        edges.add(new Edge(2, 9, 2));
        edges.add(new Edge(0, 4, 1));
        edges.add(new Edge(0, 3, 4));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(2, 7, 4));
        edges.add(new Edge(2, 8, 1));
        edges.add(new Edge(9, 8, 0));
        edges.add(new Edge(4, 5, 1));
        edges.add(new Edge(5, 6, 7));
        edges.add(new Edge(6, 8, 4));
        edges.add(new Edge(4, 3, 2));
        edges.add(new Edge(5, 3, 5));
        edges.add(new Edge(3, 6, 11));
        edges.add(new Edge(6, 7, 1));
        edges.add(new Edge(3, 7, 2));
        edges.add(new Edge(7, 8, 6));

        KruskalAlgorithm Kruskal = new KruskalAlgorithm();
        int minCost = Kruskal.Kruskals(edges, numNodes);
        System.out.println(minCost);
    }

    // Union find data structure
    static class UnionFind {

        private int[] id, sz;

        public UnionFind(int n) {
            id = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                sz[i] = 1;
            }
        }

        public int find(int p) {
            int root = p;
            while (root != id[root])
                root = id[root];
            while (p != root) { // Do path compression
                int next = id[p];
                id[p] = root;
                p = next;
            }
            return root;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public int size(int p) {
            return sz[find(p)];
        }

        public void union(int p, int q) {
            int root1 = find(p);
            int root2 = find(q);
            if (root1 == root2)
                return;
            if (sz[root1] < sz[root2]) {
                sz[root2] += sz[root1];
                id[root1] = root2;
            } else {
                sz[root1] += sz[root2];
                id[root2] = root1;
            }
        }
    }

    int Kruskals(List<Edge> edges, int n) {
        if (edges == null) return 0;

        //  stores the sum of edges
        int sum = 0;
        Collections.sort(edges);
        UnionFind uf = new UnionFind(n);

        for (Edge e: edges) {
            // Skip this edge to avoid creating a cycle in MST
            if (uf.connected(e.from, e.to)) continue;

            //  include this edge
            uf.union(e.from, e.to);
            sum += e.cost;

            //stop optimization if MST includes all nodes
            if (uf.size(0) == n) break;
        }

        //  check if MST includes all the nodes
        if (uf.size(0) != n) return 0;

        return sum;
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