package Graph_Algorithms.Breadth_First_Search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
        Write a java program to implement breadth first search.
        This is a simple implementation.
*/
/*
Author: Sayantan Paul
Regd. No: 1841012233
CSE-E 4th Sem 2020
*/
public class bf_Search {
    public static void main(String[] args) {
        BFS bfs = new BFS();

        //initializing the Graph vertices
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);

        //drawing directed edges in between vertices.
        v1.addNeighborVertex(v2);

        v1.addNeighborVertex(v4);
        v2.addNeighborVertex(v3);

        v4.addNeighborVertex(v5);

        /*the graph looks like this:                 (1)
                                                   /   \
                                                 (2)  (4)
                                                 /      \
                                                (3)     (5)
         */

        bfs.bfs(v1);


    }
}

class Vertex {
    private int data;
    private boolean visited;
    private List<Vertex> neighborList;

    public Vertex(int data) {
        this.data = data;
        this.neighborList = new ArrayList<>();
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Vertex> getNeighborList() {
        return neighborList;
    }

    public void addNeighborVertex(Vertex vertex) {
        this.neighborList.add(vertex);
    }


    @Override
    public String toString() {
        return "vertex{" +
                "data=" + data +
                '}';
    }
}

class BFS {
    public void bfs(Vertex root) {
        Queue<Vertex> queue = new LinkedList<>();
        root.setVisited(true);
        queue.add(root);

        while (!queue.isEmpty()) {

            Vertex actualVertex = queue.remove();
            System.out.println(actualVertex);

            for (Vertex v : actualVertex.getNeighborList()) {
                if (!v.isVisited()) {
                    v.setVisited(true);
                    queue.add(v);
                }
            }
        }
    }
}