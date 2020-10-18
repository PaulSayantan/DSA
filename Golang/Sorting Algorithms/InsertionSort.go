package main

import "fmt"

func main() {
	var size int
	fmt.Print("Enter the size of the unsorted array: ")
	_, _ = fmt.Scanf("%d", &size)

	intArray := make([]int, size)

	fmt.Println("Enter the array elements:")
	for i := 0; i < size; i++ {
		_, _ = fmt.Scanf("%d", &intArray[i])
	}

	InsertionSort(intArray, size)

	fmt.Print("Sorted Array is: ")
	fmt.Println(intArray)
}

// Insertion Sort Algorithm with T(n) = O(n^2) in worst case
func InsertionSort(arr []int, size int) {
	for j := 1; j < size; j++ {
		key := arr[j]
		i := j - 1

		for i >= 0 && arr[i] > key {
			arr[i + 1] = arr[i]
			i = i - 1
		}
		arr[i + 1] = key
	}
}
