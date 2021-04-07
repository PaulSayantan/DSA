//
// Author: Sayantan Paul
//

/**
 * A C Program to construct a simple weighted directed graph
 */

#include <stdio.h>

#define num_vertices 5

// AddEdge() -> This method adds a directed edge with weight
void AddEdge(int arr[][num_vertices], int src, int dest, int weight) {
    arr[src][dest] = weight;
}


// Graph_Adjacent_Matrix() -> This method prints the undirected Graph in the form of matrix
void Graph_Adjacent_Matrix(int arr[][num_vertices]) {
    for (int i = 0; i < num_vertices; i++) {
        for (int j = 0; j < num_vertices; j++) {
            printf("%d ", arr[i][j]);
        }
        printf("\n");
    }
    
}

int main() {
    int adjacent_matrix[num_vertices][num_vertices] = {{0}};

    printf("\nWeighted Directed Graph G:\n");

    AddEdge(adjacent_matrix, 0, 1, 5);
    AddEdge(adjacent_matrix, 0, 2, 6);
    AddEdge(adjacent_matrix, 0, 3, 7);
    AddEdge(adjacent_matrix, 1, 3, 2);
    AddEdge(adjacent_matrix, 1, 4, 6);
    AddEdge(adjacent_matrix, 2, 3, 6);
    AddEdge(adjacent_matrix, 3, 4, 9);

    Graph_Adjacent_Matrix(adjacent_matrix);

    return 0;
}