package Graph_Algorithms.Depth_First_Search.Problem__Remove_Vertex;

/*
    Objective:  Given a graph represented by an adjacency list and a vertex, 
                write a program to remove the given vertex and all edges 
                connected to that vertex.

    Approach:   -   Delete the vertex from the indexes map since there will be no traversal from the deleted vertex.
                
                -   Remove the vertex from the first map (vertices are stored as a key in the map). 
                    This will delete vertex and all outgoing edges from the deleted vertex.
                
                -   To delete incoming edges towards deleted vertex from the other vertices, 
                    traverse all the linked list for other vertices and delete the vertex if there is any. 
                    This will delete the edge from other vertices to the deleted vertex.

*/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class RemoveVertexFromGraph {

    static class Graph {
        HashMap<Object, LinkedList<Object>> adjList = new HashMap();
        HashMap<Object, Integer> indexes = new HashMap<>();
        int index = -1;

        public Graph(ArrayList<Object> vertices) {
            for (int i = 0; i < vertices.size(); i++) {
                Object vertex = vertices.get(i);
                LinkedList<Object> list = new LinkedList<>();
                adjList.put(vertex, list);
                indexes.put(vertex, ++index);
            }
        }

        public void addEdge(Object source, Object destination) {
            //add forward edge
            LinkedList<Object> list;
            list = adjList.get(source);
            list.addFirst(destination);
            adjList.put(source, list);
        }

        public void removeVertex(Object vertex){
            adjList.remove(vertex);
            indexes.remove(vertex);

            Set<Object> set = adjList.keySet();
            Iterator<Object> iterator = set.iterator();

            while(iterator.hasNext()){
                Object v = iterator.next();
                LinkedList<Object> list = adjList.get(v);
                for (int i = 0; i <list.size() ; i++) {
                    if(list.contains(vertex))
                        list.remove(vertex);
                }
            }

            System.out.println("Removed Vertex " + vertex + " is removed from the graph (including all associated edges)");
        }

        public void printGraph() {
            Set<Object> set = adjList.keySet();
            Iterator<Object> iterator = set.iterator();

            while(iterator.hasNext()){
                Object vertex = iterator.next();
                System.out.print("Vertex " + vertex + " is connected to: ");
                LinkedList<Object> list = adjList.get(vertex);
                for (int i = 0; i <list.size() ; i++) {
                    System.out.print(list.get(i) + " ");
                }
                System.out.println();
            }
        }
    }
    public static void main(String[] args) {
        ArrayList<Object> vertices = new ArrayList<>();
        vertices.add(0);
        vertices.add(1);
        vertices.add(2);
        vertices.add(3);
        vertices.add(4);
        Graph graph = new Graph(vertices);
        graph.addEdge(0, 1);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.printGraph();
        graph.removeVertex(3);
        graph.printGraph();
    }
}