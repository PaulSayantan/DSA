// Doubly LinkedList Implementation in Golang

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

// DoublyLinkedList class
type DoublyLinkedList struct {
	head *Node
	tail *Node
}

// AddAfter method -> add a node after a node of doublylinkedlist
func (dllist *DoublyLinkedList) AddAfter(prevData, newData int) {
	var curr *Node
	var newNode = &Node{}
	newNode.data = newData
	newNode.next = nil

	for curr = dllist.head; curr != nil; curr = curr.next {
		if curr.data == prevData {
			newNode.next = curr.next
			newNode.previous = curr
			curr.next = newNode
		}
	}
}

// NodeBetweenValues method -> return a node present between two nodes of doublylinkedlist
func (dllist *DoublyLinkedList) NodeBetweenValues(prevData, afterData int) *Node {
	var curr *Node
	var node *Node
	// iterate through the linkedlist
	for curr = dllist.head; curr != nil; curr = curr.next {

		if curr.previous != nil && curr.next != nil {
			if curr.previous.data == prevData && curr.next.data == afterData {
				node = curr
				break
			}
		}
	}

	return node
}

// AddToHead method -> add a node to the head of doublylinkedlist
func (dllist *DoublyLinkedList) AddToHead(newData int) {
	var node = &Node{}
	node.data = newData
	node.next = nil

	if dllist.head != nil {
		node.next = dllist.head
		dllist.head.previous = node
	} else {
		dllist.tail = node
	}

	dllist.head = node
}

// AddToEnd method -> add a node to the end of doublylinkedlist
func (dllist *DoublyLinkedList) AddToEnd(newData int) {
	var newNode = &Node{}
	newNode.data = newData
	newNode.next = nil

	newNode.previous = dllist.tail
	dllist.tail.next = newNode
	dllist.tail = newNode
}

// DeleteFromHead method -> delete a node from the head of doublylinkedlist
func (dllist *DoublyLinkedList) DeleteFromHead() (*Node, error) {
	if dllist.head == nil {
		return nil, fmt.Errorf("DoublyLinkedList is empty")
	}

	var temp = dllist.head
	dllist.head = dllist.head.next
	return temp, nil
}

// DeleteFromEnd method -> delete a node from the end of doublylinkedlist
func (dllist *DoublyLinkedList) DeleteFromEnd() (*Node, error) {
	if dllist.head == nil {
		return nil, fmt.Errorf("DoublyLinkedList is empty")
	}

	var temp = dllist.tail

	//var prev = dllist.tail.previous
	//prev.next = nil
	dllist.tail.previous.next = nil

	return temp, nil
}

// DeleteAfter method -> delete a node present after a given node in doublylinkedlist
func (dllist *DoublyLinkedList) DeleteAfter(givenData int) (*Node, error) {
	if dllist.head == nil {
		return nil, fmt.Errorf("DoublyLinkedList is empty")
	}

	var temp *Node
	var prev = dllist.head
	var curr = dllist.head.next
	for curr != nil {
		if curr.data == givenData {
			temp = curr
			curr = curr.next
			prev.next = curr
			return temp, nil
		}
		prev = curr
		curr = curr.next
	}
	return nil, fmt.Errorf("Given node not found in DoublyLinkedList")
}

// IterateList method -> prints the doublylinkedlist
func (dllist *DoublyLinkedList) IterateList() {
	var curr *Node
	fmt.Println("\nDoublyLinkedList: ")
	for curr = dllist.head; curr != nil; curr = curr.next {
		fmt.Print("[ ", curr.data, " ] <-> ")
	}
	fmt.Print("NULL\n")
	fmt.Println()
}

func main() {
	var dllist DoublyLinkedList

	dllist.AddToHead(12)
	dllist.AddToEnd(15)
	dllist.AddToHead(62)
	dllist.AddToEnd(43)
	dllist.AddToHead(16)
	dllist.AddToEnd(84)
	dllist.AddAfter(12, 5)

	dllist.IterateList()

	node := dllist.NodeBetweenValues(12, 15) // 5
	fmt.Println("Node between 12 and 15: [", node.data, "]")

	del, err := dllist.DeleteFromHead()
	if err != nil {
		log.Fatal(err)
	} else {
		fmt.Println("Deleted Node (From Head): [", del.data, "]")
	}

	del, err = dllist.DeleteFromEnd()
	if err != nil {
		log.Fatal(err)
	} else {
		fmt.Println("Deleted Node (From Tail): [", del.data, "]")
	}

	dllist.IterateList()
}
