package Graph_Algorithms.Depth_First_Search.Cycle_Detection;


import java.util.LinkedList;

public class CycleDetection {
    
    static class Graph{
        int vertices;
        LinkedList<Integer> [] adjList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new LinkedList[vertices];
            for (int i = 0; i <vertices ; i++) {
                adjList[i] = new LinkedList<>();
            }
        }
        public void addEdge(int source, int destination){
            //add forward edge
            adjList[source].addFirst(destination);

            //add reverse edge
            adjList[destination].addFirst(source);
        }

        public boolean isCycle() {
            boolean result = false;

            //visited array
            boolean[] visited = new boolean[vertices];
            //do DFS, from each vertex
            for (int i = 0; i <vertices ; i++) {
                if(visited[i]==false){
                    if(isCycleUtil(i, visited, -1)){
                        return true;
                    }
                }
            }
            return result;
        }

        boolean isCycleUtil(int currVertex, boolean [] visited, int parent){

            //add this vertex to visited vertex
            visited[currVertex] = true;

            //visit neighbors except its direct parent
            for (int i = 0; i <adjList[currVertex].size() ; i++) {
                int vertex = adjList[currVertex].get(i);
                //check the adjacent vertex from current vertex
                if(vertex!=parent) {
                    //if destination vertex is not its direct parent then
                    if(visited[vertex]){
                        //if here means this destination vertex is already visited
                        //means cycle has been detected
                        return true;
                    }
                    else{
                        //recursion from destination node
                        if (isCycleUtil(vertex, visited, currVertex)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }
    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 2);
        boolean result = graph.isCycle();
        System.out.println("is Cycle present: " + result);
    }
}