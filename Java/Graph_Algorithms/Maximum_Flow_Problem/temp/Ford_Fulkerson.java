package Graph_Algorithms.Maximum_Flow_Problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Ford_Fulkerson {
    private boolean[] marked;   // if marked[v] = true -> there is a path from source to v in the residual graph
    private Edge[] edgeTo;      // edgeTo[v] = edges in the augmenting path
    private double valueMaxFlow;

    public Ford_Fulkerson(FlowNetwork flowNet, Vertex source, Vertex sink) {
        while (hasAugmentingPath(flowNet, source, sink)) {
            double bottleneck = Double.POSITIVE_INFINITY;

            for (Vertex v = sink; v != source; v = edgeTo[v.getId()].getOtherVertex(v)) {
                bottleneck = Math.min(bottleneck, edgeTo[v.getId()].getResidualNetCapacity(v));
            }

            for (Vertex v = sink; v != source; v = edgeTo[v.getId()].getOtherVertex(v)) {
                edgeTo[v.getId()].addResidualFlowTo(v, bottleneck);
            }

            valueMaxFlow += bottleneck;
        }
    }

    private boolean hasAugmentingPath(FlowNetwork flowNet, Vertex source, Vertex sink) {
        edgeTo = new Edge[flowNet.getNum_Vertices()];
        marked = new boolean[flowNet.getNum_Vertices()];

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(source);
        marked[source.getId()] = true;

        while (!queue.isEmpty() && marked[sink.getId()]) {
            Vertex u = queue.remove();

            for (Edge e:
                 flowNet.getAdjacencyList(u)) {
                Vertex v = e.getOtherVertex(u);

                if (e.getResidualNetCapacity(v) > 0) {
                    if (!marked[v.getId()]) {
                        edgeTo[v.getId()] = e;
                        marked[v.getId()] = true;
                        queue.add(v);
                    }
                }
                
            }
        }

        return marked[sink.getId()];
    }

    public double getMaxFlow() {
        return this.valueMaxFlow;
    }

    public boolean isMinCut(int index) {
        return marked[index];
    }
}

class Ford_fulkerson_Algo {
    public static void main(String[] args) {
        FlowNetwork flowNet = new FlowNetwork(7);

        Vertex v1 = new Vertex(0, "s");
        Vertex v2 = new Vertex(1, "A");
        Vertex v3 = new Vertex(2, "B");
        Vertex v4 = new Vertex(3, "t");

        List<Vertex> vertexList = new ArrayList<>();
        vertexList.add(v1);
        vertexList.add(v2);
        vertexList.add(v3);
        vertexList.add(v4);

        flowNet.addEdge(new Edge(v1, v2, 4));
        flowNet.addEdge(new Edge(v1, v3, 5));
        flowNet.addEdge(new Edge(v1, v3, 7));
        flowNet.addEdge(new Edge(v2, v1, 4));
        flowNet.addEdge(new Edge(v2, v3, 1));

        Ford_Fulkerson fordFulk = new Ford_Fulkerson(flowNet, v1, v4);

        System.out.println("Maximum Flow is: " + fordFulk.getMaxFlow());
    }
}


class Vertex {
    private int id;
    private String data;
    private boolean isVisited;

    public Vertex(int id, String data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", isVisited=" + isVisited +
                '}';
    }
}

class Edge {
    private Vertex fromVertex;
    private Vertex targetVertex;
    private final double capacity;
    private double flow;

    public Edge(Vertex fromVertex, Vertex targetVertex, double capacity) {
        this.fromVertex = fromVertex;
        this.targetVertex = targetVertex;
        this.capacity = capacity;
        this.flow = 0.0;
    }

    public Edge(Edge edge) {
        this.fromVertex = edge.getFromVertex();
        this.targetVertex = edge.getTargetVertex();
        this.capacity = edge.getCapacity();
        this.flow = edge.getFlow();
    }

    public Vertex getFromVertex() {
        return fromVertex;
    }

    public void setFromVertex(Vertex fromVertex) {
        this.fromVertex = fromVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }

    public Vertex getOtherVertex(Vertex vertex) {
        if (vertex == fromVertex) {
            return targetVertex;
        } else {
            return fromVertex;
        }
    }

    public double getResidualNetCapacity(Vertex vertex) {
        if (vertex == fromVertex)
            return flow;                // backward edge
        else
            return capacity - flow;     // forward edge
    }

    public void addResidualFlowTo(Vertex vertex, double deltaFlow) {
        if (vertex == fromVertex) {
            flow -= deltaFlow;          // backward edge
        } else {
            flow += deltaFlow;          // forward edge
        }
    }

    @Override
    public String toString() {
        return "Edge{" +
                "fromVertex=" + fromVertex +
                ", targetVertex=" + targetVertex +
                ", capacity=" + capacity +
                ", flow=" + flow +
                '}';
    }
}


class FlowNetwork {
    private int num_Vertices;
    private int num_Edges;
    private List<List<Edge>> adjacencyList;

    public FlowNetwork(int num_Vertices) {
        this.num_Vertices = num_Vertices;
        this.num_Edges = 0;
        this.adjacencyList = new ArrayList<>();

        for (int i = 0; i < num_Vertices; ++i) {
            List<Edge> edgeList = new ArrayList<>();
            adjacencyList.add(edgeList);
        }
    }

    public int getNum_Vertices() {
        return this.num_Vertices;
    }

    public int getNum_Edges() {
        return this.num_Edges;
    }

    public void addEdge(Edge e) {
        Vertex u = e.getFromVertex();
        Vertex v = e.getTargetVertex();
        adjacencyList.get(u.getId()).add(e);
        adjacencyList.get(v.getId()).add(e);
        num_Edges++;
    }

    public List<Edge> getAdjacencyList(Vertex v) {
        return adjacencyList.get(v.getId());
    }
}