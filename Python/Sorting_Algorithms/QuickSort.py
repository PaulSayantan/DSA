# Implementation Of Quick Sort in Python3

from typing import List
import unittest


def quick_sort(arr: List[int], front: int, rear: int):
    """
    Quick Sort Algorithm

    T(N):
            Best Case:  Ω(nlogn)

            Avg. Case:  Θ(nlogn)

            Worst Case: O(n^2)

    S(N):   O(logn)
    :param arr:     An unsorted array of integers
    :param front:   the first element of the subarray
    :param rear:    the last element of the subarray
    :return:        Sorted array of integers
    """
    if front < rear:
        pivot_loc = Partition(arr, front, rear)
        quick_sort(arr, front, pivot_loc - 1)
        quick_sort(arr, pivot_loc + 1, rear)

    return arr


def Partition(arr: List[int], front: int, rear: int):
    """

    Initially, taking the rear element as the pivot element

    :param arr:     An unsorted array of integers
    :param front:   the first element of the subarray
    :param rear:    the last element of the subarray
    :return:
    """
    pivot = arr[rear]

    i = front - 1
    for j in range(front, rear):
        if arr[j] < pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]

    arr[i + 1], arr[rear] = arr[rear], arr[i + 1]
    # print("Pivot at position: ", i + 1);
    return i + 1


class SortingTest(unittest.TestCase):

    def test1(self):
        self.assertEqual(quick_sort([4, 8, 3, 7, 9, 1], 0, 5), [1, 3, 4, 7, 8, 9])

    def test2(self):
        self.assertEqual(quick_sort([1, 1, 1, 0, 0], 0, 4), [0, 0, 1, 1, 1])

    def test3(self):
        self.assertEqual(quick_sort([2], 0, 0), [2])

    def test4(self):
        self.assertEqual(quick_sort([], 0, 0), [])


if __name__ == '__main__':
    unittest.main()
