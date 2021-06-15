package main

import (
	"fmt"
	"time"
)

func main() {
	var num int
	fmt.Println("Enter a number: ")
	_, _ = fmt.Scanf("%d", &num)
	start := time.Now()
	fmt.Println("Fibonacci number is: ", fibonacci(num))
	end := time.Now()
	fmt.Println("Time elapsed: ", end.Sub(start))
}

func fibonacci(numRange int) int {
	if numRange <= 2 {
		return 1
	}
	return fibonacci(numRange - 1) + fibonacci(numRange - 2)
}
