//
// @author: Sayantan Paul
//

// Implementation of Insertion Sort in C++


#include <iostream>

/**
 * Insertion Sort Algorithm
 *
 * T(n):
        Best Case:  Ω(n)
        Avg. Case:  Θ(n^2)
        Worst Case: O(n^2)
    S(n):   O(1)    Best for cases when we are short on memory space.
 *
 * @param arr : Unsorted Array of integers
 * @return arr : Sorted Array of integers (In-Place)
 */


void insertionSort(int[], int);


int main() {
    int size;
    std::cout << "Enter size of the array: ";
    std::cin >> size;

    int arr[size];

    std::cout << "Enter array elements (Un-sorted Order): " << std::endl;
    for (int i = 0; i < size; i++) {
        std::cin >> arr[i];
    }

    insertionSort(arr, size);

    std::cout << "Sorted Array: " << std::endl;
    for (int k = 0; k < size; k++) {
        std::cout << arr[k] << " ";
    }
    std::cout << std::endl;

    return 0;
}


void insertionSort(int arr[], int size) {
    int i, key;
    int c = 1;
    for (int j = 1; j < size; j++) {
        key = arr[j];
        i = j - 1;

        while (i >= 0 && arr[i] > key) {
            arr[i + 1] = arr[i];
            i--;
        }

        arr[i + 1] = key;
    }
}