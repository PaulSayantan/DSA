//
// @author: Sayantan Paul
//

/*
 * Stack Implementation in C++
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

    Node(int info): data(info), next(NULL) {}
};

class Stack {
protected:
    Node* head;
    int size = 0;
public:
    Stack(): head(NULL) {}

    ~Stack() {
        Node* curr = head;
        while (curr != NULL) {
            Node* temp = curr;
            curr = curr->next;
            delete temp;
        }
    }

    bool isEmpty() {
        return head == NULL;
    }

    // push -> insert a new data at the top of the stack
    void push(int data) {
        Node* newNode = new Node(data);
        newNode->next = head;
        head = newNode;
        size++;
    }

    // pop -> remove a new data from the top of the stack
    Node* pop() {
        Node* pop = head;
        head = head->next;
        size--;
        return pop;
    }

    // displayStack -> print the stack
    void displayStack() {
        Node* curr = head;
        std::cout << "\nStack: " << std::endl;
        while (curr != NULL) {
            std::cout << "[ " << curr->data << " ]" << std::endl;
            curr = curr->next;
        }
        std::cout << "\tSize: " << size << std::endl << std::endl;
    }

};


int main() {
    Stack myStack;
    myStack.push(45);
    myStack.push(35);
    myStack.push(25);
    myStack.push(15);
    myStack.displayStack();

    std::cout << "Popped : " << myStack.pop()->data << std::endl;
    myStack.displayStack();

    return 0;
}