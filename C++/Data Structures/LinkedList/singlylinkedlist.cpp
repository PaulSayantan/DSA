//
// @author: Sayantan Paul
//

/*
 * Single LinkedList Implementation in C++
 */


#include <iostream>

// Node Class
class Node {
public:
    int data;       // data inside a node
    Node* next;     // pointer to next node

    // constructor
    Node(int info): data(info), next(NULL) {}

    // Display Node
    void displayNode() {
        std::cout << data << " ";
    }
};

// SinglyLinkedList Class
class SinglyLinkedList {
private:
    Node* head;             // head node of the SinglyLinkedList
    int size = 0;
public:
    // constructor define the head to be NULL
    SinglyLinkedList(): head(NULL) {}

    // destructor -> delete all the nodes of SinglyLinkedList after complete execution of program
    ~SinglyLinkedList() {
        Node* curr = head;
        while (curr != NULL) {
            Node* temp = curr;
            curr = curr->next;
            delete temp;
        }
    }

    // check if singlylinkedlist is empty
    bool isEmpty() {
        return head == NULL;
    }

    // AddToHead -> add node at the top of the singlylinkedlist
    void AddToHead(int data) {
        Node* newNode = new Node(data);
        newNode->next = head;
        head = newNode;
        size++;
    }

    // AddToEnd -> add node at the end of the singlylinkedlist
    void AddToEnd(int data) {
        Node* newNode = new Node(data);
        if (isEmpty()) {
            AddToHead(data);
        } else {
            Node* currNode = head;
            while (currNode->next != NULL) {
                currNode = currNode->next;
            }
            currNode->next = newNode;
            size++;
        }
    }

    // FindData -> Find position of the data in the singlylinkedlist
    int FindData(int data) {
        Node* currNode = head;
        int pos = 0;
        while (currNode != NULL) {
            if (currNode->data == data) {
                std::cout << currNode->data << " found at position " << pos+1 << std::endl;
                return pos;
            }
            currNode = currNode->next;
            pos++;
        }

        std::cout << "[Error]:" << data << " not present" << std::endl;
        return -1;
    }

    // DeleteFromHead -> Delete Node at the top of the singlylinkedlist
    Node* DeleteFromHead() {
        if (head == NULL) {
            std::cout << "[Error]: Linkedlist is empty" << std::endl;
        } else {
            Node* temp = head;
            head = head->next;
            size--;
            return temp;
        }
    }

    // DeleteFromEnd -> Delete Node at the end of the singlylinkedlist
    Node* DeleteFromEnd() {
        if (head == NULL) {
            std::cout << "[Error]: Linkedlist is empty" << std::endl;
        } else {
            Node* prev = head;
            Node* curr = head->next;
            while (curr->next != NULL) {
                prev = curr;
                curr = curr->next;
            }
            prev->next = NULL;
            size--;
            return curr;
        }
    }

    // DeleteData -> Find a data and delete the node
    void DeleteData(int data) {
        Node* prev = head;
        Node* curr = head->next;
        if (prev->data == data) {
            DeleteFromHead();
        } else {
            while (curr != NULL) {
                if (curr->data == data) {
                    curr = curr->next;
                    prev->next = curr;
                    break;
                }
                prev = curr;
                curr = curr->next;
            }
            if (curr == NULL) {
                std::cout << "Data Not Found in SinglyLinkedList" << std::endl;
            }
        }

    }

    // PrintList -> Print out the items in singlylinkedlist
    void PrintList() {
        Node* currNode = head;
        std::cout << "\nLinked List: \n----------------" << std::endl;
        while (currNode != NULL) {
            std::cout << "[ " << currNode->data << " ] --> ";
            currNode = currNode->next;
        }
        std::cout << "NULL\n" << std::endl;
    }

};


int main() {
    SinglyLinkedList* sllist = new SinglyLinkedList();

    sllist->AddToHead(12);
    sllist->AddToEnd(15);
    sllist->PrintList();

    sllist->AddToHead(17);
    sllist->AddToEnd(19);
    sllist->AddToHead(32);
    sllist->AddToEnd(95);
    sllist->PrintList();

    sllist->FindData(32);
    sllist->FindData(17);

    int data = 15;
    sllist->DeleteData(data);
    std::cout << "Deleted Node: " << data << std::endl;
    sllist->PrintList();

    std::cout << "Deleted Node: " << sllist->DeleteFromHead()->data << std::endl;
    std::cout << "Deleted Node: " << sllist->DeleteFromEnd()->data << std::endl;
    sllist->PrintList();


    sllist->FindData(15);
    sllist->FindData(32);

    return 0;
}