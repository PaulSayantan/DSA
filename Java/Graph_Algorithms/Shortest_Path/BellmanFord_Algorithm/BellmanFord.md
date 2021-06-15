# Bellman Ford's Algorithm

The Bellman-Ford algorithm is a graph search algorithm that finds the shortest path between a given source vertex and all other vertices in the graph. This algorithm can be used on both weighted and unweighted graphs.

Like Dijkstra's shortest path algorithm, the Bellman-Ford algorithm is guaranteed to find the shortest path in a graph. Though it is slower than Dijkstra's algorithm, Bellman-Ford is capable of handling graphs that contain negative edge weights, so it is more versatile. It is worth noting that if there exists a negative cycle in the graph, then there is no shortest path. Going around the negative cycle an infinite number of times would continue to decrease the cost of the path (even though the path length is increasing). Because of this, Bellman-Ford can also detect negative cycles which is a useful feature.

The Bellman-Ford algorithm, like Dijkstra's algorithm, uses the principle of relaxation to find increasingly accurate path length. Bellman-Ford, though, tackles two main issues with this process:

1. If there are negative weight cycles, the search for a shortest path will go on forever.
2. Choosing a bad ordering for relaxations leads to exponential relaxations.

The detection of negative cycles is important, but the main contribution of this algorithm is in its ordering of relaxations. Dijkstra's algorithm is a greedy algorithm that selects the nearest vertex that has not been processed. Bellman-Ford, on the other hand, relaxes all of the edges.

## Pseudocode

The Initialization and Relaxation procedure in Bellman Ford's algorithm is same as in Dijkstra's algorithm.

```
initialise_single_source( Graph g, Node s )
   for each vertex v in Vertices( g )
       g.d[v] := ∞
       g.pi[v] := nil
   g.d[s] := 0;
```

```
relax( Node u, Node v, double w[][] )
    if d[v] > d[u] + w[u,v] then
       d[v] := d[u] + w[u,v]
       pi[v] := u
```

Now, the Bellman Ford's algorithm is as follows:

```
BELLMAN-FORD( Graph g, Node s )
    initialise_single_source( g, s )
    for i = 1 to Vertices( g )
        for each ( u, v ) in Edges( g )
            relax( u,v,w )
    for each ( u, v ) in Edges( g )
        if d[v] > d[u] + w[u,v]
            return FALSE           // negative cycle detected
    return TRUE
```

---