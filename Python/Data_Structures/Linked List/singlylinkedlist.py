# SinglyLinkedList Implementation in Python


class Node:
    def __init__(self, data):
        self.data = data
        self.next = None


class SinglyLinkedList:
    def __init__(self):
        self.head = None
        self.size = 0

    def printList(self):
        """
            Printlist Function to Print Nodes in LinkedList manner
        """
        if self.size == 0:
            print("Linked List is Empty !!")
            return

        ptr = self.head
        while ptr:
            print("[ ", ptr.data, " ] -> ", end=" ")
            ptr = ptr.next
        print("NULL")
        print("Length: ", self.size)

    def __len__(self):
        return self.size

    def append_at_top(self, value: int):
        node = Node(value)

        if self.head is None:
            self.head = node
            self.size += 1
            return

        node.next = self.head
        self.head = node
        self.size += 1

    def append_at_bottom(self, value: int):
        node = Node(value)
        ptr = self.head

        if self.size == 0:
            self.head = node
            self.size += 1
            return

        while ptr.next:
            ptr = ptr.next

        ptr.next = node
        self.size += 1

    def append_after(self, previous: int, new: int):
        new_node = Node(new)

        if self.size == 0:
            self.head = new_node
            self.size += 1
            return

        prev_ptr = self.head
        next_ptr = prev_ptr.next
        while prev_ptr.data != previous:
            prev_ptr = next_ptr
            next_ptr = next_ptr.next

        prev_ptr.next = new_node
        new_node.next = next_ptr
        self.size += 1

    def delete_at_top(self):
        if not len(self):
            print("LinkedList is empty !!")
            return

        node = self.head
        self.head = self.head.next
        self.size -= 1

        print("Deleted: ", node.data)

    def reverse_iterative(self):
        prev_ptr = None
        curr_ptr = self.head

        while curr_ptr:
            next_ptr = curr_ptr.next
            curr_ptr.next = prev_ptr
            prev_ptr = curr_ptr
            curr_ptr = next_ptr

        self.head = prev_ptr

    def reverse_recursive(self, prev_ptr, curr_ptr):
        if curr_ptr is None:
            self.head = prev_ptr
            return
        next_ptr = curr_ptr.next
        curr_ptr.next = prev_ptr
        return self.reverse_recursive(curr_ptr, next_ptr)


if __name__ == '__main__':
    sll = SinglyLinkedList()
    sll.append_at_top(12)
    sll.append_at_bottom(45)
    sll.printList()
    sll.append_after(12, 64)
    sll.printList()
    sll.reverse_iterative()
    sll.printList()
    sll.reverse_recursive(None, sll.head)
    sll.printList()
    sll.delete_at_top()
    sll.printList()
