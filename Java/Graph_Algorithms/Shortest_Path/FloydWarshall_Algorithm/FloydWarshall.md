# Floyd Warshall's Algorithm

The Floyd-Warshall algorithm is a shortest path algorithm for graphs. Like the __Bellman-Ford algorithm__ or the __Dijkstra's algorithm__, it computes the shortest path in a graph. However, Bellman-Ford and Dijkstra are both single-source, shortest-path algorithms. This means they only compute the shortest path from a single source. Floyd-Warshall, on the other hand, computes the shortest distances between every pair of vertices in the input graph.

At the heart of Floyd-Warshall is this function:
<br>
<img style="display: block; margin-left: auto; margin-right: auto; width: 30%;" src="https://latex.codecogs.com/svg.latex?\inline&space;ShortestPath(i,j,k)" title="ShortestPath(i,j,k)" />
<br>

This function returns the shortest path from `A` to `C` using the vertices from 1 to `k` in the graph. The vertices are individually numbered 1,2,...,k.

There is a base case and a recursive case. The base case is that the shortest path is simply the weight of the edge connecting `A` and `C`:
<br>
<img style="display: block; margin-left: auto; margin-right: auto; width: 30%;" src="https://latex.codecogs.com/svg.latex?\inline&space;ShortestPath(i,j,0)&space;=&space;weight(i,&space;j)" title="ShortestPath(i,j,0) = weight(i, j)" />
<br>
The recursive case will take advantage of the dynamic programming nature of this problem. There are two possible answers for this function. Either the shortest path between `i` and `j` is the shortest known path, or it is the shortest known path from `i` to some vertex (let's call it `z`) plus the shortest known path from `z` to `j`:
<br>
<img style="display: block; margin-left: auto; margin-right: auto; width: 30%;" src="https://latex.codecogs.com/svg.latex?\small&space;\text{ShortestPath}(i,&space;j,&space;k)&space;=&space;\text{min}\big(\text{ShortestPath}(i,&space;j,&space;k-1),&space;\text{ShortestPath}(i,&space;k,&space;k-1)&space;&plus;&space;\text{ShortestPath}(k,&space;j,&space;k-1)\big)" title="\small \text{ShortestPath}(i, j, k) = \text{min}\big(\text{ShortestPath}(i, j, k-1), \text{ShortestPath}(i, k, k-1) + \text{ShortestPath}(k, j, k-1)\big)" />
<br>

Basically, what this function setup is asking this: "Is the vertex `k` an intermediate of our shortest path (any vertex in the path besides the first or the last)?"

If `k` is not an intermediate vertex, then the shortest path from `i` to `j` using the vertices in <img src="https://latex.codecogs.com/svg.latex?\small&space;\1,&space;2,&space;...,&space;k-1\" title="\small \1, 2, ..., k-1\" /> is also the shortest path using the vertices in <img src="https://latex.codecogs.com/svg.latex?\small&space;\1,&space;2,&space;...,&space;k\" title="\small \1, 2, ..., k\" />.

If `k` is an intermediate vertex, then the path can be broken down into two paths, each of which uses the vertices in <img src="https://latex.codecogs.com/svg.latex?\small&space;\1,&space;2,&space;...,&space;k-1\" title="\small \1, 2, ..., k-1\" /> to make a path that uses all vertices in <img src="https://latex.codecogs.com/svg.latex?\small&space;\1,&space;2,&space;...,&space;k\" title="\small \1, 2, ..., k\" />. That is because the vertex `k` is the middle point.

## Pseudocode

The Floyd-Warshall algorithm can be described by the following pseudo code:

```
Create a |V| x |V| matrix, M, that will describe the distances between vertices
for each cell (i, j) in M:
    if i == j:
        M[i][j] = 0
    if (i, j) is an edge in E:
        M[i][j] = weight(i, j)
    else:
        M[i][j] = ∞
for k from 1 to |V|:
    for i from 1 to |V|:
        for j from 1 to |V|:
            if M[i][j] > M[i][k] + M[k][j]:
                M[i][j] = M[i][k] + M[k][j]
```

## Implementation

The following implementation of Floyd-Warshall is written in Python.

```py
class Edge:
    def __init__(self, start, end, weight):
        self.start = start
        self.end = end
        self.weight = weight

class Graph:
    def __init__(self):
        self.adj = {} #Adjacency matrix that holds graph data
        self.vertexCount = 0

    def addVertex(self, vertex):
        if vertex in self.adj:
            return "Vertex already exists"
        if vertex != self.vertexCount:
            return "Don't skip a vertex"
        self.adj[vertex] = []
        self.vertexCount += 1

    def addEdge(self, start, end, weight):
        if start not in self.adj:
            return "Starting vertex not in graph"
        if end not in self.adj:
            return "Ending vertex not in graph"
        if start == end:
            return "Cannot have same start and end vertex"
        edge = Edge(start, end, weight)
        self.adj[start].append(edge)

    def doesEdgeExist(self, start, end):
        for vertex in self.adj:
            for edge in self.adj[vertex]:
                if edge.start == start and edge.end == end:
                    return (True, edge)
        return (False, None)

    def floydwarshall(self):
        M = [[9999999 for x in range(len(self.adj))] for y in range(len(self.adj))]
        for x in range(len(M)):
            for y in range(len(M[0])):
                if x == y:
                    M[x][y] = 0
                exists, edge = self.doesEdgeExist(x, y)
                if exists:
                    M[x][y] = edge.weight
        for k in range(len(M)):
            for i in range(len(M)):
                for j in range(len(M)):
                    newDistance = M[i][k] + M[k][j]
                    if newDistance < M[i][j]:
                        M[i][j] = newDistance
        return M
```

The `Edge` class on line 1 is a simple object that holds information about the edge such as endpoints and weight. The vertex is just a simple integer for this implementation. The `Graph` class uses a dictionary--initialized on line 9--to represent the graph. Keys in this dictionary are vertex numbers and the values are a list of edges.

The `floydwarshall()` function on line 33 creates a matrix `M`. It populates this matrix with shortest path information for each vertex. For example, the shortest path distance from vertex 0 to vertex 2 can be found at `M[0][2]`.

## Improvement - Path Reconstruction

In general, Floyd-Warshall, at its most basic, only provides the distances between vertices in the resulting matrix. However, a simple change can allow the algorithm to reconstruct the shortest path as well. There are many different ways to do this, and all of them have their costs in memory. Speed is not a factor with path reconstruction because any time it takes to reconstruct the path will pale in comparison to the basic algorithm itself.

The most common way is to compute a sequence of predecessor matrices. During path calculation, even the matrices
<img src="https://latex.codecogs.com/svg.latex?P^{(0)}&space;,P^{(1)}&space;,...,P^{(n)}" title="P^{(0)} ,P^{(1)} ,...,P^{(n)}" /> can be computed. 
​

<img src="https://latex.codecogs.com/svg.latex?P^{(k)}_{ij}" title="P^{(k)}_{ij}" /> is defined as the predecessor of vertex `j` on a shortest path from vertex ii with all intermediate vertices in the set 1, 2, ... , k. So, for each iteration of the main loop, a new predecessor matrix is created.