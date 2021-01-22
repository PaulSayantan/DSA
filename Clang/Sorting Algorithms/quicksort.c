#include<stdio.h>

/*
 * Implementation of Quick Sort Algorithm in C
 */

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}


int partition(int arr[], int low, int high) {
    //Element to be placed at the rightmost position
    int pivot = arr[high];

    // index of smaller element
    int i = low - 1;

    for (int j = low; j < high; j++) {
        // if current element is smaller than pivot -> increment the index of smaller element
        if (arr[j] < pivot) {
            i++;
            // swap in between smaller element and current element 
            swap(&arr[i], &arr[j]);
        }
    }

    swap(&arr[i+1], &arr[high]);
    return i + 1;
}


void quickSort(int arr[], int low, int high) {
    if (low < high) {
        // retrieve the pivot element from the array and do partition
        int pivot_pos = partition(arr, low, high);

        // recursive sort left-part & right-part
        quickSort(arr, low, pivot_pos - 1);
        quickSort(arr, pivot_pos + 1, high);
    }
}


int main() {
    int size;
    printf("Enter an array size: ");
    scanf("%d", &size);

    int array[size];
    printf("Enter the array elements: ");
    for (int i = 0; i < size; i++) {
        scanf("%d", &array[i]);
    }

    printf("Performing Quick Sort ......");
    quickSort(array, 0, size - 1);

    printf("Sorted Array: ");
    for (int i = 0; i < size; i++) {
        printf("%d ", array[i]);
    }
    printf("\n");
}
