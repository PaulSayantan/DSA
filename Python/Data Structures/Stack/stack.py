# Stack Implementation In Python

class Stack:
    """
    LIFO Stack Implementation using List as underlying storage.
    """
    def __init__(self):
        """Initialize an Empty Stack"""
        self._data = []

    def __len__(self):
        """Return the length of the stack"""
        return len(self._data)

    def is_empty(self):
        """Check if the stack is empty"""
        return len(self._data) == 0

    def push(self, item):
        """Push an item to the stack"""
        self._data.append(item)

    def pop(self):
        """Pop an item from the stack"""
        if self.is_empty():
            raise IndexError('Stack is Empty !!')
        return self._data.pop()

    def top(self):
        """Show the topmost item in the stack"""
        if self.is_empty():
            raise IndexError('Stack is Empty !!')
        return self._data[-1]

    def __repr__(self):
        """Display the stack"""
        return 'Stack : {} '.format(self._data)


if __name__ == '__main__':
    myStack = Stack()

    myStack.push(12)
    myStack.push(1)
    myStack.push(34)
    myStack.push(16)
    myStack.push('Sayantan')

    print('Top Item: ', myStack.top())
    print(myStack)
    print('Popped Item: ', myStack.pop())
    print(myStack)
