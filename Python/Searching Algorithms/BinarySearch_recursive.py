# Binary Search Algorithm (Recursive)

from typing import List
import unittest


def binary_search(arr: List[int], target: int, *positions: int):
    """
    :param arr: Sorted Array of Integers
    :param target: the element to find in the array
    :param positions: the lowest and highest index of the array
    :return: position of the target in the array, -1 if not found
    """

    mini, maxi = positions[0], positions[1]

    if mini <= maxi:
        mid = mini + (maxi - mini) // 2

        if arr[mid] == target:
            return mid

        elif arr[mid] > target:
            binary_search(arr, target, mini, mid - 1)
        elif arr[mid] < target:
            binary_search(arr, target, mid + 1, maxi)
    else:
        return -1


class SearchTest(unittest.TestCase):
    def test1(self):
        self.assertEqual(binary_search([5, 9, 13, 25, 34], 13, 0, 5), 2)

    def test2(self):
        self.assertEqual(binary_search([9, 12, 28, 39, 49, 51], 39, 0, 6), 3)


if __name__ == '__main__':
    unittest.main()
