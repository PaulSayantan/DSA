package Data_Structures.Binary_Tree;

import java.util.LinkedList;
import java.util.Queue;

public class bin_tree {
    public static void main(String[] args) {
		Binary_tree b_tree = new Binary_tree();

		Node root = new Node(10);
        root.left = new Node(11);
        root.left.left = new Node(7);
        root.right = new Node(9);
        root.right.left = new Node(15);
        root.right.right = new Node(8);

		b_tree.set_root(root);
		b_tree.inorder_traversal(b_tree.get_root());
		System.out.println();

		// deletion of node 11 from Binary Tree
		b_tree.delete(root, 11);
		b_tree.inorder_traversal(b_tree.get_root());
		System.out.println();
		
		// insertion of node with value 12 in Binary Tree
		b_tree.insert(new Node(12));
		b_tree.inorder_traversal(b_tree.get_root());
		System.out.println();
		
	}
	
}

class Binary_tree {
	private Node root;

	public Binary_tree() {
		root = null;
	}
	
	public void set_root(Node root) {
		this.root = root;
	}

	public Node get_root() {
		return root;
	}

	// function to insert node
	public void insert(Node new_node) {
		Node curr = get_root();
		
		// check if node is empty
		if (curr == null) {
			return;
		}

		Queue<Node> node_q = new LinkedList<Node>();
		node_q.add(curr);

		while (!node_q.isEmpty()) {
			Node temp = node_q.remove();

			if (temp.left == null) {
				temp.left = new_node;
				break;
			} else {
				node_q.add(temp.left);
			}

			if (temp.right == null) {
				temp.right = new_node;
				break;
			} else {
				node_q.add(temp.right);
			}
		}
	}


	// inorder traversal of Binary Tree
	public void inorder_traversal(Node root) {
		if (root == null) {
			return;
		}
		inorder_traversal(root.left);
		System.out.print(root.data + " ");
		inorder_traversal(root.right);
	}

	// function to delete deepest rightmost node in Binary Tree
	public void delete_deepest(Node node, Node delNode) {
		Queue<Node> node_q = new LinkedList<Node>();

		node_q.add(node);

		Node temp = null;
		while (!node_q.isEmpty()) {
			temp = node_q.remove();

			if (temp == delNode) {
				temp = null;
				return;
			}

			if (temp.right != null) {
				if (temp.right == delNode) {
					temp.right = null;
					return;
				}
			} else {
				node_q.add(temp.right);
			}

			if (temp.left != null) {
				if (temp.left == delNode) {
					temp.left = null;
					return;
				}
			} else {
				node_q.add(temp.left);
			}
		}
	}
	
	
	// Deletion of Node in Binary Tree
	public void delete(Node node, int data) {
		if (node == null) {
			return;
		}

		if (node.left == null && node.right == null) {
			if (node.data == data) {
				node = null;
				return;
			} else {
				return;
			}
		}

		Queue<Node> node_q = new LinkedList<Node>();
		node_q.add(node);

		Node temp = null;
		Node key_node = null;

		while (!node_q.isEmpty()) {
			temp = node_q.remove();

			if (temp.data == data) {
				key_node = temp;
			}

			if (temp.left != null) {
				node_q.add(temp.left);
			}

			if (temp.right != null) {
				node_q.add(temp.right);
			}
		}

		if (key_node != null) {
			int del_n = temp.data;
			delete_deepest(get_root(), temp);
			key_node.data = del_n;
		}
	}
}


class Node {
	int data;
	Node left, right;
	
	public Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}	
}
