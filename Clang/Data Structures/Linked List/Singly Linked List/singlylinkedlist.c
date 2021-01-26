#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

/*
 * Singly LinkedList Implementation in C
*/

struct Node {
    int data;
    struct Node *next;
};

struct Node *headNode = NULL;
int size_count = 0;             // counter for size of LinkedList


// ListEmpty -> check if LinkedList is empty
bool ListEmpty() {
    return headNode == NULL;
}

// AddTop -> add node to the top of the linkedlist
void AddTop(int value) {
    struct Node *node = (struct Node*)malloc(sizeof(struct Node));
    node->data = value;

    if (ListEmpty()) {
        headNode = node;
        size_count++;
        node->next = NULL;
        return;
    }

    node->next = headNode;
    headNode = node;
}

// AddEnd -> add node at the end of the linkedlist
void AddEnd(int value) {
    struct Node *node = (struct Node*)malloc(sizeof(struct Node));
    node->data = value;

    if (ListEmpty()) {
        headNode = node;
        size_count++;
        node->next = headNode;
        return;
    }

    struct Node *ptr = headNode;
    while (ptr->next != NULL) {
        ptr = ptr->next;
    }

    ptr->next = node;
    node->next = NULL;
    size_count++;
}

// AddAfter -> add node after a specific node in linkedlist
void AddAfter(int prevValue, int nextValue) {
    struct Node *node = (struct Node*)malloc(sizeof(struct Node));
    node->data = nextValue;

    if (ListEmpty()) {
        headNode = node;
        size_count++;
        node->next = headNode;
        return;
    }


    struct Node *prevptr = headNode;
    struct Node *nextptr = prevptr;
    while (prevptr->data != prevValue) {
        prevptr = nextptr;
        nextptr = nextptr->next;
    }


    // if prevValue not present in the linkedlist -> call AddEnd() method
    printf("Provided Value not present in linkedlist\nAdding value at End...\n");
    if (prevptr->next == NULL) {
        AddEnd(nextValue);
    }

    prevptr->next = node;
    node->next = nextptr;
    size_count++;
}


// PrintList -> Print SinglyLinkedList
void PrintList() {

    if (ListEmpty()) {
        printf("LinkedList in Empty !!\n");
        return;
    }

    struct Node *ptr = headNode;
    while (ptr != NULL) {
        printf("[ %d ] -> ", ptr->data);

        ptr = ptr->next;
    }
    printf("null\n\n");
}

int main() {
    int choice, data, prevdata;

    while (true) {
        printf("--------------------------------\n");
        printf("[1] Add at top of linkedlist\n");
        printf("[2] Add at end of linkedlist\n");
        printf("[3] Add a value after another value of linkedlist\n");
        printf("[4] Print linkedlist\n");
        printf("--------------------------------\n");

        printf("Enter Choice [1-4]: ");
        scanf("%d", &choice);
        printf("\n\n");
        switch (choice) {
            case 1:
                printf("Enter Integer Data: ");
                scanf("%d", &data);
                AddTop(data);
                break;
            
            case 2:
                printf("Enter Integer Data: ");
                scanf("%d", &data);
                AddEnd(data);
                break;

            case 3:
                
                printf("Enter Integer Data present in LinkedList: ");
                scanf("%d", &prevdata);
                printf("Enter New Integer Data to be added in LinkedList: ");
                scanf("%d", &data);
                AddAfter(prevdata, data);
                break;

            case 4:
                PrintList();
                break;

            default:
                printf("Invalid input: ");
                PrintList();
                break;
        }
    }
}