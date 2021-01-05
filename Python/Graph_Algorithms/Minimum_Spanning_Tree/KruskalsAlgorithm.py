# Kruskal's Algorithm for Minimum Spanning Tree

from Data_Structures.Queue import Priority_Dict
from Graph_Algorithms import Graph
from Graph_Algorithms.Graph import AdjacencyMatrixGraph


def cycle_exists(spanning_tree) -> bool:
    # iterate over all the vertices present in the spanning tree
    for vertex in spanning_tree:
        # declare a queue and append the current vertex
        queue = list()
        queue.append(vertex)

        # mark the vertex as visited
        visited_vertices = set()

        # iterate till queue is empty
        while len(queue) > 0:
            vertex = queue.pop(0)
            # if vertex in visited_vertices means that cycle present in the tree
            if vertex in visited_vertices:
                return True

            visited_vertices.add(vertex)

            # Add all vertices connected by edges in this spanning tree
            queue.extend(spanning_tree[vertex])

    # cycle doesn't exists
    return False


def KruskalsMST(graph: Graph):

    # priority_queue holds mapping of vertex number to distance from source
    # also, here we can access highest priority item first
    # priority of a node = weight of the edge connected to it
    priority_queue = Priority_Dict.PriorityDict()

    for vertex in range(graph.num_vertices):
        for neighbour in graph.get_adjacent_vertices(vertex):
            priority_queue[(vertex, neighbour)] = graph.get_edge_weight(vertex, neighbour)

    # keep track of visited nodes
    visited_vertices = set()

    # spanning_tree -> each item represents a edge between two vertices
    # '1 --> 2' represents edge between verteices 1 and 2
    spanning_tree = dict()

    # minimum cost of spanning_tree
    spanning_tree_cost = 0

    # number of edges added to spanning_tree, i.e, visited so far
    num_edges = 0

    # every vertex has an empty set associated to itself which stores its neighbours
    for vertex in range(graph.num_vertices):
        # every vertex in spanning_tree has an empty set associated to itself
        spanning_tree[vertex] = set()

    # iterate over the priority queue until its empty or num_edges visited is equal to number of vertices
    while len(priority_queue.keys()) > 0 and num_edges < graph.num_vertices - 1:

        # pop off the vertices with lowest edge weight among others
        u, v = priority_queue.pop_smallest()

        # if edge already present in spanning_tree, ignore that edge and jump to next iteration
        if u in spanning_tree[v]:
            continue

        # sort the connection so that the smaller vertex id always comes First
        sorted_pair = sorted([u, v])

        # this works similar to the makeSet function of a disjoint set, where we represent any vertex of a set,
        # by the smallest vertex present in the set
        # update the spanning_tree_cost
        spanning_tree[sorted_pair[0]].add(sorted_pair[1])
        spanning_tree_cost += graph.get_edge_weight(u, v)

        # if adding edge results in a cycle, remove the edge, decrement spanning_tree_cost and jump to next iteration
        if cycle_exists(spanning_tree):
            spanning_tree[sorted_pair[0]].remove(sorted_pair[1])
            spanning_tree_cost -= graph.get_edge_weight(u, v)
            continue

        # increment the num_edges to denote that one edge has been visited
        num_edges += 1

        # add vertices u, v in visited_vertices for the same
        visited_vertices.add(u)
        visited_vertices.add(v)

    # if visited_vertices < or > graph.num_vertices, then Minimum Spanning Tree doesn't exists
    if len(visited_vertices) != graph.num_vertices:
        print('MST not found')
    else:
        # print the spanning tree
        for key in spanning_tree:
            for value in spanning_tree[key]:
                print(key, '-->', value)

        # print the minimum cost of the whole spanning tree
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

    KruskalsMST(graph)
