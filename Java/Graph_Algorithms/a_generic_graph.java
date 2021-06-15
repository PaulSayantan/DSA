package Graph_Algorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
Write a java program to implement a generic graph Implementation.
*/
/*
Author: Sayantan Paul
Regd. No: 1841012233
CSE-E 4th Sem 2020
*/
public class a_generic_graph {
    public static void main(String[] args) {
        Graph<Integer> integerGraph = new Graph<>();
        /*
        Try to visualize the graph by drawing it
            then try to addEdge(vertex u, vertex v) where u < v
            if u add u > v, your graph traversal will run into error.
         */
        integerGraph.addEdge(0, 1);
        integerGraph.addEdge(0, 2);
        integerGraph.addEdge(1, 3);
        integerGraph.addEdge(2, 3);
        integerGraph.addEdge(1, 3);
        integerGraph.addEdge(3, 4);
        integerGraph.addEdge(0, 4);

        System.out.println("Graph: \n" + integerGraph.toString());

        integerGraph.vertexCount();

        System.out.println("Is there an edge between vertex 2 & 3 ? " + integerGraph.isConnected(2, 3));
        System.out.println("Is there an edge between vertex 4 & 1 ? " + integerGraph.isConnected(4, 1));

        System.out.println("Is vertex 6 present in graph ? " + integerGraph.isVertex(6));
    }
}

class Graph<T> {

    //  HashMap to store the edges in the graph.
    private Map<T, List<T>> graph = new HashMap<>();

    //  Add a new vertex to the graph
    public void addVertex(T s) {
        graph.put(s, new LinkedList<>());
    }

    /**
     * @param root is referred to as the root node (source vertex) of the graph
     * @param des  is referred to as the destination of the vertex we want to join.
     *             The joining starts from the root node and continues till the destination node.
     */
    public void addEdge(T root, T des) {

        if (!graph.containsKey(root))
            addVertex(root);

        if (!graph.containsKey(des))
            addVertex(des);

        graph.get(root).add(des);
    }

    //  Counts the number of vertices in a Graph
    public void vertexCount() {
        System.out.println("Total number of vertices in this Graph is: " + graph.keySet().size());
    }

    //  Checks if a particular vertex is present in the Graph or not
    public boolean isVertex(T v) {
        return graph.containsKey(v);
    }

    //  Checks if two vertices are connected by an edge or not
    public boolean isConnected(T u, T v) {
        return graph.get(u).contains(v) || graph.get(v).contains(u);
    }

    //  Display the Graph
    @Override
    public String toString() {
        StringBuilder graph_disp = new StringBuilder();

        for (T v : graph.keySet()) {
            for (T w : graph.get(v))
                graph_disp.append("( ").append(v.toString()).append(" --> ").append(w.toString()).append(" )").append("\t");
            graph_disp.append("\n");
        }
        return graph_disp.toString();
    }

}