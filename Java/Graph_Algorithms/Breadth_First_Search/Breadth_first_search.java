package Graph_Algorithms.Breadth_First_Search;

/*
        Write a java program to implement a Breadth-first-search in a Generic directed graph.

*/
/*
Author: Sayantan Paul
Regd. No: 1841012233
CSE-E 4th Sem 2020
*/


import java.util.*;

public class Breadth_first_search {

    public static void main(String[] args) {
        bfs_Graph<Integer> g = new bfs_Graph<>();

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal " +
                "(starting from vertex 2)");

        g.bfs(2);

        System.out.println();

        bfs_Graph<String> g2 = new bfs_Graph<>();

        g2.addEdge("Adam", "Eva");
        g2.addEdge("Boris", "Carl");
        g2.addEdge("Adam", "Carl");
        g2.addEdge("Carl", "Eva");
        g2.addEdge("Boris", "Adam");
        g2.addEdge("Carl", "Martha");
        g2.addEdge("Martha", "Eva");

        System.out.println("Following is Breadth First Traversal " +
                "(starting from Boris)");

        g2.bfs("Boris");

    }
}

class bfs_Graph<T extends Comparable<T>> {

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

    public void bfs(T root) {

        List<T> vertices = new LinkedList<>();
        for (Map.Entry<T, List<T>> entry : graph.entrySet())
            vertices.add(entry.getKey());

        Map<T, Boolean> visited = new HashMap<>();
        for (T v : vertices) {
            visited.put(v, false);
        }
        Queue<T> queue = new LinkedList<>();

        visited.replace(root, true);

        queue.add(root);

        while (queue.size() != 0) {
            root = queue.poll();
            System.out.println(root.toString() + " ");

            for (Map.Entry<T, List<T>> entry : graph.entrySet()) {

                T r = entry.getKey();

                if (r.equals(root)) {

                    List<T> neighbors = entry.getValue();


                    for (T n : neighbors) {
                        if (!visited.get(n)) {
                            visited.replace(n, true);
                            queue.add(n);
                        }
                    }
                }
            }


        }


    }

}
