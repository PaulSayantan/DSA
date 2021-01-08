package main

import "fmt"

/*			Quick Sort Algorithm has T(N) = O(nlogn) in avg. case and O(n^2) in worst case

			better than merge sort because S(N) = O(1) for Quick Sort
			where in Merge Sort S(N) = O(n)
*/

// QuickSort Algorithm
func QuickSort(arr []int, low, high int) []int {
	if low < high {
		pivot := Partition(arr, low, high)
		QuickSort(arr, low, pivot-1)
		QuickSort(arr, pivot+1, high)
	}

	return arr
}

// Partition -> Lomuto Partition Technique: Placing the pivot element to its correct position in the array
func Partition(arr []int, low int, high int) int {
	pivot := arr[high]
	i := low - 1

	for j := low; j < high; j++ {
		if arr[j] < pivot {
			i++
			arr[i], arr[j] = arr[j], arr[i]
		}
	}

	arr[i+1], arr[high] = arr[high], arr[i+1]
	return i + 1
}

func main() {
	fmt.Println(QuickSort([]int{2, 8, 4, 6, 3, 1, 9}, 0, 6))
}
