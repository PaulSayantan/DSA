//
// @author: Sayantan Paul
//

/*
 * Implementation of Binary Search Tree in C++
 */

#include <iostream>

class Node {
public:
    int data;
    Node* left;
    Node* right;

    // constructor
    Node(int info): data(info), left(NULL), right(NULL) {}

    // display
    void displayNode(Node* node) {
        std::cout << node->data << " " << std::endl;
    }
};


class bst {
private:
    Node* root;                 // root node of the Binary Search Tree
    int height = 0;             // height of the Binary Search Tree
    int num_nodes = 0;          // number of nodes in Binary Search Tree
public:
    bst();
    ~bst();

    Node* get_root();
    bool is_empty(); 

    Node* insert_node(Node*, Node*);       // insertion of node in the Binary Search Tree
    Node* search_node(Node*, Node*);       // searching of node in Binary Search Tree
    Node* delete_node(Node*, Node*);       // deleting node in Binary Search Tree

    Node* get_successor(Node*);             // returns node with highest value in left subtree 
    Node* get_predecessor(Node*);           // returns node with lowest value in right subtree

    int height_of_tree(Node*);  // calculate height of the Binary Search Tree
};

// constructor to initialize Binary Search Tree
bst::bst(): root(NULL) {}

// destructor destroys Binary Search Tree after execution of program is complete
bst::~bst() {
    destroy_tree(root);
    height = 0;
    num_nodes = 0;
}
// a recursive function to delete all nodes of the Binary Search Tree
void destroy_tree(Node* node) {
    if (node) {
        destroy_tree(node->left);
        destroy_tree(node->right);
        delete node;
    }
}

/**
 * @brief Check if Binary Search Tree is empty
 * 
 * @return true if empty
 * @return false if full
 */
bool bst::is_empty() {
    return root == NULL;
}


/**
 * @brief returns root node of Binary Search Tree
 * 
 * @return Node* 
 */
Node* bst::get_root() {
    return root;
}


/**
 * @brief Insertion Operation in Binary Search Tree
 *        Time Complexity: O(n), where n is total number of nodes
 * 
 * @param node 
 */
Node* bst::insert_node(Node* curr, Node* node) {
    if (curr == NULL) {
        curr = node;
        return curr;
    }

    if (curr->data < node->data) {
        curr->right = insert_node(curr->right, node);
    } else if (curr->data > node->data) {
        curr->left = insert_node(curr->left, node);
    }

    return curr;
}

/**
 * @brief Searching Operation in Binary Search Tree
 *        Time Complexity: O(h), where h is height of the tree
 * 
 * @param root 
 * @param node 
 * @return Node* 
 */
Node* bst::search_node(Node* root, Node* node) {
    if (root == NULL) return NULL;
    
    if (root->data == node->data) {
        return root;
    }

    if (root->data > node->data) {
        return search_node(root->left, node);
    } else {
        return search_node(root->right, node);
    }
}


/**
 * @brief Deletion Operation in Binary Search Tree
 *        Time Complexity: O(logn), where n is total number of nodes
 * 
 *        Deleting node consists of three cases:
 *          1. Deleted Node is leaf node
 *          2. Deleted Node has 1 child
 *          3. Deleted Node has 2 children
 * 
 * @param root 
 * @param node 
 * @return Node* return the node that is deleted
 */
Node* bst::delete_node(Node* root, Node* node) {
    if (root == NULL) return root;
    Node* tmp;

    // Finding the node by traversing left or right subtree recursively
    if (node->data > root->data) {
        delete_node(root->right, node);
    } else if (node->data < root->data) {
        delete_node(root->left, node);
    } else {
        // Now, we have found the node to be deleted
        // It can have no child or 1 child or 2 children

        // case 1: No child, i.e, this is a leaf node
        if (root->left == NULL && root->right == NULL) {
            std::cout << "Deleted Node is a leaf node" << std::endl;
            tmp = root;
            delete root;
            return tmp;
        }
        // case 2: 1 child, i.e, either left child or right child
        // Node has right child only
        if (root->left == NULL) {
            std::cout << "Deleted Node has a right-child" << std::endl;
            tmp = root->right;
            delete root;
            return tmp;
        } 
        // Node has left child only
        else if (root->right == NULL) {
            std::cout << "Deleted Node has a left-child" << std::endl;
            tmp = root->left;
            delete root;
            return tmp;
        }
        // case 2: 2 children, i.e, both left and right child
        std::cout << "Deleted Node has both left and right-child" << std::endl;
        tmp = get_successor(root->left);
        root->data = tmp->data;
        root->right = delete_node(root->right, tmp);
    }

    return root;
}


Node* bst::get_successor(Node* node) {
    if (node->left) {
        return get_successor(node->left);
    }

    return node;
}




int main() {

    return 0;
}