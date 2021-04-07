//
// @Author: Sayantan Paul
//

/*
 * A C Program to demonstrate a simple undirected graph
 */

#include <stdio.h>

#define num_vertices 5

// AddEdge() -> This method adds an undirected edge to the graph 
void AddEdge(int arr[][num_vertices], int src, int dest) {
    arr[src][dest] = 1;
    arr[dest][src] = 1;
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

    printf("\nUndirected Graph G:\n");

    AddEdge(adjacent_matrix, 0, 1);
    AddEdge(adjacent_matrix, 0, 2);
    AddEdge(adjacent_matrix, 0, 3);
    AddEdge(adjacent_matrix, 1, 3);
    AddEdge(adjacent_matrix, 1, 4);
    AddEdge(adjacent_matrix, 2, 3);
    AddEdge(adjacent_matrix, 3, 4);

    Graph_Adjacent_Matrix(adjacent_matrix);

    return 0;
}
