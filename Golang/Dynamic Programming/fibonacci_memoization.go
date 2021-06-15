package main

import "fmt"

func main() {
	var num int
	fmt.Println("Enter a number: ")
	_, _ = fmt.Scanf("%d", &num)
	// fibValues := make([]int, num)
	fib := fibb(num)
	fmt.Println(fib)
}

func fibb(num int) int {
	fib := make([]int, num)
	if fib[num] != 0 {
		return fib[num]
	}

	if num <= 2 {
		return 1
	}

	fib[num] = fibb(num - 1) + fibb(num - 2)

	return fib[num]
}
