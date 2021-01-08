package main

import "fmt"

// CountingSort Algorithm with T(n) = O(n + k) in worst case
func CountingSort(arr []int, limit int) []int {
	outputArr := make([]int, len(arr))
	counterArr := make([]int, limit)

	for i := 0; i < limit; i++ {
		counterArr[i] = 0
	}

	for j := 0; j < len(arr); j++ {
		counterArr[arr[j]]++
	}

	for i := 1; i < limit; i++ {
		counterArr[i] += counterArr[i-1]
	}

	for i := 0; i < len(arr); i++ {
		outputArr[counterArr[arr[i]]-1] = arr[i]
		counterArr[arr[i]]--
	}

	return outputArr
}

func main() {
	fmt.Println(CountingSort([]int{3, 8, 6, 3, 1, 9}, 10))
}
