# Shortest Path Algorithm

Shortest path algorithms are a family of algorithms designed to solve the shortest path problem. The shortest path problem is something most people have some intuitive familiarity with: given two points, A and B, what is the shortest path between them? In computer science, however, the shortest path problem can take different forms and so different algorithms are needed to be able to solve them all.

## Types of Shortest Path Algorithms

There are two main types of shortest path algorithms, single-source and all-pairs. Both types have algorithms that perform best in their own way. All-pairs algorithms take longer to run because of the added complexity. All shortest path algorithms return values that can be used to find the shortest path, even if those return values vary in type or form from algorithm to algorithm.

### Single Source

> Given a graph `G`, with vertices `V`, edges `E` with weight function w(u,v)=w <sub>u,v</sub> , and a single source vertex, `s`, return the shortest paths from `s` to all other vertices in `V`.

If the goal of the algorithm is to find the shortest path between only two given vertices, `s` and `t`, then the algorithm can simply be stopped when that shortest path is found. Because there is no way to decide which vertices to "finish" first, all algorithms that solve for the shortest path between two given vertices have the same worst-case asymptotic complexity as single-source shortest path algorithms.

This paradigm also works for the _single-destination shortest path problem_. By reversing all of the edges in a graph, the single-destination problem can be reduced to the _single-source problem_. So, given a destination vertex,`t`, this algorithm will find the shortest paths starting at all other vertices and ending at `t`.

### All-Pairs

> Given a graph `G`, with vertices `V`, edges `E` with weight function w(u,v)=w<sub>u,v</sub> return the shortest path from `u` to `v` for all `(u,v)` in `V`.

The most common algorithm for the all-pairs problem is the _floyd-warshall algorithm_. This algorithm returns a matrix of values `M`, where each cell M<sub>i,j</sub> is the distance of the shortest path from vertex `i` to vertex `j`. Path reconstruction is possible to find the actual path taken to achieve that shortest path, but it is not part of the fundamental algorithm.

Broadly there are three main types of Shortest Path Algorithms:
1. Bellman Ford's Algorithm
2. Dijkstra's Algorithm
3. Floyd Warshall's Algorithm
4. Johnson's Algorithm      <sub>(optional)</sub>

---