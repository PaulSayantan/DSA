package main

import (
	"fmt"
	"log"
	"sort"
)

/*

			Activity Selection Problem	Greedy Algorithm - 1
		-------------------------------------------------------

You are given n activities with their start and finish times.

Select the maximum number of activities that can be performed by a single person,
assuming that a person can only work on a single activity at a time.

Example:
--------

Input: {(2, 3), (1, 4), (5, 8), (6, 10)}

Output: 2

Here, Task1 and Task2 cannot be performed at the same time, since their times are overlapping.
Similarly, Task3 and Task4 cannot be performed at the same time.
Hence, person can choose at-most 2 tasks consecutively.

Possible variations are:
	(Task1, Task3), 	(Task2, Task4), 	(Task2, Task3), 	(Task1, Task4)
 */

func activitySelection(tasks [][]int, numTasks int) (int, error) {
	possibleTasks := 1
	if len(tasks) == 0 {
		return 0, fmt.Errorf("you didn't specify any tasks to perform")
	}

	tasks, err := TasksSorting(tasks, numTasks)
	if err != nil {
		return 0, err
	}

	prevEnd := tasks[0][1]

	for i := 1; i < numTasks; i++ {
		currStart := tasks[i][0]
		currEnd := tasks[i][1]

		if prevEnd >= currStart {
			prevEnd = currEnd
			possibleTasks++
		}
	}

	return possibleTasks, nil
}

func main() {
	var numTasks int
	fmt.Println("Enter how many tasks you want to perform: ")
	_, err := fmt.Scanln(&numTasks)
	if err != nil {
		log.Fatal(err)
	}

	var tasks [][]int
	var start, end int
	fmt.Println("Enter the Task Timings ")
	for i := 0; i < numTasks; i++ {
		var row []int
		fmt.Printf("----------\nTask %d starts at: ", i+1)
		_, _ = fmt.Scanf("%d", &start)
		row = append(row, start)
		fmt.Printf("Task %d ends at: ", i+1)
		_, _ = fmt.Scanf("%d", &end)
		if end < start {
			_ = fmt.Errorf("end timings must be greater than start")
		}
		row = append(row, end)
		tasks = append(tasks, row)
	}

	fmt.Println(tasks)

	possibleTasks, _ := activitySelection(tasks, numTasks)

	fmt.Println("Maximum number of Tasks that can be performed: ", possibleTasks)

}

func TasksSorting(tasks [][]int, len int) ([][]int, error) {
	var endTimings []int
	var sortedTasks [][]int

	for i := 0; i < len; i++ {
		endTimings = append(endTimings, tasks[i][1])
	}

	sort.Ints(endTimings)

	for _, end := range endTimings {
		for j := 0; j < len; j++ {
			if tasks[j][1] == end {
				sortedTasks = append(sortedTasks, tasks[j])
			}
		}
	}

	return sortedTasks, nil
}