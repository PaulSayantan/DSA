class Node:
    def __init__(self, data):
        self.data = data
        self.leftchild = None
        self.rightchild = None

    def __lt__(self, other):
        return self.data < other.data

    def __repr__(self):
        return 'Node (data={}, leftchild={}, rightchild={})'.format(self.data, self.leftchild, self.rightchild)


class NodeNotFoundError(Exception):
    pass


class EmptyTreeFoundError(Exception):
    pass


class BinaryTree:
    def __init__(self):
        self.root = None

    def get_root(self):
        return self.root

    def delete_tree(self):
        self.root = None

    def is_empty(self):
        return self.root is None

    def print_tree(self):
        if self.is_empty():
            return EmptyTreeFoundError("No Tree exists")
        if self.root is not None:
            self._PrintTree(self.root)

    def _PrintTree(self, node):
        if node is not None:
            self._PrintTree(node.leftchild)
            print(str(node.data), ' ')
            self._PrintTree(node.rightchild)

    def find_node(self, data):
        if self.is_empty():
            return EmptyTreeFoundError("No Tree exists")
        else:
            return self._find(data, self.root)

    def _find(self, data, node):
        if data == node.data:
            return node.data
        elif data < node.data:
            if node.leftchild is not None:
                self._find(data, node.leftchild)
            else:
                return NodeNotFoundError("Given Node not present in tree")
        elif data > node.data:
            if node.rightchild is not None:
                self._find(data, node.rightchild)
            else:
                return NodeNotFoundError("Given Node not present in tree")

    def add_node(self, data):
        if self.root is None:
            self.root = Node(data)
        else:
            self._add(data, self.root)

    def _add(self, data, node):
        if data < node.data:
            if node.leftchild is not None:
                self._add(data, node.leftchild)
            else:
                node.leftchild = Node(data)
        else:
            if node.rightchild is not None:
                self._add(data, node.rightchild)
            else:
                node.rightchild = Node(data)


if __name__ == '__main__':
    b_tree = BinaryTree()

    n1 = Node(3)
    n2 = Node(4)
    n3 = Node(0)
    n4 = Node(8)
    n5 = Node(2)

    b_tree.add_node(n1)
    b_tree.add_node(n2)
    b_tree.add_node(n3)
    b_tree.add_node(n4)
    b_tree.add_node(n5)

    b_tree.print_tree()
    print(b_tree.get_root())



