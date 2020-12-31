# Topological Sort Algorithm

from Graph_Algorithms.Graph import Graph, AdjacencySetGraph
from typing import List
from queue import Queue
import numpy as np


def topological_sort(graph: Graph) -> List[int]:
    """
    :param graph: Graph Data Structure
    :return:      list of nodes in topological sorted order.
    """
    topological_sorted_list = list()
    queue = Queue()
    indegree = dict()

    for v in range(graph.num_vertices):
        indegree[v] = graph.get_indegree(v)

    for v in range(graph.num_vertices):
        if indegree[v] == 0:
            queue.put(v)

    while not queue.empty():
        vertex_visited = queue.get()
        topological_sorted_list.append(vertex_visited)

        for neighbour in graph.get_adjacent_vertices(vertex_visited):
            indegree[neighbour] -= 1
            if indegree[neighbour] == 0:
                queue.put(neighbour)

    if len(topological_sorted_list) != graph.num_vertices:
        raise ValueError("Graph contains a cycle !!")

    return topological_sorted_list


if __name__ == '__main__':
    g = AdjacencySetGraph(9, isdirected=True)
    g.add_edge(0, 1)
    g.add_edge(1, 2)
    g.add_edge(2, 0)
    g.add_edge(2, 7)
    g.add_edge(2, 4)
    g.add_edge(2, 3)
    g.add_edge(1, 5)
    g.add_edge(5, 6)
    g.add_edge(3, 6)
    g.add_edge(3, 4)
    g.add_edge(6, 8)

    print(topological_sort(g))
