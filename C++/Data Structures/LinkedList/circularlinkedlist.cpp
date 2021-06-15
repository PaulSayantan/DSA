//
// @author: Sayantan Paul
//


#include <iostream>

// Node class
class Node {
public:
    int data;
    Node* next;

    // constructor
    Node(int info): data(info), next(NULL) {}

    // display Node
    void displayNode() {
        std::cout << data << " ";
    }
};


class CircularLinkedList {
private:
    Node* head;
    int size = 0;

public:
    // constructor
    CircularLinkedList(): head(NULL) {}


    // isEmpty() -> check if the linked list is empty or not
    bool isEmpty() {
        return size == 0;
    }


    // Add() -> add a node at the head of the linkedlist
    void Add(int data) {
        Node* node  = new Node(data);
        if (isEmpty()) {
            head = node;
        } else {
            if (size == 1) {
                head->next = node;
            } else {
                Node* curr = head;
                while (curr->next != head) {
                    curr = curr->next;
                }
                curr->next = node;
            }
            node->next = head;
            head = node;
        }
        size++;
    }


    // Add() -> add a node after a particular node of the linkedlist
    void Add(int newData, int data) {
        Node* newNode  = new Node(newData);
        
        if (isEmpty()) {
            head = newNode;
        } else {
            Node* currNode = head;
            do
            {
                if (currNode->data == data) {
                    newNode->next = currNode->next;
                    currNode->next = newNode;
                }
                currNode = currNode->next;
            } while (currNode != head);
        }

        size++;
    }


    // Delete() -> Delete a node from the linkedlist
    Node* Delete(int data) {
        Node* currNode = head;
        Node* nextNode = head->next;
        Node* temp = NULL;

        if (isEmpty()) {
            printf("No node to delete !!");
        } else {
            do {
                if (nextNode->data == data) {
                    temp = nextNode;

                    // check if node is head node
                    if (head == nextNode) {
                        head = nextNode->next;
                    }
                    currNode->next = nextNode->next;
                    delete nextNode;
                    size--;
                    break;
                }
                currNode = nextNode;
                nextNode = nextNode->next;
            }while (currNode != head);
        }
        if (temp == NULL) {
            std::cout << data << " not found !!" << std::endl;
        } else {
            std::cout << data << " deleted from circular linkedlist !!" << std::endl;
        }
        return temp;
    }


    // PrintList() -> Traverse the circular linkedlist and print data in the nodes
    void PrintList() {
        Node* currNode = head;
        
        std::cout << "\nCircular Linked List:" << std::endl;
        std::cout << "Number of nodes: " << size << std::endl;
        
        do {
            std::cout << "[ " << currNode->data << " ] --> ";
            currNode = currNode->next;
        } while (currNode != head);
        std::cout << "...." << std::endl;
        std::cout << std::endl;
    }
};

int main() {
    CircularLinkedList* cllist = new CircularLinkedList();

    cllist->Add(15);
    cllist->Add(25);
    cllist->Add(65);

    cllist->PrintList();

    cllist->Add(95, 65);
    cllist->PrintList();
    
    cllist->Add(85, 15);
    cllist->PrintList();

    cllist->Delete(45);
    cllist->Delete(15);
    cllist->PrintList();

    cllist->Delete(65);
    cllist->PrintList();

    return 0;
}