//
// @author: Sayantan Paul
//

/**
 * A Simple C++ program to depict a graph_matrix data structure using adjacency list and adjacency matrix
 */

#include <iostream>
#include <vector>
#include <algorithm>

#define n_v 5                           // n_v denotes number of vertices in the graph_matrix

bool graph_matrix[n_v][n_v];            // graph matrix of size n_v
std::vector<int> graph_adj_list[n_v];   // adjacency list of n_v vertices in the graph


void initialize_graph();
void create_graph_matrix(std::string);
void create_graph_adjlist(std::string);
void print_graph_matrix();
void print_graph_adjlist();



int main() {
    std::cout << "*********UNDIRECTED GRAPH*********" << std::endl;
    initialize_graph();
    create_graph_matrix("UNDIRECTED");
    print_graph_matrix();

    std::cout << "*********DIRECTED GRAPH*********" << std::endl;
    create_graph_adjlist("DIRECTED");
    print_graph_adjlist();
    return 0;
}

/**
 * @brief initialize_graph: this function initializes all the vertices of the graph as empty
 */
void initialize_graph() {
    for (ssize_t i = 0; i < n_v; i++) {
        graph_matrix[i][i] = false;
    }
}


/**
 * @brief create_graph_matrix: this function accepts two integers from user as x and y and connects x -> y,
 *                      if graph is undirected y -> x
 *
 * @param graph_type: determines if the graph is directed or undirected
 */
void create_graph_matrix(std::string graph_type) {
    int x, y;
    int n_e;
    std::cout << "How many edges in this graph_matrix?" << std::endl;
    std::cin >> n_e;

    for (ssize_t i = 0; i < n_e; i++) {
        std::cout << "Enter x and y: ";
        std::cin >> x >> y;

        // check if x and y values are valid or xy-edge already exists
        if ((x < n_v && y < n_v) || !graph_matrix[x][y]) {
            graph_matrix[x][y] = true;
            if (graph_type == "UNDIRECTED")
                graph_matrix[y][x] = true;
        }
    }
}


/**
* @brief print_graph: print the graph_matrix
*/
void print_graph_matrix() {
    std::cout << "Edges in this graph_matrix are as follows: " << std::endl;
    for (ssize_t i = 0; i < n_v; i++) {
        for (ssize_t j = 0; j < n_v; j++) {
            if (graph_matrix[i][j])
                std::cout << i << " --> " << j << std::endl;
        }
    }
}

/**
 * @brief create_graph_adjlist: this function accepts two integers from user as x and y and connects x -> y,
 *                      if graph is undirected y -> x
 *
 * @param graph_type: determines if the graph is directed or undirected
 */
void create_graph_adjlist(std::string graph_type) {
    int x, y;
    int n_e;
    std::cout << "How many edges in this graph_matrix?" << std::endl;
    std::cin >> n_e;

    for (ssize_t i = 0; i < n_e; i++) {
        std::cout << "Enter x and y: ";
        std::cin >> x >> y;

        // check if x and y values are valid or xy-edge already exists
        if ((x < n_v && y < n_v) || std::find(graph_adj_list[x].begin(), graph_adj_list[x].end(), y) != graph_adj_list[x].end()) {
            graph_adj_list[x].push_back(y);
            if (graph_type == "UNDIRECTED")
                graph_adj_list[y].push_back(x);
        }
    }
}

void print_graph_adjlist() {
    std::cout << "Edges in this graph_matrix are as follows: " << std::endl;
    for (ssize_t i = 1; i < n_v; i++) {

        for (ssize_t j = 0; j < graph_adj_list[i].size(); j++) {
            std::cout << i << " --> " << graph_adj_list[i][j] << std::endl;
        }
    }
}