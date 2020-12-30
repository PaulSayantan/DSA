# Depth First Search Algorithm

from GraphAlgorithms.graph import Graph, AdjacencySetGraph
import numpy as np
from typing import List


def depth_first_search_iterative(graph: Graph, root: int) -> List[int]:
    """
    :param graph:   importing the Graph Data structure defined in graph.py
    :param root:    the starting node of graph traversal
    :return:        list of nodes depicting traversal of the graph
    """
    stack, result = list(), list()
    stack.append(root)
    visited = np.zeros(graph.num_vertices)
    visited[root] = 1
    result.append(root)

    while len(stack) != 0:
        visited_vertex = stack[-1]
        stack.pop()

        for neighbour in graph.get_adjacent_vertices(visited_vertex):
            if visited[neighbour] != 1:
                stack.append(neighbour)
                visited[neighbour] = 1
                result.append(neighbour)

    return result


def depth_first_search_recursive(graph: Graph, visited: np.array, current: int, result: List[int]):
    """
    :param result:      list of node traversed accordingly
    :param graph:       importing the Graph Data structure defined in graph.py
    :param visited:     an array of nodes to keep track of nodes which are previously visited
    :param current:     the current node being visited
    :return:            result
    """
    if visited[current] == 1:
        return result

    visited[current] = 1
    result.append(current)

    for neighbour in graph.get_adjacent_vertices(current):
        depth_first_search_recursive(graph, visited, neighbour, result)

    return result


if __name__ == '__main__':
    g = AdjacencySetGraph(9)
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

    r1 = depth_first_search_iterative(g, 2)
    print("Depth First Search Iterative", r1)

    r2 = []
    depth_first_search_recursive(g, np.zeros(g.num_vertices), 2, r2)
    print("Depth First Search Recursive", r2)
