import unittest
from typing import List


def selection_sort(arr: List[int]) -> List[int]:
    """
    Selection Sort Algorithm

    T(N):
            Best Case:  Ω(n^2)

            Avg. Case:  Θ(n^2)

            Worst Case: O(n^2)

    S(N):   O(1)    Best for cases when we are short on memory space.

    :param arr: An unsorted array of n elements
    :return:    sorted Array
    """
    n = len(arr)

    for i in range(n):
        min_idx = i

        for j in range(i + 1, n):
            if arr[j] < arr[min_idx]:
                min_idx = j

        arr[i], arr[min_idx] = arr[min_idx], arr[i]

    return arr


class SortingTest(unittest.TestCase):

    def test1(self):
        self.assertEqual(selection_sort([4, 8, 3, 7, 9, 1]), [1, 3, 4, 7, 8, 9])

    def test2(self):
        self.assertEqual(selection_sort([1, 1, 1, 0, 0]), [0, 0, 1, 1, 1])

    def test3(self):
        self.assertEqual(selection_sort([2]), [2])

    def test4(self):
        self.assertEqual(selection_sort([]), [])


if __name__ == '__main__':
    unittest.main()
