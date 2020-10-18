#include<stdio.h>

/*
 *  Implementation Of Insertion Sort Algorithm in C
 */

void insertionSort(int array[], int size) {
    for (int j = 1; j < size; j++) {
        int key = array[j];
        int i = j - 1;

        while (i >= 0 && array[i] > key) {
            array[i + 1] = array[i];
            i = i - 1;
        }
        array[i + 1] = key;
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

    printf("Performing Insertion Sort .......");
    insertionSort(array, size);

    printf("\nSorted array is: ");
    for (int i = 0; i < size; i++) {
        printf("%d ", array[i]);
    }
    printf("\n");

    return 0;
}
