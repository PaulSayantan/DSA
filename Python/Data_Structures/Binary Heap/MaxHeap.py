# Max Heap Implementation in Python

from typing import List
class MaxHeap:
    def __init__(self):
        self.__heap = []
    
    # insert an element in the heap
    def heappush(self, val: int) -> None:
        self.__heap.append(val)
        # maintain heap property
        self.__heapify_up(len(self.__heap) - 1)
    
    # return the top, i.e. the maximum element in the heap
    def getMax(self) -> int:
        if self.__heap:
            return self.__heap[0]
    
    # return and remove the top, i.e. the maximum element in the heap
    def heappop(self) -> int:
        # if heap size is greater than 1
        if len(self.__heap) > 1:
            # store the maximum element in a variable max
            max = self.__heap[0]
            # move the last element of the heap to the top
            self.__heap[0] = self.__heap[-1]
            # remove the last element
            self.__heap.pop()
            # maintain heap property
            self.__heapify_down(0)
            return max
        elif len(self.__heap) == 1:
            # store the maximum element in a variable max
            max = self.__heap[0]
            self.__heap.pop(0)
            return max
        else:
            return None
    
    # maintain heap property in down to up fashion
    def __heapify_up(self, index) -> None:
        # get parent index
        parent = (index - 1) // 2
        # stop iteration if index goes out of bound or check if parent is smaller than child
        if index <= 0:
            return
        elif self.__heap[parent] < self.__heap[index]:
            # swap parent and child
            tmp = self.__heap[parent]
            self.__heap[parent] = self.__heap[index]
            self.__heap[index] = tmp
            # continue heapifying
            self.__heapify_up(parent)
    
    # maintain heap property in up to down fashion
    def __heapify_down(self, index) -> None:
        # get index of left child and right child
        left = (index * 2) + 1
        right = (index * 2) + 2
        # assume current item at index is largest
        largest = index
        # if leftchild is larger than index, update largest
        if len(self.__heap) > left and self.__heap[largest] < self.__heap[left]:
            largest = left
        # if rightchild is larger than index, update largest
        if len(self.__heap) > right and self.__heap[largest] < self.__heap[right]:
            largest = right
        # if largest is not index, swap and continue heapifying
        if largest != index:
            tmp = self.__heap[largest]
            self.__heap[largest] = self.__heap[index]
            self.__heap[index] = tmp
            self.__heapify_down(largest)
    
    # build heap from an array of elements by heapifying each element
    def buildHeap(self, arr: List[int]) -> None:
        self.__heap = arr
        for i in range(len(arr) - 1, -1, -1):
            self.__heapify_down(i)

    # return the maxheap as an array
    def getHeap(self) -> List[int]:
        return self.__heap


if __name__ == '__main__':
    mxheap = MaxHeap()

    mxheap.heappush(10)
    mxheap.heappush(8)
    mxheap.heappush(12)
    mxheap.heappush(20)
    mxheap.heappush(2)
    mxheap.heappush(4)
    
    
    print(mxheap.getHeap())

    print("Deleted Item: ", mxheap.heappop())
    print(mxheap.getHeap())
    print("Deleted Item: ", mxheap.heappop())
    print(mxheap.getHeap())
    print("Deleted Item: ", mxheap.heappop())
    print(mxheap.getHeap())