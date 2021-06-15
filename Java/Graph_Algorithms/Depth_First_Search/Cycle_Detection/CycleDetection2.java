package Graph_Algorithms.Depth_First_Search.Cycle_Detection;

import java.util.ArrayList;
import java.util.List;

public class CycleDetection2 {
    
    public void detectCycle(List<Vertex> vertices) {
        for (Vertex vertex : vertices) {
            if (!vertex.isVisited()) {
                df_Search(vertex);
            }
        }
    }

    private void df_Search(Vertex vertex) {
        vertex.setBeingVisited(true);

        for (Vertex adjVertex : vertex.getAjacencyList()) {
            if (adjVertex.isBeingVisited()) {
                System.out.println("There is a cycle in the graph.");
                return;
            }

            if (!adjVertex.isVisited()) {
                adjVertex.setVisited(true);
                df_Search(adjVertex);
            }
        }

        vertex.setBeingVisited(false);
        vertex.setVisited(true);
    }

    public static void main(String[] args) {
        
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");

        v1.addNeighbours(v2);
        v1.addNeighbours(v3);
        v2.addNeighbours(v3);
        v4.addNeighbours(v5);
        v4.addNeighbours(v1);
        v6.addNeighbours(v4);
        v5.addNeighbours(v6);

        List<Vertex> vertices = new ArrayList<>();
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        vertices.add(v6);

        CycleDetection2 detect = new CycleDetection2();
        detect.detectCycle(vertices);


    }
}

class Vertex {
    private String name;
    private boolean beingVisited;
    private boolean visited;
    private List<Vertex> adjacencyList;

    public Vertex(String name) {
        this.name = name;
        this.adjacencyList = new ArrayList<>();
    }

    public void addNeighbours(Vertex v) {
        this.adjacencyList.add(v);
    }

    public boolean isBeingVisited() {
        return beingVisited;
    }

    public void setBeingVisited(boolean beingVisited) {
        this.beingVisited = beingVisited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public List<Vertex> getAjacencyList() {
        return adjacencyList;
    }


}