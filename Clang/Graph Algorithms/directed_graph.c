//
// @Author: Sayantan Paul
//

/**
 * A C Program to demonstrate a simple directed graph
 */


#include <stdio.h>

#define num_vertices 5

void AddEdge(int arr[][num_vertices], int src, int dest) {
    arr[src][dest] = 1;
}

void Graph_Adjacent_Matrix(int arr[][num_vertices]) {
    printf("\nDirected Graph G:\n");

    for (int i = 0; i < num_vertices; i++) {
        for (int j = 0; j < num_vertices; j++) {
            printf("%d  ", arr[i][j]);
        }
        printf("\n");
    }
}

int main() {
    int adjacent_matrix[num_vertices][num_vertices] = {{0}};

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