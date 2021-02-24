package main

import (
	"fmt"
	"log"
)

// Node class
type Node struct {
	data     int
	next     *Node
	previous *Node // used in DoublyLinkedList in doublylinkedlist.go
}

// LinkedList class
type LinkedList struct {
	head *Node
}

// AddToHead method -> adds node to the start of the LinkedList
func (llist *LinkedList) AddToHead(data int) {
	var node = Node{}
	node.data = data

	if llist.head != nil { // if linkedlist is not empty
		node.next = llist.head // new node points to head of linked list and becomes new head
	}

	llist.head = &node // head pointer points to the new node
}

// IterateList method -> iterates over the LinkedList
func (llist *LinkedList) IterateList() {
	var node *Node
	for node = llist.head; node != nil; node = node.next {
		_, _ = fmt.Print(node.data, " --> ")
	}
	fmt.Printf(" NULL\n")
}

// LastNode method -> returns the node at the end of the LinkedList
func (llist *LinkedList) LastNode() *Node {
	var node, lastNode *Node
	for node = llist.head; node != nil; node = node.next {
		if node.next == nil {
			lastNode = node
		}
	}

	return lastNode
}

// AddToEnd method -> adds node to the end of the LinkedList
func (llist *LinkedList) AddToEnd(data int) {
	var node = Node{}
	node.data = data

	if llist.head == nil {
		llist.head = &node
	} else {
		var ptrNode *Node

		for ptrNode = llist.head; ptrNode != nil; ptrNode = ptrNode.next {
			if ptrNode.next == nil {
				ptrNode.next = &node
				break
			}
		}
	}

	node.next = nil
}

// NodeValue method -> returns node with the given value
func (llist *LinkedList) NodeValue(value int) (*Node, error) {
	if llist.head == nil {
		return nil, fmt.Errorf("LinkedList is empty")
	}
	var ptrNode, nodeWithValue *Node
	for ptrNode = llist.head; ptrNode != nil; ptrNode = ptrNode.next {
		if ptrNode.data == value {
			nodeWithValue = ptrNode
			break
		}
	}
	if ptrNode == nil {
		return nil, fmt.Errorf("value not found")
	}

	return nodeWithValue, nil
}

// AddAfter method -> add a new node after a specific node in LinkedList
func (llist *LinkedList) AddAfter(data int, newData int) error {
	if llist.head == nil {
		return fmt.Errorf("LinkedList is empty")
	}
	var ptrNode *Node
	var newNode = Node{data: newData}
	for ptrNode = llist.head; ptrNode != nil; ptrNode = ptrNode.next {
		if ptrNode.data == data {
			newNode.next = ptrNode.next
			ptrNode.next = &newNode
			break
		}
	}
	return nil
}

// DeleteFromHead method -> deletes the head node of the LinkedList
func (llist *LinkedList) DeleteFromHead() (*Node, error) {
	if llist.head == nil {
		return nil, fmt.Errorf("LinkedList is empty")
	}
	var temp = llist.head
	llist.head = llist.head.next
	return temp, nil
}

// DeleteFromEnd method -> deletes the end node of the LinkedList
func (llist *LinkedList) DeleteFromEnd() (*Node, error) {
	if llist.head == nil {
		return nil, fmt.Errorf("LinkedList is empty")
	}
	var prev = llist.head
	var curr = llist.head.next
	for curr.next != nil {
		prev = curr
		curr = curr.next
	}
	prev.next = nil

	return curr, nil

}

// DeleteAfter method -> deletes a node present after the given node
func (llist *LinkedList) DeleteAfter(data int) (*Node, error) {
	if llist.head == nil {
		return nil, fmt.Errorf("LinkedList is empty")
	}

	var temp *Node
	var prev = llist.head
	var curr = llist.head.next
	for curr != nil {
		if curr.data == data {
			temp = curr
			curr = curr.next
			prev.next = curr
			return temp, nil
		}
		prev = curr
		curr = curr.next
	}
	return nil, fmt.Errorf("no node present as %d", data)
}

func main() {
	var llist LinkedList

	llist.AddToHead(3)
	llist.AddToHead(2)
	llist.AddToHead(8)
	llist.AddToHead(6)
	llist.AddToHead(7)
	llist.AddToHead(1)
	llist.IterateList()

	fmt.Println("\nCurrent Head: ", llist.head.data)
	llist.AddToEnd(6)

	err := llist.AddAfter(2, 9)
	if err != nil {
		log.Println(err)
	}
	llist.IterateList()

	del, err := llist.DeleteFromEnd()
	if err != nil {
		log.Println(err)
	} else {
		fmt.Println("\nDeleted From End: ", del.data)
	}

	del, err = llist.DeleteFromHead()
	if err != nil {
		log.Println(err)
	} else {
		fmt.Println("\nDeleted From Head: ", del.data)
	}
	llist.IterateList()
}
