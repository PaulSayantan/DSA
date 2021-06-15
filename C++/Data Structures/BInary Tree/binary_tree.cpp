//
// @author: Sayantan Paul
//


#include <iostream>
#include <queue>

/**
 * @brief Node Data Structure for representing a node of the binary tree
 * 
 */
class Node {
public:
    int data;
    Node* left;
    Node* right;

    // constructor
    Node(int info): data(info), left(NULL), right(NULL) {}

    // display
    void display() {
        std::cout << "[ " << data << " ]" << std::endl;
    }

};



/**
 * @brief Binary Tree Data Structure
 */
class Binary_tree {
private:
    Node* root;
    int height_of_tree = 0;
public:
    // constructor -> instantiate Binary Tree with a root node of value NULL
    Binary_tree(): root(NULL) {
        std::cout << "\nBinary Tree initialized successfully" << std::endl;
    }

    void set_root(Node* node) {
        root = node;
        height_of_tree = calculate_height(root);
    }

    Node* get_root() {
        return root;
    }


    /**
     * @brief destroy() -> perform inorder_traversal of binary tree and delete each node
     * @param node
     */
    void destroy(Node* node) {
        if (node) {
            destroy(node->left);
            destroy(node->right);
            delete node;
        }
    }

    // destructor -> call destroy func
    ~Binary_tree() {
        destroy(root);
        std::cout << "\nBinary Tree destroyed successfully" << std::endl;
    }

    /**
     * @brief calculate height of the binary tree
     * @param node
     * @return
     */
    int calculate_height(Node* node) {
       if (node == NULL) return 0;
       else {
           int left = calculate_height(node->left);
           int right = calculate_height(node->right);

           if (left > right) return left + 1;
           else return right + 1;
       }
    }

    /**
     * @brief Inorder Traversal of Nodes in binary tree. (Left, Node, Right)
     *
     * @param node
     */
    void inorder_traversal(Node* node) {
        if (node == NULL)
            return;

        inorder_traversal(node->left);
        std::cout << node->data << " ";
        inorder_traversal(node->right);
    }

    /**
     * @brief Preorder Traversal of Nodes in binary tree. (Node, Left, Right)
     *
     * @param node
     */
    void preorder_traversal(Node* node) {
        if (node == NULL)
            return;
        std::cout << node->data << " ";
        preorder_traversal(node->left);
        preorder_traversal(node->right);
    }


    /**
     * @brief Postorder traversal of Nodes in binary tree. (Left, Right, Node)
     *
     * @param node
     */
    void postorder_traversal(Node* node) {
        if (node == NULL)
            return;
        postorder_traversal(node->left);
        postorder_traversal(node->right);
        std::cout << node->data << " ";
    }

    /**
     * @brief Breadth-First Traversal of Nodes in a Binary Tree
     */
    void breadth_first_traversal() {
        for (int i = 1; i <= height_of_tree; i++) {
            print_level(root, i);
        }
    }

    /**
     * @brief Print the left and the right nodes present at the particular level
     * @param node
     * @param level
     */
    void print_level(Node* node, int level) {
        if (node == NULL) return;
        if (level == 1) {
            std::cout << node->data << " ";
        } else if (level > 1) {
            print_level(node->left, level - 1);
            print_level(node->right, level - 1);
        }
    }


    /**
     * @brief Insertion of new node in Binary Tree
     * 
     * @param node 
     */
    void insert_node(Node* node) {
        if (root == NULL) {
            root = node;
        } else {
            std::queue<Node*> nodes_queue;
            nodes_queue.push(root);

            while (!nodes_queue.empty()) {
                Node* curr = nodes_queue.front();
                nodes_queue.pop();

                // check if leftchild is empty -> if empty add node to left
                {
                    if (curr->left == NULL) {
                        curr->left = node;
                        std::cout << "Insertion Complete !" << std::endl;
                        break;
                    } else {
                        nodes_queue.push(curr->left);
                    }
                }

                // check if rightchild is empty -> if empty add node to right
                {
                    if (curr->right == NULL) {
                        curr->right = node;
                        std::cout << "Insertion Complete !" << std::endl;
                        break;
                    } else {
                        nodes_queue.push(curr->right);
                    }
                }
            }
        }
    }

