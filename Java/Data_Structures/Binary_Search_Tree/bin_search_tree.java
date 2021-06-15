package Data_Structures.Binary_Search_Tree;


public class bin_search_tree {
    public static void main(String[] args) {
        Binary_Search_Tree bst = new Binary_Search_Tree();

        bst.insert_node(53);
        bst.insert_node(30);
        bst.insert_node(72);
        bst.insert_node(14);
        bst.insert_node(39);
        bst.insert_node(34);
        bst.insert_node(9);
        bst.insert_node(23);
        bst.insert_node(47);
        bst.insert_node(61);
        bst.insert_node(79);
        bst.insert_node(84);

        bst.inorder_traversal(bst.getRoot());

        System.out.println("Maximum: " + bst.maximum_node());
        System.out.println("Minimum: " + bst.minimum_node());

        System.out.println(bst.delete(23));
        bst.inorder_traversal(bst.getRoot());

        System.out.println(bst.delete(30));
        bst.postorder_traversal(bst.getRoot());

        System.out.println(bst.delete(79));
        bst.preorder_traversal(bst.getRoot());
    }
}

class Binary_Search_Tree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    // Finding a node -> Avg. Case: O(logN)  Worst Case: O(N)
    public Node find(int data) {
        Node current = getRoot();

        while (data != current.data) {
            if (data < current.data) {
                current = current.left_child;
            } else {
                current = current.right_child;
            }
            if (current == null) {
                return null;
            }
        }

        return current;
    }

    // Insert a node -> Avg. Case: O(logN)  Worst Case: O(N) or O(h) where h is the height of the tree
    public void insert_node(int data) {
        Node newNode = new Node(data);

        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;

            while (true) {
                parent = current;
                if (data < current.data) {
                    current = current.left_child;

                    if (current == null) {
                        parent.left_child = newNode;
                        return;
                    }
                } else {
                    current = current.right_child;

                    if (current == null) {
                        parent.right_child = newNode;
                        return;
                    }
                }
            }
        }
    }

    public Node delete(int data) {
        Node current = getRoot();
        Node parent = getRoot();
        Node temp;
        boolean isLeftChild = true;     // w.r.t parent is current left_child or right_child ?

        while (current.data != data) {
            parent = current;
            if (data < current.data) {
                isLeftChild = true;
                current = current.left_child;
            } else {
                isLeftChild = false;
                current = current.right_child;
            }
            if (current == null) {
                return null;
            }
        }

        // Case 1: when deleted node is a leaf node (node with no children)
        if (current.left_child == null && current.right_child == null) {
            temp = current;
            if (current == root) {
                setRoot(null);
            }else if (isLeftChild) {
                parent.left_child = null;
            } else {
                parent.right_child = null;
            }
            return temp;
        }

        // Case 2: when deleted node has only one child (either left_child or right_child)
        else if (current.right_child == null) {
            temp = current;
            if (current == root) {
                root = current.left_child;
            } else if (isLeftChild) {
                parent.left_child = current.left_child;
            } else {
                parent.right_child = current.left_child;
            }
            return temp;
        } else if (current.left_child == null) {
            temp = current;
            if (current == root) {
                root = current.right_child;
                return temp;
            } else if (isLeftChild) {
                parent.left_child = current.right_child;
            } else {
                parent.right_child = current.right_child;
            }
            return temp;
        }

        // Case 3: when deleted node has both left and right children
        else {
            temp = current;
            Node successor = get_successor(current);
            if (current == root) {
                root = successor;

            } else if (isLeftChild) {
                parent.left_child = successor;

            } else {
                parent.right_child = successor;
                successor.right_child = current.right_child;

            }
            return temp;
        }
    }

    private Node get_successor(Node node) {
        Node parent = node;
        Node successor = node;
        Node current = node.right_child;

        while (current != null) {
            parent = successor;
            successor = current;
            current = current.left_child;
        }
        if (successor != node.right_child) {
            parent.left_child = successor.right_child;
            successor.right_child = node.right_child;
        }
        return successor;
    }

    public void inorder_traversal(Node current) {
        current = getRoot();
        if (current != null) {
            inorder_traversal(current.left_child);
            System.out.println("Node: " + current.data);
            inorder_traversal(current.right_child);
        }
    }

    public void postorder_traversal(Node current) {
        current = getRoot();
        if (current != null) {
            postorder_traversal(current.left_child);
            postorder_traversal(current.right_child);
            System.out.println("Node: " + current.data);
        }
    }

    public void preorder_traversal(Node current) {
        current = getRoot();
        if (current != null) {
            System.out.println("Node: " + current.data);
            preorder_traversal(current.left_child);
            preorder_traversal(current.right_child);
        }
    }

    public int maximum_node() {
        Node current = getRoot();
        while (current.right_child != null) {
            current = current.right_child;
        }
        return current.data;
    }

    public int minimum_node() {
        Node current = getRoot();
        while (current.left_child != null) {
            current = current.left_child;
        }
        return current.data;
    }

    @Override
    public String toString() {
        return "Binary_Tree{" +
                "root=" + root.toString() +
                "}";
    }
}

class Node {
    int data;
    Node left_child;
    Node right_child;

    public Node(int data) {
        this.data = data;
        this.left_child = null;
        this.right_child = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "\ndata=" + data +
                ", left=" + left_child +
                ", right=" + right_child +
                "}";
    }
}
