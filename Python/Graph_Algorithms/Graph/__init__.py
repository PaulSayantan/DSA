import abc
from typing import List
from abc import ABC

import numpy as np


class Graph(ABC):
    """
    Base class representation of Graph with all interface methods.

    :func add_edge(): to create an edge connecting vertex v1 and v2 of certain weight.
    :func get_adjacent_vertices(): return the list of adjacent edges connected to a vertex v
    :func get_indgree(): returns the number of incomming edges to vertex v
    :func get_edge_weight(): returns the edge weight
    """

    def __init__(self, num_vertices: int, isdirected=False):
        self.num_vertices = num_vertices
        self.isdirected = isdirected

    @abc.abstractmethod
    def add_edge(self, v1, v2, weight):
        pass

    @abc.abstractmethod
    def get_adjacent_vertices(self, v):
        pass

    @abc.abstractmethod
    def get_indegree(self, v):
        pass

    @abc.abstractmethod
    def get_edge_weight(self, v1, v2):
        pass

    @abc.abstractmethod
    def __repr__(self):
        pass


#       Node        #

class Node:
    def __init__(self, vertex_id):
        self.vertex_id = vertex_id
        self.adjacency_set = set()

    def add_edge(self, v):
        if self.vertex_id == v:
            raise ValueError("The vertex %d cannot be adjacent to itself" % v)

        self.adjacency_set.add(v)

    def get_adjacent_vertices(self):
        """
        :rtype: set
        """
        return sorted(self.adjacency_set)


#       ADJACENCY SET       #

class AdjacencySetGraph(Graph, ABC):
    def __init__(self, num_vertices, isdirected=False):
        super(AdjacencySetGraph, self).__init__(num_vertices, isdirected)

        self.vertex_list = list()
        for i in range(num_vertices):
            self.vertex_list.append(Node(i))

    def add_edge(self, v1, v2, weight=1):
        if v1 >= self.num_vertices or v2 >= self.num_vertices or v1 < 0 or v2 < 0:
            raise ValueError("Vertices %d and %d are out of bounds" % (v1, v2))

        if weight != 1:
            raise ValueError("Edge weight should be 1")

        self.vertex_list[v1].add_edge(v2)

        if not self.isdirected:
            self.vertex_list[v2].add_edge(v1)

    def get_adjacent_vertices(self, v):
        if v >= self.num_vertices or v < 0:
            raise ValueError("Cannot access vertex %d" % v)

        return self.vertex_list[v].get_adjacent_vertices()

    def get_indegree(self, v):
        if v >= self.num_vertices or v < 0:
            raise ValueError("Cannot access vertex %d" % v)

        indegree = 0
        for i in range(self.num_vertices):
            if v in self.get_adjacent_vertices(i):
                indegree += 1

        return indegree

    def get_edge_weight(self, v1, v2):
        return 1

    def __repr__(self):
        for i in range(self.num_vertices):
            for v in self.get_adjacent_vertices(i):
                print(i, '-->', v)


#         ADJACENCY MATRIX           #

class AdjacencyMatrixGraph(Graph, ABC):
    def __init__(self, num_vertices: int, isdirected=False):
        super(AdjacencyMatrixGraph, self).__init__(num_vertices, isdirected)
        self.matrix = np.zeros((num_vertices, num_vertices))

    def add_edge(self, v1, v2, weight=1):
        if v1 >= self.num_vertices or v2 >= self.num_vertices or v1 < 0 or v2 < 0:
            raise ValueError("Vertices %d and %d are out of bounds" % (v1, v2))

        if weight < 1:
            raise ValueError("Edge weight cannot be less than 1")

        self.matrix[v1][v2] = weight

        if not self.isdirected:
            self.matrix[v2][v1] = weight

    def get_adjacent_vertices(self, v) -> List[int]:
        if v >= self.num_vertices or v < 0:
            raise ValueError("Cannot access vertex %d" % v)

        adjacent_vertices = list()
        for i in range(self.num_vertices):
            if self.matrix[v][i] > 0:
                adjacent_vertices.append(i)

        return adjacent_vertices

    def get_indegree(self, v):
        if v >= self.num_vertices or v < 0:
            raise ValueError("Cannot access vertex %d" % v)

        indegree = None
        for i in range(self.num_vertices):
            if self.matrix[i][v] > 0:
                indegree += 1

        return indegree

    def get_edge_weight(self, v1, v2):
        return self.matrix[v1][v2]

    def __repr__(self):
        for i in range(self.num_vertices):
            for v in self.get_adjacent_vertices(i):
                print(i, '-->', v)
