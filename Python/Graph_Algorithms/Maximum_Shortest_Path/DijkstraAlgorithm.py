# Dijkstra Algorithm to find Shortest Path in a Directed/Undirected Graph with +ve edge-weights only

from Data_Structures.Queue import Priority_Dict
from Graph_Algorithms.Graph import Graph, AdjacencyMatrixGraph
import math


def dijkstraSP(graph: Graph, source: int, destination: int):
    # declare a distance_table and initialize all the vertices with two attributes
    # 1st value defines minimum distance
    # 2nd value defines its predecessor
    # initially set min_distance of all unvisited vertices to INFINITY and set predecessor to None
    distance_table = dict()
    for i in range(graph.num_vertices):
        # i -> (min_distance, predecessor)
        distance_table[i] = (math.inf, None)

    # store the source node as 0, since its the first visited node
    distance_table[source] = (0, source)

    # declare a priority queue implemented using Binary min-heap
    # min-heap is used here because we know that in min-heap, the smallest item is the root node
    priority_queue = Priority_Dict.PriorityDict()
    # store the source node as key and  distance as its priority value
    priority_queue[source] = 0

    # check until priority queue is empty
    while len(priority_queue.keys()) > 0:
        # pop out the key with the least distance value
        vertex_u = priority_queue.pop_smallest()
        # obtain its distance value from the distance table
        dist_u = distance_table[vertex_u][0]

        # iterate over all the adjacent vertices connected with the current_vertex
        for vertex_v in graph.get_adjacent_vertices(vertex_u):
            # adding the min distance value of u with the edge weight between (u, v), save them as dist_u
            dist_u = dist_u + graph.get_edge_weight(vertex_u, vertex_v)
            # min_distance value of v
            dist_v = distance_table[vertex_v][0]

            # if dist_u is less than dist_v, then the min_distance value of v should be updated with dist_u value
            # also, u becomes predecessor of v
            # add vertex_v in the priority_queue
            if dist_v > dist_u:
                distance_table[vertex_v] = (dist_u, vertex_u)
                priority_queue[vertex_v] = dist_u

    # initialize the shortest path with its destination vertex
    shortest_path = [destination]

    # obtain the predecessor value of destination vertex
    predecessor = distance_table[destination][1]

    # reverse loop over the distance_table starting from the destination vertex
    # append the predecessor to the top of the list
    # continue till we have not met the source vertex
    while predecessor is not None and predecessor is not source:
        shortest_path = [predecessor] + shortest_path

        predecessor = distance_table[predecessor][1]

    # reached here, means predecessor = source vertex or None
    # if None, then shortest path doesn't exist
    # else, append the source vertex to the shortest_path list and print
    if predecessor is None:
        print("There is no path from %d to %d" % (source, destination))
    else:
        shortest_path = [source] + shortest_path
        print("Shortest Path from %d to %d is: %r" % (source, destination, shortest_path))


if __name__ == '__main__':
    undirected_graph = AdjacencyMatrixGraph(8, isdirected=False)
    undirected_graph.add_edge(0, 1, 1)
    undirected_graph.add_edge(1, 2, 2)
    undirected_graph.add_edge(1, 3, 6)
    undirected_graph.add_edge(2, 3, 2)
    undirected_graph.add_edge(1, 4, 3)
    undirected_graph.add_edge(3, 5, 1)
    undirected_graph.add_edge(5, 4, 5)
    undirected_graph.add_edge(3, 6, 1)
    undirected_graph.add_edge(6, 7, 1)
    undirected_graph.add_edge(0, 7, 8)

    dijkstraSP(undirected_graph, 4, 7)
    dijkstraSP(undirected_graph, 7, 0)
    dijkstraSP(undirected_graph, 0, 6)

    print()

    directed_graph = AdjacencyMatrixGraph(8, isdirected=True)
    directed_graph.add_edge(0, 1, 1)
    directed_graph.add_edge(1, 2, 2)
    directed_graph.add_edge(1, 3, 6)
    directed_graph.add_edge(2, 3, 2)
    directed_graph.add_edge(1, 4, 3)
    directed_graph.add_edge(3, 5, 1)
    directed_graph.add_edge(5, 4, 5)
    directed_graph.add_edge(3, 6, 1)
    directed_graph.add_edge(6, 7, 1)
    directed_graph.add_edge(0, 7, 8)

    dijkstraSP(directed_graph, 4, 7)
    dijkstraSP(directed_graph, 7, 0)
    dijkstraSP(directed_graph, 0, 6)
