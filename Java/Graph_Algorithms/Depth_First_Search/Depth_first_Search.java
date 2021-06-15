package Graph_Algorithms.Depth_First_Search;


import java.util.*;

/*
Write a java program to implement Depth first implementation.
Graph is directed, but this implementation doesn't follows depth-first search based on direction
Try-it yourself.
*/
/*
Author: Sayantan Paul
Regd. No: 1841012233
CSE-E 4th Sem 2020
*/
public class Depth_first_Search {
    public static void main(String[] args) {
        dfs_Graph<Integer> g = new dfs_Graph<>();

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Depth First Traversal " +
                "(starting from vertex 2)");

        g.dfs(2);

        System.out.println();

    }
}

class dfs_Graph<T> {

    private Map<T, List<T>> graph = new HashMap<>();

    public void addVertex(T s) {
        graph.put(s, new LinkedList<>());
    }

    public void addEdge(T root, T des) {

        if (!graph.containsKey(root))
            addVertex(root);

        if (!graph.containsKey(des))
            addVertex(des);

        graph.get(root).add(des);
    }

    public void dfs(T root) {
        //  A linkedlist to store the vertices
        List<T> vertices = new LinkedList<>();

        // extracting all the key-value pairs form the graph and adding them to vertices-list
        for (Map.Entry<T, List<T>> entry : graph.entrySet())
            vertices.add(entry.getKey());
        
	//  a boolean linkedlist visted to check whether the vertex is visited.
        Map<T, Boolean> visited = new HashMap<>();
        
	//  initializing the visted linkedlist to be false, since none vertices are visited.
        for (T v : vertices) {
            visited.put(v, false);
        }
        
	//  A stack 
        Stack<T> stack = new Stack<>();

        //  marking the root vertex to be true
        visited.replace(root, true);
        
	//  adding the vertex in the stack
        stack.add(root);

        
        while (stack.size() != 0) {
            root = stack.pop();
            System.out.println(root.toString() + " ");

            for (Map.Entry<T, List<T>> entry : graph.entrySet()) {

                T r = entry.getKey();

                if (r.equals(root)) {

                    List<T> neighbors = entry.getValue();


                    for (T n : neighbors) {
                        if (!visited.get(n)) {
                            visited.replace(n, true);
                            stack.add(n);
                        }
                    }
                }
            }


        }
    }

}

