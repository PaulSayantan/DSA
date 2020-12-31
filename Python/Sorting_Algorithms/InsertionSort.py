# Implementation Of Insertion Sort in Python3


from typing import List
import unittest


def insertionSort(arr: List[int]) -> List[int]:
    for j in range(1, len(arr)):
        key = arr[j]
        i = j - 1

        while i >= 0 and arr[i] > key:
            arr[i + 1] = arr[i]
            i = i - 1

        arr[i + 1] = key

    return arr


class sortingTest(unittest.TestCase):

    def test1(self):
        self.assertEqual(insertionSort([4, 8, 3, 7, 9, 1]), [1, 3, 4, 7, 8, 9])

    def test2(self):
        self.assertEqual(insertionSort([1, 1, 1, 0, 0]), [0, 0, 1, 1, 1])

    def test3(self):
        self.assertEqual(insertionSort([2]), [2])

    def test4(self):
        self.assertEqual(insertionSort([]), [])


if __name__ == '__main__':
    unittest.main()
