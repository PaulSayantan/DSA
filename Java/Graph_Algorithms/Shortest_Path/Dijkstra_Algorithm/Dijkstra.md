# Dijkstra Algorithm

One algorithm for finding the shortest path from a starting node to a target node in a weighted graph is Dijkstra’s algorithm. The algorithm creates a tree of shortest paths from the starting vertex, the source, to all other points in the graph.

Dijkstra’s algorithm, published in 1959 and named after its creator Dutch computer scientist Edsger Dijkstra, can be applied on a weighted graph. The graph can either be directed or undirected. One stipulation to using the algorithm is that the graph needs to have a nonnegative weight on every edge.

Dijkstra’s algorithm finds a shortest path tree from a single source node, by building a set of nodes that have minimum distance from the source.

The graph has the following:

- vertices, or nodes, denoted in the algorithm by `v` or `u`;
- weighted edges that connect two nodes: `(u,v)` denotes an edge, and `w(u,v)` denotes its weight.

This is done by initializing three values:

- `dist`, an array of distances from the source node `s` to each node in the graph, initialized the following way: `dist(s) = 0`; and for all other nodes `v`, `dist(v) = ∞`. This is done at the beginning because as the algorithm proceeds, the `dist` from the source to each node `v` in the graph will be recalculated and finalized when the shortest distance to `v` is found
- `Q`, a queue of all nodes in the graph. At the end of the algorithm's progress, `Q` will be empty.
- `S`, an empty set, to indicate which nodes the algorithm has visited. At the end of the algorithm's run, `S` will contain all the nodes of the graph.

The algorithm proceeds as follows:

1. While `Q` is not empty, pop the node `v`, that is not already in `S`, from `Q` with the smallest `dist(v)`. In the first run, source node `s` will be chosen because `dist(s)` was initialized to 0. In the next run, the next node with the smallest `dist` value is chosen.
2. Add node `v` to `S`, to indicate that `v` has been visited
3. Update `dist` values of adjacent nodes of the current node `v` as follows: for each new adjacent node `u`,
   - if `dist` (`v`) + weight(u,v)weight(u,v) < `dist` (`u`), there is a new minimal distance found for `u`, so update `dist(u)` to the new minimal distance value;
   - otherwise, no updates are made to `dist(u)`.

The algorithm has visited all nodes in the graph and found the smallest distance to each node. `dist` now contains the shortest path tree from source `s`.

__Note__: The weight of an edge `(u,v)` is taken from the value associated with `(u,v)` on the graph.


## Pseudocode

Dijkstra's algorithm keeps two sets of vertices:
- `S`: the set of vertices whose shortest paths from the source have already been determined and
- `V-S`: the remaining vertices.

The other data structures needed are:
- `d`: array of best estimates of shortest path to each vertex
- `pi`:	an array of predecessors for each vertex

The basic mode of operation is:

1. Initialise `d` and `pi`,
2. Set `S` to empty,
3. While there are still vertices in `V-S`,
    - Sort the vertices in `V-S` according to the current best estimate of their distance from the source,
    - Add u, the closest vertex in `V-S`, to `S`,
    - __Relax__ all the vertices still in `V-S` connected to `u`

### Relaxation

The relaxation process updates the costs of all the vertices, `v`, connected to a vertex, `u`, if we could improve the best estimate of the shortest path to `v` by including `(u,v)` in the path to `v`.

The __Initialization procedure__ proceeds as follows:

```
initialise_single_source( Graph g, Node s )
   for each vertex v in Vertices( g )
       g.d[v] := ∞
       g.pi[v] := nil
   g.d[s] := 0;
```

This sets up the graph so that each node has no predecessor (`pi[v] = nil`) and the estimates of the cost (distance) of each node from the source (`d[v]`) are infinite, except for the source node itself (`d[s] = 0`).

__Note__: that we have also introduced a further way to store a graph (or part of a graph - as this structure can only store a spanning tree), the __predecessor sub-graph__ - the list of predecessors of each node,
<img style="display: block; margin-left: auto; margin-right: auto; width: 25%;" src="https://latex.codecogs.com/svg.latex?pi[j],&space;1&space;\leq&space;j&space;\leq&space;\left&space;|&space;V&space;\right&space;|" title="pi[j], 1 \leq j \leq \left | V \right |" />
The edges in the __predecessor sub-graph__ are `(pi[v],v)`.

The __Relaxation procedure__ checks whether the current best estimate of the shortest distance to `v` `(d[v])` can be improved by going through `u` (i.e. by making `u` the predecessor of `v`):

```
relax( Node u, Node v, double w[][] )
    if d[v] > d[u] + w[u,v] then
       d[v] := d[u] + w[u,v]
       pi[v] := u
```

Now, the Dijkstra algorithm is as follows:

```
DIJKSTRA( Graph g, Node s )
    initialise_single_source( g, s )
    S := { 0 }                               /* Make S empty */
    Q := Vertices( g )                       /* Put the vertices in a PQ */
    while not Empty(Q) 
        u := ExtractCheapest( Q );
        AddNode( S, u );                     /* Add u to S */
        for each vertex v in Adjacent( u )
            relax( u, v, w )
```


---

Below written is another pseudocode for Dijkstra's algorithm, wriiten in form of python syntax.

```py
function Dijkstra(Graph, source):
       dist[source]  := 0                                      // Distance from source to source is set to 0
       for each vertex v in Graph:                             // Initializations
           if v ≠ source
               dist[v]  := infinity                            // Unknown distance function from source to each node set to infinity
           add v to Q                                          // All nodes initially in Q

      while Q is not empty:                                    // The main loop
          v := vertex in Q with min dist[v]                    // In the first run-through, this vertex is the source node
          remove v from Q 

          for each neighbor u of v:                            // where neighbor u has not yet been removed from Q.
              dist[u] = min(dist[u], dist[v] + length(v, u)    // if shorter path to u is found, update distance to u, else pass                 

      return dist[]
  end function
```

---