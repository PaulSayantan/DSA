package Data_Structures.Binary_Search_Tree;

public class bin_search_tree {
    public static void main(String[] args) {
        Binary_Search_Tree bst = new Binary_Search_Tree();

        bst.insert_node(53);
        bst.insert_node(30);
        bst.insert_node(72);
        bst.insert_node(14);
        bst.insert_node(84);
        bst.insert_node(39);
        bst.insert_node(34);
        bst.insert_node(9);
        bst.insert_node(23);
        bst.insert_node(47);
        bst.insert_node(61);
        bst.insert_node(79);

        bst.inorder_traversal(bst.getRoot());
        System.out.println();

        System.out.println("Maximum: " + bst.maximum_node(bst.getRoot()).data);
        System.out.println("Minimum: " + bst.minimum_node(bst.getRoot()).data);

        System.out.println(bst.delete(23));
        bst.inorder_traversal(bst.getRoot());
        System.out.println();

        System.out.println(bst.delete(30));
        bst.postorder_traversal(bst.getRoot());
        System.out.println();

        System.out.println(bst.delete(79));
        bst.preorder_traversal(bst.getRoot());
        System.out.println();
    }


    static class Binary_Search_Tree {
        private Node root;

        public void setRoot(Node root) {
            this.root = root;
        }

        public Node getRoot() {
            return root;
        }

        // Finding a node -> Avg. Case: O(logN) Worst Case: O(N)
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

        // Insert a node -> Avg. Case: O(logN) Worst Case: O(N) or O(h) where h is the height of the tree
        public void insert_node(int data) {

            if (root == null) {
                root = new Node(data);
            } else {
                Node current = root;
                Node parent;

                while (true) {
                    parent = current;
                    if (data < current.data) {
                        current = current.left_child;

                        if (current == null) {
                            parent.left_child = new Node(data);
                            return;
                        }
                    } else {
                        current = current.right_child;

                        if (current == null) {
                            parent.right_child = new Node(data);
                            return;
                        }
                    }
                }
            }
        }

        // Delete a Node -> Avg. Case: O(logN) Worst Case: O(N) or O(h) where h is the height of the tree
        public Node delete(int data) {
            Node current = getRoot();
            Node parent = getRoot();
            Node temp;
            boolean isLeftChild = true; // w.r.t parent is current left_child or right_child ?

            // reach the position where the data we want to delete is located
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
                System.out.println("Deleted node is a leaf node");
                temp = current;
                if (current == root) {
                    setRoot(null);
                } else if (isLeftChild) {
                    parent.left_child = null;
                } else {
                    parent.right_child = null;
                }
                return temp;
            }

            // Case 2: when deleted node has only one child (either left_child or right_child)
            
            // only left_child is present
            else if (current.right_child == null) {
                System.out.println("Deleted node has only left child");
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
                System.out.println("Deleted node has only right child");
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
                System.out.println("Deleted node has both left and right children");
                temp = current;
                Node successor = get_successor(current);
                System.out.println("Successor is: " + successor.data);
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

        // Successor of a Node -> find the smallest node in the right subtree of the node
        private Node get_successor(Node node) { 
            return minimum_node(node.right_child);
        }

        //Predecessor of a Node -> find the largest node in the left subtree of the node
        private Node get_predecessor(Node node) {
            return maximum_node(node.left_child);
        }

        public void inorder_traversal(Node current) {
            if (current != null) {
                inorder_traversal(current.left_child);
                System.out.print(current.data + " ");
                inorder_traversal(current.right_child);
            }
        }

        public void postorder_traversal(Node current) {
            if (current != null) {
                postorder_traversal(current.left_child);
                postorder_traversal(current.right_child);
                System.out.print(current.data + " ");
            }
        }

        public void preorder_traversal(Node current) {
            if (current != null) {
                System.out.print(current.data + " ");
                preorder_traversal(current.left_child);
                preorder_traversal(current.right_child);
            }
        }

        public Node maximum_node(Node node) {
            while (node.right_child != null) {
                node = node.right_child;
            }
            return node;
        }

        public Node minimum_node(Node node) {
            while (node.left_child != null) {
                node = node.left_child;
            }
            return node;
        }

        @Override
        public String toString() {
            return "Binary_Tree{" + "root=" + root.toString() + "}";
        }
    }

    static class Node {
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
            return "Node:{" + "data=" + data + "}";
        }
    }

}