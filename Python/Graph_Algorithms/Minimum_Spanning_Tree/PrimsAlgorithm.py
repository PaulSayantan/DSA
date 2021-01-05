# Prim's Algorithm for Minimum Spanning Tree

from Data_Structures.Queue import Priority_Dict
from Graph_Algorithms import Graph
from Graph_Algorithms.Graph import AdjacencyMatrixGraph


def PrimsMST(graph: Graph, source: int):
    # distance_table -> maps the vertex number to a tuple containing
    # (distance of vertex from source, last vertex path from source)
    distance_table = dict()

    # initialize the value of distance for every node
    for vertex in range(graph.num_vertices):
        distance_table[vertex] = (None, None)

    # initialize the value of distance for source node
    distance_table[source] = (0, source)

    # priority_queue holds mapping of vertex number to distance from source
    # also, here we can access highest priority item first
    # priority of a node = weight of the edge connected to it
    priority_queue = Priority_Dict.PriorityDict()

    priority_queue[source] = 0

    # keep track of visited nodes
    visited_vertices = set()

    # spanning_tree -> each item represents a edge between two vertices
    # '1 --> 2' represents edge between verteices 1 and 2
    spanning_tree = set()

    # minimum cost of spanning_tree
    spanning_tree_cost = 0

    # process the priority_queue till its not empty
    while len(priority_queue.keys()) > 0:

        # current_vertex -> the vertex with lowest priority
        current_vertex = priority_queue.pop_smallest()

        # if current_vertex is visited, means that we have processed all outgoing edges from it
        # no need to process the node again
        if current_vertex in visited_vertices:
            continue

        visited_vertices.add(current_vertex)

        # if current_vertex is not source, we have visited a low-weight edge, add it to the spanning_tree
        if current_vertex != source:
            last_vertex = distance_table[current_vertex][1]
            edge = str(last_vertex) + '-->' + str(current_vertex)

            if edge not in spanning_tree:
                spanning_tree.add(edge)
                spanning_tree_cost += graph.get_edge_weight(current_vertex, last_vertex)

        # explore neighbouring vertices that are adjacent to the current vertex
        for neighbour in graph.get_adjacent_vertices(current_vertex):
            distance = graph.get_edge_weight(current_vertex, neighbour)

            # last recorded distance of the neighbour
            neighbour_distance = distance_table[neighbour][0]

            # if neighbour is visited for the first time or
            # new edge connecting the neighbour has lower weight than the last,
            # update the distance table and the priority queue
            if neighbour_distance is None or neighbour_distance > distance:
                distance_table[neighbour] = (distance, current_vertex)
                priority_queue[neighbour] = distance

    # print out all the edges
    for edge in spanning_tree:
        print(edge)

    print('Minimum Cost: ', spanning_tree_cost)


if __name__ == '__main__':
    graph = AdjacencyMatrixGraph(8)
    graph.add_edge(0, 1, 1)
    graph.add_edge(1, 2, 2)
    graph.add_edge(1, 3, 2)
    graph.add_edge(2, 3, 2)
    graph.add_edge(1, 4, 3)
    graph.add_edge(3, 5, 1)
    graph.add_edge(5, 4, 3)
    graph.add_edge(3, 6, 1)
    graph.add_edge(6, 7, 1)
    graph.add_edge(7, 0, 1)

    PrimsMST(graph, 1)
