package main

// Rat in a Maze Problem Solved using Backtracking Technique

import "fmt"

var inputMaze = [][]int{
	{1, 1, 1, 1, 1, 1, 1, 1},
	{1, 0, 1, 1, 1, 1, 1, 1},
	{1, 0, 0, 0, 0, 0, 0, 1},
	{1, 1, 1, 1, 1, 1, 1, 1},
	{1, 0, 1, 0, 0, 1, 1, 1},
	{1, 1, 1, 1, 1, 1, 1, 1},
	{1, 0, 1, 0, 1, 0, 1, 0},
	{1, 1, 0, 1, 1, 1, 1, 1},
}

var outputMaze = [][]int{
	{0, 0, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0, 0},
}

var numrows = len(inputMaze)
var numcols = len(inputMaze[0])

func ratMazeSolution() {

	if mazeSolved(inputMaze, 0, 0, outputMaze) {
		fmt.Println("Path Found !!")
		for row := 0; row < numrows; row++ {
			fmt.Println(outputMaze[row])
		}
	} else {
		fmt.Println("Path not Found !!")
	}
}

func mazeSolved(input [][]int, x, y int, solution [][]int) bool {
	if x == numrows-1 && y == numcols-1 {
		solution[x][y] = 1
		return true
	}

	if isSafe(input, x, y) {
		solution[x][y] = 1

		if mazeSolved(input, x+1, y, solution) {
			return true
		}

		if mazeSolved(input, x, y+1, solution) {
			return true
		}

		solution[x][y] = 0
		return false
	}

	return false
}

func isSafe(input [][]int, x, y int) bool {
	return 0 <= x && x < numrows && 0 <= y && y < numcols && input[x][y] != 0
}

func main() {
	ratMazeSolution()
}
