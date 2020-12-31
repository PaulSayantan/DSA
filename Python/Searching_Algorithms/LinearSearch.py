# Linear Search Algorithm

from typing import List
import unittest


def linear_search(arr: List[int], target: int) -> int:
    """
    :param arr: Array of integers (may be sorted or unsorted)
    :param target: integer we want to find
    :return: position of the target in the array, -1 if not found
    """
    for i in range(0, len(arr)):
        if arr[i] == target:
            return i + 1

    return -1


class SearchTest(unittest.TestCase):
    def test1(self):
        self.assertEqual(linear_search([1, 5, 2, 7, 3, 0], 5), 2)

    def test2(self):
        self.assertEqual(linear_search([5, 6, 2, 8, 4, 9, 4, 6, 3, 9, 1, 0], 4), 5)

    def test3(self):
        self.assertEqual(linear_search([3, 5, 7, 1, 8], 4), -1)


if __name__ == '__main__':
    unittest.main()
