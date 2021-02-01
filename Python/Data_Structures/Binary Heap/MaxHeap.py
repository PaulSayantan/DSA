# Max Heap Implementation in Python

class MaxHeap:
    def __init__(self):
        self.heap = []
        self.curr_size = 0

    def swap(self, idx1, idx2):
        """
            Swapping two elements in the heap array
        :param idx1:
        :param idx2:
        :return:
        """
        self.heap[idx1], self.heap[idx2] = self.heap[idx2], self.heap[idx1]

    def heappush(self, data: int):
        """
           A push an item into the heap and perform heapify operation to maintain heap property
        :param data:
        :return:
        """
        self.heap.append(data)
        self.curr_size += 1
        self.heapify_up(self.curr_size - 1)

    def heappop(self) -> int:
        """
            Pop out the max value present at top of the heap and maintain the heap property
        :return: root element
        """
        self.swap(0, self.curr_size - 1)
        popped_item = self.heap.pop(self.curr_size - 1)
        self.curr_size -= 1
        self.heapify_down(0)

        return popped_item

    def heapify(self):
        """
            Top-Down Heapify
            Use, when all elements are added to the heap together
        :return:
        """
        size = self.curr_size - 1
        for idx in range(size // 2, -1, -1):
            self.heapify_down(idx)

    def heapify_up(self, child_idx):
        parent_idx = (child_idx - 1) // 2
        # if value at parent_idx is smaller than value at the child_idx, swap values
        if self.heap[parent_idx] < self.heap[child_idx]:
            self.swap(parent_idx, child_idx)

        # when we have reached top of the heap: heapify is complete
        if parent_idx <= 0:
            return
        else:
            # recursively continue heapify
            self.heapify_up(parent_idx)

    def heapify_down(self, parent_idx):
        leftchild_idx = 2 * parent_idx + 1

        # if no leftchild exists, we have reached the end of the heap
        if leftchild_idx > len(self.heap) - 1:
            return

        # if rightchild_idx exists and is greater than leftchild_idx, take the rightchild into consideration
        if leftchild_idx + 1 <= len(self.heap) - 1 and self.heap[leftchild_idx] < self.heap[leftchild_idx + 1]:
            leftchild_idx += 1

        # if heap property violated, maintain by heapify
        if self.heap[parent_idx] < self.heap[leftchild_idx]:
            self.swap(parent_idx, leftchild_idx)
            self.heapify_down(leftchild_idx)
        else:
            return


if __name__ == '__main__':
    mxheap = MaxHeap()

    # mxheap.heap = []
    # mxheap.curr_size = 0
    # mxheap.heappush(7)
    # mxheap.heappush(10)
    # mxheap.heappush(4)
    # mxheap.heappush(3)
    # mxheap.heappush(20)
    # mxheap.heappush(15)
    #
    # print(mxheap.heap)

    mxheap.heap = [7, 10, 4, 3, 20, 15]
    mxheap.curr_size = 6
    mxheap.heapify()
    print(mxheap.heap)

    print("Deleted Item: ", mxheap.heappop())
    print(mxheap.heap)
    print("Deleted Item: ", mxheap.heappop())
    print(mxheap.heap)
    print("Deleted Item: ", mxheap.heappop())
    print(mxheap.heap)