package Data_Structures.Stack.Stack1;

import java.util.Arrays;

public class Stack {
    public static void main(String[] args) {
        stackX myStack = new stackX(10);

        myStack.push(45);
        myStack.push(15);
        myStack.push(5);
        myStack.push(78);
        myStack.push(31);
        myStack.push(92);

        System.out.println(myStack.toString());
    }
}

class stackX {
    private final int maxSize;
    private final int[] stackArray;
    private int top_pointer;

    public stackX(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top_pointer = -1;
    }

    // ---------- PUSH OPERATION OF STACK ---------
    public void push(int item) {
        if (!isFull()) {
            stackArray[++top_pointer] = item;
        } else {
            throw new ArrayIndexOutOfBoundsException("Cannot push anymore items into the stack !!");
        }
    }

    // ---------- POP OPERATION OF STACK ---------
    public int pop() {
        if (!isEmpty()) {
            return stackArray[top_pointer--];
        } else {
            throw new ArrayIndexOutOfBoundsException("Cannot Pop item from an empty Stack !!");
        }
    }

    // ---------- PEEK OPERATION OF STACK ---------
    public int peek() {
        if (!isEmpty()) {
            return stackArray[top_pointer];
        } else {
            throw new ArrayIndexOutOfBoundsException("Cannot Peek item from an empty Stack!!");
        }
    }

    public boolean isEmpty() {
        return (top_pointer == -1);
    }

    public boolean isFull() {
        return top_pointer == maxSize - 1;
    }

    @Override
    public String toString() {
        return "stack: {" +
                "maxSize=" + maxSize +
                ", stackArray=" + Arrays.toString(stackArray) +
                ", top_pointer=" + top_pointer +
                '}';
    }
}