    /**
     * @brief Deletion of Node from a Binary Tree
     * 
     * @param node 
     */
    Node* delete_node(Node* node) {
        if (node == NULL) return NULL;

        if (root->left == NULL && root->right == NULL) {
            if (node->data == root->data) {
                return NULL;
            } else {
                return root;
            }
        }

        Node* temp;
        Node* del_node = NULL;

        std::queue<Node*> node_q;
        node_q.push(root);

        while (!node_q.empty()) {
            temp = node_q.front();
            node_q.pop();

            if (temp->data == node->data) {
                del_node = temp;
            }

            if (temp->left) {
                node_q.push(temp->left);
            }

            if (temp->right) {
                node_q.push(temp->right);
            }
        }

        if (del_node) {
            int data = temp->data;
            delete_deepest_Node(root, temp);
            del_node->data = data;
        }

        return root;
    }

    /**
     * @brief delete_deepest_node function deletes the last node present at the last level of Binary Tree
     * 
     * @param node considering the startnode/root of the Binary Tree
     * @param deepest_node the last node in the Binary Tree
     */
    void delete_deepest_Node(Node* node, Node* deepest_node) {
        if (node == NULL)   return;
        std::queue<Node*> node_q;
        node_q.push(node);

        while (!node_q.empty()) {
            Node* curr = node_q.front();
            node_q.pop();

            if (curr->data == deepest_node->data) {
                curr = NULL;
                delete deepest_node;
                return;
            }

            if (curr->left) {
                if (curr->left->data == deepest_node->data) {
                    curr->left = NULL;
                    delete deepest_node;
                    return;
                } else {
                node_q.push(curr->left);
                }
            }

            if (curr->right) {
                if (curr->right->data == deepest_node->data) {
                    curr->right = NULL;
                    delete deepest_node;
                    return;
                } else {
                    node_q.push(curr->right);
                }
            }
        }
    }
};

int main() {
    Binary_tree* b_tree = new Binary_tree();
    
    /**
     *  Binary Tree:
     * 
     *          10   <- root
     *         /  \
     *        8    6
     *      /  \    \
     *     15  11    9
     *    /
     *   3
     * 
     */
    Node* root = new Node(10);
    root->left = new Node(8);
    root->right = new Node(6);
    root->left->left = new Node(15);
    root->left->right = new Node(11);
    root->right->right = new Node(9);
    root->left->left->left = new Node(3);


    b_tree->set_root(root);

    std::cout << "\nHeight of the tree: " << b_tree->calculate_height(root) << std::endl;

    std::cout << "\nInorder Traversal:" << std::endl;
    b_tree->inorder_traversal(b_tree->get_root());

    std::cout << "\nDeleting Node 15: ";
    b_tree->delete_node(new Node(15));

    std::cout << "\nInorder Traversal:" << std::endl;
    b_tree->inorder_traversal(b_tree->get_root());

    std::cout << "\nInserting 12 in Binary tree.... ";
    b_tree->insert_node(new Node(12));

    std::cout << "New Inorder Traversal:" << std::endl;
    b_tree->inorder_traversal(b_tree->get_root());

    std::cout << "\nPreorder Traversal:" << std::endl;
    b_tree->preorder_traversal(b_tree->get_root());

    std::cout << "\nPostorder Traversal:" << std::endl;
    b_tree->postorder_traversal(b_tree->get_root());

    std::cout << "\nBreadth-first traversal:" << std::endl;
    b_tree->breadth_first_traversal();
    b_tree->~Binary_tree();

    

    return 0;
}