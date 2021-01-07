# Implementation Of Insertion Sort in Python3


from typing import List
import unittest


def insertion_sort(arr: List[int]) -> List[int]:
    """
    Insertion Sort Algorithm

    T(n):
        Best Case:  Ω(n)

        Avg. Case:  Θ(n^2)

        Worst Case: O(n^2)

    S(n):   O(1)    Best for cases when we are short on memory space.

    :param arr: An unsorted array of integers
    :return:    sorted array of integers
    """
    for j in range(1, len(arr)):
        key = arr[j]
        i = j - 1

        while i >= 0 and arr[i] > key:
            arr[i + 1] = arr[i]
            i = i - 1

        arr[i + 1] = key

    return arr


class SortingTest(unittest.TestCase):

    def test1(self):
        self.assertEqual(insertion_sort([4, 8, 3, 7, 9, 1]), [1, 3, 4, 7, 8, 9])

    def test2(self):
        self.assertEqual(insertion_sort([1, 1, 1, 0, 0]), [0, 0, 1, 1, 1])

    def test3(self):
        self.assertEqual(insertion_sort([2]), [2])

    def test4(self):
        self.assertEqual(insertion_sort([]), [])


if __name__ == '__main__':
    unittest.main()
