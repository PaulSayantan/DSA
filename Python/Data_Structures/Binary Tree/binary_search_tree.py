"""
Implementation of Binary Search Tree in Python
"""


class Node:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None

    def __str__(self):
        return str(self.data)

    def __repr__(self):
        return str(self.data)


class BinarySearchTree:
    # initialize the BST
    def __init__(self):
        self.root = None
        self.size = 0

    # insert a new node into the BST
    def insert(self, data):
        if not self.root:
            self.root = Node(data)
        else:
            self.insertNode(data, self.root)
        self.size += 1

    # insert a new node into the BST
    def insertNode(self, data, node):
        if data < node.data:
            if node.left:
                self.insertNode(data, node.left)
            else:
                node.left = Node(data)
        else:
            if node.right:
                self.insertNode(data, node.right)
            else:
                node.right = Node(data)

    # search for a node in the BST
    def search(self, data):
        if self.root:
            return self.searchNode(data, self.root)
        else:
            return False

    # search for a node in the BST
    def searchNode(self, data, node):
        if data == node.data:
            return True
        elif data < node.data and node.left:
            return self.searchNode(data, node.left)
        elif data > node.data and node.right:
            return self.searchNode(data, node.right)
        else:
            return False

    # print the BST as inorder traversal
    def inorder(self, node):
        if node:
            self.inorder(node.left)
            print(node.data, end=' ')
            self.inorder(node.right)

    # print the BST as preorder traversal
    def preorder(self, node):
        if node:
            print(node.data, end=' ')
            self.preorder(node.left)
            self.preorder(node.right)

    # print the BST as postorder traversal
    def postorder(self, node):
        if node:
            self.postorder(node.left)
            self.postorder(node.right)
            print(node.data, end=' ')

    # print the BST as levelorder traversal
    def printLevelorder(self):
        if self.root:
            self.printLevelorderNode(self.root)
        else:
            print("Tree is empty")

    # print the BST as levelorder traversal
    def printLevelorderNode(self, node):
        queue = [node]
        while queue:
            node = queue.pop(0)
            print(node.data)
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)

    # delete a node from the BST
    def delete(self, node: Node, data: int) -> Node:
        if node is None:
            return node

        # reach the position where the node to be deleted is present
        if data < node.data:
            node.left = self.delete(node.left, data)
        elif data > node.data:
            node.right = self.delete(node.right, data)
        else:
            # case 1: node has no child - a leaf node
            if node.left is None and node.right is None:
                return None

            # case 3: node has both left and right child
            elif node.left and node.right:
                # get the successor of the node to be deleted
                successor = self.get_successor(node)
                val = successor.data
                # delete the successor node
                self.delete(self.root, val)
                # set the value of the node as that of successor
                node.data = val

            else:
                # node has either left or right child
                child = node.left if node.left else node.right
                node = child
        return node

    def get_successor(self, node: Node) -> Node:
        return self.minimum_node(node.right)

    def minimum_node(self, node: Node) -> Node:
        while node.left:
            node = self.minimum_node(node.left)
        return node


if __name__ == "__main__":
    bst = BinarySearchTree()
    bst.insert(10)
    bst.insert(5)
    bst.insert(15)
    bst.insert(6)
    bst.insert(3)
    bst.insert(8)
    bst.insert(13)
    bst.insert(20)
    bst.insert(1)
    bst.insert(4)
    bst.insert(7)
    bst.insert(9)
    bst.insert(11)

    bst.inorder(bst.root)
    print()

    bst.delete(bst.root, 3)

    bst.inorder(bst.root)
    print()
