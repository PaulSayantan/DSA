# Breadth First Search Algorithm

from GraphAlgorithms.graph import Graph, AdjacencyMatrixGraph
import numpy as np
from queue import Queue
from typing import List


def breadth_first_search(graph: Graph, root: int) -> List[int]:
    queue = Queue()
    queue.put(root)
    result = list()

    visited = np.zeros(graph.num_vertices)

    while not queue.empty():
        vertex_visited = queue.get()

        if visited[vertex_visited] == 1:
            continue

        result.append(vertex_visited)
        visited[vertex_visited] = 1

        for neighbour in graph.get_adjacent_vertices(vertex_visited):
            if visited[neighbour] != 1:
                queue.put(neighbour)

    return result


if __name__ == '__main__':
    g = AdjacencyMatrixGraph(9)
    g.add_edge(0, 1)
    g.add_edge(1, 2)
    g.add_edge(2, 7)
    g.add_edge(2, 4)
    g.add_edge(2, 3)
    g.add_edge(1, 5)
    g.add_edge(5, 6)
    g.add_edge(6, 3)
    g.add_edge(3, 4)
    g.add_edge(6, 8)

    r = breadth_first_search(g, 2)
    print(r)

