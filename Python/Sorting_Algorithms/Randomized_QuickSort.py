# Implementation of Randomized QuickSort in Python

import random
import unittest
from typing import List


def randomized_quick_sort(arr: List[int], front: int, rear: int):
    if front < rear:
        pivot = randomized_partition(arr, front, rear)
        randomized_quick_sort(arr, front, pivot - 1)
        randomized_quick_sort(arr, pivot + 1, rear)

    return arr


def randomized_partition(arr: List[int], front: int, rear: int):
    # random choice of pivot
    random_pivot = random.randrange(front, rear)
    arr[front], arr[random_pivot] = arr[random_pivot], arr[front]

    return partition(arr, front, rear)


def partition(arr, front, rear):
    pivot = arr[rear]
    i = front - 1

    for j in range(front, rear):
        if arr[j] < pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]

    arr[i + 1], arr[rear] = arr[rear], arr[i + 1]
    return i + 1


class SortingTest(unittest.TestCase):

    def test1(self):
        self.assertEqual(randomized_quick_sort([4, 8, 3, 7, 9, 1], 0, 5), [1, 3, 4, 7, 8, 9])

    def test2(self):
        self.assertEqual(randomized_quick_sort([1, 1, 1, 0, 0], 0, 4), [0, 0, 1, 1, 1])

    def test3(self):
        self.assertEqual(randomized_quick_sort([2], 0, 0), [2])

    def test4(self):
        self.assertEqual(randomized_quick_sort([], 0, 0), [])


if __name__ == '__main__':
    unittest.main()
