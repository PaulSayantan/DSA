"""
Implementation of Binary Search Tree in Python
"""

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
    
    # print the BST
    def printTree(self):
        if self.root:
            self.printNode(self.root)
        else:
            print("Tree is empty")
    
    # print the BST as inorder traversal
    def printNode(self, node):
        if node.left:
            self.printNode(node.left)
        print(node.data)
        if node.right:
            self.printNode(node.right)
    
    # print the BST as preorder traversal
    def printPreorder(self):
        if self.root:
            self.printPreorderNode(self.root)
        else:
            print("Tree is empty")
    
    # print the BST as preorder traversal
    def printPreorderNode(self, node):
        print(node.data)
        if node.left:
            self.printPreorderNode(node.left)
        if node.right:
            self.printPreorderNode(node.right)
    
    # print the BST as postorder traversal
    def printPostorder(self):
        if self.root:
            self.printPostorderNode(self.root)
        else:
            print("Tree is empty")
    
    # print the BST as postorder traversal
    def printPostorderNode(self, node):
        if node.left:
            self.printPostorderNode(node.left)
        if node.right:
            self.printPostorderNode(node.right)
        print(node.data)
    
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
    def delete(self, data):
        if self.size > 1:
            self.deleteNode(data, self.root)
            self.size -= 1
        else:
            if self.root:
                self.root = None
            else:
                self.size = 0
    
    # delete a node from the BST
    def deleteNode(self, data, node):
        if data < node.data:
            if node.left:
                self.deleteNode(data, node.left)
            else:
                print("Element not found")
        elif data > node.data:
            if node.right:
                self.deleteNode(data, node.right)
            else:
                print("Element not found")
        else:
            if node.left and node.right:
                self.delMin(node.right)
                node.data = node.right.data
                node.right = self.delMin(node.right)
            elif node.left:
                node = node.left
            elif node.right:
                node = node.right
            else:
                node = None
        return node

    # delete the smallest node in the BST
    def delMin(self, node):
        if node.left:
            node.left = self.delMin(node.left)
        return node
    
    # delete the largest node in the BST
    def delMax(self, node):
        if node.right:
            node.right = self.delMax(node.right)
        return node


class Node:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None
    
    def __str__(self):
        return str(self.data)
    
    def __repr__(self):
        return str(self.data)

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
    
    bst.printTree()