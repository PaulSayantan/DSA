package main

import "fmt"

/*
Implementation of Merge Sort Algorithm in Golang
Divide and Conquer strategy of sorting
*/

func main() {
	var size int
	fmt.Print("Enter the size of the unsorted array: ")
	_, _ = fmt.Scanf("%d", &size)

	intArray := make([]int, size)

	fmt.Println("Enter the array elements:")
	for i := 0; i < size; i++ {
		_, _ = fmt.Scanf("%d", &intArray[i])
	}

	fmt.Println("Performing Merge Sort ....")
	MergeSort(intArray, 0, size-1)

	fmt.Println("Sorted Array is", intArray)

}

func MergeSort(array []int, front int, rear int) {
	if front < rear {
		middle := (front + rear) / 2

		// recursively divide left-part of array into many left and right subarray
		MergeSort(array, front, middle)
		// recursively divide right-part of array into many left and right subarray
		MergeSort(array, middle+1, rear)
		// sort and merge
		Merge(array, front, middle, rear)
	}
}

func Merge(array []int, front int, middle int, rear int) {
	// length of two subarray for left and right
	n1 := middle - front + 1 // length of right subarray
	n2 := rear - middle      // length of left subarray

	// initialize left and right subarray
	var LeftSubarray = make([]int, n1)
	var RightSubarray = make([]int, n2)

	// fill-up left and right subarray with elements of array
	for i := 0; i < n1; i++ {
		LeftSubarray[i] = array[front+i]
	}
	for j := 0; j < n2; j++ {
		RightSubarray[j] = array[middle+j+1]
	}

	var i, j, k = 0, 0, front
	// compare between elements of left and right subarray and
	// place them in the original array
	for i < n1 && j < n2 {
		if LeftSubarray[i] <= RightSubarray[j] {
			array[k] = LeftSubarray[i]
			i++
		} else {
			array[k] = RightSubarray[j]
			j++
		}
		k++
	}
	// copy leftover elements from left subarray to original array
	for i < n1 {
		array[k] = LeftSubarray[i]
		i++
		k++
	}

	// copy leftover elements from right subarray to original array
	for j < n2 {
		array[k] = RightSubarray[j]
		j++
		k++
	}
}
