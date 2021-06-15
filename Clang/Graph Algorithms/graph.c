/**
 * graph.c
 * @author Sayantan Paul
 *
 * @brief A Graph data structure implementation in C
 * 
 */


#include "graph.h"

node_p create_node(int);
graph_p create_graph(int, graph_type);
void destroy_graph(graph_p);
void graph_addEdge(int, int, graph_p);
void display_graph(graph_p);



/**
 * @brief Creation of a node object with an integer value
 * 
 * @param data Integer value stored in the node
 * @return a node object
 */
node_p create_node(int data) {
    // allocate memory for the node
    node_p new_node = (node_p)malloc(sizeof(node_t));
    
    // check if memory allocation is successful
    if (!new_node) err_exit("Memory allocation for node creation failed");

    new_node->vertex = data;
    new_node->next = NULL;

    return new_node;
}

/**
 * @brief Creation a graph object of n vertices
 * 
 * @param n Number of vertices in the graph
 * @param type whether the graph is directed or undirected
 * @return a graph object with n vertices
 */
graph_p create_graph(int n, graph_type type) {
    // allocate memory for the graph object
    graph_p graph = (graph_p)malloc(sizeof(graph_t));

    // check if memory allocation for graph is successful
    if (!graph) err_exit("Memory allocation for graph unsuccessful");

    graph->num_vertices = n;
    graph->type = type;

    // creation of an array of adjacency lists of size n
    graph->adjacent_list = (adjList_p)malloc(n * sizeof(adjList_t));

    // check if memory allocation for adjacency lists is successful
    if (!graph->adjacent_list) err_exit("Memory allocation for adjacency lists failed");

    // initialize all vertices of graph
    for (int i = 0; i < n; i++) {
        graph->adjacent_list[i].head = NULL;
        graph->adjacent_list[i].num_members = 0;
    }

    return graph;
}

/**
 * @brief Function for destroying Graph object
 * 
 * @param graph a graph object with n vertices
 */
void destroy_graph(graph_p graph) {
    // check if graph object is not NULL
    if (graph) {

        // check if adjacent_list object of graph is not NULL
        if (graph->adjacent_list) {

            // iterate over the adjacent_list array
            for (int v = 0; v < graph->num_vertices; v++) {

                node_p curr_ptr = graph->adjacent_list[v].head;

                while (curr_ptr != NULL) {
                    node_p temp = curr_ptr;
                    curr_ptr = curr_ptr->next;
                    free(temp);
                }
            }
        }
        // after adjacent_list is empty, destroy the object
        free(graph->adjacent_list);
    }
    // after graph is empty, destroy the graph
    free(graph);
}

/**
 * @brief Adding a new edge connecting a source vertex to it's destination vertex of a graph
 * 
 * @param src an integer denoting a vertex in the graph
 * @param dest an integer denoting a vertex in the graph.
 *             In case of directed graph, dest_vertex is the neighbour of src_vertex, and not vice-versa
 * @param graph a graph object of n vertex
 */
void graph_addEdge(int src, int dest, graph_p graph) {

    node_p dest_vertex = create_node(dest);

    // connect src_vertex to dest_vertex
    dest_vertex->next = graph->adjacent_list[src].head;
    graph->adjacent_list[src].head = dest_vertex;
    graph->adjacent_list[src].num_members++;

    // check if graph is undirected
    if (graph->type == UNDIRECTED) {
        node_p src_vertex = create_node(src);

        // connect dest_vertex to src_vertex
        src_vertex->next = graph->adjacent_list[dest].head;
        graph->adjacent_list[dest].head = src_vertex;
        graph->adjacent_list[dest].num_members++;
    }
}

/**
 * @brief Function to print the adjacency list of a graph
 * @param graph a graph object with n vertices
 */
void display_graph(graph_p graph) {

    // iterate over the vertices of the graph
    for (int i = 0; i < graph->num_vertices; i++) {
        node_p curr_vertex = graph->adjacent_list[i].head;

        printf("\n%d: ", i);
        while (curr_vertex != NULL) {
            printf("%d -> ", curr_vertex->vertex);
            curr_vertex = curr_vertex->next;
        }
        printf("NULL\n");
    }
}
