/**
 * @file graph.h
 * @author Sayantan Paul
 * @brief  basic header file for graph program
 * 
 */

#ifndef _GRAPH_H_
#define GRAPH_H_

#include <stdio.h>
#include <stdlib.h>

typedef enum {
    UNDIRECTED = 0, DIRECTED = 1
} graph_type;


/**
 * @brief Node structure: 
 * 
 */
typedef struct node {
    int vertex;
    struct node* next;
} node_t, *node_p;


/**
 * @brief Adjacency List structure: Contains the list of all vertices 
 *        adjacent to a given vertex.
 * 
 * @param num_members Number of vertices adjacent to a vertex
 * @param head pointing to the head of the adjacency list
 */
typedef struct adjacencyList {
    int num_members;
    node_t* head;
} adjList_t, *adjList_p;

/**
 * @brief Graph structure: A graph is an array of adjacent_lists
 * 
 * @param type denotes whether the graph is directed or undirected
 * @param num_vertices Total number of vertices in the graph
 * @param adjacent_list array containing all the adjacent vertices of the head node
 */
typedef struct graph {
    graph_type type;
    int num_vertices;
    adjList_p adjacent_list;
} graph_t, *graph_p;



/* Exit functions to handle fatal errors */
__inline void err_exit(char* msg) {
    printf("[Error]: %s\nCode Execution closed\n", msg);
    exit(1);
}


#endif