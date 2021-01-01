package main

import (
	"fmt"
	"log"
	"strconv"
)

// Element class
type Element struct {
	elementValue int
}

// toString method on Element class
func (e *Element) toString() string {
	return strconv.Itoa(e.elementValue)
}

// Stack class
type Stack struct {
	elements []*Element
	count    int
}

// New method -> return a dynamic array of elements as stack
func (stack *Stack) New() {
	stack.elements = make([]*Element, 0)
}

// Push method -> push element into the stack
func (stack *Stack) Push(e *Element) {
	stack.elements = append(stack.elements[:stack.count], e)
	stack.count++
}

// Pop method -> pop element from the stack
func (stack *Stack) Pop() (*Element, error) {
	if stack.count == 0 {
		return nil, fmt.Errorf("Stack is empty")
	}

	length := stack.count
	poppedElement := stack.elements[length-1]

	if length > 1 {
		stack.elements = stack.elements[:length-1]
	} else {
		stack.elements = stack.elements[0:]
	}

	stack.count = len(stack.elements)
	return poppedElement, nil
}

// Peek method -> peeks element from the stack
func (stack *Stack) Peek() (*Element, error) {
	if stack.count == 0 {
		return nil, fmt.Errorf("Stack is empty")
	}

	length := stack.count
	peekedElement := stack.elements[length-1]

	return peekedElement, nil
}

// printStackTrace method -> print element of the stack
func (stack *Stack) printStackTrace() {
	for i := stack.count - 1; i >= 0; i-- {
		fmt.Println(stack.elements[i].elementValue)
		fmt.Println("---")
	}
}

// main method
func main() {
	var stack *Stack = &Stack{}
	stack.New()

	var ele1 = &Element{4}
	var ele2 = &Element{7}
	var ele3 = &Element{1}

	stack.Push(ele1)
	stack.Push(ele2)
	stack.Push(ele3)
	stack.printStackTrace()
	fmt.Println()
	element, err := stack.Pop()
	if err != nil {
		log.Println(err)
	}

	fmt.Println("Popped Item: ", element.elementValue)
	fmt.Println()
	stack.printStackTrace()
}
