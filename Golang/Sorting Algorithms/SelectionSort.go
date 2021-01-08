package main

import "fmt"

// SelectionSort Algorithm with T(n) = O(n^2) in worst case
func SelectionSort(arr []int) []int {
	for i := 0; i < len(arr); i++ {
		mini := i

		for j := i + 1; j < len(arr); j++ {
			if arr[j] < arr[mini] {
				mini = j
			}
		}

		arr[i], arr[mini] = arr[mini], arr[i]
	}

	return arr

}

func main() {
	fmt.Println(SelectionSort([]int{2, 8, 4, 6, 3, 1, 9}))
}
