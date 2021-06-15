#include<stdio.h>
#include<stdlib.h>

/*
 * Implementation of Merge Sort Algorithm in C
 */

void Merge(int array[], int front, int middle, int rear) {
    // dividing array into two parts with lengths n1 and n2
    int n1 = middle - front + 1;
    int n2 = rear - middle;

    // Create two empty arrays as L and R
    int L[n1], R[n2];

    //fill-up the arrays L and R with elements of array
    for (int i = 0; i < n1; i++) {
        L[i] = array[front + i];
    }
    for (int j = 0; j < n2; j++) {
        R[j] = array[middle + j + 1];
    }

    int i = 0, j = 0, k = front;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            array[k] = L[i];
            i++;
        } else {
            array[k] = R[j];
            j++;
        }
        k++;
    }

    // copy the remaining elements of L to array
    while (i < n1) {
        array[k] = L[i];
        i++;
        k++;
    }

    // copy the remaining elements of R to array
    while (j < n2) {
        array[k] = R[j];
        j++;
        k++;
    }
}

void MergeSort(int array[], int front, int rear) {
    if (front < rear) {
        int middle = (int)((front + rear) / 2);

        MergeSort(array, front, middle);
        MergeSort(array, middle+1, rear);
        Merge(array, front, middle, rear);
    }
}

int main() {
    int size;
    printf("Enter the size of the unsorted array: ");
    scanf("%d", &size);

    int array[size];
    printf("Enter the array elements: ");
    for (int i = 0; i < size; i++) {
        scanf("%d", &array[i]);
    }

    printf("Performing Merge Sort.....\n");
    MergeSort(array, 0, size-1);

    printf("Sorted Array is: ");
    for (int i = 0; i < size; i++) {
        printf("%d ", array[i]);
    }
    printf("\n");

    return 0;
}
