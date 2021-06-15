//
// @author: Sayantan Paul
//

/*
 * Queue Implementation in C++
 */

#include <iostream>
#include <sstream>
#include <string>
#include <ctime>
#include <vector>
#include <climits>
#include <numeric>
#include <cmath>
#include <algorithm>

class Node {
public:
    int data;
    Node* next;

    Node(int data) : data(data), next(NULL) {}
};

class Queue {
protected:
    Node* head;
    Node* tail;
    int size = 0;
public:
    Queue(): head(NULL), tail(NULL) {}

    ~Queue() {
        Node* curr = head;
        while (curr != NULL) {
            Node* temp = curr;
            curr = curr->next;
            size--;
            delete temp;
        }
    }

    bool isEmpty() {
        return head == NULL && tail == NULL;
    }

    void enqueue(int data) {
        Node* newNode = new Node(data);
        if (isEmpty()) {
            tail = newNode;
        }
        newNode->next = head;
        head = newNode;
        size++;
    }

    Node* deque() {
        if (isEmpty()) {
            std::cout << "Queue is Empty." << std::endl;
            return NULL;
        } else {
            Node* temp;
            Node* prev = head;
            Node* curr = head->next;
            while (curr->next != NULL) {
                prev = curr;
                curr = curr->next;
            }
            temp = curr;
            prev->next = NULL;
            delete curr;
            size--;
            return temp;
        }
    }

    // displayQueue -> print the queue
    void displayQueue() {
        Node* curr = head;
        std::cout << "\nQueue:" << std::endl;
        while (curr != NULL) {
            std::cout << "[ " << curr->data << " ]" << std::endl;
            curr = curr->next;
        }
        std::cout << "\tSize:" << size << std::endl;
        std::cout << std::endl;
    }

};

int main() {
    Queue queue;
    queue.enqueue(12);
    queue.enqueue(24);
    queue.enqueue(36);
    queue.enqueue(48);
    queue.enqueue(64);
    queue.displayQueue();

    queue.deque();
    queue.displayQueue();

    return 0;
}